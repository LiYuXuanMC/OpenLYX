package al.logger.client.utils.fontRender;

import al.logger.client.Logger;
import al.logger.client.ui.managers.GuiManager;

import java.awt.*;
import java.io.InputStream;

public abstract class FontLoadersWithChinese {

    public static ChineseFontRenderer intersemibold15, hongMengBlod22, hongMengSR14, hongMengSR15, hongMengSR17, hongMengSR14S, hongMengBold14S, hongMengBlod17, hongMengBlod20;

    public static Font fontFromTTF(String fontLocation, float fontSize, int fontType) {
        Font output = null;
        try {
            InputStream is = Logger.getInstance().resourceManager.getInputStream(fontLocation);
            output = Font.createFont(fontType, is);
            output = output.deriveFont(fontType, fontSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void loadFonts() {
        FontLoadersWithChinese.hongMengBold14S = new ChineseFontRenderer(fontFromTTF("harmonyosbo.ttf", 14, Font.PLAIN), true, true, true, 0, 1);
        FontLoadersWithChinese.hongMengBlod20 = new ChineseFontRenderer(fontFromTTF("harmonyosbo.ttf", 20, Font.PLAIN), true, true, true, 0, 1);
        FontLoadersWithChinese.hongMengSR14S = new ChineseFontRenderer(fontFromTTF("harmonyosre.ttf", 14, Font.PLAIN), true, true, true, 0, 1);
        FontLoadersWithChinese.hongMengSR15 = new ChineseFontRenderer(fontFromTTF("harmonyosre.ttf", 15, Font.PLAIN), true, true, true, 0, 1);
        FontLoadersWithChinese.hongMengSR14 = new ChineseFontRenderer(fontFromTTF("harmonyosre.ttf", 13, Font.PLAIN), true, true, true, 0, 1);
        FontLoadersWithChinese.hongMengBlod22 = new ChineseFontRenderer(fontFromTTF("harmonyosbo.ttf", 22, Font.PLAIN), true, true, true, 0, 1);
        FontLoadersWithChinese.hongMengBlod17 = new ChineseFontRenderer(fontFromTTF("harmonyosbo.ttf", 17, Font.PLAIN), true, true, true, 0, 1);
        FontLoadersWithChinese.intersemibold15 = new ChineseFontRenderer(fontFromTTF("intersemibold.ttf", 15, Font.PLAIN), true, true, true, 0, 1);
        FontLoadersWithChinese.hongMengSR17 = new ChineseFontRenderer(fontFromTTF("harmonyosre.ttf", 17, Font.PLAIN), true, true, true, 0, 1);
        Logger.getInstance().guiManager = new GuiManager();
    }
}
