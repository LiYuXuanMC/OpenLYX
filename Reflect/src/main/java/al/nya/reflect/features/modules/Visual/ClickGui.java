package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.gui.clickgui.Neverlose;
import al.nya.reflect.gui.clickgui.reflect.Reflect;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.bridge.BridgeUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.gui.IGuiScreen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class ClickGui extends Module {
    public static float startX = 100;
    public static float startY = 30;
    public static ModuleType type = ModuleType.Ghost;
    public static int wheel = 0;
    public static ModeValue theme = new ModeValue("Theme", Theme.Reflect, Theme.values());
    public ClickGui() {
        super("ClickGui",ModuleType.Visual);
        addValues(theme);
        if (theme.getValue() == Theme.Reflect)
            EventBus.guiScreen = new Reflect();
        if (theme.getValue() == Theme.Neverlose)
            EventBus.guiScreen = new Neverlose();
        setBinding(EnumKey.Insert);
    }
    @Override
    public void onEnable(){
        setEnableNoNotification(false);
        if (MargeleAntiCheatDetector.getHyGui() != null){
            if (theme.getValue() == Theme.Reflect)
                EventBus.guiScreen = new Reflect();
            if (theme.getValue() == Theme.Neverlose)
                EventBus.guiScreen = new Neverlose();
            Class<?> hyGui = MargeleAntiCheatDetector.getHyGui();
            try {
                Constructor<?> constructor = hyGui.getConstructor(int.class,MargeleAntiCheatDetector.getGuiTab());
                constructor.setAccessible(true);
                Object gui = constructor.newInstance(0,MargeleAntiCheatDetector.getHyTab());
                Minecraft.getMinecraft().displayGuiScreenBypass(new IGuiScreen(gui));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                NotificationPublisher.queue("Fail","Cannot open ClickGui", 2000, NotificationType.ERROR);
            }
            return;
        }
        GuiScreen guiScreenWrapper = Minecraft.getMinecraft().getCurrentScreen();
        if (Objects.isNull(guiScreenWrapper.getWrapObject())){
            if (theme.getValue() == Theme.Reflect)
                Minecraft.getMinecraft().displayGuiScreenBypass(BridgeUtil.createGuiScreen(new Reflect()));
            if (theme.getValue() == Theme.Neverlose)
                Minecraft.getMinecraft().displayGuiScreenBypass(BridgeUtil.createGuiScreen(new Neverlose()));
        }
    }
    public enum Theme {
        Reflect,
        Neverlose
    }
}
