package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c02;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapEnum;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.EnumWrapper;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C02PacketUseEntity$Action",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketUseEntity$Action",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C02Action extends EnumWrapper {
    @ClassInstance
    public static Class ActionClass;
    @WrapEnum(deobfName = "ATTACK",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "ATTACK",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C02Action ATTACK;
    public C02Action(Object obj) {
        super(obj);
    }
}
