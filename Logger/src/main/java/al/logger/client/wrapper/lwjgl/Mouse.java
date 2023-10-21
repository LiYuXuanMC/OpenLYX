package al.logger.client.wrapper.lwjgl;

import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Method;

public class Mouse extends IWrapper {
    public static Class Mouse;
    public static Method isButtonDown;
    public static Method getDWheel;
    public static Method hasWheel;
    public static Method getX;
    public static Method getY;
    public static Method getEventButtonState;


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
            getEventButtonState = Mouse.getDeclaredMethod("getEventButtonState");
            getEventButtonState.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Mouse(Object wrap) {
        super(wrap);
    }

    public static boolean isButtonDown(int button){
        return (boolean) invokeStatic(isButtonDown,button);
    }
    public static int getDWheel(){
        return (int) invokeStatic(getDWheel);
    }
    public static boolean hasWheel(){
        return (boolean) invokeStatic(hasWheel);
    }
    public static int getX(){
        return (int) invokeStatic(getX);
    }
    public static int getY(){
        return (int) invokeStatic(getY);
    }
}
