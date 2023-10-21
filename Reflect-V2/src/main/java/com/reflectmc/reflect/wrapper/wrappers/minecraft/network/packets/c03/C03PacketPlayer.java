package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


@WrapperClass(deobfName = "net.minecraft.network.play.client.C03PacketPlayer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketPlayer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C03PacketPlayer extends Packet {
    @ClassInstance
    public static Class<?> C03PacketPlayer;
    @WrapField(deobfName = "moving", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field moving;
    @WrapField(deobfName = "x", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field x;
    @WrapField(deobfName = "y", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field y;
    @WrapField(deobfName = "z", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field z;
    @WrapField(deobfName = "yaw", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field yaw;
    @WrapField(deobfName = "pitch", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field pitch;
    @WrapField(deobfName = "onGround", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field onGround;
    @WrapField(deobfName = "rotating", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field rotating;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = {boolean.class})
    public static Constructor<?> C03PacketPlayer_boolean;

    public C03PacketPlayer(Object obj) {
        super(obj);
    }

    public C03PacketPlayer(Packet e) {
        super(e.getWrappedObject());
    }

    public C03PacketPlayer(boolean b) {
        this(construct(C03PacketPlayer_boolean, b));
    }

    public static boolean isPacketPlayer(Packet packet) {
        return C03PacketPlayer.isInstance(packet.getWrappedObject());
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
