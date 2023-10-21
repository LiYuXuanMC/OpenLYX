package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

import java.lang.reflect.Constructor;


@WrapperClass(deobfName = "net.minecraft.network.play.client.C0APacketAnimation",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketAnimation",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C0APacketAnimation extends Packet {
    @ClassInstance
    public static Class C0APacketAnimationClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Constructor C0APacketAnimation_V;

    public C0APacketAnimation(Object obj) {
        super(obj);
    }
    public C0APacketAnimation(){
        this(construct(C0APacketAnimation_V));
    }
}
