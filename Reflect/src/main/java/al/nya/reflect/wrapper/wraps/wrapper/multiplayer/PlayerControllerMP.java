package al.nya.reflect.wrapper.wraps.wrapper.multiplayer;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.PlayerControllerMP",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.multiplayer.PlayerControllerMP",targetMap = Maps.Srg1_12_2)
public class PlayerControllerMP extends IWrapper {
    @WrapField(mcpName = "curBlockDamageMP",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "curBlockDamageMP",targetMap = Maps.Srg1_12_2)
    public static Field curBlockDamageMP;
    @WrapMethod(mcpName = "getBlockReachDistance",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getBlockReachDistance",targetMap = Maps.Srg1_12_2)
    public static Method getBlockReachDistance;
    @WrapClass(mcpName = "net.minecraft.client.multiplayer.PlayerControllerMP",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.multiplayer.PlayerControllerMP",targetMap = Maps.Srg1_12_2)
    public static Class PlayerControllerMPClass;
    @WrapMethod(mcpName = "attackEntity",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "attackEntity",targetMap = Maps.Srg1_12_2)
    public static Method attackEntity;
    @WrapMethod(mcpName = "onPlayerRightClick",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "processRightClickBlock",targetMap = Maps.Srg1_12_2)
    public static Method onPlayerRightClick;
    @WrapMethod(mcpName = "windowClick",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "windowClick",targetMap = Maps.Srg1_12_2)
    public static Method windowClick;
    @WrapField(mcpName = "blockHitDelay", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "blockHitDelay", targetMap = Maps.Srg1_12_2)
    public static Field blockHitDelay;
    @WrapMethod(mcpName = "onPlayerDamageBlock", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "onPlayerDestroyBlock", targetMap = Maps.Srg1_8_9)
    public static Method onPlayerDamageBlock;
    @WrapMethod(mcpName = "extendedReach", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "extendedReach", targetMap = Maps.Srg1_12_2)
    public static Method extendedReach;
    @WrapMethod(mcpName = "gameIsSurvivalOrAdventure", targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "gameIsSurvivalOrAdventure", targetMap = Maps.Srg1_8_9)
    public static Method gameIsSurvivalOrAdventure;
    @WrapMethod(mcpName = "isInCreativeMode", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isInCreativeMode", targetMap = Maps.Srg1_12_2)
    public static Method isInCreativeMode;
    @WrapMethod(mcpName = "onStoppedUsingItem", targetMap = Maps.Srg1_8_9)
    public static Method onStoppedUsingItem;

    public PlayerControllerMP(Object obj) {
        super(obj);
    }

    public boolean gameIsSurvivalOrAdventure() {
        return (boolean) invoke(gameIsSurvivalOrAdventure);
    }

    public float getCurBlockDamageMP() {
        return (float) ReflectUtil.getField(curBlockDamageMP, getWrapObject());
    }

    public boolean onPlayerRightClick(EntityPlayerSP player,
                                      WorldClient world,
                                      ItemStack item,
                                      BlockPos hitPos,
                                      Enum side,
                                      Vec3 vec) {
        return (boolean) invoke(onPlayerRightClick,player.getWrapObject()
                ,world.getWrapObject()
                ,item.getWrapObject()
                ,hitPos.getWrapObject()
                ,side
                ,vec.getWrapObject());
    }
    public Enum processRightClickBlock(EntityPlayerSP player,
                                                   WorldClient world,
                                                   ItemStack item,
                                                   BlockPos hitPos,
                                                   Enum side,
                                                   Vec3 vec){
        return (Enum) invoke(onPlayerRightClick,player.getWrapObject()
                ,world.getWrapObject()
                ,item.getWrapObject()
                ,hitPos.getWrapObject()
                ,side
                ,vec.getWrapObject());
    }
    public ItemStack windowClick(int windowId, int slotId, int mouseButtonClicked, int mode, EntityPlayer playerIn)
    {
        return new ItemStack(invoke(windowClick,windowId,slotId,mouseButtonClicked,mode,playerIn.getWrapObject()));
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
        return (boolean) invoke(onPlayerDamageBlock, bp.getWrapObject(), face);
    }

    public boolean extendedReach() {
        return (boolean) invoke(extendedReach);
    }

    public boolean isInCreativeMode() {
        return (boolean) invoke(isInCreativeMode);
    }

    public void onStoppedUsingItem(EntityPlayer player) {
        invoke(onStoppedUsingItem, player.getWrapObject());
    }
}
