package al.nya.proxy.network.packets;

import al.nya.proxy.network.packets.annotations.PacketField;
import al.nya.proxy.utils.CryptManager;
import al.nya.proxy.utils.PacketBuffer;
import al.nya.proxy.utils.ReflectUtils;
import al.nya.proxy.utils.UnsafeAccessories;

import java.lang.reflect.Field;
import java.security.PublicKey;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PacketBuilder {
    public static int processProtocolVersion(PacketBuffer buffer) throws RuntimeException {
        buffer.markReaderIndex();
        int packetId = buffer.readVarIntFromBuffer();
        if (packetId == 0x00) {
            int protocolVersion = buffer.readVarIntFromBuffer();
            buffer.resetReaderIndex();
            return protocolVersion;
        } else {
            buffer.resetReaderIndex();
            throw new RuntimeException("Cannot process protocol version from other packets");
        }
    }

    public static void writeToBuffer(PacketBuffer buffer, Packet packet) throws IllegalAccessException {
        Map<PacketField, Field> packetFields = listPacketFields(packet);
        for (Map.Entry<PacketField, Field> entry : packetFields.entrySet()) {
            switch (entry.getKey().type()) {
                case Enum: {
                    buffer.writeVarIntToBuffer(((Enum) entry.getValue().get(packet)).ordinal());
                    continue;
                }
                case String: {
                    buffer.writeString(entry.getValue().get(packet).toString());
                    continue;
                }
                case UnsignedShort: {
                    buffer.writeShort(entry.getValue().getShort(packet));
                    continue;
                }
                case VarInt: {
                    buffer.writeVarIntToBuffer(entry.getValue().getInt(packet));
                    continue;
                }
                case VarLong: {
                    buffer.writeVarLong(entry.getValue().getLong(packet));
                    continue;
                }
                case ByteArray: {
                    buffer.writeByteArray((byte[]) entry.getValue().get(packet));
                    continue;
                }
                case PublicKey: {
                    buffer.writeByteArray(((PublicKey) entry.getValue().get(packet)).getEncoded());
                    continue;
                }
                default:
                    throw new RuntimeException("Unknown data type");
            }
        }
    }

    public static Packet constructAndProcessPacket(PacketBuffer buffer, int protocolVersion, int type) throws RuntimeException, IllegalAccessException {
        int packetId = buffer.readVarIntFromBuffer();
        Packet packet = constructPacket(protocolVersion, packetId, type);
        if (packet == null) {
            return null;
        }
        Map<PacketField, Field> packetFields = listPacketFields(packet);
        for (Map.Entry<PacketField, Field> entry : packetFields.entrySet()) {
            switch (entry.getKey().type()) {
                case Enum: {
                    int ordinal = buffer.readVarIntFromBuffer();
                    entry.getValue().set(packet, entry.getKey().enumClass().getEnumConstants()[ordinal]);
                    continue;
                }
                case String: {
                    entry.getValue().set(packet, buffer.readStringFromBuffer(32767));
                    continue;
                }
                case UnsignedShort: {
                    entry.getValue().set(packet, Integer.valueOf(buffer.readUnsignedShort()).shortValue());
                    continue;
                }
                case VarInt: {
                    entry.getValue().set(packet, buffer.readVarIntFromBuffer());
                    continue;
                }
                case VarLong: {
                    entry.getValue().set(packet, buffer.readVarLong());
                    continue;
                }
                case ByteArray: {
                    entry.getValue().set(packet, buffer.readByteArray());
                    continue;
                }
                case PublicKey: {
                    entry.getValue().set(packet, CryptManager.decodePublicKey(buffer.readByteArray()));
                    continue;
                }
                default:
                    throw new RuntimeException("Unknown data type");
            }
        }
        return packet;
    }

    private static Map<PacketField, Field> listPacketFields(Packet packet) {
        Map<PacketField, Field> map = new HashMap<>();
        Class<? extends Packet> packetClass = packet.getClass();
        for (Field declaredField : packetClass.getDeclaredFields()) {
            if (ReflectUtils.hasAnnotation(declaredField, PacketField.class)) {
                declaredField.setAccessible(true);
                map.put(ReflectUtils.getAnnotation(declaredField, PacketField.class), declaredField);
            }
        }
        Map<PacketField, Field> sortedMap = new LinkedHashMap<>();
        List<Map.Entry> entries = map.entrySet().stream().sorted(new Comparator<Map.Entry<PacketField, Field>>() {
            @Override
            public int compare(Map.Entry<PacketField, Field> o1, Map.Entry<PacketField, Field> o2) {
                return o1.getKey().seq() - o2.getKey().seq();
            }
        }).collect(Collectors.toList());
        entries.stream().forEachOrdered(x -> sortedMap.put((PacketField) x.getKey(), (Field) x.getValue()));
        return sortedMap;
    }

    public static Packet constructPacket(int protocolVersion, int packetId, int type) {
        Map<Integer, Class<? extends Packet>[]> protocol = PacketMap.refMap.get(protocolVersion);
        if (protocol == null)
            return null;
        Class<? extends Packet>[] packetClasses = protocol.get(packetId);
        if (packetClasses == null || packetClasses.length == 0)
            return null;
        Class<? extends Packet> packetClass = packetClasses[type];
        if (packetClass == null)
            return null;
        Packet packetInstance = (Packet) UnsafeAccessories.allocInstance(packetClass);
        return packetInstance;
    }

    public static int getIdByPacket(int protocolVersion, Packet packet) {
        AtomicInteger packetId = new AtomicInteger(-1);
        PacketMap.refMap.forEach((protocol, m) -> {
            if (protocol == protocolVersion) {
                m.forEach((id, p) -> {
                    for (Class<? extends Packet> aClass : p) {
                        if (aClass == packet.getClass()) {
                            packetId.set(id);
                        }
                    }
                });
            }
            if (protocol == 0) {
                m.forEach((id, p) -> {
                    for (Class<? extends Packet> aClass : p) {
                        if (aClass == packet.getClass()) {
                            packetId.set(id);
                        }
                    }
                });
            }
        });
        return packetId.get();
    }
}
