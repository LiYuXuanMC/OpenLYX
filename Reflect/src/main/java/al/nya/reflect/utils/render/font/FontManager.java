package al.nya.reflect.utils.render.font;


import al.nya.reflect.Reflect;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.resource.ResourceManager;
import al.nya.reflect.utils.render.font.reflect.FontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FontManager {

    public static FontRenderer MCFONT;
    public static FontRenderer Baloo30;
    public static FontRenderer Baloo15;
    public static FontRenderer Comfortaa20;
    public static FontRenderer Comfortaa30;
    public static FontRenderer arial40;
    public static FontRenderer arial15;
    public static FontRenderer arial20;
    public static FontRenderer Verdana14;
    public static FontRenderer Verdana16;
    public static FontRenderer Verdana18;
    public static FontRenderer Verdana20;
    public static FontRenderer RobotoLight18;
    public static FontRenderer RobotoLight25;
    public static FontRenderer RobotoLight22;
    public static FontRenderer RobotoLight16;
    public static boolean shouldLoad = false;
    public static boolean loadFinish = false;

    public static Font fontFromTTF(InputStream stream) {
        Font output = null;
        try {
            output = Font.createFont(Font.PLAIN, stream);
            output = output.deriveFont(72f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static Font fontFromTTF(InputStream stream, float fontSize, int fontType) {//Font.PLAIN
        Font output = null;
        try {
            output = Font.createFont(fontType, stream);
            output = output.deriveFont(fontSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void init() {
        System.out.println("Init Font");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    MCFONT = new FontRenderer("微软雅黑", 18, true);
                    Baloo30 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Baloo.ttf")), 30, Font.PLAIN), true, true);
                    Baloo15 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Baloo.ttf")), 23, Font.PLAIN), true, true);
                    Comfortaa20 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Comfortaa.ttf")), 20, Font.PLAIN), true, true);
                    Comfortaa30 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Comfortaa.ttf")), 30, Font.PLAIN), true, true);
                    arial40 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Arial.ttf")), 40, Font.PLAIN), true, true);
                    arial15 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Arial.ttf")), 15, Font.PLAIN), true, true);
                    arial20 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Arial.ttf")), 20, Font.PLAIN), true, true);
                    Verdana14 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Verdana.ttf")), 14, Font.PLAIN), true, true);
                    Verdana18 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Verdana.ttf")), 18, Font.PLAIN), true, true);
                    Verdana16 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Verdana.ttf")), 16, Font.PLAIN), true, true);
                    Verdana20 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("Verdana.ttf")), 20, Font.PLAIN), true, true);
                    RobotoLight18 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("RobotoLight.ttf")), 18, Font.PLAIN), true, true);
                    RobotoLight25 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("RobotoLight.ttf")), 25, Font.PLAIN), true, true);
                    RobotoLight22 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("RobotoLight.ttf")), 22, Font.PLAIN), true, true);
                    RobotoLight16 = new FontRenderer(fontFromTTF(new ByteArrayInputStream(ResourceManager.getRes("RobotoLight.ttf")), 16, Font.PLAIN), true, true);
                    System.out.println("Font finish");
                    loadFinish = true;
                    Reflect.Instance.eventBus.init = true;
                    Reflect.loading.setLoadingType("Done");
                    Reflect.loading.finishLoading();
                    long totalTime = System.currentTimeMillis() - Reflect.startTime;
                    NotificationPublisher.queue("Reflect", "Inject finish! Please press Insert to open ClickGui.(Total " + (totalTime / 1000) + "s)", 5000, NotificationType.SUCCESS);
                });
            }
        }).start();
        //ReflectLoading.finishLoading();
    }
}
