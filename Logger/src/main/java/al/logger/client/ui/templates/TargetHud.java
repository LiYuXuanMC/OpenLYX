package al.logger.client.ui.templates;

import al.logger.client.features.modules.impls.Combat.KillAura;
import al.logger.client.features.modules.impls.Visual.Hud;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.entity.AbstractClientPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;
import al.logger.client.wrapper.LoggerMC.render.GlStateManager;

import java.awt.*;

public class TargetHud extends ComponentBase {


    private final Animation scaleAnimation = new Animation();
    private final Animation alphaAnimation = new Animation();
    private final Animation healthAnimation = new Animation();
    public EntityLivingBase target = null;
    public EntityLivingBase renderTarget = null;
    public boolean isTargetPT = false;
    public double[] renderPositions = new double[]{0.0D, 0.0D, 0.0D, 0.0D};

    public TargetHud(String elementName) {
        super(elementName);
    }

    @Override
    protected void _initElements() {
        this.position.setWidth(120f);
        this.position.setHeight(40f);
    }

    @Override
    protected void _drawElement() {
        if (!Hud.componentWithAnimation.getValue()) {
            if(target == null){
                return;
            }
        }
        if (this.isEdit) {
            target = mc.getThePlayer();
        } else if (isTargetPT) {
            ScaledResolution scaledRes = new ScaledResolution(mc);
            GlStateManager.pushMatrix();
            if ((renderPositions[3] < 0.0D) || (renderPositions[3] >= 1.0D)) {
                GlStateManager.popMatrix();
                return;
            }
            GlStateManager.translate(renderPositions[0] / scaledRes.getScaleFactor(), renderPositions[1] / scaledRes.getScaleFactor(), 0.0D);
            GlStateManager.scale(1, 1, 1);
            GlStateManager.translate(0.0D, -2.5D, 0.0D);
            this.position.setX(30f);
            this.position.setY(30f);
        }
        boolean isRender = target != null;
        if (isRender) {
            renderTarget = target;
            alphaAnimation.start(alphaAnimation.getValue(), 1.0f, 0.35f, Type.EASE_IN_OUT_QUAD);
            if (renderTarget.getHurtTime() == 0) {
                scaleAnimation.start(scaleAnimation.getValue(), 1.0f, 0.15f, Type.EASE_OUT_BACK);
            } else {
                scaleAnimation.start(
                        scaleAnimation.getValue(),
                        1.0f - renderTarget.getHurtTime() / 100f,
                        0.15f,
                        Type.EASE_IN_OUT_QUAD
                );
            }
        } else {
            alphaAnimation.start(alphaAnimation.getValue(), 0.0, 0.35f, Type.EASE_IN_OUT_QUAD);
            scaleAnimation.start(scaleAnimation.getValue(), 1.15, 0.35f, Type.EASE_IN_OUT_QUAD);
        }
        if (renderTarget != null) {
            RenderUtil.doScale(position.getX() + position.getWidth() / 2, position.getY() + position.getHeight() / 2, (float) scaleAnimation.getValue(), () -> {
                RenderUtil.doStencil(position.getX() - 1, position.getY() - 1, position.getWidth() + 2, position.getHeight() + 2, 4f);
                int alpha = (int) (255 * alphaAnimation.getValue());
                if ((float) scaleAnimation.getValue() == 1 && !KillAura.TargetHudPTEntity.getValue()) {
                    RenderUtil.upShadow(position.getX(), position.getY(), position.getWidth(), position.getHeight(), 8, 8);
                }
                if (alphaAnimation.getValue() != 0) {
                    if (!KillAura.TargetHudPTEntity.getValue()) {
                        RenderUtil.upBlur(position.getX(), position.getY(), position.getWidth(), position.getHeight(), 8, 12);
                        RenderUtil.drawRound(position.getX(), position.getY(), position.getWidth(), position.getHeight(), 8, new Color(26, 26, 26, (int) (80 * alphaAnimation.getValue())));
                    } else {
                        RenderUtil.drawRound(position.getX(), position.getY(), position.getWidth(), position.getHeight(), 8, new Color(26, 26, 26, (int) (200 * alphaAnimation.getValue())));
                    }
                }
                if (!KillAura.TargetHudPTEntity.getValue()) {
                    RenderUtil.upBlur(position.getX(), position.getY(), position.getWidth(), position.getHeight(), 12, () -> {
                        FontLoadersWithChinese.hongMengSR15.drawString(
                                renderTarget.getName(),
                                position.getX() + 40f,
                                position.getY() + 10f,
                                new Color(255, 255, 255, alpha).getRGB()
                        );
                    });
                }
                FontLoadersWithChinese.hongMengSR15.drawString(
                        renderTarget.getName(),
                        position.getX() + 40f,
                        position.getY() + 10f,
                        new Color(255, 255, 255, (int) (160 * alphaAnimation.getValue())).getRGB()
                );
                if (EntityPlayer.isEntityPlayer(renderTarget)) {
                    GlStateManager.resetColor();
                    mc.getTextureManager().bindTexture(new AbstractClientPlayer(renderTarget.getWrappedObject()).getLocationSkin());
                    GlStateManager.color(1.0f, 1.0f, 1.0f, (float) alphaAnimation.getValue());
                    RenderUtil.drawScaledCustomSizeModalRect(
                            (int) (position.getX() + 10),
                            (int) (position.getY() + 10),
                            8.0f, 8.0f, 8, 8, 21, 21, 64f, 64f
                    );
                }
                float maxHealth = renderTarget.getMaxHealth();
                healthAnimation.start(healthAnimation.getValue(), renderTarget.getHealth(), 0.2f, Type.LINEAR);
                float health = (float) healthAnimation.getValue();
                float healthPercent = health / maxHealth;
                if (healthPercent > 1.0) {
                    healthPercent = 1.0f;
                }
                RenderUtil.drawRound(
                        position.getX() + 40f,
                        position.getY() + 22f,
                        70f,
                        2f,
                        1f,
                        new Color(0, 0, 0, (int) (80 * alphaAnimation.getValue()))
                );
                RenderUtil.drawRound(
                        position.getX() + 40f,
                        position.getY() + 22f,
                        70f * healthPercent,
                        2f,
                        1f,
                        new Color(193, 61, 61, alpha)
                );
                RenderUtil.drawRound(
                        position.getX() + 40f,
                        position.getY() + 28f,
                        70f,
                        2f,
                        1f,
                        new Color(0, 0, 0, (int) (80 * alphaAnimation.getValue()))
                );

                RenderUtil.drawRound(
                        position.getX() + 40f,
                        position.getY() + 28f,
                        70f * (renderTarget.getTotalArmorValue() / 20f),
                        2f,
                        1f,
                        new Color(74, 74, 74, alpha)
                );
                RenderUtil.uninstallStencil();
                healthAnimation.update();
            });
        }
        alphaAnimation.update();
        scaleAnimation.update();
        if (isTargetPT) {
            GlStateManager.popMatrix();
        }
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            this.isMouseDown = true;
            return true;
        }
        return false;
    }

    @Override
    protected boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        return false;
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
