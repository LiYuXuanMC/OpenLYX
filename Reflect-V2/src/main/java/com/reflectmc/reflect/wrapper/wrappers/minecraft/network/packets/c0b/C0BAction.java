package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c0b;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapEnum;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.EnumWrapper;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C0BPacketEntityAction$Action",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketEntityAction$Action",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C0BAction extends EnumWrapper {
    @ClassInstance
    public static Class C08ActionClass;
    @WrapEnum(deobfName = "START_SPRINTING", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "START_SPRINTING", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C0BAction START_SPRINTING;
    @WrapEnum(deobfName = "STOP_SPRINTING", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "STOP_SPRINTING", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C0BAction STOP_SPRINTING;
    @WrapEnum(deobfName = "STOP_SNEAKING", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "STOP_SNEAKING", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C0BAction STOP_SNEAKING;
    @WrapEnum(deobfName = "OPEN_INVENTORY", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "OPEN_INVENTORY", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C0BAction OPEN_INVENTORY;

    public C0BAction(Object obj) {
        super(obj);
    }
}
