package al.nya.reflect.utils;

import al.nya.reflect.Reflect;
import al.nya.reflect.wrapper.utils.ReflectUtil;

import java.lang.reflect.Method;

public class LWJGLKeyBoard {
    public static Class<?> Keyboard;
    public static Method getEventKeyState;
    public static Method getEventKey;
    public static Method getEventCharacter;
    public static Method enableRepeatEvents;
    public static Method isKeyDown;

    static {
        try {
            Keyboard = Class.forName("org.lwjgl.input.Keyboard");
            getEventKeyState = Keyboard.getDeclaredMethod("getEventKeyState");
            getEventKeyState.setAccessible(true);
            getEventKey = Keyboard.getDeclaredMethod("getEventKey");
            getEventKey.setAccessible(true);
            getEventCharacter = Keyboard.getDeclaredMethod("getEventCharacter");
            getEventCharacter.setAccessible(true);
            isKeyDown = Keyboard.getDeclaredMethod("isKeyDown",int.class);
            isKeyDown.setAccessible(true);
            enableRepeatEvents = Keyboard.getDeclaredMethod("enableRepeatEvents",boolean.class);
            enableRepeatEvents.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static int getEventKey() {
        return (int) ReflectUtil.invoke(getEventKey,null);
    }
    public static boolean getEventKeyState(){
        return (boolean) ReflectUtil.invoke(getEventKeyState,null);
    }
    public static char getEventCharacter(){
        return (char) ReflectUtil.invoke(getEventCharacter,null);
    }
    public static void enableRepeatEvents(boolean b){
        ReflectUtil.invoke(enableRepeatEvents,null,b);
    }
    public static boolean isKeyDown(int i){
        return (boolean) ReflectUtil.invoke(isKeyDown,null,i);
    }
}
