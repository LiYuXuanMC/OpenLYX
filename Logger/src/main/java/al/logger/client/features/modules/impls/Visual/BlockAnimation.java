package al.logger.client.features.modules.impls.Visual;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventAnimation;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.animation.Type;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.render.GlStateManager;
import al.logger.client.wrapper.LoggerMC.render.ItemRenderer;
import al.logger.client.wrapper.LoggerMC.utils.MathHelper;
import org.lwjgl.opengl.GL11;


public class BlockAnimation extends Module {

    public ModeValue mode = new ModeValue("Mode", BlockAnimationType._1_7_10, BlockAnimationType.values());
    public DoubleValue posX = new DoubleValue("PosX", 2.0, -2.0, 0.0, 0.1);
    public DoubleValue posY = new DoubleValue("PosY", 2.0, -2.0, 0.0, 0.1);
    public DoubleValue posZ = new DoubleValue("PosZ", 2.0, -2.0, 0.0, 0.1);
    public Animation rotateAnimation = new Animation();

    public BlockAnimation() {
        super("BlockAnimation", "Block animation", Category.Visual);
        addValues(mode, posX, posY, posZ);
    }

    private void doBlockTransformations() {
        GlStateManager.translate(-0.5F, 0.2F, 0.0F);
        GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
    }

    private void doBlockRotateTransformations(float x, float y, float z) {
        if (rotateAnimation.getValue() == 360) {
            rotateAnimation.reset();
        }
        rotateAnimation.start(rotateAnimation.getValue(), 360, 1f, Type.LINEAR);
        rotateAnimation.update();
        GlStateManager.rotate((float) rotateAnimation.getValue(), x, y, z);
    }

    private void transformFirstPersonItem(float equipProgress, float swingProgress) {
        GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
        GlStateManager.translate(0.0F, equipProgress * -0.6F, 0.0F);
        GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
        float f = MathHelper.sin(swingProgress * swingProgress * (float) Math.PI);
        float f1 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float) Math.PI);
        GlStateManager.rotate(f * -20.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(0.4F, 0.4F, 0.4F);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        rotateAnimation.setEnd(360);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        rotateAnimation.reset();
    }

    @Listener
    public void onBlockAnimation(EventAnimation eventAnimation) {
        eventAnimation.cancel();

        GL11.glTranslated(this.posX.getValue().floatValue(), this.posY.getValue().floatValue(), posZ.getValue().floatValue());
        ItemRenderer itemRenderer = mc.getItemRenderer();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        float swingProgress = 1.0F - (itemRenderer.getPrevEquippedProgress() + (itemRenderer.getEquippedProgress() - itemRenderer.getPrevEquippedProgress()) * mc.getTimer().getRenderPartialTicks());
        float f1 = thePlayer.getSwingProgress(mc.getTimer().getRenderPartialTicks());
        float mEquippedProgress = itemRenderer.getEquippedProgress();
        float var15 = MathHelper.sin(f1 * f1 * 3.1415927F);

        switch (mode.getValue().name()) {
            case "Sigma":
                this.transformFirstPersonItem(swingProgress * 0.5f, 0);
                GlStateManager.rotate(-var15 * 55 / 2.0F, -8.0F, -0.0F, 9.0F);
                GlStateManager.rotate(-var15 * 45, 1.0F, var15 / 2, -0.0F);
                this.doBlockTransformations();
                GL11.glTranslated(1.2, 0.3, 0.5);
                GL11.glTranslatef(-1, this.mc.getThePlayer().isSneaking() ? -0.1F : -0.2F, 0.2F);
                break;
            case "_1_7_10":
                this.transformFirstPersonItem(swingProgress, f1);
                GlStateManager.translate(0, 0.3, 0);
                this.doBlockTransformations();
                break;
            case "Jello":
                GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
                GlStateManager.translate(0.0F, 0 * -0.6F, 0.0F);
                GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
                float var3 = MathHelper.sin((float) (0.0F * 0.0F * Math.PI));
                float var4 = MathHelper.sin((float) (MathHelper.sqrt_float(0.0F) * Math.PI));
                GlStateManager.rotate(var3 * -20.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(var4 * -20.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(var4 * -80.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.scale(0.4F, 0.4F, 0.4F);

                GlStateManager.translate(-0.5F, 0.2F, 0.0F);
                GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
                int alpha = (int) Math.min(255,
                        ((System.currentTimeMillis() % 255) > 255 / 2
                                ? (Math.abs(Math.abs(System.currentTimeMillis()) % 255 - 255))
                                : System.currentTimeMillis() % 255) * 2);
                GlStateManager.translate(0.3f, -0.0f, 0.40f);
                GlStateManager.rotate(0.0f, 0.0f, 0.0f, 1.0f);
                GlStateManager.translate(0, 0.5f, 0);

                GlStateManager.rotate(90, 1.0f, 0.0f, -1.0f);
                GlStateManager.translate(0.6f, 0.5f, 0);
                GlStateManager.rotate(-90, 1.0f, 0.0f, -1.0f);

                GlStateManager.rotate(-10, 1.0f, 0.0f, -1.0f);
                GlStateManager.rotate(thePlayer.isSwingInProgress() ? -alpha / 5f : 1, 1.0f, -0.0f, 1.0f);
                break;
            case "RotationX":
                this.transformFirstPersonItem(swingProgress, mEquippedProgress);
                this.doBlockRotateTransformations(1f, 0f, 0f);
                break;
            case "RotationY":
                this.transformFirstPersonItem(swingProgress, mEquippedProgress);
                this.doBlockRotateTransformations(0f, 1f, 0f);
                break;
            case "RotationZ":
                this.transformFirstPersonItem(swingProgress, mEquippedProgress);
                this.doBlockRotateTransformations(0f, 0f, 1f);
                break;
            case "Stella":
                this.transformFirstPersonItem(-0.1F, f1);
                GlStateManager.translate(-0.5F, 0.4F, -0.2F);
                GlStateManager.rotate(32, 0, 1, 0);
                GlStateManager.rotate(-70, 1, 0, 0);
                GlStateManager.rotate(40, 0, 1, 0);
                break;
            case "Fathum":
                GlStateManager.popMatrix();
                GL11.glRotated(25, 0, 0.2, 0);
                this.transformFirstPersonItem(0.0f, f1);
                GlStateManager.scale(0.9F, 0.9F, 0.9F);
                this.doBlockTransformations();
                GlStateManager.pushMatrix();
                break;
            case "Smooth":
                this.transformFirstPersonItem(f1 / 5, f1);
                GlStateManager.translate(0, 0.2, 0);
                GlStateManager.rotate(-var15, 4, -0.8F, -1F);
                this.doBlockTransformations();
                break;
            case "Exhi":
                this.transformFirstPersonItem(swingProgress / 2, 0);
                GlStateManager.rotate(-var15 * 40.0F / 2.0F, var15 / 2.0F, -0.0F, 9.0F);
                GlStateManager.rotate(-var15 * 30.0F, 1.0F, var15 / 2.0F, -0.0F);
                this.doBlockTransformations();
                GL11.glTranslatef(-0.05F, this.mc.getThePlayer().isSneaking() ? -0.2F : 0.0F, 0.1F);
                break;
            case "Exhi2":
                this.transformFirstPersonItem(swingProgress / 2, f1);
                var15 = MathHelper.sin(MathHelper.sqrt_float(f1) * 3.1415927F);
                GlStateManager.rotate(var15 * 30.0F, -var15, -0.0F, 9.0F);
                GlStateManager.rotate(var15 * 40.0F, 1.0F, -var15, -0.0F);
                this.doBlockTransformations();
                GL11.glTranslatef(-0.05F, this.mc.getThePlayer().isSneaking() ? -0.2F : 0.0F, 0.1F);
                break;
            case "Shred":
                this.transformFirstPersonItem(swingProgress / 2, f1);
                var15 = MathHelper.sin(f1 * f1 * 3.1415927F);
                GlStateManager.rotate(var15 * 30.0F / 2.0F, -var15, -0.0F, 9.0F);
                GlStateManager.rotate(var15 * 40.0F, 1.0F, -var15 / 2.0F, -0.0F);
                this.doBlockTransformations();
                GL11.glTranslatef(-0.05F, this.mc.getThePlayer().isSneaking() ? -0.2F : 0.0F, 0.1F);
                break;
        }
    }

    enum BlockAnimationType {
        _1_7_10,
        RotationX,
        RotationY,
        RotationZ,
        Jello,
        Sigma,
        Stella,
        Fathum,
        Smooth,
        Exhi,
        Exhi2,
        Shred
    }
}
