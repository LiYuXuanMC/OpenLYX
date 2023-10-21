package al.nya.reflect.utils;

import al.nya.reflect.wrapper.utils.ReflectUtil;

import java.lang.reflect.Method;

public class LWJGLMouse {
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
    public static boolean isButtonDown(int button){
        return (boolean) ReflectUtil.invoke(isButtonDown,null,button);
    }
    public static int getDWheel(){
        return (int) ReflectUtil.invoke(getDWheel,null);
    }
    public static boolean hasWheel(){
        return (boolean) ReflectUtil.invoke(hasWheel,null);
    }
    public static int getX(){
        return (int) ReflectUtil.invoke(getX,null);
    }
    public static int getY(){
        return (int) ReflectUtil.invoke(getY,null);
    }
}
