package al.logger.client.ui.bases.components;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Notification extends ComponentBase {

    @Getter
    @Setter
    private final String title;
    @Getter
    @Setter
    private final String message;
    @Getter
    @Setter
    private final NotificationType type;
    @Getter
    @Setter
    private final Animation xAnimation = new Animation();
    @Getter
    @Setter
    private boolean isForwards = false;

    public Notification(String message, NotificationType type) {
        super("Notification");
        this.title = type.name();
        this.message = message;
        this.type = type;
        float textWidth = FontLoadersWithChinese.hongMengBold14S.getStringWidth(title);
        float textWidth2 = FontLoadersWithChinese.hongMengBold14S.getStringWidth(message);
        float textHeight = FontLoadersWithChinese.hongMengBold14S.getStringHeight(title);
        if (textWidth2 > textWidth) {
            textWidth = textWidth2;
        }
        this.getPosition().setWidth(textWidth + 12f);
        this.getPosition().setHeight((textHeight + 6f) * 2);
    }

    @Override
    protected void _initElements() {
    }

    @Override
    protected void _drawElement() {
        xAnimation.fstart(xAnimation.getValue(), isForwards ? -1.0f : 1.0f, 0.2f, Type.LINEAR);
        xAnimation.update();
        int backgroundAlpha = (int) (160 * xAnimation.getValue());
        if (backgroundAlpha < 0) {
            backgroundAlpha = 0;
        }
        int fontAlpha = (int) (200 * xAnimation.getValue());
        if (fontAlpha < 0) {
            fontAlpha = 0;
        }
        float y = (float) (this.getPosition().getY() - 30 * (1 - xAnimation.getValue()));
        RenderUtil.drawRound(this.getPosition().getX(), y, this.getPosition().getWidth(), this.getPosition().getHeight(), this.getPosition().getHeight() / 2, new Color(this.type.getR(), this.type.getG(), this.type.getB(), backgroundAlpha));
        float textWidth = FontLoadersWithChinese.hongMengBold14S.getStringWidth(this.title);
        float textHeight = FontLoadersWithChinese.hongMengBold14S.getStringHeight(this.title);
        float textWidth2 = FontLoadersWithChinese.hongMengBold14S.getStringWidth(this.message);
        float textX = this.getPosition().getX() + (this.getPosition().getWidth() - textWidth) / 2;
        float textX2 = this.getPosition().getX() + (this.getPosition().getWidth() - textWidth2) / 2;
        FontLoadersWithChinese.hongMengBold14S.drawString(this.title, textX, y + 5, new Color(255, 255, 255, fontAlpha).getRGB());
        FontLoadersWithChinese.hongMengBold14S.drawString(this.message, textX2, y + 7 + textHeight, new Color(255, 255, 255, fontAlpha).getRGB());
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

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }

    public enum NotificationType {
        Info(0, 0, 0),
        Warning(204, 156, 0),
        Success(31, 182, 104),
        Error(193, 61, 61);

        @Getter
        private int r;

        @Getter
        private int g;

        @Getter
        private int b;

        NotificationType(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
}
