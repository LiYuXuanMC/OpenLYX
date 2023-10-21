package al.logger.client.wrapper.LoggerMC;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.annotations.CactusWrapping;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.settings.KeyBinding",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.settings.KeyBinding",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class KeyBinding extends IWrapper {
    @WrapField(mcpName = "pressed", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "pressed", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field pressed;
    @WrapMethod(mcpName = "onTick", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(I)V")
    @WrapMethod(mcpName = "onTick", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method onTick;
    @WrapField(mcpName = "keyCode", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyCode", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyCode;
    @WrapField(mcpName = "pressTime", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "pressTime", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field pressTime;
    @WrapMethod(mcpName = "setKeyBindState", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setKeyBindState", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setKeyBindState;

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
        return (boolean) getField(pressed);
    }

    public void setPressed(boolean v) {
        setField(pressed, v);
    }

    public boolean isPressed() {
        return (boolean) getField(pressed);
    }

    public static void onTick(int keyCode) {
        invokeStatic(onTick,keyCode);
    }
    public static void setKeyBindState(int keyCode, boolean pressed) {
        invokeStatic(setKeyBindState,keyCode,pressed);
    }
    public int getKeyCode() {
        return (int) getField(keyCode);
    }
}
