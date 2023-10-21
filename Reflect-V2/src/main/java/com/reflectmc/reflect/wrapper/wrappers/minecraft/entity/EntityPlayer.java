package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.Wrapper;
import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.GameProfile;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.Container;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemStack;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.FoodStats;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.entity.player.EntityPlayer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.entity.player.EntityPlayer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class EntityPlayer extends EntityLivingBase {
    @WrapField(deobfName = "inventory",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "inventory",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field inventory;
    @WrapField(deobfName = "itemInUse",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "itemStackMainHand",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})//?
    public static Field itemInUse;
    @ClassInstance
    public static Class EntityPlayerClass;
    @WrapField(deobfName = "foodStats",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "foodStats",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field foodStats;
    @WrapField(deobfName = "sleeping",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "sleeping",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field sleeping;
    @WrapField(deobfName = "inventoryContainer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "inventoryContainer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field inventoryContainer;
    @WrapField(deobfName = "openContainer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "openContainer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field openContainer;
    @WrapField(deobfName = "gameProfile",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "gameProfile",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field gameProfile;
    @WrapMethod(deobfName = "isSpectator",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isSpectator",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isSpectator;
    @WrapMethod(deobfName = "getCurrentEquippedItem",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getHeldItemMainhand",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getCurrentEquippedItem;
    @WrapField(deobfName = "itemInUseCount",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field itemInUseCount;
    @WrapMethod(deobfName = "isBlocking",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method isBlocking;
    @WrapMethod(deobfName = "getItemInUseDuration",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getItemInUseDuration;
    @WrapMethod(deobfName = "getHeldItem", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getHeldItem;
    @WrapField(deobfName = "capabilities", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "capabilities", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field capabilities;
    @WrapField(deobfName = "cameraYaw", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapField(deobfName = "cameraYaw", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field cameraYaw;
    @WrapField(deobfName = "speedInAir", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "speedInAir", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field speedInAir;
    @WrapMethod(deobfName = "attackTargetEntityWithCurrentItem", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "attackTargetEntityWithCurrentItem", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method attackTargetEntityWithCurrentItem;
    @WrapMethod(deobfName = "clonePlayer", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method clonePlayer;

    public EntityPlayer(Object obj) {
        super(obj);
    }

    public float getCameraYaw() {
        return (float) getField(cameraYaw);
    }
    public void setCameraYaw(float f) {
        setField(cameraYaw, f);
    }
    public InventoryPlayer getInventory() {
        return new InventoryPlayer(getField(inventory));
    }
    public boolean isSpectator(){
        return (boolean) invokeMethod(isSpectator);
    }
    public ItemStack getItemInUse() {
        return new ItemStack(getField(itemInUse));
    }
    public static boolean isEntityPlayer(Entity entityWrapper) {
        return EntityPlayerClass.isInstance(entityWrapper.getWrappedObject());
    }
    public GameProfile getGameProfile() {
        return new GameProfile(getField(gameProfile));
    }
    public boolean isUsingItem() {
        //1.8.9 Only
        return !getItemInUse().isNull();
    }
    public ItemStack getItemStackMainHand() {
        return new ItemStack(getField(itemInUse));
    }
    public FoodStats getFoodStats() {
        return new FoodStats(getField(foodStats));
    }
    public boolean isPlayerSleeping() {
        return (boolean) getField(sleeping);
    }
    public void setItemInUseCount(int i) {
        setField(itemInUseCount, i);
    }
    public boolean isBlocking(){
        return (boolean) invokeMethod(isBlocking);
    }
    public Container getInventoryContainer(){
        return new Container(getField(inventoryContainer));
    }
    public ItemStack getCurrentEquippedItem(){
        return new ItemStack(invokeMethod(getCurrentEquippedItem));
    }
    public Container getOpenContainer(){
        return new Container(getField(openContainer));
    }
    public int getItemInUseDuration(){
        return Wrapper.getMapper().isVersionMatch(Environment.Forge1122,Environment.Vanilla1122) ? getItemInUseMaxCount () : (int) invokeMethod(getItemInUseDuration);
    }
    public ItemStack getHeldItem(){
        return new ItemStack(invokeMethod(getHeldItem));
    }
    public PlayerCapabilities getCapabilities() {
        return new PlayerCapabilities(getField(capabilities));
    }
    public float getSpeedInAir() {
        return (float) getField(speedInAir);
    }
    public void setSpeedInAir(float speed) {
        setField(speedInAir, speed);
    }
    public void clonePlayer(EntityPlayer oldPlayer, boolean respawnFromEnd) {
        invokeMethod(clonePlayer, oldPlayer.getWrappedObject(), respawnFromEnd);
    }
}
