package al.nya.reflect.wrapper.wraps.wrapper.network.C03;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


@WrapperClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer",targetMap = Maps.Srg1_12_2)
public class C03PacketPlayer extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer", targetMap = Maps.Srg1_12_2)
    public static Class<?> C03PacketPlayer;
    @WrapField(mcpName = "moving", targetMap = Maps.Srg1_8_9)
    public static Field moving;
    @WrapField(mcpName = "x", targetMap = Maps.Srg1_8_9)
    public static Field x;
    @WrapField(mcpName = "y", targetMap = Maps.Srg1_8_9)
    public static Field y;
    @WrapField(mcpName = "z", targetMap = Maps.Srg1_8_9)
    public static Field z;
    @WrapField(mcpName = "yaw", targetMap = Maps.Srg1_8_9)
    public static Field yaw;
    @WrapField(mcpName = "pitch", targetMap = Maps.Srg1_8_9)
    public static Field pitch;
    @WrapField(mcpName = "onGround", targetMap = Maps.Srg1_8_9)
    public static Field onGround;
    @WrapField(mcpName = "rotating", targetMap = Maps.Srg1_8_9)
    public static Field rotating;
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {boolean.class})
    public static Constructor<?> C03PacketPlayer_boolean;

    public C03PacketPlayer(Object obj) {
        super(obj);
    }

    public C03PacketPlayer(Packet e) {
        super(e.getWrapObject());
    }

    public C03PacketPlayer(boolean b) {
        this(ReflectUtil.construction(C03PacketPlayer_boolean, b));
    }

    public static boolean isPacketPlayer(Packet packet) {
        return C03PacketPlayer.isInstance(packet.getWrapObject());
    }

    public boolean isMoving() {
        return (boolean) getField(moving);
    }

    public double getX() {
        return (double) getField(x);
    }

    public double getY() {
        return (double) getField(y);
    }

    public double getZ() {
        return (double) getField(z);
    }

    public void setX(double d) {
        setField(x, d);
    }

    public void setY(double d) {
        setField(y, d);
    }

    public void setZ(double d) {
        setField(z, d);
    }

    public boolean isOnGround() {
        return (boolean) getField(onGround);
    }

    public void setOnGround(boolean onGroundValue) {
        setField(onGround, onGroundValue);
    }

    public boolean isRotating() {
        return (boolean) getField(rotating);
    }

    public float getYaw() {
        return (float) getField(yaw);
    }

    public float getPitch() {
        return (float) getField(pitch);
    }
}
