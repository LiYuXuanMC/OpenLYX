package al.logger.client.ui.bases.components;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageDateFormat extends ComponentBase {
    private final String time;

    public MessageDateFormat(long timestamp) {
        super("MessageDateFormat");
        this.time = new SimpleDateFormat("HH:mm:ss").format(new Date(timestamp));
    }

    @Override
    protected void _initElements() {
        this.position.setHeight(10);
    }

    @Override
    protected void _drawElement() {
        float textWidth = FontLoadersWithChinese.hongMengSR14.getStringWidth(this.time);
        float textHeight = FontLoadersWithChinese.hongMengSR14.getStringHeight(this.time);
        float textX = this.position.getX() + (this.position.getWidth() - textWidth) / 2;
        float textY = this.position.getY() + (this.position.getHeight() - textHeight) / 2;
        FontLoadersWithChinese.hongMengSR14.drawString(this.time, textX, textY, new Color(255, 255, 255, 128).getRGB());
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
}
