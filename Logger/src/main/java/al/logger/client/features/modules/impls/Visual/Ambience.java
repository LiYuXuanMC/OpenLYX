package al.logger.client.features.modules.impls.Visual;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.network.server.S03PacketTimeUpdate;

public class Ambience extends Module {
    private ModeValue mode = new ModeValue("Time", Mode.Sunset, Mode.values());
    public Ambience() {
        super("Ambience", Category.Visual);
        addValues(mode);
    }
    @Listener
    public void onUpdate(EventLivingUpdate update){
        if (mode.getValue().equals(Mode.Darkness)) {
            this.mc.getTheWorld().setWorldTime(-18000);
        } else if (mode.getValue().equals(Mode.Sunset)) {
            this.mc.getTheWorld().setWorldTime(-13000);
        } else if (mode.getValue().equals(Mode.Day)) {
            this.mc.getTheWorld().setWorldTime(2000);
        } else if (mode.getValue().equals(Mode.Sunrise)) {
            this.mc.getTheWorld().setWorldTime(22500);
        }
    }

    @Listener
    public void onPacket(EventPacket packet) {
        if (S03PacketTimeUpdate.isPacketTimeUpdate(packet.getPacket())) {
            packet.cancel();
        }
    }

    public enum Mode {
        Darkness,
        Sunset,
        Day,
        Sunrise
    }
}
