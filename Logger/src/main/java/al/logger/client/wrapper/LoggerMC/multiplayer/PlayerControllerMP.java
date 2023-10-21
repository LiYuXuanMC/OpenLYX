package al.logger.client.wrapper.LoggerMC.multiplayer;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.PlayerControllerMP",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.multiplayer.PlayerControllerMP",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class PlayerControllerMP extends IWrapper {
    @WrapField(mcpName = "curBlockDamageMP",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "curBlockDamageMP",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field curBlockDamageMP;
    @WrapMethod(mcpName = "getBlockReachDistance",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getBlockReachDistance",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getBlockReachDistance;
@ClassInstance    
public static Class PlayerControllerMPClass;
    @WrapMethod(mcpName = "attackEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "attackEntity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method attackEntity;
    @WrapMethod(mcpName = "onPlayerRightClick",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "processRightClickBlock",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method onPlayerRightClick;
    @WrapMethod(mcpName = "windowClick",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "windowClick",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method windowClick;
    @WrapField(mcpName = "blockHitDelay", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "blockHitDelay", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field blockHitDelay;
    @WrapMethod(mcpName = "onPlayerDamageBlock", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "onPlayerDestroyBlock", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method onPlayerDamageBlock;
    @WrapMethod(mcpName = "extendedReach", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "extendedReach", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method extendedReach;
    @WrapMethod(mcpName = "gameIsSurvivalOrAdventure", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "gameIsSurvivalOrAdventure", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method gameIsSurvivalOrAdventure;
    @WrapMethod(mcpName = "isInCreativeMode", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isInCreativeMode", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isInCreativeMode;
    @WrapMethod(mcpName = "onStoppedUsingItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method onStoppedUsingItem;

    @WrapMethod(mcpName = "sendUseItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method sendUseItem;
    public PlayerControllerMP(Object obj) {
        super(obj);
    }

    public boolean gameIsSurvivalOrAdventure() {
        return (boolean) invoke(gameIsSurvivalOrAdventure);
    }

    public float getCurBlockDamageMP() {
        return (float) ReflectUtil.getField(curBlockDamageMP, getWrappedObject());
    }

    public void attackEntity(EntityPlayer player, Entity target) {
        invoke(attackEntity, player.getWrappedObject(), target.getWrappedObject());
    }
    public void sendUseItem(EntityPlayer playerIn, WorldClient worldIn, ItemStack itemStackIn) {
        invoke(sendUseItem, playerIn, worldIn, itemStackIn);
    }
    public boolean onPlayerRightClick(EntityPlayerSP player,
                                      WorldClient world,
                                      ItemStack item,
                                      BlockPos hitPos,
                                      Enum side,
                                      Vec3 vec) {
        return (boolean) invoke(onPlayerRightClick,player.getWrappedObject()
                ,world.getWrappedObject()
                ,item.getWrappedObject()
                ,hitPos.getWrappedObject()
                ,side
                ,vec.getWrappedObject());
    }
    public Enum processRightClickBlock(EntityPlayerSP player,
                                                   WorldClient world,
                                                   ItemStack item,
                                                   BlockPos hitPos,
                                                   Enum side,
                                                   Vec3 vec){
        return (Enum) invoke(onPlayerRightClick,player.getWrappedObject()
                ,world.getWrappedObject()
                ,item.getWrappedObject()
                ,hitPos.getWrappedObject()
                ,side
                ,vec.getWrappedObject());
    }
    public ItemStack windowClick(int windowId, int slotId, int mouseButtonClicked, int mode, EntityPlayer playerIn)
    {
        return new ItemStack(invoke(windowClick,windowId,slotId,mouseButtonClicked,mode,playerIn.getWrappedObject()));
    }
    public void setCurBlockDamageMP(float f){
        setField(curBlockDamageMP,f);
    }
    public void setBlockHitDelay(int delay){
        setField(blockHitDelay,delay);
    }

    public float getBlockReachDistance() {
        return (float) invoke(getBlockReachDistance);
    }

    public boolean onPlayerDamageBlock(BlockPos bp, Enum face) {
        return (boolean) invoke(onPlayerDamageBlock, bp.getWrappedObject(), face);
    }

    public boolean extendedReach() {
        return (boolean) invoke(extendedReach);
    }

    public boolean isInCreativeMode() {
        return (boolean) invoke(isInCreativeMode);
    }

    public void onStoppedUsingItem(EntityPlayer player) {
        invoke(onStoppedUsingItem, player.getWrappedObject());
    }
}
