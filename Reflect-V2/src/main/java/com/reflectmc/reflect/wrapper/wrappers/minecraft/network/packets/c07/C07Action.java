package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c07;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapEnum;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.EnumWrapper;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C07PacketPlayerDigging$Action",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketPlayerDigging$Action",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C07Action extends EnumWrapper {
    @ClassInstance
    public static Class ActionClass;
    @WrapEnum(deobfName = "START_DESTROY_BLOCK",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "START_DESTROY_BLOCK",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C07Action START_DESTROY_BLOCK;
    @WrapEnum(deobfName = "ABORT_DESTROY_BLOCK",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "ABORT_DESTROY_BLOCK",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C07Action ABORT_DESTROY_BLOCK;
    @WrapEnum(deobfName = "STOP_DESTROY_BLOCK",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "STOP_DESTROY_BLOCK",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C07Action STOP_DESTROY_BLOCK;
    @WrapEnum(deobfName = "DROP_ALL_ITEMS",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "DROP_ALL_ITEMS",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C07Action DROP_ALL_ITEMS;
    @WrapEnum(deobfName = "DROP_ITEM",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "DROP_ITEM",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C07Action DROP_ITEM;
    @WrapEnum(deobfName = "RELEASE_USE_ITEM",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "RELEASE_USE_ITEM",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static C07Action RELEASE_USE_ITEM;
    public C07Action(Object obj) {
        super(obj);
    }
}
