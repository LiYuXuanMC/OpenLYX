package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

import java.lang.reflect.Constructor;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketPlayer$Rotation", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C06PacketPlayerPositionLook extends C03PacketPlayer {
    @ClassInstance
    public static Class<?> C06PacketPlayerPositionLookClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = {double.class, double.class, double.class, float.class, float.class, boolean.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge189, Environment.Vanilla189}, signature = {double.class, double.class, double.class, float.class, float.class, boolean.class})
    public static Constructor<?> C06PacketPlayerPositionLookConstructor;

    public C06PacketPlayerPositionLook(Object obj) {
        super(obj);
    }

    public C06PacketPlayerPositionLook(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        super(construct(C06PacketPlayerPositionLookConstructor, x, y, z, yaw, pitch, onGround));
    }

    public static boolean isPacketPlayerPositionLook(Packet packet) {
        return C06PacketPlayerPositionLookClass.isInstance(packet.getWrappedObject());
    }
}
