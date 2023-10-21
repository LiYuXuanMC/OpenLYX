package com.reflectmc.reflect.features.modules.visual;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.render.EventRender2D;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.DoubleValue;
import com.reflectmc.reflect.features.values.ModeValue;
import com.reflectmc.reflect.features.values.OptionValue;
import com.reflectmc.reflect.notification.NotificationManager;
import com.reflectmc.reflect.utils.ClientUtil;
import com.reflectmc.reflect.utils.render.ColorUtils;
import com.reflectmc.reflect.utils.render.RenderUtil;
import com.reflectmc.reflect.utils.render.font.FontManager;
import com.reflectmc.reflect.utils.translation.TranslationManager;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.ScaledResolution;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HUD extends Module {
    private static final OptionValue renderLogo = new OptionValue("RenderLogo", true);
    private static final OptionValue renderArrayList = new OptionValue("RenderArrayList", true);
    private static final DoubleValue height = new DoubleValue("Height", 20, 9, 11, "0.0");
    private static final OptionValue importantModules = new OptionValue("Important", false);
    private static final ModeValue font = new ModeValue("Font", FontType.Custom, FontType.values());
    private static final ModeValue rainbow = new ModeValue("Color", color.Rainbow, color.values());
    private static final DoubleValue R = new DoubleValue("R", 255D, 0D, 100D, "0.0"){
        @Override
        public boolean show(){
            return rainbow.getValue() == color.Fade;
        }
    };
    private static final DoubleValue G = new DoubleValue("G", 255D, 0D, 100D, "0.0"){
        @Override
        public boolean show(){
            return rainbow.getValue() == color.Fade;
        }
    };
    private static final DoubleValue B = new DoubleValue("B", 255D, 0D, 100D, "0.0"){
        @Override
        public boolean show(){
            return rainbow.getValue() == color.Fade;
        }
    };
    private static final DoubleValue colorIndex = new DoubleValue("Color Seperation", 100, 5, 20, "0");
    private static final DoubleValue colorSpeed = new DoubleValue("Color Speed", 30, 2, 15, "0");
    private static final OptionValue background = new OptionValue("Background", true);
    private static final DoubleValue backgroundAlpha = new DoubleValue("Background Color", 1, .01, .35, "0.00"){
        @Override
        public boolean show(){
            return background.getValue();
        }
    };
    public static OptionValue renderNotification = new OptionValue("Notification", true);
    private final ModeValue language = new ModeValue("Language", TranslationManager.Language.English,TranslationManager.Language.values()){
        @Override
        public void onValueChange(Enum o,Enum n){
            translationManager.translateAll((TranslationManager.Language) n);
            hudRendermodules = null;
        }
    };
    public TranslationManager translationManager = new TranslationManager();
    public List<Module> hudRendermodules;
    public String longest = "";
    double longestWidth = 0;
    private final Comparator<Object> SORT_METHOD = Comparator.comparingDouble(m -> {
        Module module = (Module) m;
        String name = module.getDisplayName() + (module.getSuffix() != null ? " " + module.getSuffix() : "");
        return font.getValue() == FontType.Custom ?
                FontManager.Verdana20.getStringWidth(name)
                : mc.getFontRenderer().getStringWidth(name);
    }).reversed();
    public HUD() {
        super("HUD",Category.Visual);
        setEnableNoNotification(true);
        addValues(renderLogo,renderArrayList,height,importantModules,font,rainbow,R,G,B,colorIndex,colorSpeed,background,backgroundAlpha,renderNotification,
                language);
    }
    @EventTarget
    public void onRender2D(EventRender2D render2D){
        if (renderLogo.getValue()){
            int rainbowTick = 100;
            Color rainbow = new Color(Color.HSBtoRGB((float) ((double) Minecraft.getMinecraft().getThePlayer().getTicksExisted() / 50.0 + Math.sin((double) rainbowTick / 50.0 * 1.6)) % 2.0f, 0.5f, 1.0f));
            FontManager.Baloo30.drawString("R", 5, 5, rainbow.getRGB());
            FontManager.Baloo30.drawString("eflect", 5 + FontManager.Baloo30.getStringWidth("R"), 5, Color.white.getRGB());
            FontManager.Baloo20.drawString(Reflect.CLIENT_VERSION, 5 + FontManager.Baloo30.getStringWidth("Reflect") + 2, 0, Color.pink.getRGB());
            FontManager.Baloo20.drawString(Reflect.CLIENT_SUB_VERSION, 5 + FontManager.Baloo30.getStringWidth("Reflect") + 2, 10, Color.white.getRGB());
        }
        if (renderArrayList.getValue())
            renderArrayList();
        if (renderNotification.getValue()){
            NotificationManager.onRender2D(render2D);
        }
    }
    public void renderArrayList(){
        getModules();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        double yOffset = 0;
        int count = 0;
        for (Module module : hudRendermodules) {
            if (importantModules.getValue() && module.getCategory() == Category.Visual) continue;
            if (!module.isEnable()) continue;
            count++;
            String displayText = module.getDisplayName() + (module.getSuffix() != null ? " §7" + module.getSuffix() : "");
            double textWidth = font.getValue() == FontType.Custom ? FontManager.Verdana20.getStringWidth(displayText) : mc.getFontRenderer().getStringWidth(displayText);
            double heightVal = height.getValue() + 1;
            double x = sr.getScaledWidth() - (textWidth + 2);
            double y = yOffset + 3;
            module.getTranslate().interpolate((float) x, (float) y, 0.15);
            x = module.getTranslate().getX();
            y = module.getTranslate().getY();

            float alphaAnimation = 1;

            if (background.getValue()){
                RenderUtil.drawRect(x - 2, y - 3,x - 2 + textWidth + 5,y - 3 + heightVal
                        ,ColorUtils.applyOpacity(new Color(10, 10, 10),
                        backgroundAlpha.getValue().floatValue() * alphaAnimation).getRGB());
            }
            Color textcolor;
            int index = (int) (count * colorIndex.getValue());
            if (rainbow.getValue() == color.Rainbow) {
                textcolor = ColorUtils.rainbow(colorSpeed.getValue().intValue(), index, 1f, 1, 1);
            } else if (rainbow.getValue() == color.Fade) {
                textcolor = ColorUtils.fade(colorSpeed.getValue().intValue(), index, new Color(R.getValue().intValue(), G.getValue().intValue(), B.getValue().intValue()), 1);
            } else {
                textcolor = new Color(R.getValue().intValue(), G.getValue().intValue(), B.getValue().intValue());
            }
            if (font.getValue() == FontType.Custom) {
                FontManager.Verdana20.drawStringWithShadow(displayText, x, (y - 3) + FontManager.Verdana20.getMiddleOfBox((float) heightVal), ColorUtils.applyOpacity(textcolor.getRGB(), alphaAnimation));
            } else {
                mc.getFontRenderer().drawStringWithShadow(displayText, (float) x, (float) ((y - 3) + (heightVal / 2f - mc.getFontRenderer().getFontHeight() / 2f)), ColorUtils.applyOpacity(textcolor.getRGB(), alphaAnimation));
            }
            yOffset += heightVal;
        }
    }
    public void getModules() {
        if (hudRendermodules == null) {
            hudRendermodules = new ArrayList<>(Reflect.getINSTANCE().getModuleManager().getModules());
            hudRendermodules.sort(SORT_METHOD);
        }
    }
    enum color {
        None,
        Rainbow,
        Fade
    }
    enum FontType {
        Custom,
        Minecraft
    }
}
