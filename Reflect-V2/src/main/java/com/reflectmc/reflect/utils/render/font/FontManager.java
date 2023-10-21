package com.reflectmc.reflect.utils.render.font;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class FontManager {
    public static FontRenderer Baloo30;
    public static FontRenderer Baloo20;
    public static FontRenderer Verdana20;
    public static FontRenderer Verdana18;
    public static FontRenderer Verdana16;
    public static FontRenderer Verdana14;
    public static FontRenderer Arial40;
    public static FontRenderer RobotoLight22;
    public static FontRenderer RobotoLight18;
    public static FontRenderer RobotoLight16;
    public static FontRenderer RobotoLight24;
    public static FontRenderer UIFont10;
    public static FontRenderer UIFont14;
    public static FontRenderer UIFont18;
    public static FontRenderer UIFont30;
    public static void init(){
        Minecraft.getMinecraft().addScheduledTask(() -> {
            long startTime = System.currentTimeMillis();
            byte[] fontBaloo = Reflect.getINSTANCE().getResourceManager().getResource("Baloo.ttf").getBuffer().array();
            byte[] fontVerdana = Reflect.getINSTANCE().getResourceManager().getResource("Verdana.ttf").getBuffer().array();
            byte[] fontArial = Reflect.getINSTANCE().getResourceManager().getResource("Arial.ttf").getBuffer().array();
            byte[] fontRobotoLight = Reflect.getINSTANCE().getResourceManager().getResource("RobotoLight.ttf").getBuffer().array();
            byte[] fontUIFont = Reflect.getINSTANCE().getResourceManager().getResource("ReflectUIFont.ttf").getBuffer().array();
            try {
                Baloo30 = loadTTF(fontBaloo,30,true);
                Baloo20 = loadTTF(fontBaloo,20,true);
                Verdana20 = loadTTF(fontVerdana,20,true);
                Verdana18 = loadTTF(fontVerdana,18,true);
                Verdana16 = loadTTF(fontVerdana,16,true);
                Verdana14 = loadTTF(fontVerdana,14,true);
                Arial40 = loadTTF(fontArial,40,true);
                RobotoLight22 = loadTTF(fontRobotoLight,22,true);
                RobotoLight18 = loadTTF(fontRobotoLight,18,true);
                RobotoLight16 = loadTTF(fontRobotoLight,16,true);
                RobotoLight24 = loadTTF(fontRobotoLight,24,true);
                UIFont10 = loadTTF(fontUIFont,10,true);
                UIFont14 = loadTTF(fontUIFont,14,true);
                UIFont18 = loadTTF(fontUIFont,18,true);
                UIFont30 = loadTTF(fontUIFont,30,true);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("Font total time "+totalTime+" ms");
        });
    }
    private static FontRenderer loadTTF(byte[] bytes,int size,boolean antiAlias) throws IOException, FontFormatException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Font font = Font.createFont(Font.TRUETYPE_FONT,bais);
        FontRenderer fr = new FontRenderer(font,size,antiAlias);
        return fr;
    }
}
