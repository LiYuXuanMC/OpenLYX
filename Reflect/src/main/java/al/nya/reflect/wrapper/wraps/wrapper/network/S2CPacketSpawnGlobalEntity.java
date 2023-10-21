package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketSpawnGlobalEntity", targetMap = Maps.Srg1_12_2)
public class S2CPacketSpawnGlobalEntity extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketSpawnGlobalEntity", targetMap = Maps.Srg1_12_2)
    public static Class<?> S2CPacketSpawnGlobalEntityClass;
    @WrapField(mcpName = "entityId", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "entityId", targetMap = Maps.Srg1_12_2)
    public static Field entityId;
    @WrapField(mcpName = "x", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "x", targetMap = Maps.Srg1_12_2)
    public static Field x;
    @WrapField(mcpName = "y", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "y", targetMap = Maps.Srg1_12_2)
    public static Field y;
    @WrapField(mcpName = "z", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "z", targetMap = Maps.Srg1_12_2)
    public static Field z;
    @WrapField(mcpName = "type", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "type", targetMap = Maps.Srg1_12_2)
    public static Field type;

    public S2CPacketSpawnGlobalEntity(Object packet) {
        super(packet);
    }

    public static boolean isS2CPacketSpawnGlobalEntity(Packet packet) {
        return S2CPacketSpawnGlobalEntityClass.isInstance(packet.getWrapObject());
    }

    public int getEntityId() {
        return (int) getField(entityId);
    }

    public int getX() {
        return (int) getField(x);
    }

    public int getY() {
        return (int) getField(y);
    }

    public int getZ() {
        return (int) getField(z);
    }

    public int getType() {
        return (int) getField(type);
    }

}
