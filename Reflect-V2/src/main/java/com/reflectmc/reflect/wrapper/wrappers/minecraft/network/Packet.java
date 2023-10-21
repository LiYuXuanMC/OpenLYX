package com.reflectmc.reflect.wrapper.wrappers.minecraft.network;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.network.Packet",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.Packet",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Packet extends WrapperBase {
    @ClassInstance
    public static Class<?> PacketClass;
    @WrapMethod(deobfName = "processPacket", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method processPacket;
    public Packet(Object obj) {
        super(obj);
    }
    public boolean isPacket(Class c){
        return c.isInstance(getWrappedObject());
    }
    public void processPacket(INetHandler handler){
        invokeMethod(processPacket, handler.getWrappedObject());
    }
}
