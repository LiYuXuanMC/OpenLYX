package al.nya.reflect.gui.clickgui.reflect;

import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.Visual.AntiScreenshot;
import al.nya.reflect.resource.ResourceManager;
import al.nya.reflect.utils.LWJGLMouse;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.utils.render.Translate;
import al.nya.reflect.utils.render.font.FontManager;
import al.nya.reflect.value.*;
import al.nya.reflect.wrapper.wraps.impl.GuiScreenImpl;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public strictfp class Reflect extends GuiScreenImpl {

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
    private float wheel;

    private ScaledResolution resolution;

    private Minecraft minecraft;

    private boolean previousMouse = false;
    private ModuleType currentType = ModuleType.values()[0];
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
    @Override
    public void initGui(){
        super.initGui();
        this.minecraft = Minecraft.getMinecraft();
        this.resolution = new ScaledResolution(minecraft);
        translate = new Translate((int) ((resolution.getScaledWidth() / 2) - (width / 2)), resolution.getScaledHeight());
        typeTranslate = new Translate(50,50);
        currentTypeTranslate = new Translate(Math.round(startX + 2f),Math.round((resolution.getScaledHeight() / 2) - (height/2)));
        wheelTranslate = new Translate(0, 0);
        al.nya.reflect.Reflect.info.detectServer();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (AntiScreenshot.isGetingSS) return;
        scaleFactor = resolution.getScaleFactor();
        translate.interpolate(startX,(resolution.getScaledHeight() / 2) - (height/2),0.05);
        startX = Math.round(translate.getX());
        startY = Math.round(translate.getY());
        renderBackground();
        RenderUtil.beginScissor(Math.round(startX),Math.round(startY),Math.round(width),Math.round(height));
        renderModuleType(mouseX,mouseY);
        float end = renderModules(mouseX,mouseY);
        RenderUtil.endScissor();
        if (end < Math.round(startY + height)){
            wheel -= 2;
            wheelTranslate.interpolate(0,Math.round(wheel),0.04);
        }
        if (Math.round(startY - wheel) > startY){
            wheel += 2;
            wheelTranslate.interpolate(0,Math.round(wheel),0.04);
        }
        if (!LWJGLMouse.isButtonDown(0) && !LWJGLMouse.isButtonDown(1)) {
            this.previousMouse = false;
        }
    }
    private float renderModules(int mouseX,int mouseY){
        wheel -= LWJGLMouse.getDWheel();
        wheelTranslate.interpolate(0,Math.round(wheel),0.04);
        float moduleStartX = startX + (typeWidth) + 10;
        float moduleStartY = Math.round(startY + 10 - wheelTranslate.getY());
        for (Module module : ModuleManager.getModuleByType(currentType)) {
            moduleStartY  = Math.round(renderModule(module,moduleStartX,moduleStartY,mouseX,mouseY) + (moduleInterval));
        }
        return moduleStartY;
    }
    private float renderModule(Module module,float moduleStartX,float nowY,int mouseX,int mouseY) {
        float moduleY = nowY;
        module.switchTranslate.interpolate(module.isEnable() ? 15 : 5, 0, 0.1);
        RenderUtil.drawFastRoundedRect(Math.round(moduleStartX), Math.round(moduleY), Math.round(startX + (width) - 10), Math.round(moduleY + (moduleHeight)), 5f, whiteType.getRGB());
        RenderUtil.drawImage(module.isFavorite() ? ResourceManager.clickGuiFillStar.getResourceLocation() : ResourceManager.clickGuiStar.getResourceLocation()
                , Math.round(moduleStartX + 7), Math.round(moduleY + ((moduleHeight) / 2) - ((starSize) / 2))
                , Math.round(starSize), Math.round(starSize));
        if (isHover(mouseX, mouseY, Math.round(moduleStartX + 7 - (starSize / 2) - 1),
                Math.round(moduleY + ((moduleHeight) / 2) - ((starSize) / 2) - 1), Math.round(moduleStartX + 7 - (starSize / 2) + 1),
                Math.round(moduleY + ((moduleHeight) / 2) - ((starSize) / 2) + 1))) {
            if ((LWJGLMouse.isButtonDown(0) || LWJGLMouse.isButtonDown(1)) && !previousMouse) {
                this.previousMouse = true;
                module.setFavorite(!module.isFavorite());
            }
        }
        RenderUtil.drawImage(isUnfold(module) ? ResourceManager.clickGuiUp.getResourceLocation() : ResourceManager.clickGuiDown.getResourceLocation()
                , Math.round(startX + (width) - 30)
                , Math.round(moduleY + ((moduleHeight) / 2) - ((foldSize) / 2)), Math.round(foldSize), Math.round(foldSize));
        if (isHover(mouseX, mouseY, Math.round(startX + (width) - 30 - 2), Math.round(moduleY + ((moduleHeight) / 2) - ((foldSize) / 2) - 2)
                , Math.round(startX + (width) - 30 + foldSize + 2), Math.round(moduleY + ((moduleHeight) / 2) - ((foldSize) / 2)) + foldSize + 2)) {
            if ((LWJGLMouse.isButtonDown(0) || LWJGLMouse.isButtonDown(1)) && !previousMouse) {
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
            if ((LWJGLMouse.isButtonDown(0) || LWJGLMouse.isButtonDown(1)) && !previousMouse) {
                this.previousMouse = true;
                module.setEnable(!module.isEnable());
            }
        }
        FontManager.RobotoLight22.drawString(module.getName(),Math.round(moduleStartX + 7 + (starSize) + 8)
                ,Math.round(moduleY + ((moduleHeight)/2) - ((FontManager.RobotoLight22.getHeight())/2)),textLightBlack.getRGB());
        FontManager.RobotoLight18.drawString(module.getDescribe()
                ,Math.round(moduleStartX + 7 + (starSize) + 8 + FontManager.RobotoLight22.getStringWidth(module.getName()) + 10f)
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
        for (Object o : module.getValues().stream().filter(v -> v.show()).toArray()) {
            Value v = (Value) o;
            if (!(v instanceof ModeValue)) {
                height += (15 + valueInterval);
            } else {
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
            }
        }
        return 10 + 10 + height;
    }
    private float renderValue(Value<?> value,float moduleStartX,float nowY,int mouseX,int mouseY){
        if (!value.show()) return nowY;
        if (value instanceof OptionValue){
            FontManager.RobotoLight18.drawString(value.getName(),Math.round(moduleStartX + 7f),Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight()/2)),textLightBlack.getRGB());
            if (((OptionValue) value).getValue()){
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 50),Math.round(nowY + 7.5 - 5),Math.round(startX + (width) - 40),Math.round(nowY + 7.5 + 5),1,cureentTypePink.getRGB());
                RenderUtil.drawImage(ResourceManager.clickGuiTrue.getResourceLocation(),Math.round(startX + (width) - 48), (int) Math.round(nowY + 7.5 - 3),6,6);
            }else {
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 50),Math.round(nowY + 7.5 - 5),Math.round(startX + (width) - 40),Math.round(nowY + 7.5 + 5),1,textLightBlack.getRGB());
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 49),Math.round(nowY + 7.5 - 4),Math.round(startX + (width) - 41),Math.round(nowY + 7.5 + 4),1,whiteType.getRGB());
            }
            if (isHover(mouseX,mouseY,Math.round(startX + (width) - 50),Math.round(nowY + 7.5 - 6),Math.round(startX + (width) - 40),Math.round(nowY + 7.5 + 6))){
                if ((LWJGLMouse.isButtonDown(0) || LWJGLMouse.isButtonDown(1)) && !previousMouse) {
                    this.previousMouse = true;
                    ((OptionValue) value).setValue(!((OptionValue) value).getValue());
                }
            }
            return nowY + 15 + valueInterval;
        }
        if (value instanceof NumberValue){
            if (value instanceof DoubleValue){
                FontManager.RobotoLight18.drawString(value.getName(),Math.round(moduleStartX + 7f),Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight()/2)),textLightBlack.getRGB());
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - sliderWidth),Math.round(nowY + 7.5 - sliderHeight),Math.round(startX + (width) - 40),Math.round(nowY + 7.5 + sliderHeight),sliderRadius,sliderGray.getRGB());
                float progress = (float) ((((DoubleValue) value).getValue() - ((DoubleValue) value).getMin())/ (((DoubleValue) value).getMax()-((DoubleValue) value).getMin()));
                ((NumberValue)value).translate.interpolate(Math.round(progress * sliderWidth),0,0.2);
                RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - sliderWidth),Math.round(nowY + 7.5 - sliderHeight),Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),Math.round(nowY + 7.5 + sliderHeight),sliderRadius,cureentTypePink.getRGB());
                RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),(int)Math.round(nowY + 7.5),4,cureentTypePink.getRGB());
                RenderUtil.drawCircle(Math.round(startX + (width) - 40 - sliderWidth + ((NumberValue)value).translate.getX()),(int)Math.round(nowY + 7.5),3,whiteType.getRGB());
                FontManager.RobotoLight16.drawString(((DoubleValue)value).getValue().toString()
                        , Math.round(startX + (width) - 40 - sliderWidth + ((DoubleValue)value).translate.getX() - (FontManager.RobotoLight16.getStringWidth(((DoubleValue)value).getValue().toString())/2))
                        , (int)Math.round(nowY + 7.5 - 4 - FontManager.RobotoLight16.getHeight()),textLightBlack.getRGB());
                if (isHover(mouseX,mouseY,Math.round(startX + (width) - 40 - sliderWidth - 4),Math.round(nowY + 7.5 - 5),Math.round(startX + (width) - 40 + 4),Math.round(nowY + 7.5 + 5))){
                    if ((LWJGLMouse.isButtonDown(0) || LWJGLMouse.isButtonDown(1))) {
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
            FontManager.RobotoLight18.drawString(value.getName(), Math.round(moduleStartX + 7f), Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight() / 2)), textLightBlack.getRGB());
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
                FontManager.RobotoLight16.drawString(anEnum.name(), renderX + (modeSelectSize) + modeInterval, Math.round(renderY + 7.5 - (FontManager.RobotoLight16.getHeight() / 2)), textLightBlack.getRGB());
                if (((ModeValue) value).getValue().equals(anEnum)) {
                    RenderUtil.drawCircle(renderX + (modeSelectSize / 2), renderY + 7.5, modeSelectSize - 2, cureentTypePink.getRGB());
                }
                if (isHover(mouseX, mouseY, renderX, renderY, renderX + size, renderY + 15)) {
                    if ((LWJGLMouse.isButtonDown(0) || LWJGLMouse.isButtonDown(1))) {
                        this.previousMouse = true;
                        ((ModeValue) value).setValue(anEnum);
                    }
                }
            }
            return nowY + yAddon;
        }
        if (value instanceof KeyBindValue) {
            FontManager.RobotoLight18.drawString(value.getName(), Math.round(moduleStartX + 7f), Math.round(nowY + 7.5 - (FontManager.RobotoLight18.getHeight() / 2)), textLightBlack.getRGB());
            RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - FontManager.RobotoLight16.getStringWidth(value.getValue().toString()) - 3), Math.round(nowY + 7.5 - (FontManager.RobotoLight16.getHeight() / 2) - 4), Math.round(startX + (width) - 40 + 3), Math.round(nowY + 7.5 + (FontManager.RobotoLight16.getHeight() / 2) + 4), 1, textLightBlack.getRGB());
            RenderUtil.drawFastRoundedRect(Math.round(startX + (width) - 40 - FontManager.RobotoLight16.getStringWidth(value.getValue().toString()) - 2), Math.round(nowY + 7.5 - (FontManager.RobotoLight16.getHeight() / 2) - 3), Math.round(startX + (width) - 40 + 2), Math.round(nowY + 7.5 + (FontManager.RobotoLight16.getHeight() / 2) + 3), 1, whiteType.getRGB());
            FontManager.RobotoLight16.drawString(((KeyBindValue) value).getValue().getDisplayName(), Math.round(startX + (width) - 40 - FontManager.RobotoLight16.getStringWidth(((KeyBindValue) value).getValue().getDisplayName())), Math.round(nowY + 7.5 - (FontManager.RobotoLight16.getHeight() / 2)), textLightBlack.getRGB());
        }
        return nowY;
    }

    private void renderModuleType(int mouseX, int mouseY){
        float typeStartX = startX + 6f;
        float typeStartY = startY + 30f + 20f;
        for (ModuleType value : ModuleType.values()) {
            if (isHover(mouseX,mouseY,typeStartX - 4f,typeStartY,startX + typeWidth -2f,typeStartY + typeHeight)){
                if (currentType != value){
                    RenderUtil.drawFastRoundedRect(Math.round(typeStartX - 2f),Math.round(typeStartY),Math.round(startX+typeWidth-2f)
                            ,Math.round(typeStartY+typeHeight),3f,hoverGray.getRGB());
                }
                if ((LWJGLMouse.isButtonDown(0) || LWJGLMouse.isButtonDown(1)) && !previousMouse) {
                    this.previousMouse = true;
                    if (currentType != value){
                        currentType = value;
                        unfoldModules.clear();
                        wheel = 0f;
                        typeTranslate = new Translate(50,50);
                    }
                }
            }
            if (currentType == value){
                typeTranslate.interpolate(102,102,0.05);
                currentTypeTranslate.interpolate(Math.round(startX + 2f),Math.round(typeStartY + ((typeHeight/2))),0.1);
                Color nowGray = new Color(selectedGray.getRed(),selectedGray.getGreen(),selectedGray.getBlue(),(int) typeTranslate.getX());
                RenderUtil.drawFastRoundedRect(Math.round(typeStartX - 2f),Math.round(typeStartY),Math.round(startX+typeWidth-2f)
                        ,Math.round(typeStartY+typeHeight),3f,nowGray.getRGB());
            }
            RenderUtil.drawImageAntiAlias(value.getByteImageLocation().getResourceLocation(),
                    Math.round(typeStartX), Math.round(typeStartY + (typeHeight / 2) - (imageSize / 2)),
                    Math.round(imageSize), Math.round(imageSize));
            GlStateManager.resetColor();
            FontManager.RobotoLight18.drawString(value.name(), Math.round(typeStartX + 2f + (imageSize))
                    , Math.round(typeStartY + ((typeHeight) / 2) - (FontManager.RobotoLight18.getHeight() / 2)), textLightBlack.getRGB());
            typeStartY += ((typeInterval) + (typeHeight));
        }
        RenderUtil.drawRect(Math.round(currentTypeTranslate.getX()), Math.round(currentTypeTranslate.getY() - Math.round((currentTypeHeight) / 2))
                , Math.round(currentTypeTranslate.getX() + 2f), Math.round(currentTypeTranslate.getY() + Math.round((currentTypeHeight) / 2)), cureentTypePink.getRGB());
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
