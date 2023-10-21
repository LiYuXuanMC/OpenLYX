package al.logger.client.features.modules.impls.Visual;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventCameraClip;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.wrapper.LoggerMC.GameSettings;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.IBlockState;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityAnimal;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.render.ActiveRenderInfo;
import al.logger.client.wrapper.LoggerMC.render.EntityRenderer;
import al.logger.client.wrapper.LoggerMC.render.GlStateManager;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;

public class CameraClip extends Module {
    public CameraClip() {
        super("CameraClip",Category.Visual);
    }
    @Listener
    public void onCameraClip(EventCameraClip cameraClip) {
        cameraClip.cancel();
        Entity entity = mc.getRenderViewEntity();
        float f = entity.getEyeHeight();
        GameSettings gameSettings = mc.getGameSettings();
        WorldClient theWorld = mc.getTheWorld();
        float partialTicks = cameraClip.getPartialTicks();
        EntityRenderer entityRenderer = cameraClip.getRenderer();
        if (EntityLivingBase.isEntityLivingBase(entity) && new EntityLivingBase(entity.getWrappedObject()).isPlayerSleeping()) {
            f += 1;
            GlStateManager.translate(0F, 0.3F, 0f);

            if (!gameSettings.isDebugCamEnable()) {
                BlockPos blockPos = new BlockPos(entity);
                IBlockState blockState = theWorld.getBlockState(blockPos);
                //net.minecraftforge.client.ForgeHooksClient.orientBedCamera(theWorld, blockPos, blockState, entity);

                GlStateManager.rotate(entity.getPrevRotationYaw() + (entity.getRotationYaw() - entity.getPrevRotationYaw()) * partialTicks + 180f, 0f, -1f, 0f);
                GlStateManager.rotate(entity.getPrevRotationPitch() + (entity.getRotationPitch() - entity.getPrevRotationPitch()) * partialTicks, -1f, 0f, 0f);
            }
        } else if (gameSettings.getThirdPersonView() > 0) {
            double d3 = entityRenderer.getThirdPersonDistanceTemp() + (entityRenderer.getThirdPersonDistance() - entityRenderer.getThirdPersonDistanceTemp()) * partialTicks;

            if (gameSettings.isDebugCamEnable()) {
                GlStateManager.translate(0f, 0f, (float) (-d3));
            } else {
                float f1 = entity.getRotationYaw();
                float f2 = entity.getRotationPitch();

                if (gameSettings.getThirdPersonView() == 2) f2 += 180f;

                if (gameSettings.getThirdPersonView() == 2) GlStateManager.rotate(180f, 0f, 1f, 0f);

                GlStateManager.rotate(entity.getRotationPitch() - f2, 1f, 0f, 0f);
                GlStateManager.rotate(entity.getRotationYaw() - f1, 0f, 1f, 0f);
                GlStateManager.translate(0f, 0f, (float) (-d3));
                GlStateManager.rotate(f1 - entity.getRotationYaw(), 0f, 1f, 0f);
                GlStateManager.rotate(f2 - entity.getRotationPitch(), 1f, 0f, 0f);
            }
        } else {
            GlStateManager.translate(0f, 0f, -0.1F);
        }

        if (!gameSettings.isDebugCamEnable()) {
            float yaw = entity.getPrevRotationYaw() + (entity.getRotationYaw() - entity.getPrevRotationYaw()) * partialTicks + 180f;
            float pitch = entity.getPrevRotationPitch() + (entity.getRotationPitch() - entity.getPrevRotationPitch()) * partialTicks;
            float roll = 0f;
            if (EntityAnimal.isEntityAnimal(entity)) {
                EntityAnimal entityanimal = new EntityAnimal(entity.getWrappedObject());
                yaw = entityanimal.getPrevRotationYawHead() + (entityanimal.getRotationYawHead() - entityanimal.getPrevRotationYawHead()) * partialTicks + 180f;
            }

            //Block block = ActiveRenderInfo.getBlockAtEntityViewpoint(theWorld, entity, partialTicks);
            //net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup event = new net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup((EntityRenderer) (Object) this, entity, block, partialTicks, yaw, pitch, roll);
            //net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
            GlStateManager.rotate(roll, 0f, 0f, 1f);
            GlStateManager.rotate(pitch, 1f, 0f, 0f);
            GlStateManager.rotate(yaw, 0f, 1f, 0f);
        }

        GlStateManager.translate(0f, -f, 0f);
        double d0 = entity.getPrevPosX() + (entity.getPosX() - entity.getPrevPosX()) * partialTicks;
        double d1 = entity.getPrevPosY() + (entity.getPosY() - entity.getPrevPosY()) * partialTicks + f;
        double d2 = entity.getPrevPosZ() + (entity.getPosZ() - entity.getPrevPosZ()) * partialTicks;
        entityRenderer.setCloudFog(mc.getRenderGlobal().hasCloudFog(d0, d1, d2, partialTicks));
    }
}
