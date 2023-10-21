package al.logger.client.ui.screens.logger.Components.config;

import al.logger.client.Logger;
import al.logger.client.config.ConfigInfo;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.screens.logger.Components.normal.RightMenuButton;
import al.logger.client.ui.screens.logger.Container.ListBox;
import al.logger.client.utils.ScreenUtil;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;

import java.awt.*;
import java.util.Objects;

public class ConfigItem extends ComponentBase {

    public ConfigInfo configInfo;
    public ListBox parent;
    public float textWidth;
    public boolean isMouseDrag = false;

    public ConfigItem(ConfigInfo configInfo, ListBox parent) {
        super("ConfigItem");
        this.configInfo = configInfo;
        this.parent = parent;
        if (!parent.rightClickMenu.containsKey(this.elementName)) {

            RightMenuButton delButton = new RightMenuButton("Delete Config", FontLoadersWithChinese.hongMengSR14, () -> {
                Logger.getInstance().configManager.delConfig(parent.rightSelectInfo.keycode);
                parent.update();
            });
            delButton.setIconLocation(Logger.getInstance().resourceManager.getResourceLocation("rightmenu/delete.png"));

            RightMenuButton loadButton = new RightMenuButton("Load Config", FontLoadersWithChinese.hongMengSR14, () -> {
                Logger.getInstance().configManager.loadConfig(parent.rightSelectInfo.keycode, !Objects.equals(parent.rightSelectInfo.passowrd, ""));
            });
            loadButton.setIconLocation(Logger.getInstance().resourceManager.getResourceLocation("rightmenu/load.png"));

            parent.addRightClickMenu(this.elementName, delButton, loadButton);
        }
    }

    @Override
    public void _initElements() {
        this.position.setWidth(140);
        this.position.setHeight(68);
    }

    @Override
    public void _drawElement() {
        boolean isCurrent = Logger.getInstance().configManager.getCurrentConfigName() != null && Objects.equals(Logger.getInstance().configManager.getCurrentConfigName().keycode, this.configInfo.keycode);
        boolean isNotOutline = this.parent.parent.parent.IsNotOutline;
        RenderUtil.drawRoundOutline(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 4f, isCurrent ? 6f : 2f, new Color(38, 38, 38), isCurrent ? new Color(69, 122, 247) : new Color(49, 49, 49)
                , isNotOutline);
        int size = FontLoadersWithChinese.hongMengBlod17.formatString(this.configInfo.name, this.position.getWidth() - 22f).size();
        float addHeight = (FontLoadersWithChinese.hongMengBlod17.getHeight() * size) + (6f * size);
        FontLoadersWithChinese.hongMengBlod17.drawSplitString(this.configInfo.name, this.position.getX() + 10f, this.position.getY() + 11f, this.position.getWidth() - 22f, new Color(255, 255, 255).getRGB());
        FontLoadersWithChinese.hongMengSR14.drawSplitString(this.configInfo.keycode, this.position.getX() + 10f, this.position.getY() + 11f + addHeight, this.position.getWidth() - 22f, new Color(255, 255, 255, 128).getRGB());

        if (!Objects.equals(this.configInfo.passowrd, "")) {
            String text;
            if (this.isMouseDrag) {
                text = this.configInfo.passowrd;
            } else {
                text = "****";
            }
            textWidth = FontLoadersWithChinese.hongMengSR14.getStringWidth(text);
            RenderUtil.drawRound(this.position.getX() + 11f, this.position.getY() + this.position.getHeight() - 16f, textWidth + 4f, 8f, 2f, new Color(26, 26, 26));
            FontLoadersWithChinese.hongMengSR14.drawSplitString(text, this.position.getX() + 13f, this.position.getY() + this.position.getHeight() - 14f, this.position.getWidth() - 22f, new Color(255, 255, 255, 128).getRGB());
        }
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (ScreenUtil.isHovered(this.position.getX() + 11f, this.position.getY() + this.position.getHeight() - 16f, textWidth + 4f, 8f, mouseX, mouseY)) {
            this.isMouseDrag = true;
            return true;
        }
        if (isMouseHover) {
            if (mouseButton == 0) {
                Logger.getInstance().configManager.loadConfig(this.configInfo.keycode, !Objects.equals(this.configInfo.passowrd, ""));
                return true;
            } else if (mouseButton == 1) {
                parent.rightSelectInfo = this.configInfo;
                parent.displayRightClickMenu(this.elementName, mouseX + 3f, mouseY + 3f);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        if (this.isMouseDrag) {
            this.isMouseDrag = false;
            return true;
        }
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
