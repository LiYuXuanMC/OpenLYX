package com.reflectmc.reflect.wrapper.wrappers.minecraft.multiplayer;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemStack;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.BlockPos;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.EnumFacing;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.Vec3;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.world.WorldClient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.multiplayer.PlayerControllerMP",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.multiplayer.PlayerControllerMP",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class PlayerControllerMP extends WrapperBase {
    @WrapField(deobfName = "curBlockDamageMP",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "curBlockDamageMP",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field curBlockDamageMP;
    @WrapMethod(deobfName = "getBlockReachDistance",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getBlockReachDistance",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getBlockReachDistance;
    @ClassInstance
    public static Class PlayerControllerMPClass;
    @WrapMethod(deobfName = "attackEntity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "attackEntity",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method attackEntity;
    @WrapMethod(deobfName = "onPlayerRightClick",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "processRightClickBlock",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method onPlayerRightClick;
    @WrapMethod(deobfName = "windowClick",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "windowClick",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method windowClick;
    @WrapField(deobfName = "blockHitDelay", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "blockHitDelay", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field blockHitDelay;
    @WrapMethod(deobfName = "onPlayerDamageBlock", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "onPlayerDestroyBlock", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method onPlayerDamageBlock;
    @WrapMethod(deobfName = "extendedReach", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "extendedReach", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method extendedReach;
    @WrapMethod(deobfName = "gameIsSurvivalOrAdventure", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapMethod(deobfName = "gameIsSurvivalOrAdventure", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method gameIsSurvivalOrAdventure;
    @WrapMethod(deobfName = "isInCreativeMode", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isInCreativeMode", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isInCreativeMode;
    @WrapMethod(deobfName = "onStoppedUsingItem", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method onStoppedUsingItem;

    public PlayerControllerMP(Object obj) {
        super(obj);
    }

    public boolean gameIsSurvivalOrAdventure() {
        return (boolean) invokeMethod(gameIsSurvivalOrAdventure);
    }

    public float getCurBlockDamageMP() {
        return (float) getField(curBlockDamageMP);
    }

    public boolean onPlayerRightClick(EntityPlayerSP player,
                                      WorldClient world,
                                      ItemStack item,
                                      BlockPos hitPos,
                                      EnumFacing side,
                                      Vec3 vec) {
        return (boolean) invokeMethod(onPlayerRightClick,player.getWrappedObject()
                ,world.getWrappedObject()
                ,item.getWrappedObject()
                ,hitPos.getWrappedObject()
                ,side.getWrappedObject()
                ,vec.getWrappedObject());
    }
    public Enum processRightClickBlock(EntityPlayerSP player,
                                                   WorldClient world,
                                                   ItemStack item,
                                                   BlockPos hitPos,
                                                   EnumFacing side,
                                                   Vec3 vec){
        return (Enum) invokeMethod(onPlayerRightClick,player.getWrappedObject()
                ,world.getWrappedObject()
                ,item.getWrappedObject()
                ,hitPos.getWrappedObject()
                ,side.getWrappedObject()
                ,vec.getWrappedObject());
    }
    public ItemStack windowClick(int windowId, int slotId, int mouseButtonClicked, int mode, EntityPlayer playerIn)
    {
        return new ItemStack(invokeMethod(windowClick,windowId,slotId,mouseButtonClicked,mode,playerIn.getWrappedObject()));
    }
    public void setCurBlockDamageMP(float f){
        setField(curBlockDamageMP,f);
    }
    public void setBlockHitDelay(int delay){
        setField(blockHitDelay,delay);
    }
    public float getBlockReachDistance() {
        return (float) invokeMethod(getBlockReachDistance);
    }
    public boolean onPlayerDamageBlock(BlockPos bp, Enum face) {
        return (boolean) invokeMethod(onPlayerDamageBlock, bp.getWrappedObject(), face);
    }
    public boolean extendedReach() {
        return (boolean) invokeMethod(extendedReach);
    }
    public boolean isInCreativeMode() {
        return (boolean) invokeMethod(isInCreativeMode);
    }
    public void onStoppedUsingItem(EntityPlayer player) {
        invokeMethod(onStoppedUsingItem, player.getWrappedObject());
    }
}
