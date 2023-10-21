package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;

public class Regen extends Module {
    private ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());
    private DoubleValue healthValue = new DoubleValue("Health", 1, 0.01, 1, "0.00");
    private DoubleValue foodValue = new DoubleValue("Food", 20, 0, 18, "0");
    private DoubleValue speedValue = new DoubleValue("Speed",100,0,100,"0");
    private OptionValue noAirValue = new OptionValue("NoAir",true);
    private OptionValue potionEffectValue = new OptionValue("PotionEffect", false);
    public Regen() {
        super("Regen",ModuleType.Player);
        addValues(mode,healthValue,foodValue,speedValue,noAirValue,potionEffectValue);
    }
    @EventTarget
    public void onUpdate(EventUpdate update) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if ((!noAirValue.getValue() || thePlayer.isOnGround()) && !thePlayer.getCapabilities().isCreativeMode() &&
                thePlayer.getFoodStats().getFoodLevel() > foodValue.getValue() && thePlayer.isEntityAlive() && (thePlayer.getHealth() / thePlayer.getMaxHealth()) < healthValue.getValue()) {
            if (potionEffectValue.getValue() && !thePlayer.isPotionActive(Potion.regeneration))
                return;
            if (mode.getValue() == Mode.Vanilla) {
                int i = 0;
                NetHandlerPlayClient netHandler = mc.getNetHandler();
                boolean onGround = thePlayer.isOnGround();
                while (i != speedValue.getValue()) {
                    netHandler.addToSendQueue(new C03PacketPlayer(onGround));
                    i++;
                }
            }
        }
    }
    @Override
    public String getSuffix(){
        return mode.getValue().name();
    }
    public enum Mode {
        Vanilla
    }
}
