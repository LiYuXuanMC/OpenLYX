package al.nya.reflect.socket.handlers;

import al.nya.reflect.socket.packets.*;

import java.util.HashMap;
import java.util.Map;

public class PacketProcessHandler implements IHandler {
    private Map<String, Class<? extends Packet>> packetMap = new HashMap<>();

    public PacketProcessHandler() {
        packetMap.put("Download", PacketDownload.class);
        packetMap.put("HeartBeta", PacketHeartBeat.class);
        packetMap.put("IRCChat", PacketIRCChat.class);
        packetMap.put("SendFinish", PacketSendFinish.class);
        packetMap.put("ShellCode", PacketShellCode.class);
        packetMap.put("Upload", PacketUpload.class);
        packetMap.put("Verified", PacketVerified.class);
        packetMap.put("Verify", PacketVerify.class);
    }

    @Override
    public void processPacket(Packet packet) {

    }

    @Override
    public Class<? extends Packet> getPacketByName(String name) {
        return packetMap.get(name);
    }

    @Override
    public String getNameByPacket(Class<? extends Packet> packet) {
        for (Map.Entry<String, Class<? extends Packet>> stringClassEntry : packetMap.entrySet()) {
            if (stringClassEntry.getValue() == packet) {
                return stringClassEntry.getKey();
            }
        }
        return null;
    }
}
