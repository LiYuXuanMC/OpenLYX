package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

import java.lang.reflect.Constructor;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketPlayer$Rotation",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C05PacketPlayerLook extends C03PacketPlayer{
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {float.class,float.class,boolean.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122, Environment.Vanilla1122},signature = {float.class,float.class,boolean.class})
    public static Constructor C04PacketPlayerPosition_FFZ;
    public C05PacketPlayerLook(Object obj) {
        super(obj);
    }
    @ClassInstance
    public static Class<?> C05PacketPlayerLookClass;
    public static boolean isC05PacketPlayerLook(Packet packet){
        return C05PacketPlayerLookClass.isInstance(packet.getWrappedObject());
    }
    public C05PacketPlayerLook(float playerYaw, float playerPitch, boolean isOnGround)
    {
        super(construct(C04PacketPlayerPosition_FFZ,playerYaw,playerPitch,isOnGround));
    }
}
