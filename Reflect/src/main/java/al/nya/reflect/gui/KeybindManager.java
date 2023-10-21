package al.nya.reflect.gui;

import al.nya.reflect.Reflect;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.font.FontManager;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.utils.LWJGLMouse;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.wrapper.wraps.impl.GuiScreenImpl;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KeybindManager extends GuiScreenImpl {
    private List<KeyBindBox> bindBoxes = new ArrayList<KeyBindBox>();
    private float startX;
    private float startY;
    private Color alphaWhite = new Color(255,255,255,200);
    private Color alphaGray1 = new Color(181,181,191,200);
    private Color alphaGray2 = new Color(232,232,232,200);
    private Color gray3 = new Color(105,105,105);
    private boolean previousMouse = true;
    private float normalInc = 26;
    private float normalSize = 24;
    private float width = 400;
    private float height = normalInc * 5 + 2;
    private EnumKey key = null;
    private float wheelOffset = 0;
    @Override
    public void initGui(){
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        startX = (scaledResolution.getScaledWidth()/2)-(width / 2);
        startY = (scaledResolution.getScaledHeight()/2)-(height / 2);
        float nowX = startX + 2;
        float nowY = startY + 2;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Symbol41, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N1, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N2, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N3, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N4, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N5, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N6, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N7, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N8, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N9, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N0, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Sub, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Equal, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,58,normalSize, EnumKey.BackSpace, FontManager.Verdana14));
        nowX = startX + 2;
        nowY = startY + 2;
        nowY += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize+8,normalSize, EnumKey.Tab, FontManager.Verdana14));
        nowX += normalInc + 8;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Q, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.W, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.E, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.R, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.T, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Y, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.U, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.I, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.O, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.P, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.LeftBrackets, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.RightBrackets, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,50,normalSize, EnumKey.LeftSlash, FontManager.Verdana14));
        nowX = startX + 2;
        nowY = startY + 2;
        nowY += normalInc*2;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize+16,normalSize, EnumKey.CapsLock, FontManager.Verdana14));
        nowX += normalInc + 16;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.A, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.S, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.D, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.F, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.G, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.H, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.J, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.K, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.L, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Symbol39, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Symbol40, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,68,normalSize, EnumKey.Enter, FontManager.Verdana14));
        nowX = startX + 2;
        nowY = startY + 2;
        nowY += normalInc*3;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize*2,normalSize, EnumKey.LSHIFT, FontManager.Verdana14));
        nowX += normalSize*2 + 2;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Z, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.X, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.C, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.V, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.B, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.N, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.M, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Symbol51, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.Symbol52, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.RightSlash, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize*3 + 14,normalSize, EnumKey.RSHIFT, FontManager.Verdana14));
        nowX = startX + 2;
        nowY = startY + 2;
        nowY += normalInc*4;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize+8,normalSize, EnumKey.LCTRL, FontManager.Verdana14));
        nowX += normalInc + 8;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize,normalSize, EnumKey.LeftWin, FontManager.Verdana14));
        nowX += normalInc;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize+8,normalSize, EnumKey.LAlt, FontManager.Verdana14));
        nowX += normalInc + 8;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize*8 + 8,normalSize, EnumKey.Space, FontManager.Verdana14));
        nowX += normalSize*8 + 8 + 2;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize+8,normalSize, EnumKey.RAlt, FontManager.Verdana14));
        nowX += normalInc + 8;
        bindBoxes.add(new KeyBindBox(nowX,nowY,normalSize*2 + 18,normalSize, EnumKey.CTRL, FontManager.Verdana14));
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {
        if (key != null) {
            if (keyCode == 1) {
                if (listeningKey)

                key = null;
                return true;
            }
        }
        return super.keyTyped(typedChar, keyCode);
    }

    boolean listeningKey;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        if (key != null){
            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
            RenderUtil.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), ColorUtils.color(0.0F, 0.0F, 0.0F, 0.5F));



            float widthK = 100;
            float heightK = 150;
            RenderUtil.drawFastRoundedRect(startX + width + 5,startY,startX+width + 5 + widthK,startY+heightK,2,Color.white.getRGB());
            FontManager.Verdana20.drawString(key.getDisplayName(),startX + width + 5 +3, startY +3,Color.darkGray.getRGB() );
            RenderUtil.beginScissor((int) (startX + width + 5), (int) (startY+20), (int) widthK,100);
            float startKY = startY + 20 + wheelOffset;
            for (Module module : ModuleManager.getModules()) {
                if (module.getBinding() == key){
                    FontManager.Verdana20.drawString(module.getName(),startX + width + 5+3, startKY +3,Color.darkGray.getRGB() );
                    RenderUtil.drawFilledCircle(startX+width+5+widthK - 10,startKY + 10,3,Color.RED.getRGB());
                    startKY += 20;
                }
            }
            FontManager.Verdana16.drawString("Add",startX + width + 5 + widthK - 3 - FontManager.Verdana16.getStringWidth("Add"),
                    startY+heightK- 3 -FontManager.Verdana16.getStringHeight("Add"), Reflect.ColorStyle.getRGB());
            if (RenderUtil.isHovered(startX + width + 5 + widthK - 3 - FontManager.Verdana16.getStringWidth("Add"),
                    startY+heightK- 3 -FontManager.Verdana16.getStringHeight("Add"),
                    startX + width + 5 + widthK - 3 + FontManager.Verdana16.getStringWidth("Add"),
                    startY+heightK- 3 +FontManager.Verdana16.getStringHeight("Add"),mouseX,mouseY)){
                if (LWJGLMouse.isButtonDown(0) && !this.previousMouse){
                    //key = bindBox.key;
                    previousMouse = true;
                }
            }
            RenderUtil.endScissor();
        }


        RenderUtil.drawFastRoundedRect(startX,startY,startX+width,startY+height,5,alphaWhite.getRGB());

        for (KeyBindBox bindBox : bindBoxes) {
            RenderUtil.drawFastRoundedRect(bindBox.x,bindBox.y,bindBox.x+bindBox.width,bindBox.y+bindBox.height,1,alphaGray1.getRGB());
            if (RenderUtil.isHovered(bindBox.x,bindBox.y,bindBox.x+bindBox.width,bindBox.y+bindBox.height,mouseX,mouseY)){
                RenderUtil.drawFastRoundedRect(bindBox.x,bindBox.y,bindBox.x+bindBox.width,bindBox.y+bindBox.height - 1,1,alphaGray2.getRGB());
            }else {
                RenderUtil.drawFastRoundedRect(bindBox.x,bindBox.y,bindBox.x+bindBox.width,bindBox.y+bindBox.height - 1,1,Color.white.getRGB());
            }
            FontManager.Verdana14.drawString(bindBox.key.getDisplayName(), bindBox.stringPosX, bindBox.stringPosY,gray3.getRGB());
            if (RenderUtil.isHovered(bindBox.x,bindBox.y,bindBox.x+bindBox.width,bindBox.y+bindBox.height,mouseX,mouseY)){
                if (LWJGLMouse.isButtonDown(0) && !this.previousMouse){
                    key = bindBox.key;
                    previousMouse = true;
                }
            }
        }
        if (!LWJGLMouse.isButtonDown(0) && !LWJGLMouse.isButtonDown(1)) {
            this.previousMouse = false;
        }
    }
}
