package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

import java.lang.reflect.Constructor;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketPlayer$Position",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C04PacketPlayerPosition extends C03PacketPlayer{
    @ClassInstance
    public static Class C04PacketPlayerPositionClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189, Environment.Vanilla189},signature = {double.class,double.class,double.class,boolean.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {double.class,double.class,double.class,boolean.class})
    public static Constructor C04PacketPlayerPosition_DDDZ;
    public C04PacketPlayerPosition(Object obj) {
        super(obj);
    }
    public static boolean isPacketPlayerPosition(Packet packet){
        return C04PacketPlayerPositionClass.isInstance(packet.getWrappedObject());
    }
    public C04PacketPlayerPosition(double posX, double posY, double posZ, boolean isOnGround)
    {
        super(construct(C04PacketPlayerPosition_DDDZ,posX,posY,posZ,isOnGround));
    }
}
