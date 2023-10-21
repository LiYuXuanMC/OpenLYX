package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.event.client.player.EventRotationAnimation;
import al.logger.client.event.client.render.EventPostRenderLiving;
import al.logger.client.event.client.render.EventPreRenderLiving;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.utils.MathHelper;

public class RotationAnimation extends Module {
    float yaw, pitch, prevYaw, prevPitch;
    float oldYaw, oldPitch, oldPrevYaw, oldPrevPitch,oldRotationYawHead,oldPrevRotationYawHead,oldRenderYawOffset,oldPrevRenderYawOffset;
    boolean needMod;
    private OptionValue headOnly = new OptionValue("Flex", false);
    public RotationAnimation() {
        super("RotationAnimation", Category.Visual);
        addValues(headOnly);
    }
    @Listener(priority = 0)
    public void onPre(EventPreUpdate e) {
        needMod = e.isChanged();
        yaw = e.getYaw();
        pitch = e.getPitch();
    }
    @Listener
    public void onTick(EventTick e) {
        prevYaw = yaw;
        prevPitch = pitch;
    }
    @Listener
    public void onRotationAnimation(EventRotationAnimation e) {
        if (EntityPlayerSP.isEntityPlayerSP(e.getEntity()) && e.getPartialTicks() != 1.0F && mc.getThePlayer().getRidingEntity().isNull()) {
            if (needMod) {
                e.setRenderYawOffset(interpolateAngle(e.getPartialTicks(), prevYaw, yaw));
                e.setRenderHeadYaw(interpolateAngle(e.getPartialTicks(), prevYaw, yaw) - e.getRenderYawOffset());
                e.setRenderHeadPitch(lerp(e.getPartialTicks(), prevPitch, pitch));
            }
        }
    }
    @Listener
    public void doPreRender(EventPreRenderLiving preRenderLiving){
        if (EntityPlayerSP.isEntityPlayerSP(preRenderLiving.getEntity()) && needMod){
            EntityLivingBase entity = preRenderLiving.getEntity();
            oldYaw = entity.getRotationYaw();
            oldPitch = entity.getRotationPitch();
            oldPrevYaw = entity.getPrevRotationYaw();
            oldPrevPitch = entity.getPrevRotationPitch();
            oldRotationYawHead = entity.getRotationYawHead();
            oldPrevRotationYawHead = entity.getPrevRotationYawHead();
            oldRenderYawOffset = entity.getRenderYawOffset();
            oldPrevRenderYawOffset = entity.getPrevRenderYawOffset();

            entity.setPrevRotationPitch(prevPitch);
            entity.setPrevRotationYaw(prevYaw);
            entity.setRotationPitch(pitch);
            entity.setRotationYaw(yaw);
            entity.setRotationYawHead(yaw);
            entity.setPrevRotationYawHead(prevYaw);
            if(!headOnly.getValue()){
                entity.setRenderYawOffset(yaw);
                entity.setPrevRenderYawOffset(prevYaw);
            }
        }
    }
    @Listener
    public void doPostRender(EventPostRenderLiving postRenderLiving){
        if (EntityPlayerSP.isEntityPlayerSP(postRenderLiving.getEntity()) & needMod){
            EntityLivingBase entity = postRenderLiving.getEntity();
            entity.setPrevRotationPitch(oldPrevPitch);
            entity.setPrevRotationYaw(oldPrevYaw);
            entity.setRotationPitch(oldPitch);
            entity.setRotationYaw(oldYaw);
            entity.setRotationYawHead(oldRotationYawHead);
            entity.setPrevRotationYawHead(oldPrevRotationYawHead);
            if (!headOnly.getValue()){
                entity.setRenderYawOffset(oldRenderYawOffset);
                entity.setPrevRenderYawOffset(oldPrevRenderYawOffset);
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
