package com.reflectmc.reflect.features.modules.visual;

import com.reflectmc.reflect.event.Priority;
import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.game.EventTick;
import com.reflectmc.reflect.event.events.player.EventPreUpdate;
import com.reflectmc.reflect.event.events.player.EventRotationAnimation;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.utils.ClientUtil;
import com.reflectmc.reflect.utils.MathHelper;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;

public class RotationAnimation extends Module {
    float yaw, pitch, prevYaw, prevPitch;
    boolean needMod;
    public RotationAnimation() {
        super("RotationAnimation", Category.Visual);
    }

    @EventTarget(priority = Priority.Lowest)
    public void onPre(EventPreUpdate e) {
        needMod = e.isValueChanged();
        yaw = e.getYaw();
        pitch = e.getPitch();
    }
    @EventTarget
    public void onTick(EventTick e) {
        prevYaw = yaw;
        prevPitch = pitch;
    }
    @EventTarget
    public void onRotationAnimation(EventRotationAnimation e) {
        if (EntityPlayerSP.isEntityPlayerSP(e.getEntity()) && e.getPartialTicks() != 1.0F && mc.getThePlayer().getRidingEntity().isNull()) {
            if (needMod) {
                e.setRenderYawOffset(interpolateAngle(e.getPartialTicks(), prevYaw, yaw));
                e.setRenderHeadYaw(interpolateAngle(e.getPartialTicks(), prevYaw, yaw) - e.getRenderYawOffset());
                e.setRenderHeadPitch(lerp(e.getPartialTicks(), prevPitch, pitch));
            }
        }
    }

    public static float interpolateAngle(float p_219805_0_, float p_219805_1_, float p_219805_2_) {
        return p_219805_1_ + p_219805_0_ * MathHelper.wrapAngleTo180_float(p_219805_2_ - p_219805_1_);
    }

    public static float lerp(float pct, float start, float end) {
        return start + pct * (end - start);
    }

}
