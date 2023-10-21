package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender2D;
import al.nya.reflect.events.events.EventText;
import al.nya.reflect.events.events.client.EventShader;
import al.nya.reflect.features.modules.Combat.AntiBot;
import al.nya.reflect.features.modules.Combat.SuperKnockBack;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.Player.AutoTool;
import al.nya.reflect.features.modules.Visual.hud.HUDObject;
import al.nya.reflect.features.modules.Visual.hud.implement.ScoreboardHUD;
import al.nya.reflect.features.modules.World.GodBridgeHelper;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.utils.StringUtil;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.utils.render.RoundedUtil;
import al.nya.reflect.utils.render.font.FontManager;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.render.FontRenderer;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HUD extends Module {
    public static ScoreboardHUD scoreboardHUD;
    public static ArrayList<HUDObject> hudObjects = new ArrayList<>();
    private static final ModeValue theme = new ModeValue("Theme", Theme.Reflect, Theme.values());
    private static final OptionValue renderLogo = new OptionValue("RenderLogo", true) {
        @Override
        public boolean show() {
            return theme.getValue() == Theme.Reflect;
        }
    };
    private static final OptionValue renderArrayList = new OptionValue("RenderArrayList", true) {
        @Override
        public boolean show() {
            return theme.getValue() == Theme.Reflect;
        }
    };
    private static final OptionValue importantModules = new OptionValue("Important", true) {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && theme.getValue() == Theme.Reflect;
        }
    };
    private static final DoubleValue height = new DoubleValue("Height", 20, 9, 11, "0.0") {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && theme.getValue() == Theme.Reflect;
        }
    };
    private static final ModeValue font = new ModeValue("Font", FontType.Custom, FontType.values()) {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && theme.getValue() == Theme.Reflect;
        }
    };
    private static final ModeValue rainbow = new ModeValue("Color", color.Rainbow, color.values()) {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && theme.getValue() == Theme.Reflect;
        }
    };
    private static final DoubleValue R = new DoubleValue("R", 255D, 0D, 100D, "0.0") {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && !(rainbow.getValue() == color.Rainbow) && theme.getValue() == Theme.Reflect;
        }
    };
    private static final DoubleValue G = new DoubleValue("G", 255D, 0D, 100D, "0.0") {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && !(rainbow.getValue() == color.Rainbow) && theme.getValue() == Theme.Reflect;
        }
    };
    private static final DoubleValue B = new DoubleValue("B", 255D, 0D, 100D, "0.0") {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && !(rainbow.getValue() == color.Rainbow) && theme.getValue() == Theme.Reflect;
        }
    };

    private static final DoubleValue colorIndex = new DoubleValue("Color Seperation", 100, 5, 20, "0") {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && !(rainbow.getValue() == color.None) && theme.getValue() == Theme.Reflect;
        }
    };
    private static final DoubleValue colorSpeed = new DoubleValue("Color Speed", 30, 2, 15, "0") {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && !(rainbow.getValue() == color.None) && theme.getValue() == Theme.Reflect;
        }
    };
    private static final OptionValue background = new OptionValue("Background", true) {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && theme.getValue() == Theme.Reflect;
        }
    };
    private static final DoubleValue backgroundAlpha = new DoubleValue("Background Color", 1, .01, .35, "0.00") {
        @Override
        public boolean show() {
            return renderArrayList.getValue() && background.getValue() && theme.getValue() == Theme.Reflect;
        }
    };


    public static OptionValue renderNotification = new OptionValue("Notification", true) {
        @Override
        public boolean show() {
            return theme.getValue() == Theme.Reflect;
        }
    };
    public static OptionValue renderuser = new OptionValue("RenderUser", true) {
        @Override
        public boolean show() {
            return theme.getValue() == Theme.Reflect;
        }
    };
    public static OptionValue fixUnicode = new OptionValue("FixUnicode", false);
    public static OptionValue betterFont = new OptionValue("BetterFont", false);

    public HUD() {
        super("HUD", ModuleType.Visual);
        setEnableNoNotification(true);
        addValues(theme, renderLogo, renderArrayList, importantModules, height, font, rainbow, R, G, B, colorIndex, colorSpeed, background, backgroundAlpha, renderNotification, renderuser, fixUnicode, betterFont);
    }

    private final Comparator<Object> SORT_METHOD = Comparator.comparingDouble(m -> {
        Module module = (Module) m;
        String name = module.getName() + (module.getSuffix() != null ? " " + module.getSuffix() : "");
        return font.getValue() == FontType.Custom ?
                FontManager.Verdana20.getStringWidth(name)
                : mc.getFontRenderer().getStringWidth(name);
    }).reversed();

    @EventTarget
    public void render2D(EventRender2D evt) {
        if (!this.mc.getGameSettings().getForceUnicodeFont() && fixUnicode.getValue()) // 修复中文模式下英文字符过小问题
            this.mc.getFontRenderer().setUnicodeFlag(false);
        if (theme.getValue() == Theme.Vapu) {
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glTranslated(10, 10, -2000.0);

            FontRenderer fontRenderer = mc.getFontRenderer();

            int y = -10;
            fontRenderer.drawStringWithShadow(String.format(" %s: %s", "auto sneak", "off"), 0, y += 10, 0xF1F2F3);
            fontRenderer.drawStringWithShadow(String.format(" %s: %s", "god bridge", ModuleManager.getModule(GodBridgeHelper.class).isEnable() ? "on" : "off"), 0, y += 10, 0xF1F2F3);
            fontRenderer.drawStringWithShadow(String.format(" %s: %s", "anti Bot", ModuleManager.getModule(AntiBot.class).isEnable() ? "on" : "off"), 0, y += 10, 0xF1F2F3);
            fontRenderer.drawStringWithShadow(String.format(" %s: %s", "autoTool ", ModuleManager.getModule(AutoTool.class).isEnable() ? "on" : "off"), 0, y += 10, 0xF1F2F3);
            fontRenderer.drawStringWithShadow(String.format(" %s: %s", "autoWeapon ", "off"), 0, y += 10, 0xF1F2F3);
            fontRenderer.drawStringWithShadow(String.format(" %s: %s", "waterFly ", "off"), 0, y += 10, 0xF1F2F3);
            fontRenderer.drawStringWithShadow(String.format(" %s: %s", "knockback ", ModuleManager.getModule(SuperKnockBack.class).isEnable() ? "on" : "off"), 0, y += 10, 0xF1F2F3);
            fontRenderer.drawStringWithShadow(String.format(" %s: %s", "bedFirst ", "off"), 0, y += 10, 0xF1F2F3);
            GL11.glPopMatrix();
            return;
        }
        if (renderLogo.getValue()) renderLogo();
        if (renderArrayList.getValue()) renderArrayList();
        if (renderNotification.getValue())
            NotificationPublisher.publish(new ScaledResolution(Minecraft.getMinecraft()));
        if (true) renderUser();

        for (HUDObject hudObject : hudObjects) {
            if (hudObject.doDraw()) {
                hudObject.draw(evt.getPartialTicks());
            }
        }

//        FontManager.MCFONT.drawString("すみません",20,20,Color.WHITE.getRGB(),false);
//        FontManager.MCFONT.drawString("§1死§2去§3的§4矩§5阵§6突§7然§8开§9始§a攻§b击§c我",20,40,Color.WHITE.getRGB(),false);
//        FontManager.MCFONT.drawString("我看起来有阴影",20,80,Color.WHITE.getRGB(),false);
//        FontManager.MCFONT.drawString("别在这理发店",20,100,Color.WHITE.getRGB(),false);
//        FontManager.MCFONT.drawString("上一句话的长度是:" + FontManager.MCFONT.getStringWidth("别在这理发店"),20,120,Color.WHITE.getRGB(),false);
//        Minecraft.getMinecraft().getFontRenderer().drawString("uwu " + FontManager.MCFONT.getStringWidth("别在这理发店"),20,140,Color.WHITE.getRGB());

    }

    public void renderUser() {
        ScaledResolution sr = new ScaledResolution(mc);
        FontManager.Baloo15.drawStringWithShadow("[" + Reflect.USER.rank + "]\u00a77" + Reflect.USER.name, 0, sr.getScaledHeight() - FontManager.Baloo15.getHeight(), Color.pink.getRGB());
    }

    public static void visibleWaterMark() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        //FontManager.Verdana14.drawString(String.valueOf(Reflect.USER.qq), sr.getScaledWidth() - FontManager.Verdana14.getStringWidth(String.valueOf(Reflect.USER.qq)), sr.getScaledHeight() / 2, new Color(0, 0, 0, 2).getRGB());
    }

    public void renderLogo() {
        int rainbowTick = 100;
        Color rainbow = new Color(Color.HSBtoRGB((float) ((double) Minecraft.getMinecraft().getThePlayer().getTicksExisted() / 50.0 + Math.sin((double) rainbowTick / 50.0 * 1.6)) % 2.0f, 0.5f, 1.0f));
        FontManager.Baloo30.drawString("R", 5, 5, rainbow.getRGB());
        FontManager.Baloo30.drawString("eflect", 5 + FontManager.Baloo30.getStringWidth("R"), 5, Color.white.getRGB());
        FontManager.Baloo15.drawString(Reflect.CLIENT_VERSION, 5 + FontManager.Baloo30.getStringWidth("Reflect") + 2, 5, Color.pink.getRGB());
        FontManager.Baloo15.drawString(Reflect.CLIENT_SUB_VERSION, 5 + FontManager.Baloo30.getStringWidth("Reflect") + 2, FontManager.Baloo15.getStringHeight(Reflect.CLIENT_VERSION), Color.white.getRGB());
    }

    public List<Module> hudRendermodules;
    public String longest = "";
    double longestWidth = 0;

    @EventTarget
    public void onRenderString(EventText e) {
        if (!betterFont.getValue()) return;
        if (e.isRender()) {
//            FontManager.MCFONT.renderString(e.getText() + " " + e.isDropShadow() + " " + e.getColor(),20,y+=10,e.getColor(),false);
            e.setReturnValue((int) FontManager.MCFONT.drawString(e.getText(), e.getX(), e.getY(), e.getColor(), e.isDropShadow()));
            e.setCancel(true);
        } else {
            e.setReturnValue(FontManager.MCFONT.getStringWidth(e.getText()));
            e.setCancel(true);
        }
    }

    @EventTarget
    public void onBlur(EventShader event) {
        for (HUDObject hudObject : hudObjects) {
            if (hudObject.doDraw()) {
                hudObject.onBlur(event);
            }
        }
        if (renderArrayList.getValue()) {
            renderArrayListBlur();
        }
    }

    public void renderArrayList() {
        getModules();
        hudRendermodules.sort(SORT_METHOD);
        if (!StringUtil.getToggledModules(hudRendermodules).isEmpty()) {
            Module firstMod = StringUtil.getToggledModules(hudRendermodules).get(0);
            longest = firstMod.getName() + (firstMod.getSuffix() != null ? " " + firstMod.getSuffix() : "");
            longestWidth = font.getValue() == FontType.Custom ? FontManager.Verdana20.getStringWidth(longest)
                    : mc.getFontRenderer().getStringWidth(longest);
        }
        double yOffset = 0;
        ScaledResolution sr = new ScaledResolution(mc);
        int count = 0;
        for (Module module : hudRendermodules) {
            if (importantModules.getValue() && module.getModuleType() == ModuleType.Visual) continue;

//            moduleAnimation.setDirection(module.isToggled() ? Direction.FORWARDS : Direction.BACKWARDS);
//
//            if (!module.isEnable() && moduleAnimation.finished(Direction.BACKWARDS)) continue;

            if (!module.isEnable()) continue;

            String displayText = module.getName() + (module.getSuffix() != null ? " §7" + module.getSuffix() : "");
            double textWidth = font.getValue() == FontType.Custom ?
                    FontManager.Verdana20.getStringWidth(displayText)
                    : mc.getFontRenderer().getStringWidth(displayText);

            double xValue = sr.getScaledWidth() - (2);


            boolean flip = xValue <= sr.getScaledWidth() / 2f;
            double x = flip ? xValue : sr.getScaledWidth() - (textWidth + 2);


            float alphaAnimation = 1;

            double y = yOffset + 3;

            double heightVal = height.getValue() + 1;

            module.getTranslate().interpolate((float) x, (float) y, 0.15);

            x = module.getTranslate().getX();
            y = module.getTranslate().getY();

            if (background.getValue()) {
                RenderUtil.drawRect2(x - 2, y - 3,
                        (font.getValue() == FontType.Custom ?
                                FontManager.Verdana20.getStringWidth(displayText)
                                : mc.getFontRenderer().getStringWidth(displayText)) + 5, heightVal,
                        ColorUtils.applyOpacity(new Color(10, 10, 10),
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
            count++;
        }


    }

    public void renderArrayListBlur() {
        if (hudRendermodules == null) return;
        double yOffset = 0;
        ScaledResolution sr = new ScaledResolution(mc);
        for (Module module : hudRendermodules) {
            if (importantModules.getValue() && module.getModuleType() == ModuleType.Visual) continue;

//            moduleAnimation.setDirection(module.isToggled() ? Direction.FORWARDS : Direction.BACKWARDS);
//
//            if (!module.isEnable() && moduleAnimation.finished(Direction.BACKWARDS)) continue;

            if (!module.isEnable()) continue;

            String displayText = module.getName() + (module.getSuffix() != null ? " §7" + module.getSuffix() : "");
            double textWidth = font.getValue() == FontType.Custom ? FontManager.Verdana20.getStringWidth(displayText)
                    : mc.getFontRenderer().getStringWidth(displayText);

            double xValue = sr.getScaledWidth() - (2);


            boolean flip = xValue <= sr.getScaledWidth() / 2f;
            double x = flip ? xValue : sr.getScaledWidth() - (textWidth + 2);


            double y = yOffset + 3;

            double heightVal = height.getValue() + 1;

            x = module.getTranslate().getX();
            y = module.getTranslate().getY();
            RoundedUtil.drawRound((float) (x - 2), (float) (y - 3), (float) ((font.getValue() == FontType.Custom ? FontManager.Verdana20.getStringWidth(displayText) : mc.getFontRenderer().getStringWidth(displayText)) + 5), (float) heightVal, 0, false, Color.WHITE);
            yOffset += heightVal;
        }
    }

    public void getModules() {
        if (hudRendermodules == null) {
            hudRendermodules = new ArrayList<>(ModuleManager.getModules());
        }
    }

    enum color {
        None,
        Rainbow,
        Fade
    }

    enum Theme {
        Reflect,
        Vapu
    }

    enum FontType {
        Custom,
        Minecraft
    }

}

