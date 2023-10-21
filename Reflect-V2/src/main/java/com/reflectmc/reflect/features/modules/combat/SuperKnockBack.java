package com.reflectmc.reflect.features.modules.combat;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventAttack;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.ModeValue;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityLivingBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.NetHandlerPlayClient;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c0b.C0BAction;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c0b.C0BPacketEntityAction;

public class SuperKnockBack extends Module {
    private final ModeValue mode = new ModeValue("Mode", Mode.Normal, Mode.values());
    public SuperKnockBack() {
        super("SuperKnockBack", Category.Combat);
        addValue(mode);
    }
    @EventTarget
    public void attack(EventAttack e){
        if (mode.getValue() == Mode.Normal)
        if (EntityLivingBase.isEntityLivingBase(e.getTargetEntity())) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP entityPlayerSP = mc.getThePlayer();
            NetHandlerPlayClient netHandler = mc.getNetHandler();
            if (entityPlayerSP.isSprinting())
                netHandler.addToSendQueue(new C0BPacketEntityAction(entityPlayerSP, C0BAction.STOP_SPRINTING));
            netHandler.addToSendQueue(new C0BPacketEntityAction(entityPlayerSP, C0BAction.START_SPRINTING));
            netHandler.addToSendQueue(new C0BPacketEntityAction(entityPlayerSP, C0BAction.STOP_SPRINTING));
            netHandler.addToSendQueue(new C0BPacketEntityAction(entityPlayerSP, C0BAction.START_SPRINTING));
            entityPlayerSP.setSprinting(true);
            entityPlayerSP.setServerSprintState(true);
        }
    }


    enum Mode {
        Normal,
    }
}
