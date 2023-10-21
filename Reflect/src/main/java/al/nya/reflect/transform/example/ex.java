package al.nya.reflect.transform.example;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventBus;
import al.nya.reflect.events.events.*;
import al.nya.reflect.features.modules.Module;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ex {
    private static BufferedImage lastImage;
    public static boolean isGetingSS = false;
    public static List<Module> disabledModules = new ArrayList<>();
    public float pitch;
    public float yaw;
    public static String a;
    public void onUpdate(){
        Reflect.Instance.eventBus.callEvent(new EventUpdate());
    }
    public boolean creative(){
        return true;
    }
    public static native byte[] getString(int index);
    public int onRenderString(String text, float x, float y, int color, boolean dropShadow){
        EventText et = EventBus.onRenderString(text, x, y, color, dropShadow);
        if (et.isCancel()) return et.getReturnValue();
        text = et.getText();
        x = et.getX();
        y = et.getY();
        color = et.getColor();
        dropShadow = et.isDropShadow();
        return 0;
    }
}
