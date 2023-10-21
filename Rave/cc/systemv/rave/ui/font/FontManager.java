package cc.systemv.rave.ui.font;

import cc.systemv.rave.utils.client.FileUtil;
import cc.systemv.rave.utils.client.ResourceUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class FontManager {
    public static FontRenderer HarmonyOSbo20;
    public static FontRenderer HarmonyOSre20;
    public static FontRenderer HarmonyOSre18;
    public static FontRenderer HarmonyOSbo24;
    public static FontRenderer HarmonyOSbo18;
    public static FontRenderer HarmonyOSbo16;
    public static FontRenderer RobotoLight18;
    public static FontRenderer Verdana20;
    public static void init(){
        Minecraft.getMinecraft().addScheduledTask(() -> {
            long startTime = System.currentTimeMillis();

            try {
                byte[] harmonyOSbo = FileUtil.readInputStream(ResourceUtil.getResourceStream(new ResourceLocation("rave/font/harmonyosbo.ttf")));
                byte[] harmonyOSre = FileUtil.readInputStream(ResourceUtil.getResourceStream(new ResourceLocation("rave/font/harmonyosre.ttf")));
                byte[] RobotoLight = FileUtil.readInputStream(ResourceUtil.getResourceStream(new ResourceLocation("rave/font/RobotoLight.ttf")));
                byte[] Verdana = FileUtil.readInputStream(ResourceUtil.getResourceStream(new ResourceLocation("rave/font/Verdana.ttf")));
                HarmonyOSbo20 = loadTTF(harmonyOSbo,20,true);
                HarmonyOSbo24 = loadTTF(harmonyOSbo,24,true);
                HarmonyOSbo18 = loadTTF(harmonyOSbo,18,true);
                HarmonyOSbo16 = loadTTF(harmonyOSbo,16,true);
                HarmonyOSre20 = loadTTF(harmonyOSre,20,true);
                HarmonyOSre18 = loadTTF(harmonyOSre,18,true);
                RobotoLight18 = loadTTF(RobotoLight,18,true);
                Verdana20 = loadTTF(Verdana,20,true);
            } catch (Exception e) {
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
