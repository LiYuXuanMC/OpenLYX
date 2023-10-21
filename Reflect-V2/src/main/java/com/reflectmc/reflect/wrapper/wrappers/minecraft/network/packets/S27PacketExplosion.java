package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

@WrapperClass(deobfName = "net.minecraft.network.play.server.S27PacketExplosion",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.server.SPacketExplosion",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class S27PacketExplosion extends Packet {
    @ClassInstance
    public static Class S27PacketExplosionClass;
    public S27PacketExplosion(Object obj) {
        super(obj);
    }
    public static boolean isS27PacketExplosion(Packet p){
        return S27PacketExplosionClass.isAssignableFrom(p.getWrappedObject().getClass());
    }
}
