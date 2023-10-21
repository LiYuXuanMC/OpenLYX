package al.logger.client.ui.bases.components;

import al.logger.client.Logger;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.managers.MessageManager;
import al.logger.client.ui.screens.logger.Container.ListBox;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;

import java.awt.*;

public class Message extends ComponentBase {
    private final String rank;
    private final String username;
    private final String message;
    private ProfilePicture picture;

    public Message(String rank, String username, String message) {
        super("Message");
        this.rank = rank;
        this.username = username;
        this.message = message;
        Minecraft.getMinecraft().addScheduledTask(() -> {
            this.picture = new ProfilePicture(Logger.getInstance().getResourceManager().getPicture(this.username), this.username);
        });
    }

    @Override
    protected void _initElements() {
        this.heightBase = 20;
        this.getPosition().setHeight(this.heightBase);
    }

    @Override
    protected void _drawElement() {
        boolean isCurrent = Logger.getInstance().getLoggerUser().getUsername().equalsIgnoreCase(this.username);
        float splitWidth = this.position.getWidth() / 1.3f;
        int size = FontLoadersWithChinese.hongMengSR15.formatString(this.message, splitWidth).size();
        float addHeight = ((FontLoadersWithChinese.hongMengSR15.getStringHeight(this.message) + 4f) * size) + 2f;
        String rankUsername = "[" + this.rank + "]" + this.username;
        float textWidth = FontLoadersWithChinese.hongMengSR15.getStringWidth(this.message);
        float textWidth2 = FontLoadersWithChinese.hongMengSR14S.getStringWidth(rankUsername);
        if (textWidth > splitWidth) {
            textWidth = splitWidth;
        }
        if (size == 1) {
            addHeight = 12f;
        }
        this.getPosition().setHeight(this.heightBase + addHeight);
        if (this.picture != null) {
            this.picture.drawComponent(isCurrent ? this.position.getX() + this.getPosition().getWidth() - 26f : this.position.getX() + 8f, this.position.getY() + 6f, 16, 16, mouseX, mouseY, false);
        }
        RenderUtil.drawRound(isCurrent ? this.position.getX() + this.getPosition().getWidth() - textWidth - 38f : this.position.getX() + 30f, this.position.getY() + 16f, textWidth + 6f, addHeight + 6f, 4f, isCurrent ? new Color(30, 100, 160) : new Color(38, 38, 38));
        FontLoadersWithChinese.hongMengSR14S.drawString(rankUsername, isCurrent ? this.position.getX() + this.getPosition().getWidth() - textWidth2 - 32f : this.position.getX() + 30f, this.position.getY() + 6, new Color(255, 255, 255, 160).getRGB());
        FontLoadersWithChinese.hongMengSR15.drawSplitString(this.message, isCurrent ? this.position.getX() + this.getPosition().getWidth() - textWidth - 35f : this.position.getX() + 33f, this.position.getY() + 23f, splitWidth, isCurrent ? new Color(255, 255, 255, 255).getRGB() : new Color(255, 255, 255, 160).getRGB());
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            this.picture.isCurrent = Logger.getInstance().getLoggerUser().getUsername().equalsIgnoreCase(this.username);
            this.picture.mouseClicked(mouseX, mouseY, mouseButton);
            return true;
        }
        return false;
    }

    @Override
    protected boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        this.picture.mouseReleased(mouseX, mouseY, mouseStatus);
        return false;
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void update() {

    }

    @Override
    public void update(ComponentBase parent) {
        if (parent instanceof ListBox) {
            ListBox listBox = (ListBox) parent;
        }
    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
