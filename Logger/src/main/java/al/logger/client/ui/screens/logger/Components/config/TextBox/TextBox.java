package al.logger.client.ui.screens.logger.Components.config.TextBox;

import al.logger.client.ui.screens.logger.Container.ListBox;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextBox extends ComponentBase {

    public ListBox parent;
    public boolean isSelect = false;
    public boolean isBlink = false;
    public TimerUtils timerUtils = new TimerUtils();
    public String value = "";

    public TextBox(ListBox parent, float width, float height, String value) {
        super(value);
        this.parent = parent;
        this.position.setWidth(width);
        this.position.setHeight(height);
        this.value = "";
    }


    public static String getSysClipboardText() {
        String ret = "";
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable clipTf = sysClip.getContents(null);
        if (clipTf != null) {
            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    ret = (String) clipTf
                            .getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    public static boolean isAChar(String texts) {
        String regEx = "^[0-9a-zA-Z\u4E00-\u9FA5_` ~!@#$%^&*()+=|{}':;',.，。；]+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(texts);
        return !m.find();
    }

    @Override
    public void _initElements() {
    }

    @Override
    public void _drawElement() {
        if (timerUtils.delay(500) && this.isSelect) {
            this.isBlink = !isBlink;
            timerUtils.reset();
        }

        float textWidth = FontLoadersWithChinese.hongMengSR15.getStringWidth(this.value);
        boolean isNotOutline = this.parent.parent.parent.IsNotOutline;
        RenderUtil.drawRoundOutline(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 2f, isSelect ? 4f : 2f, new Color(41, 41, 41), isSelect ? new Color(69, 122, 247) : new Color(49, 49, 49)
                , isNotOutline);

        RenderUtil.doStencil(this.position.getX() + 6f, this.position.getY(), this.position.getWidth() - 12f, this.position.getHeight(), 4f);

        float boxWidth = this.position.getWidth() - 12f;
        float offset = ((textWidth - boxWidth) * (textWidth > boxWidth ? 1f : 0f));
        if (Objects.equals(this.value, "")) {
            FontLoadersWithChinese.hongMengSR15.drawString(this.elementName, this.position.getX() + 6f, this.position.getY() + 8f, new Color(93, 93, 93).getRGB());
        } else {
            FontLoadersWithChinese.hongMengSR15.drawString(this.value, this.position.getX() + 6f - offset, this.position.getY() + 8f, isSelect ? new Color(255, 255, 255).getRGB() : new Color(93, 93, 93).getRGB());
        }
        if (isBlink) {
            RenderUtil.drawRect(this.position.getX() + textWidth + 6f - offset, this.position.getY() + 6f, 0.5f, 9f, new Color(255, 255, 255).getRGB());
        }
        RenderUtil.uninstallStencil();
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            isSelect = true;
        } else {
            isSelect = false;
            isBlink = false;
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
        if (isSelect) {
            if ((int) p_keyTyped_1_ == 22) {
                String clippedText = getSysClipboardText();
                this.value += clippedText;
                return;
            }
            if (p_keyTyped_2_ == 28) {
                this.isBlink = false;
                this.isSelect = false;
                return;
            }
            if (p_keyTyped_2_ == 14) {
                if (value.length() > 0) {
                    value = value.substring(0, value.length() - 1);
                }
                return;
            }
            if (!isAChar(p_keyTyped_1_ + "")) {
                this.value += p_keyTyped_1_;
            }
        }
    }
}
