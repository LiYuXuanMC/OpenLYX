package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.MovementInput",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.MovementInput",targetMap = Maps.Srg1_12_2)
public class MovementInput extends IWrapper {
    @WrapField(mcpName = "moveStrafe",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "moveStrafe",targetMap = Maps.Srg1_12_2)
    public static Field moveStrafeWrapper;
    @WrapField(mcpName = "moveForward",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "moveForward",targetMap = Maps.Srg1_12_2)
    public static Field moveForwardWrapper;
    @WrapField(mcpName = "jump",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "jump",targetMap = Maps.Srg1_12_2)
    public static Field jumpWrapper;
    @WrapField(mcpName = "sneak",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "sneak",targetMap = Maps.Srg1_12_2)
    public static Field sneakWrapper;

    public MovementInput(Object obj) {
        super(obj);
    }

    public float getMoveForward() {
        return (float) ReflectUtil.getField(moveForwardWrapper,getWrapObject());
    }

    public float getMoveStrafe() {
        return (float) ReflectUtil.getField(moveStrafeWrapper,getWrapObject());
    }

    public boolean isJump() {
        return (boolean) ReflectUtil.getField(jumpWrapper,getWrapObject());
    }

    public boolean isSneak() {
        return (boolean) ReflectUtil.getField(sneakWrapper,getWrapObject());
    }

    public void setMoveForward(float moveForward) {
        ReflectUtil.setField(moveForwardWrapper,moveForward,getWrapObject());
    }

    public void setJump(boolean jump) {
        ReflectUtil.setField(jumpWrapper,jump,getWrapObject());
    }

    public void setMoveStrafe(float moveStrafe) {
        ReflectUtil.setField(moveStrafeWrapper,moveStrafe,getWrapObject());
    }

    public void setSneak(boolean sneak) {
        ReflectUtil.setField(sneakWrapper,sneak,getWrapObject());
    }
}
