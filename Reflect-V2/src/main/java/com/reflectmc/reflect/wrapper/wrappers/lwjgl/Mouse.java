package com.reflectmc.reflect.wrapper.wrappers.lwjgl;

import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

public class Mouse extends WrapperBase {
    public static Class Mouse;
    public static Method isButtonDown;
    public static Method getDWheel;
    public static Method hasWheel;
    public static Method getX;
    public static Method getY;


    static {
        try {
            Mouse = Class.forName("org.lwjgl.input.Mouse");
            isButtonDown = Mouse.getDeclaredMethod("isButtonDown",int.class);
            isButtonDown.setAccessible(true);
            getDWheel = Mouse.getDeclaredMethod("getDWheel");
            getDWheel.setAccessible(true);
            hasWheel = Mouse.getDeclaredMethod("hasWheel");
            hasWheel.setAccessible(true);
            getX = Mouse.getDeclaredMethod("getX");
            getX.setAccessible(true);
            getY = Mouse.getDeclaredMethod("getY");
            getY.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Mouse(Object wrap) {
        super(wrap);
    }

    public static boolean isButtonDown(int button){
        return (boolean) invokeStaticMethod(isButtonDown,button);
    }
    public static int getDWheel(){
        return (int) invokeStaticMethod(getDWheel);
    }
    public static boolean hasWheel(){
        return (boolean) invokeStaticMethod(hasWheel);
    }
    public static int getX(){
        return (int) invokeStaticMethod(getX);
    }
    public static int getY(){
        return (int) invokeStaticMethod(getY);
    }
}
