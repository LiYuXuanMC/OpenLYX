package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.gui.KeybindManager;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.wrapper.bridge.BridgeUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.IGuiScreen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class KeyBindManager extends Module {
    public KeyBindManager() {
        super("KeyBindManager", ModuleType.Visual);
    }

    @Override
    public void onEnable() {
        ClientUtil.printChat("HI! KeyBindManager is still in beta and not finished yet!");
        if (MargeleAntiCheatDetector.getHyGui() != null){
            EventBus.guiScreen = new KeybindManager();
            Class<?> hyGui = MargeleAntiCheatDetector.getHyGui();
            try {
                Constructor<?> constructor = hyGui.getConstructor(int.class,MargeleAntiCheatDetector.getGuiTab());
                constructor.setAccessible(true);
                Object gui = constructor.newInstance(0, MargeleAntiCheatDetector.getHyTab());
                Minecraft.getMinecraft().displayGuiScreenBypass(new IGuiScreen(gui));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace();
                NotificationPublisher.queue("Fail", "Cannot open ClickGui", 2000, NotificationType.ERROR);
            }
            return;
        }

        Minecraft.getMinecraft().displayGuiScreenBypass(BridgeUtil.createGuiScreen(new KeybindManager()));

        super.onEnable();
    }
}
