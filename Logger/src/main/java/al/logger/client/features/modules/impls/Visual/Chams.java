package al.logger.client.features.modules.impls.Visual;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventPostRenderLiving;
import al.logger.client.event.client.render.EventPreRenderLiving;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.render.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Chams extends Module {
    public Chams() {
        super("Chams" , Category.Visual);
    }

    @Listener
    public void onRenderPlayerPre(EventPreRenderLiving e) {
        if (e.getEntity() == null || !EntityLivingBase.isEntityLivingBase(e.getEntity())) {
            return;
        }
        if (!EntityPlayer.isEntityPlayer(e.getEntity())) {
            return;
        }
        mc.getEntityRenderer().disableLightmap();
        GlStateManager.disableLighting();
        GL11.glEnable(32823);
        GL11.glPolygonOffset(1.0f, -1100000.0f);
    }
    @Listener
    public void onRenderPlayerPost(EventPostRenderLiving e) {
        if (e.getEntity() == null || !EntityLivingBase.isEntityLivingBase(e.getEntity())) {
            return;
        }
        if (!EntityPlayer.isEntityPlayer(e.getEntity())) {
            return;
        }
        GL11.glDisable(32823);
        GL11.glPolygonOffset(1.0f, 1100000.0f);
        mc.getEntityRenderer().disableLightmap();
        GlStateManager.disableLighting();
    }
}
