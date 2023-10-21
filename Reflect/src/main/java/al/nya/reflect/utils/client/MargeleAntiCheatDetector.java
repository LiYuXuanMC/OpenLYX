package al.nya.reflect.utils.client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class MargeleAntiCheatDetector {
    public static boolean isMAC() {
        return getMAC() != null;
    }

    public static Class<?> getMAC() {
        try {
            return Class.forName("cn.margele.netease.clientside.MargeleAntiCheat");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    public static Class<?> getHyGui(){
        try {
            return Class.forName("cn.hycraft.core.gui.HyCraftGui");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    public static Class<?> getGuiTab(){
        try {
            return Class.forName("cn.hycraft.core.util.render.EnumUtils$GuiTab");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Enum<?> getHyTab() {
        try {
            return (Enum<?>) Class.forName("cn.hycraft.core.util.render.EnumUtils$GuiTab").getEnumConstants()[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Class<?> getHyCraftScoreBoard() {
        try {
            return Class.forName("cn.hycraft.core.module.impl.game.ScoreBoard");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean isHyCraftScreenshot() {
        return getHyCraftScoreBoard() != null;
    }

    public static void unloadHyCraftEventListener() {
        try {
            Class MinecraftForge = Class.forName("net.minecraftforge.common.MinecraftForge");
            Class EventBus = Class.forName("net.minecraftforge.fml.common.eventhandler.FMLBusEvent");
            Field EventBusField = MinecraftForge.getDeclaredField("EVENT_BUS");
            EventBusField.setAccessible(true);
            Object eventBus = EventBusField.get(null);
            Field listenersField = EventBus.getDeclaredField("listeners");
            listenersField.setAccessible(true);
            ConcurrentHashMap<Object, ArrayList<Object>> listeners = (ConcurrentHashMap<Object, ArrayList<Object>>) listenersField.get(eventBus);
            ConcurrentHashMap<Object, ArrayList<Object>> listenersNew = new ConcurrentHashMap<>();
            listeners.forEach((key, value) -> {
                if (!key.getClass().getCanonicalName().startsWith("cn.hycraft")) {
                    //你妈死了虎牙宇宙 写你妈了个逼的HWID检测
                    listenersNew.put(key, value);
                }
            });
            listenersField.set(eventBus, listenersNew);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
