package com.reflectmc.reflect.gui;

import com.reflectmc.loader.agent.ReflectNative;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.bridge.bridges.BridgeBase;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.*;
import com.reflectmc.reflect.resource.ResourceManager;
import com.reflectmc.reflect.utils.render.RenderUtil;
import com.reflectmc.reflect.utils.render.Translate;
import com.reflectmc.reflect.utils.render.font.FontManager;
import com.reflectmc.reflect.utils.render.font.ReflectUIFontReference;
import com.reflectmc.reflect.wrapper.wrappers.lwjgl.Mouse;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.ScaledResolution;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.GlStateManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public strictfp class ClickGuiReflect extends BridgeBase {

    private static final float width = 600f;
    private static final float height = 350f;
    private static final float typeWidth = 100f;
    private static final float typeInterval = 20f;
    private static final float typeHeight = 13f;
    private static final float imageSize = 13f;
    private static final float currentTypeHeight = 13f;
    private static final float moduleInterval = 4f;
    private static final float moduleHeight = 30f;
    private static final float starSize = 12f;
    private static final float foldSize = 12f;
    private static final float foldInterval = 2f;
    private static final float valueInterval = 4f;
    private static final float sliderWidth = 150f;
    private static final float sliderHeight = 2f;
    private static final float sliderRadius = sliderHeight / 2;
    private static final float modeInterval = 6f;
    private static final float modeSelectSize = 5f;

    private float startX;
    private float startY;
    private int wheel;

    private ScaledResolution resolution;

    private final Minecraft minecraft = Minecraft.getMinecraft();

    private boolean previousMouse = false;
    private Category currentType = Category.values()[0];
    private List<Module> unfoldModules = new ArrayList<Module>();
    private int scaleFactor;

    private static final Color grayBackground = new Color(225,225,225);
    private static final Color whiteType = Color.white;
    private static final Color textLightBlack = new Color(1,1,1,153);
    private static final Color selectedGray = new Color(219,219,219,102);
    private static final Color hoverGray = new Color(219,219,219,50);
    private static final Color cureentTypePink = new Color(228,107,255);
    private static final Color switchGray = new Color(176,176,176);
    private static final Color sliderGray = new Color(231,231,231);

    private Translate translate;
    private Translate typeTranslate;
    private Translate currentTypeTranslate;
    private Translate wheelTranslate;
    //-1 None 0 Min 1 Max
    private int minMaxSelect = -1;
    @Override
    public void initGui(){
        super.initGui();
        this.resolution = new ScaledResolution(minecraft);
        translate = new Translate((int) ((resolution.getScaledWidth() / 2) - (width / 2)), resolution.getScaledHeight());
        typeTranslate = new Translate(50,50);
        currentTypeTranslate = new Translate(Math.round(startX + 2f),Math.round((resolution.getScaledHeight() / 2) - (height/2)));
        wheelTranslate = new Translate(0, 0);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        scaleFactor = resolution.getScaleFactor();
        translate.interpolate(startX,Math.round((resolution.getScaledHeight() / 2) - (height/2)),1);
        startX = Math.round(translate.getX());
        startY = Math.round(translate.getY());
        renderBackground();
        RenderUtil.beginScissor(Math.round(startX),Math.round(startY),Math.round(width),Math.round(height));
        renderModuleType(mouseX,mouseY);
        float end = renderModules(mouseX,mouseY) + 10f;
        RenderUtil.endScissor();
        if (end < Math.round(startY + height)){
            wheel -= 2;
            wheelTranslate.interpolate(0,Math.round(wheel),1);
        }
        if (Math.round(startY - wheel) > startY){
            wheel += 2;
            wheelTranslate.interpolate(0,Math.round(wheel),1);
        }
        if (!Mouse.isButtonDown(0) && !Mouse.isButtonDown(1)) {
            this.previousMouse = false;
        }
    }
    private float renderModules(int mouseX,int mouseY){
        wheel -= Mouse.getDWheel();
        wheelTranslate.interpolate(0,Math.round(wheel),1);
        float moduleStartX = startX + (typeWidth) + 10;
        float moduleStartY = Math.round(startY + 10 - Math.round(wheelTranslate.getY()));
        for (Module module : Reflect.getINSTANCE().getModuleManager().getModules(currentType)) {
            moduleStartY  = Math.round(renderModule(module,moduleStartX,moduleStartY,mouseX,mouseY) + (moduleInterval));
        }
        return moduleStartY;
    }
    private float renderModule(Module module,float moduleStartX,float nowY,int mouseX,int mouseY) {
        float moduleY = nowY;
        module.switchTranslate.interpolate(module.isEnable() ? 15 : 5, 0, 2);
        RenderUtil.drawFastRoundedRect(Math.round(moduleStartX), Math.round(moduleY), Math.round(startX + (width) - 10), Math.round(moduleY + (moduleHeight)), 5f, whiteType.getRGB());
        /*RenderUtil.drawImage(module.isFavorite() ? ResourceManager.clickGuiFillStar.getResourceLocation() : ResourceManager.clickGuiStar.getResourceLocation()
                , Math.round(moduleStartX + 7), Math.round(moduleY + ((moduleHeight) / 2) - ((starSize) / 2))
                , Math.round(starSize), Math.round(starSize));
         */
        FontManager.UIFont18.drawString(module.isFavorite() ? ReflectUIFontReference.StarFill : ReflectUIFontReference.Star
                , Math.round(moduleStartX + 7), Math.round(moduleY + ((moduleHeight) / 2) - ((starSize) / 2)),Color.darkGray.getRGB());
        if (isHover(mouseX, mouseY, Math.round(moduleStartX + 7 - (starSize / 2) - 1),
                Math.round(moduleY + ((moduleHeight) / 2) - ((starSize) / 2) - 1), Math.round(moduleStartX + 7 - (starSize / 2) + 1),
                Math.round(moduleY + ((moduleHeight) / 2) - ((starSize) / 2) + 1))) {
            if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && !previousMouse) {
                this.previousMouse = true;
                module.setFavorite(!module.isFavorite());
            }
        }
        /*RenderUtil.drawImage(isUnfold(module) ? ResourceManager.clickGuiUp.getResourceLocation() : ResourceManager.clickGuiDown.getResourceLocation()
                , Math.round(startX + (width) - 30), Math.round(moduleY + ((moduleHeight) / 2) - ((foldSize) / 2)), Math.round(foldSize), Math.round(foldSize));
         */
        FontManager.UIFont10.drawString(isUnfold(module) ? ReflectUIFontReference.Up : ReflectUIFontReference.Down
                , Math.round(startX + (width) - 30), Math.round(moduleY + ((moduleHeight) / 2) - ((foldSize) / 2)),Color.darkGray.getRGB());
        if (isHover(mouseX, mouseY, Math.round(startX + (width) - 30 - 2), Math.round(moduleY + ((moduleHeight) / 2) - ((foldSize) / 2) - 2)
                , Math.round(startX + (width) - 30 + foldSize + 2), Math.round(moduleY + ((moduleHeight) / 2) - ((foldSize) / 2)) + foldSize + 2)) {
            if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && !previousMouse) {
                this.previousMouse = true;
                if (isUnfold(module)) {
                    unfoldModules.remove(module);
                } else {
                    for (Value<?> value : module.getValues()) {
                        if (value instanceof DoubleValue){
                            ((DoubleValue) value).translate = new Translate(0,0);
                        }
                    }
                    unfoldModules.add(module);
                }
            }
        }
        RenderUtil.drawFastRoundedRect(Math.round(startX + width - 30 - 30),Math.round(moduleY + ((moduleHeight)/2) - 4)
                ,Math.round(startX + width - 30 - 10),Math.round(moduleY + ((moduleHeight)/2) + 4),4,switchGray.getRGB());
        RenderUtil.drawFilledCircle(Math.round(startX + width - 30 - 30 + module.switchTranslate.getX()),Math.round(moduleY + ((moduleHeight)/2)),6
                ,module.isEnable() ? cureentTypePink.getRGB() : textLightBlack.getRGB());
        RenderUtil.drawFilledCircle(Math.round(startX + width - 30 - 30 + module.switchTranslate.getX()),Math.round(moduleY + ((moduleHeight)/2)),5,whiteType.getRGB());
        if (isHover(mouseX,mouseY,Math.round(startX + width - 30 - 30 - 1),Math.round(moduleY + ((moduleHeight)/2) - 5),
                Math.round(startX + width - 30 - 9),Math.round(moduleY + ((moduleHeight)/2) + 5))){
            if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && !previousMouse) {
                this.previousMouse = true;
                module.setEnable(!module.isEnable());
            }
        }
        FontManager.RobotoLight22.drawString(module.getDisplayName(),Math.round(moduleStartX + 7 + (starSize) + 8)
                ,Math.round(moduleY + ((moduleHeight)/2) - ((FontManager.RobotoLight22.getHeight())/2)),textLightBlack.getRGB());
        FontManager.RobotoLight18.drawString(module.getDescribe()
                ,Math.round(moduleStartX + 7 + (starSize) + 8 + FontManager.RobotoLight22.getStringWidth(module.getDisplayName()) + 10f)
                , Math.round(moduleY + ((moduleHeight)/2) - ((FontManager.RobotoLight18.getHeight())/2)),textLightBlack.getRGB());
        moduleY += (moduleHeight);
        if (isUnfold(module)){
            if (module.getValues().size() == 0){
                unfoldModules.remove(module);
                return moduleY + (moduleInterval);
            }
            moduleY += foldInterval;
            moduleY = renderValues(module,moduleStartX,moduleY,mouseX,mouseY);
        }
        return moduleY + (foldInterval);
    }
    private float renderValues(Module module,float moduleStartX,float nowY,int mouseX,int mouseY){
        float oldY = nowY;
        RenderUtil.drawFastRoundedRect(moduleStartX,nowY,Math.round(startX + (width) - 10),Math.round(nowY + mathHeight(module)),5,whiteType.getRGB());
        nowY += 5f;
        for (Value<?> value : module.getValues()) {
            nowY = renderValue(value,moduleStartX,nowY,mouseX,mouseY);
        }
        nowY += 5f;
        return oldY + mathHeight(module) + foldInterval;
    }
    private float mathHeight(Module module) {
        float height = 0f;
        for (Object o : module.getValues().stream().filter(v -> v.canShow()).toArray()) {
            Value v = (Value) o;
            if (v instanceof ModeValue) {
                //Math the height of the mode value
                float max = width - typeWidth - 40f;
                height += (15 + valueInterval);//Name render Height
                height += (15 + valueInterval);//Draw next line
                float width = 0f;
                for (Enum value : ((ModeValue) v).getValues()) {
                    float size = FontManager.RobotoLight16.getStringWidth(value.name()) + modeInterval + modeSelectSize + modeInterval;
                    if (width + size > max) {
                        height += (valueInterval + 15);
                        width = size;
                    } else {
                        width += size;
                    }
                }
            } else if (v instanceof MultiSelectValue){
                float max = width - typeWidth - 40f;
                height += (15 + valueInterval);//Name render Height
                height += (15 + valueInterval);//Draw next line
                float width = 0f;
                for (Enum value : ((MultiSelectValue) v).getValues()) {
                    float size = FontManager.RobotoLight16.getStringWidth(value.name()) + modeInterval + modeSelectSize + modeInterval;
                    if (width + size > max) {
                        height += (valueInterval + 15);
                        width = size;
                    } else {
                        width += size;
                    }
                }
            }else {
                height += (15 + valueInterval);
            }
        }
        return 10 + 10 + height;
    }
    private float renderValue(Value<?> value,float moduleStartX,float nowY,int mouseX,int mouseY){
        if (!value.canShow()) return nowY;
        if (value instanceof OptionValue){
            FontManager.RobotoLight18.drawString(value.getDisplayName(),Math.round(moduleStartX + 7f),Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight()/2)),textLightBlack.getRGB());
            if (((OptionValue) value).getValue()){
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 50),Math.round(nowY + 7.5 - 5),Math.round(startX + (width) - 40),Math.round(nowY + 7.5 + 5),1,cureentTypePink.getRGB());
                //RenderUtil.drawImage(ResourceManager.clickGuiTrue.getResourceLocation(),Math.round(startX + (width) - 48), (int) Math.round(nowY + 7.5 - 3),6,6);
                FontManager.UIFont14.drawString(ReflectUIFontReference.True,Math.round(startX + (width) - 50), (int) Math.round(nowY + 7.5 - 5),Color.white.getRGB());
            }else {
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 50),Math.round(nowY + 7.5 - 5),Math.round(startX + (width) - 40),Math.round(nowY + 7.5 + 5),1,textLightBlack.getRGB());
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 49),Math.round(nowY + 7.5 - 4),Math.round(startX + (width) - 41),Math.round(nowY + 7.5 + 4),1,whiteType.getRGB());
            }
            if (isHover(mouseX,mouseY,Math.round(startX + (width) - 50),Math.round(nowY + 7.5 - 6),Math.round(startX + (width) - 40),Math.round(nowY + 7.5 + 6))){
                if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && !previousMouse) {
                    this.previousMouse = true;
                    ((OptionValue) value).setValue(!((OptionValue) value).getValue());
                }
            }
            return nowY + 15 + valueInterval;
        }
        if (value instanceof NumberValue){
            if (value instanceof DoubleValue){
                FontManager.RobotoLight18.drawString(value.getDisplayName(),Math.round(moduleStartX + 7f),Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight()/2)),textLightBlack.getRGB());
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - sliderWidth),Math.round(nowY + 7.5 - sliderHeight),Math.round(startX + (width) - 40),Math.round(nowY + 7.5 + sliderHeight),sliderRadius,sliderGray.getRGB());
                float progress = (float) ((((DoubleValue) value).getValue() - ((DoubleValue) value).getMin())/ (((DoubleValue) value).getMax()-((DoubleValue) value).getMin()));
                ((NumberValue)value).translate.interpolate(Math.round(progress * sliderWidth),0,2);
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - sliderWidth),Math.round(nowY + 7.5 - sliderHeight),Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),Math.round(nowY + 7.5 + sliderHeight),sliderRadius,cureentTypePink.getRGB());
                RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),(int)Math.round(nowY + 7.5),4,cureentTypePink.getRGB());
                RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),(int)Math.round(nowY + 7.5),3,whiteType.getRGB());
                FontManager.RobotoLight16.drawString(((DoubleValue)value).getValue().toString()
                        , Math.round(startX + (width) - 40 - sliderWidth + ((DoubleValue)value).translate.getX() - (FontManager.RobotoLight16.getStringWidth(((DoubleValue)value).getValue().toString())/2))
                        , (int)Math.round(nowY + 7.5 - 5 - FontManager.RobotoLight16.getHeight()),textLightBlack.getRGB());
                if (isHover(mouseX,mouseY,Math.round(startX + (width) - 40 - sliderWidth - 4),Math.round(nowY + 7.5 - 5),Math.round(startX + (width) - 40 + 4),Math.round(nowY + 7.5 + 5))){
                    if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1))) {
                        this.previousMouse = true;
                        int clickLength = mouseX - Math.round(startX + (width) - 40 - sliderWidth);
                        if (clickLength < 0) clickLength = 0;
                        if (clickLength > sliderWidth) clickLength = Math.round(sliderWidth);
                        float clickProgress = clickLength / sliderWidth;
                        ((DoubleValue) value).setValue(((DoubleValue) value).getMin() + ((((DoubleValue) value).getMax() - ((DoubleValue) value).getMin()) * clickProgress));
                    }
                }
            }
            return nowY + 15 + valueInterval;
        }
        if (value instanceof ModeValue) {
            FontManager.RobotoLight18.drawString(value.getDisplayName(), Math.round(moduleStartX + 7f), Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight() / 2)), textLightBlack.getRGB());
            float yAddon = 15 + valueInterval;
            yAddon += 15 + valueInterval;
            float max = width - typeWidth - 40f;
            float width = 0f;
            for (Enum anEnum : ((ModeValue) value).getValues()) {
                float size = FontManager.RobotoLight16.getStringWidth(anEnum.name()) + modeInterval + modeSelectSize + modeInterval;
                if (width + size > max) {
                    yAddon += (valueInterval + 15);
                    width = size;
                } else {
                    width += size;
                }
                float renderX = moduleStartX + 10f + width - size;
                float renderY = nowY + yAddon - (15 + valueInterval);
                RenderUtil.drawCircle(renderX + (modeSelectSize / 2), renderY + 7.5, modeSelectSize, cureentTypePink.getRGB());
                RenderUtil.drawCircle(renderX + (modeSelectSize / 2), renderY + 7.5, modeSelectSize - 1, whiteType.getRGB());
                FontManager.RobotoLight16.drawString(anEnum.name(), renderX + (modeSelectSize) + modeInterval, Math.round(renderY + 7.5 - 1 - (FontManager.RobotoLight16.getHeight() / 2)), textLightBlack.getRGB());
                if (((ModeValue) value).getValue().equals(anEnum)) {
                    RenderUtil.drawCircle(renderX + (modeSelectSize / 2), renderY + 7.5, modeSelectSize - 2, cureentTypePink.getRGB());
                }
                if (isHover(mouseX, mouseY, renderX, renderY, renderX + size, renderY + 15)) {
                    if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1))) {
                        this.previousMouse = true;
                        ((ModeValue) value).setValue(anEnum);
                    }
                }
            }
            return nowY + yAddon;
        }
        if (value instanceof KeyBindValue) {
            FontManager.RobotoLight18.drawString(value.getDisplayName(), Math.round(moduleStartX + 7f), Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight() / 2)), textLightBlack.getRGB());
            RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - FontManager.RobotoLight16.getStringWidth(value.getValue().toString()) - 3), Math.round(nowY + 7.5 - (FontManager.RobotoLight16.getHeight() / 2) - 4), Math.round(startX + (width) - 40 + 3), Math.round(nowY + 7.5 + (FontManager.RobotoLight16.getHeight() / 2) + 4), 1, textLightBlack.getRGB());
            RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - FontManager.RobotoLight16.getStringWidth(value.getValue().toString()) - 2), Math.round(nowY + 7.5 - (FontManager.RobotoLight16.getHeight() / 2) - 3), Math.round(startX + (width) - 40 + 2), Math.round(nowY + 7.5 + (FontManager.RobotoLight16.getHeight() / 2) + 3), 1, whiteType.getRGB());
            FontManager.RobotoLight16.drawString(((KeyBindValue) value).getValue().getDisplayName(), Math.round(startX + (width) - 40 - FontManager.RobotoLight16.getStringWidth(((KeyBindValue) value).getValue().getDisplayName())), Math.round(nowY + 7.5 - (FontManager.RobotoLight16.getHeight() / 2)), textLightBlack.getRGB());
        }
        if (value instanceof MultiSelectValue){
            FontManager.RobotoLight18.drawString(value.getDisplayName(), Math.round(moduleStartX + 7f), Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight() / 2)), textLightBlack.getRGB());
            float yAddon = 15 + valueInterval;
            yAddon += 15 + valueInterval;
            float max = width - typeWidth - 40f;
            float width = 0f;
            for (Enum anEnum : ((MultiSelectValue) value).getValues()) {
                float size = FontManager.RobotoLight16.getStringWidth(anEnum.name()) + modeInterval + modeSelectSize + modeInterval;
                if (width + size > max) {
                    yAddon += (valueInterval + 15);
                    width = size;
                } else {
                    width += size;
                }
                float renderX = moduleStartX + 10f + width - size;
                float renderY = nowY + yAddon - (15 + valueInterval);
                RenderUtil.drawCircle(renderX + (modeSelectSize / 2), renderY + 7.5, modeSelectSize, cureentTypePink.getRGB());
                RenderUtil.drawCircle(renderX + (modeSelectSize / 2), renderY + 7.5, modeSelectSize - 1, whiteType.getRGB());
                FontManager.RobotoLight16.drawString(anEnum.name(), renderX + (modeSelectSize) + modeInterval, Math.round(renderY + 7.5 - 1 - (FontManager.RobotoLight16.getHeight() / 2)), textLightBlack.getRGB());
                if (isMultiSelectEnable((MultiSelectValue) value,anEnum)) {
                    RenderUtil.drawCircle(renderX + (modeSelectSize / 2), renderY + 7.5, modeSelectSize - 2, cureentTypePink.getRGB());
                }
                if (isHover(mouseX, mouseY, renderX, renderY, renderX + size, renderY + 15)) {
                    if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && !previousMouse) {
                        this.previousMouse = true;
                        ((MultiSelectValue) value).toggleValue(anEnum);
                    }
                }
            }
            return nowY + yAddon;
        }
        if (value instanceof MinMaxValue){
            FontManager.RobotoLight18.drawString(value.getDisplayName(),Math.round(moduleStartX + 7f),Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight()/2)),textLightBlack.getRGB());
            RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - sliderWidth),Math.round(nowY + 7.5 - sliderHeight),Math.round(startX + (width) - 40),Math.round(nowY + 7.5 + sliderHeight),sliderRadius,sliderGray.getRGB());
            float minProgress = (float) ((((MinMaxValue)value).getValue()[0] - ((MinMaxValue) value).getMin()) / (((MinMaxValue) value).getMax() - ((MinMaxValue) value).getMin()));
            float maxProgress = (float) ((((MinMaxValue)value).getValue()[1] - ((MinMaxValue) value).getMin()) / (((MinMaxValue) value).getMax() - ((MinMaxValue) value).getMin()));
            ((MinMaxValue) value).getMinTranslate().interpolate(Math.round(minProgress * sliderWidth),0,2);
            ((MinMaxValue) value).getMaxTranslate().interpolate(Math.round(maxProgress * sliderWidth),0,2);
            //RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - sliderWidth),Math.round(nowY + 7.5 - sliderHeight),Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),Math.round(nowY + 7.5 + sliderHeight),sliderRadius,cureentTypePink.getRGB());
            RenderUtil.drawFastRoundedRect(Math.round(startX + width - 40 - sliderWidth + ((MinMaxValue) value).getMinTranslate().getX()),Math.round(nowY + 7.5 - sliderHeight),
                    Math.round(startX + width - 40 - sliderWidth + ((MinMaxValue) value).getMaxTranslate().getX()),Math.round(nowY + 7.5 + sliderHeight),sliderRadius,cureentTypePink.getRGB());

            RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMinTranslate().getX()),(int)Math.round(nowY + 7.5),4,cureentTypePink.getRGB());
            RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMinTranslate().getX()),(int)Math.round(nowY + 7.5),3,whiteType.getRGB());
            int minX = Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMinTranslate().getX() - (FontManager.RobotoLight16.getStringWidth(((MinMaxValue)value).getValue()[0].toString())/2));
            FontManager.RobotoLight16.drawString(((MinMaxValue)value).getValue()[0].toString()
                    , minX
                    , (int)Math.round(nowY + 7.5 - 5 - FontManager.RobotoLight16.getHeight()),textLightBlack.getRGB());

            RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMaxTranslate().getX()),(int)Math.round(nowY + 7.5),4,cureentTypePink.getRGB());
            RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMaxTranslate().getX()),(int)Math.round(nowY + 7.5),3,whiteType.getRGB());
            int maxX = Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMaxTranslate().getX() - (FontManager.RobotoLight16.getStringWidth(((MinMaxValue)value).getValue()[1].toString())/2));
            if (maxX - minX < FontManager.RobotoLight16.getStringWidth(((MinMaxValue)value).getValue()[1].toString())){
                FontManager.RobotoLight16.drawString(((MinMaxValue)value).getValue()[1].toString()
                        , maxX
                        , (int)Math.round(nowY + 7.5 + 5),textLightBlack.getRGB());
            }else {
                FontManager.RobotoLight16.drawString(((MinMaxValue)value).getValue()[1].toString()
                        , maxX
                        , (int)Math.round(nowY + 7.5 - 5 - FontManager.RobotoLight16.getHeight()),textLightBlack.getRGB());
            }
            if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1))){
                //Hover min
                if (isHover(mouseX,mouseY,Math.round(startX + (width) - 40 - sliderWidth - 4),Math.round(nowY + 7.5 - 5),Math.round(startX + (width) - 40 + 4),Math.round(nowY + 7.5 + 5))){
                    if (!this.previousMouse){
                        if (isHover(mouseX,mouseY,
                                Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMinTranslate().getX() - 5),(int)Math.round(nowY + 7.5 - 5),
                                Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMinTranslate().getX() + 5),(int)Math.round(nowY + 7.5 + 5))){
                            minMaxSelect = 0;
                        }else if (isHover(mouseX,mouseY,
                                Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMaxTranslate().getX() - 5),(int)Math.round(nowY + 7.5 - 5),
                                Math.round(startX + (width) - 40 - sliderWidth + ((MinMaxValue) value).getMaxTranslate().getX() + 5),(int)Math.round(nowY + 7.5 + 5))){
                            minMaxSelect = 1;
                        }else {
                            if (mouseX < minX){
                                minMaxSelect = 0;
                            } else if (mouseX > minX && mouseX < maxX){
                                int inX = mouseX - minX;
                                float inProgress = (inX / ((maxX - minX) * 1.0F));
                                if (inProgress < 0.5){
                                    minMaxSelect = 0;
                                }else if (inProgress > 0.5){
                                    minMaxSelect = 1;
                                }else {
                                    //哥们你不会真的这么准点到1/2吧
                                    minMaxSelect = 0;
                                }
                            }else if (mouseX > maxX){
                                minMaxSelect = 1;
                            }
                        }
                        this.previousMouse = true;
                    }
                    int clickLength = mouseX - Math.round(startX + (width) - 40 - sliderWidth);
                    if (clickLength < 0) clickLength = 0;
                    if (clickLength > sliderWidth) clickLength = Math.round(sliderWidth);
                    float clickProgress = clickLength / sliderWidth;
                    Double[] oldValue = ((MinMaxValue) value).getValue();
                    ((MinMaxValue) value).setValue(new Double[]{
                            minMaxSelect == 0 ? ((MinMaxValue) value).getMin() + ((((MinMaxValue) value).getMax() - ((MinMaxValue) value).getMin()) * clickProgress) : oldValue[0],
                            minMaxSelect == 0 ? oldValue[1] : ((MinMaxValue) value).getMin() + ((((MinMaxValue) value).getMax() - ((MinMaxValue) value).getMin()) * clickProgress)
                    });
                }
            }else {
                //Clear select status
                minMaxSelect = -1;
            }
            //
            //            float progress = (float) ((((DoubleValue) value).getValue() - ((DoubleValue) value).getMin())/ (((DoubleValue) value).getMax()-((DoubleValue) value).getMin()));
//            ((NumberValue)value).translate.interpolate(Math.round(progress * sliderWidth),0,2);
//            RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - sliderWidth),Math.round(nowY + 7.5 - sliderHeight),Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),Math.round(nowY + 7.5 + sliderHeight),sliderRadius,cureentTypePink.getRGB());
//            RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),(int)Math.round(nowY + 7.5),4,cureentTypePink.getRGB());
//            RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),(int)Math.round(nowY + 7.5),3,whiteType.getRGB());
//            FontManager.RobotoLight16.drawString(((DoubleValue)value).getValue().toString()
//                    , Math.round(startX + (width) - 40 - sliderWidth + ((DoubleValue)value).translate.getX() - (FontManager.RobotoLight16.getStringWidth(((DoubleValue)value).getValue().toString())/2))
//                    , (int)Math.round(nowY + 7.5 - 5 - FontManager.RobotoLight16.getHeight()),textLightBlack.getRGB());
//            if (isHover(mouseX,mouseY,Math.round(startX + (width) - 40 - sliderWidth - 4),Math.round(nowY + 7.5 - 5),Math.round(startX + (width) - 40 + 4),Math.round(nowY + 7.5 + 5))){
//                if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1))) {
//                    this.previousMouse = true;
//                    int clickLength = mouseX - Math.round(startX + (width) - 40 - sliderWidth);
//                    if (clickLength < 0) clickLength = 0;
//                    if (clickLength > sliderWidth) clickLength = Math.round(sliderWidth);
//                    float clickProgress = clickLength / sliderWidth;
//                    ((DoubleValue) value).setValue(((DoubleValue) value).getMin() + ((((DoubleValue) value).getMax() - ((DoubleValue) value).getMin()) * clickProgress));
//                }
//            }
            return nowY + 15 + valueInterval;
        }
        return nowY;
    }

    private boolean isMultiSelectEnable(MultiSelectValue v,Enum<?> e) {
        for (Enum<?> anEnum : v.getValue()) {
            if (anEnum == e) return true;
        }
        return false;
    }
    private void renderModuleType(int mouseX, int mouseY){
        float typeStartX = startX + 6f;
        float typeStartY = startY + 30f + 20f;
        for (Category value : Category.values()) {
            if (isHover(mouseX,mouseY,typeStartX - 4f,typeStartY,startX + typeWidth -2f,typeStartY + typeHeight)){
                if (currentType != value){
                    RenderUtil.drawFastRoundedRect(Math.round(typeStartX - 2f),Math.round(typeStartY),Math.round(startX+typeWidth-2f)
                            ,Math.round(typeStartY+typeHeight),3f,hoverGray.getRGB());
                }
                if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && !previousMouse) {
                    this.previousMouse = true;
                    if (currentType != value){
                        currentType = value;
                        unfoldModules.clear();
                        wheel = 0;
                        typeTranslate = new Translate(50,50);
                    }
                }
            }
            if (currentType == value){
                typeTranslate.interpolate(102,102,2);
                currentTypeTranslate.interpolate(Math.round(startX + 2f),Math.round(typeStartY + ((typeHeight/2))),4);
                Color nowGray = new Color(selectedGray.getRed(),selectedGray.getGreen(),selectedGray.getBlue(),(int) typeTranslate.getX());
                RenderUtil.drawFastRoundedRect(Math.round(typeStartX - 2f),Math.round(typeStartY),Math.round(startX+typeWidth-2f)
                        ,Math.round(typeStartY+typeHeight),3f,nowGray.getRGB());
            }
            /*RenderUtil.drawImageAntiAlias(value.getByteImageLocation().getResourceLocation(),
                    Math.round(typeStartX), Math.round(typeStartY + (typeHeight / 2) - (imageSize / 2)),
                    Math.round(imageSize), Math.round(imageSize));
             */
            FontManager.UIFont18.drawString(value.getIcon(),Math.round(typeStartX), Math.round(typeStartY + (typeHeight / 2) - (FontManager.UIFont18.getHeight() / 2)),Color.darkGray.getRGB());
            GlStateManager.resetColor();
            FontManager.RobotoLight18.drawString(value.name(), Math.round(typeStartX + 2f + (imageSize))
                    , Math.round(typeStartY + ((typeHeight) / 2) - (FontManager.RobotoLight18.getHeight() / 2)), textLightBlack.getRGB());
            typeStartY += ((typeInterval) + (typeHeight));
        }
        RenderUtil.drawRect(Math.round(currentTypeTranslate.getX() + 3), Math.round(currentTypeTranslate.getY() - Math.round((currentTypeHeight) / 2) + 2)
                , Math.round(currentTypeTranslate.getX() + 2f + 3), Math.round(currentTypeTranslate.getY() + Math.round((currentTypeHeight) / 2) - 2), cureentTypePink.getRGB());
    }

    private void renderInformation() {

    }

    private boolean isHover(int mouseX, int mouseY, float left, float top, float right, float bottom) {
        return mouseX > left && mouseX < right && mouseY > top && mouseY < bottom;
    }

    private boolean isUnfold(Module module) {
        return unfoldModules.contains(module);
    }

    private void renderBackground() {
        RenderUtil.drawFastRoundedRect(Math.round(startX), Math.round(startY), Math.round(startX + width), Math.round(startY + height), 5, grayBackground.getRGB());
        RenderUtil.drawFastRoundedRect(Math.round(startX),Math.round(startY),Math.round(startX + typeWidth),Math.round(startY + height),5,whiteType.getRGB());
        RenderUtil.drawImage(ResourceManager.logo.getResourceLocation(),Math.round(startX + (typeWidth/ 2) - (49/2)),Math.round(startY+1),49,29);
    }
}
