package com.reflectmc.reflect.wrapper.wrappers.lwjgl;

import com.reflectmc.reflect.obfuscate.ExportObfuscate;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

public class KeyBoard extends WrapperBase {
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
        return (int) invokeStaticMethod(getEventKey);
    }
    @ExportObfuscate(name = "getEventKeyState")
    public static boolean getEventKeyState(){
        return (boolean) invokeStaticMethod(getEventKeyState);
    }
    public static char getEventCharacter(){
        return (char) invokeStaticMethod(getEventCharacter);
    }
    public static void enableRepeatEvents(boolean b){
        invokeStaticMethod(enableRepeatEvents,b);
    }
    public static boolean isKeyDown(int i){
        return (boolean) invokeStaticMethod(isKeyDown,i);
    }
}
