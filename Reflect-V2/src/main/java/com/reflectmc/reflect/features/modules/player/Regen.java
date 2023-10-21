package com.reflectmc.reflect.features.modules.player;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.DoubleValue;
import com.reflectmc.reflect.features.values.ModeValue;
import com.reflectmc.reflect.features.values.OptionValue;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.NetHandlerPlayClient;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03.C03PacketPlayer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.potion.Potion;

public class Regen extends Module {
    private ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());
    private DoubleValue healthValue = new DoubleValue("Health", 1, 0.01, 1, "0.00");
    private DoubleValue foodValue = new DoubleValue("Food", 20, 0, 18, "0");
    private DoubleValue speedValue = new DoubleValue("Speed",100,0,100,"0");
    private OptionValue noAirValue = new OptionValue("NoAir",true);
    private OptionValue potionEffectValue = new OptionValue("PotionEffect", false);
    public Regen() {
        super("Regen", Category.Player);
        addValues(mode,healthValue,foodValue,speedValue,noAirValue,potionEffectValue);
    }
    @EventTarget
    public void onUpdate(EventLivingUpdate update) {
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
