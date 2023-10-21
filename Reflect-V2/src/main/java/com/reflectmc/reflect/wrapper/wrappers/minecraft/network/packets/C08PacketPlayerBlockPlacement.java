package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemStack;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.BlockPos;

import java.lang.reflect.Constructor;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C08PacketPlayerBlockPlacement",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C08PacketPlayerBlockPlacement extends Packet {
    @ClassInstance
    public static Class C08PacketPlayerBlockPlacementClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {ItemStack.class})
    public static Constructor C08PacketPlayerBlockPlacement_ItemStack;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {BlockPos.class,int.class,ItemStack.class,float.class,float.class,float.class})
    public static Constructor C08PacketPlayerBlockPlacement_BlockPos_I_ItemStack_FFF;
    public C08PacketPlayerBlockPlacement(Object obj) {
        super(obj);
    }
    public C08PacketPlayerBlockPlacement(ItemStack itemStack){
        this(construct(C08PacketPlayerBlockPlacement_ItemStack,itemStack.getWrappedObject()));
    }

    public C08PacketPlayerBlockPlacement(BlockPos positionIn, int placedBlockDirectionIn, ItemStack stackIn, float facingXIn, float facingYIn, float facingZIn) {
        super(construct(C08PacketPlayerBlockPlacement_BlockPos_I_ItemStack_FFF,
                positionIn.getWrappedObject(), placedBlockDirectionIn, stackIn.getWrappedObject(), facingXIn, facingYIn, facingZIn));
    }
    public static boolean isPacketPlayerBlockPlacement(Packet packet) {
        return C08PacketPlayerBlockPlacementClass.isInstance(packet.getWrappedObject());
    }
}
