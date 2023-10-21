package al.nya.reflect.wrapper.wraps.wrapper;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.settings.KeyBinding",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.settings.KeyBinding",targetMap = Maps.Srg1_12_2)
public class KeyBinding extends IWrapper {
    @WrapField(mcpName = "pressed", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "pressed", targetMap = Maps.Srg1_12_2)
    public static Field pressed;
    @WrapMethod(mcpName = "onTick", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "onTick", targetMap = Maps.Srg1_12_2)
    public static Method onTick;
    @WrapField(mcpName = "keyCode", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyCode", targetMap = Maps.Srg1_12_2)
    public static Field keyCode;
    @WrapField(mcpName = "pressTime", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "pressTime", targetMap = Maps.Srg1_12_2)
    public static Field pressTime;

    public KeyBinding(Object obj) {
        super(obj);
    }

    public int getPressTime() {
        return (int) getField(pressTime);
    }

    public void setPressTime(int i) {
        setField(pressTime, i);
    }

    public boolean isKeyDown() {
        return (boolean) ReflectUtil.getField(pressed, getWrapObject());
    }

    public void setPressed(boolean v) {
        setField(pressed, v);
    }

    public boolean isPressed() {
        return (boolean) getField(pressed);
    }

    public static void onTick(int keyCode) {
        ReflectUtil.invoke(onTick,null,keyCode);
    }

    public int getKeyCode() {
        return (int) ReflectUtil.getField(keyCode,getWrapObject());
    }
}
