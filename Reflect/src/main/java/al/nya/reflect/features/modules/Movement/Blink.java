package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Blink extends Module {
    public DoubleValue packets = new DoubleValue("Packets", 100, 0, 0, "0");

    public Queue<C03PacketPlayer> savedPackets = new ConcurrentLinkedDeque<>();

    public Blink() {
        super("Blink", ModuleType.Movement);
        addValues(packets);
    }

    @EventTarget
    public void onPacket(EventPacket packet) {
        if (C03PacketPlayer.isPacketPlayer(packet.getPacket())) {
            savedPackets.add(new C03PacketPlayer(packet.getPacket().getWrapObject()));
            if (packets.getValue() != 0 && savedPackets.size() == packets.getValue()) {
                releasePackets();
            }
            packet.setCancel(true);
        }
    }

    public void releasePackets() {
        NetworkManager networkManager = mc.getNetHandler().getNetworkManager();
        while (!savedPackets.isEmpty())
            networkManager.sendPacketNoEvent(savedPackets.poll());
    }
}
