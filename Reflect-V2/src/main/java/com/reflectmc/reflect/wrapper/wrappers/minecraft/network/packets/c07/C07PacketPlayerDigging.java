package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c07;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c02.C02Action;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.BlockPos;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.EnumFacing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C07PacketPlayerDigging", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketPlayerDigging", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C07PacketPlayerDigging extends Packet {
    @ClassInstance
    public static Class C07PacketPlayerDiggingClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189, Environment.Vanilla189}, signature = {C07Action.class, BlockPos.class, EnumFacing.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = {C07Action.class, BlockPos.class, EnumFacing.class})
    public static Constructor C07PacketPlayerDigging_Action_BlockPos_EnumFacing;
    @WrapField(deobfName = "status", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field status;

    public C07PacketPlayerDigging(Object obj) {
        super(obj);
    }

    public C07PacketPlayerDigging(C07Action statusIn, BlockPos posIn, EnumFacing facingIn) {
        this(construct(C07PacketPlayerDigging_Action_BlockPos_EnumFacing, statusIn.getWrappedObject(), posIn.getWrappedObject(), facingIn.getWrappedObject()));
    }

    public static boolean isCPacketPlayerDigging(Packet packet) {
        return C07PacketPlayerDiggingClass.isInstance(packet.getWrappedObject());
    }

    public C02Action getStatus() {
        return new C02Action(getField(status));
    }
}
