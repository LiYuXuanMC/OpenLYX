package al.logger.client.ui.templates;

import al.logger.client.Logger;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.ValidUtils;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.misc.GLUtil;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.render.GlStateManager;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;

public class RadarTemplate extends ComponentBase {

    private final ArrayList<Entity> entities = new ArrayList<>();
    @Getter
    private float radarSize = 100f;
    private Animation rotateAnimation = new Animation();

    public void setRadarSize(float size) {
        this.radarSize = size;
        this.position.setWidth(radarSize);
        this.position.setHeight(radarSize);
    }

    public RadarTemplate() {
        super("RadarTemplate");
        this.position.setWidth(radarSize);
        this.position.setHeight(radarSize);
    }

    @Override
    public void _initElements() {
    }

    @Override
    public void _drawElement() {
        entities.clear();
        entities.addAll(mc.getTheWorld().getLoadedEntityList());

        RenderUtil.upShadow(this.position.getX(), this.position.getY(), radarSize, radarSize, 5, 3);
        RenderUtil.doStencil(this.position.getX(), this.position.getY(), radarSize, radarSize, 4f);

        float half = radarSize / 2;

        if (rotateAnimation.getValue() == 360) {
            rotateAnimation.reset();
        }
        rotateAnimation.start(rotateAnimation.getValue(), 360, 6f, Type.LINEAR);
        rotateAnimation.update();

        GLUtil.onRenderRotate(this.position.getX() + half, this.position.getY() + half, (float) rotateAnimation.getValue(), () -> {
            RenderUtil.drawFullImage(Logger.getInstance().resourceManager.getResourceLocation("template/radar.png"), this.position.getX() - 50, this.position.getY() - 50, this.position.getWidth() + 100, this.position.getHeight() + 100, new Color(29, 29, 29, 80));
        });

        GLUtil.onRenderRotate(this.position.getX() + half, this.position.getY() + half, mc.getThePlayer().getRotationYaw(), () -> {
            for (Entity entity : entities) {
                if (ValidUtils.isValid(entity) || entity.equals(mc.getThePlayer())) {
                    double entityT2DX = MathHelper.interpolate(entity.getPrevPosX(), entity.getPosX(), mc.getTimer().getRenderPartialTicks()) - MathHelper.interpolate(mc.getThePlayer().getPrevPosX(), mc.getThePlayer().getPosX(), mc.getTimer().getRenderPartialTicks());
                    double entityT2DZ = MathHelper.interpolate(entity.getPrevPosZ(), entity.getPosZ(), mc.getTimer().getRenderPartialTicks()) - MathHelper.interpolate(mc.getThePlayer().getPrevPosZ(), mc.getThePlayer().getPosZ(), mc.getTimer().getRenderPartialTicks());
                    if ((entityT2DX + entityT2DZ) < half) {
                        float translateX = (float) (this.position.getX() + half - entityT2DX);
                        float translateY = (float) (this.position.getY() + half - entityT2DZ);
                        RenderUtil.drawRound(translateX, translateY, 3, 3, 1f, new Color(255, 255, 255, 255));
                        GlStateManager.disableBlend();
                    }
                }
            }
        });

        RenderUtil.uninstallStencil();
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            this.isMouseDown = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
