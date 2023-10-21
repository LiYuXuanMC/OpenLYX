package al.logger.client.ui.templates.ModuleHud;

import al.logger.client.Logger;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.Visual.Hud;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.misc.ColorUtil;
import al.logger.client.utils.render.RenderUtil;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class ModuleChanger extends ComponentBase {

    @Getter
    private final Module module;
    private final float textWidth;
    private final float textHeight;
    @Getter
    @Setter
    private int progress = 0;

    public ModuleChanger(Module module) {
        super("ModuleChanger");
        this.module = module;

        if (Hud.renderMode.getValue().equals(Hud.RenderMode.Old)) {
            this.textWidth = FontLoadersWithChinese.hongMengSR17.getStringWidth(module.getDisplayName());
            this.textHeight = FontLoadersWithChinese.hongMengSR17.getStringHeight(module.getDisplayName());
            this.setWidthBase(textWidth + 12);
            this.getPosition().setHeight(textHeight + 12);
        } else if (Hud.renderMode.getValue().equals(Hud.RenderMode.New)) {
            this.textWidth = FontLoadersWithChinese.hongMengBlod17.getStringWidth(module.getDisplayName());
            this.textHeight = FontLoadersWithChinese.hongMengBlod17.getStringHeight(module.getDisplayName());
            this.setWidthBase(textWidth);
            this.getPosition().setHeight(textHeight + 6);
        } else {
            this.textWidth = 0;
            this.textHeight = 0;
        }

    }

    @Override
    protected void _initElements() {

    }

    @Override
    protected void _drawElement() {
        if (this.module.getAnimation().get() != 0) {
            this.module.getYAnimation().setTarget(this.getPosition().getY());
            this.module.getYAnimation().update();

            if (Hud.shadow.getValue()) {
                RenderUtil.drawFullImage(
                        Logger.getInstance().getResourceManager().getResourceLocation("shadow.png")
                        , this.getPosition().getX() - 9
                        , (float) this.module.getYAnimation().get() - 9
                        , this.getPosition().getWidth() + 19
                        , this.getPosition().getHeight() + 19
                );
            }

            Color startColor = Hud.arrayListColor.getColorValue("Color1").getValue();
            Color endColor = Hud.arrayListColor.getColorValue("Color2").getValue();
            Color color = ColorUtil.interpolateColorsBackAndForth(Hud.colorSpeed.getValue().intValue(), this.progress, startColor, endColor, false);
            if (Hud.renderMode.getValue().equals(Hud.RenderMode.Old)) {
                RenderUtil.drawRoundedRectGradientRect(this.getPosition().getX(), (float) this.module.getYAnimation().get(), this.getPosition().getWidth(), this.getPosition().getHeight(), 0, ColorUtil.reAlpha(color, 30), ColorUtil.reAlpha(color, 120), false);
                RenderUtil.doStencil(this.getPosition().getX(), (float) this.module.getYAnimation().get(), this.getPosition().getWidth(), this.getPosition().getHeight(), 0);
                FontLoadersWithChinese.hongMengSR17.drawString(module.getDisplayName(), this.getPosition().getX() + 6, this.module.getYAnimation().get() + 6, new Color(255, 255, 255, 225).getRGB());
                RenderUtil.uninstallStencil();
            } else if (Hud.renderMode.getValue().equals(Hud.RenderMode.New)) {
                RenderUtil.doStencil(this.getPosition().getX(), (float) this.module.getYAnimation().get(), this.getPosition().getWidth(), this.getPosition().getHeight(), 2);
                FontLoadersWithChinese.hongMengBlod17.drawString(module.getDisplayName(), this.getPosition().getX(), this.module.getYAnimation().get() + 3, color.getRGB());
                RenderUtil.uninstallStencil();
            }
        }


    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
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
        this.module.getAnimation().setTarget(this.module.isEnable() ? 1.0 : 0.0);
        this.module.getAnimation().update();
        this.getPosition().setWidth((float) (this.getWidthBase() * module.getAnimation().get()));
    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
