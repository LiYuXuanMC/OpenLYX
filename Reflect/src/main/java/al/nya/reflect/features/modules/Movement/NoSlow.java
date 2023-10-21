package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPostUpdate;
import al.nya.reflect.features.modules.Combat.KillAura;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.Scaffold;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C08PacketPlayerBlockPlacement;
import al.nya.reflect.wrapper.wraps.wrapper.network.C09PacketHeldItemChange;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;

public class NoSlow extends Module {
    public ModeValue mode = new ModeValue("Mode",Mode.Vanilla,Mode.values());
    public OptionValue stopAtSCAFFORD = new OptionValue("check",true) {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Hypixel;
        }
    };
    public NoSlow() {
        super("NoSlow",ModuleType.Movement);
        addValues(mode,stopAtSCAFFORD);
    }
    @EventTarget
    public void postUpdate(EventPostUpdate postUpdate){
        if (mode.getValue() == Mode.AAC5){
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (thePlayer.isUsingItem() || mc.getThePlayer().isBlocking() || KillAura.isBlocking) {
                mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, thePlayer.getInventory().getCurrentItem(), 0f, 0f, 0f));
            }
        } else if (mode.getValue() == Mode.Hypixel) {
            if (stopAtSCAFFORD.getValue()) {
                if (!ModuleManager.getModule(Scaffold.class).isEnable())
                    mc.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(mc.getThePlayer().getInventory().currentItem()));
            } else {
                mc.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(mc.getThePlayer().getInventory().currentItem()));
            }
        }
    }
    public enum Mode{
        Vanilla,
        AAC5,
        Hypixel
    }
}
