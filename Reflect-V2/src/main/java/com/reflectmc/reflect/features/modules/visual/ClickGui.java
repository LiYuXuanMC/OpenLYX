package com.reflectmc.reflect.features.modules.visual;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.bridge.BridgeBuildInfo;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.ModeValue;
import com.reflectmc.reflect.gui.ClickGuiReflect;
import com.reflectmc.reflect.gui.Neverlose;
import com.reflectmc.reflect.key.EnumKey;
import com.reflectmc.reflect.utils.ClientUtil;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.GuiScreen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClickGui extends Module {
    private ModeValue theme = new ModeValue("Theme",Theme.Reflect,Theme.values()){
        @Override
        public void onValueChange(Enum before, Enum after){
            clickGui = null;
        }
    };
    private Object clickGui = null;
    public ClickGui() {
        super("ClickGui",Category.Visual);
        setBind(EnumKey.RSHIFT);
        addValues(theme);
    }
    @Override
    public void onEnable(){
        setEnableNoNotification(false);
        if (!Minecraft.getMinecraft().getCurrentScreen().isNull())
            return;
        if (clickGui == null){
            Class<?> clickGuiClass = null;
            BridgeBuildInfo bridge = null;
            if (theme.getValue() == Theme.Neverlose) {
                bridge = Reflect.getINSTANCE().getBridgeManager().getBridge(Neverlose.class);
            }
            if (theme.getValue() == Theme.Reflect){
                bridge = Reflect.getINSTANCE().getBridgeManager().getBridge(ClickGuiReflect.class);
            }
            if (bridge.getBuildType() == BridgeBuildInfo.Type.DIRECT){
                clickGuiClass = bridge.getGeneratedClass();
            }
            if (clickGuiClass == null)
                ClientUtil.printChat(ClientUtil.Level.DEBUG,"傻逼 空指针了");
            try {
                Constructor constructor = clickGuiClass.getConstructor();
                constructor.setAccessible(true);
                clickGui = constructor.newInstance();
                Minecraft.getMinecraft().displayGuiScreen(new GuiScreen(clickGui));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            Minecraft.getMinecraft().displayGuiScreen(new GuiScreen(clickGui));
        }
    }
    enum Theme {
        Neverlose,
        Reflect
    }
}
