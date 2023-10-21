package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.player.PlayerCapabilities", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.player.PlayerCapabilities", targetMap = Maps.Srg1_12_2)
public class PlayerCapabilities extends IWrapper {
    @WrapField(mcpName = "isCreativeMode", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "isCreativeMode", targetMap = Maps.Srg1_12_2)
    public static Field isCreativeMode;
    @WrapField(mcpName = "isFlying", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "isFlying", targetMap = Maps.Srg1_12_2)
    public static Field isFlying;
    @WrapField(mcpName = "disableDamage", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "disableDamage", targetMap = Maps.Srg1_12_2)
    public static Field disableDamage;
    @WrapMethod(mcpName = "getWalkSpeed", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getWalkSpeed", targetMap = Maps.Srg1_12_2)
    public static Method getWalkSpeed;

    public PlayerCapabilities(Object obj) {
        super(obj);
    }

    public boolean isCreativeMode() {
        return (boolean) getField(isCreativeMode);
    }

    public boolean isFlying() {
        return (boolean) getField(isFlying);
    }

    public boolean isDisableDamage() {
        return (boolean) getField(disableDamage);
    }

    public float getWalkSpeed() {
        return (float) invoke(getWalkSpeed);
    }
}
