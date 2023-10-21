package al.logger.client.wrapper.lwjgl;

import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Method;

public class KeyBoard extends IWrapper {
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

    public KeyBoard(Object wrap) {
        super(wrap);
    }

    public static int getEventKey() {
        return (int) invokeStatic(getEventKey);
    }
    @ExportObfuscate(name = "getEventKeyState")
    public static boolean getEventKeyState(){
        return (boolean) invokeStatic(getEventKeyState);
    }
    public static char getEventCharacter(){
        return (char) invokeStatic(getEventCharacter);
    }
    public static void enableRepeatEvents(boolean b){
        invokeStatic(enableRepeatEvents,b);
    }
    public static boolean isKeyDown(int i){
        return (boolean) invokeStatic(isKeyDown,i);
    }
}
