package al.logger.client.ui.templates;

import al.logger.client.features.modules.impls.Visual.Hud;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.render.RenderUtil;
import org.lwjgl.Sys;

import java.awt.*;

public class LongJumpProgress extends ComponentBase {

    public int diff = 0;
    public int diffCurrent = 0;
    public boolean isLongJumping = false;
    public Animation alphaAnimation = new Animation();
    private float percent = 0.0f;

    public LongJumpProgress() {
        super("LongJumpProgress");
    }

    public void startLongJump(int diff) {
        this.diff = diff;
        this.isLongJumping = true;
    }

    public void updateLongJump(int diffCurrent) {
        this.diffCurrent = diffCurrent;
    }

    public void stopLongJump() {
        this.diff = 0;
        this.isLongJumping = false;
    }


    @Override
    protected void _initElements() {
        this.position.setWidth(100);
        this.position.setHeight(10);
    }

    @Override
    protected void _drawElement() {
        if (!Hud.componentWithAnimation.getValue()) {
            if (!isLongJumping) {
                return;
            }
        }
        Color backgroundColor = Hud.longJumpColor.getColorValue("Background").getValue();
        Color foregroundColor = Hud.longJumpColor.getColorValue("Foreground").getValue();
        alphaAnimation.start(alphaAnimation.getValue(), isLongJumping ? 1.0f : 0.0f, 0.2F, Type.LINEAR);
        alphaAnimation.update();
        if (isEdit) {
            alphaAnimation.setValue(1.0f);
        }
        RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 0, new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), (int) (120 * alphaAnimation.getValue())));
        if (this.diff < this.diffCurrent) {
            this.diffCurrent = this.diff;
        }
        long delDiff = (diff == 0 ? 1 : diff);
        if (isLongJumping) {
            percent = (float) this.diffCurrent / (float) delDiff;
        }
        RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth() * percent, this.position.getHeight(), 0, new Color(foregroundColor.getRed(), foregroundColor.getGreen(), foregroundColor.getBlue(), (int) (255 * alphaAnimation.getValue())));
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
