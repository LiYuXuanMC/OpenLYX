package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.network.S03PacketTimeUpdate;

public class Ambience extends Module {
    private ModeValue mode = new ModeValue("Time",Mode.Sunset,Mode.values());
    public Ambience() {
        super("Ambience",ModuleType.Visual);
        addValue(mode);
    }
    @EventTarget
    public void onUpdate(EventUpdate render3D){
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

    @EventTarget
    public void onPacket(EventPacket packet) {
        if (S03PacketTimeUpdate.isPacketTimeUpdate(packet.getPacket())) {
            packet.setCancel(true);
        }
    }

    public enum Mode {
        Darkness,
        Sunset,
        Day,
        Sunrise
    }
}
