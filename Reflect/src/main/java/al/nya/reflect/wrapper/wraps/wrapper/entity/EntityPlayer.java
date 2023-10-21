package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.GameProfile;
import al.nya.reflect.wrapper.wraps.wrapper.item.Container;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumHand;
import al.nya.reflect.wrapper.wraps.wrapper.utils.FoodStats;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.player.EntityPlayer",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.player.EntityPlayer",targetMap = Maps.Srg1_12_2)
public class EntityPlayer extends EntityLivingBase {
    @WrapField(mcpName = "inventory",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "inventory",targetMap = Maps.Srg1_12_2)
    public static Field inventory;
    @WrapField(mcpName = "itemInUse",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "itemStackMainHand",targetMap = Maps.Srg1_12_2)//?
    public static Field itemInUse;
    @WrapClass(mcpName = "net.minecraft.entity.player.EntityPlayer",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.player.EntityPlayer",targetMap = Maps.Srg1_12_2)
    public static Class EntityPlayerClass;
    @WrapField(mcpName = "foodStats",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "foodStats",targetMap = Maps.Srg1_12_2)
    public static Field foodStats;
    @WrapField(mcpName = "sleeping",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "sleeping",targetMap = Maps.Srg1_12_2)
    public static Field sleeping;
    @WrapField(mcpName = "inventoryContainer",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "inventoryContainer",targetMap = Maps.Srg1_12_2)
    public static Field inventoryContainer;
    @WrapField(mcpName = "openContainer",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "openContainer",targetMap = Maps.Srg1_12_2)
    public static Field openContainer;
    @WrapField(mcpName = "gameProfile",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "gameProfile",targetMap = Maps.Srg1_12_2)
    public static Field gameProfile;
    @WrapMethod(mcpName = "isSpectator",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isSpectator",targetMap = Maps.Srg1_12_2)
    public static Method isSpectator;
    @WrapMethod(mcpName = "getCurrentEquippedItem",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getHeldItemMainhand",targetMap = Maps.Srg1_12_2)
    public static Method getCurrentEquippedItem;
    @WrapField(mcpName = "itemInUseCount",targetMap = Maps.Srg1_8_9)
    public static Field itemInUseCount;
    @WrapMethod(mcpName = "isBlocking",targetMap = Maps.Srg1_8_9)
    public static Method isBlocking;
    @WrapMethod(mcpName = "getItemInUseDuration",targetMap = Maps.Srg1_8_9)
    public static Method getItemInUseDuration;
    @WrapMethod(mcpName = "getHeldItem", targetMap = Maps.Srg1_8_9)
    public static Method getHeldItem;
    @WrapField(mcpName = "capabilities", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "capabilities", targetMap = Maps.Srg1_12_2)
    public static Field capabilities;
    @WrapField(mcpName = "cameraYaw", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "cameraYaw", targetMap = Maps.Srg1_8_9)
    public static Field cameraYaw;
    @WrapField(mcpName = "speedInAir", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "speedInAir", targetMap = Maps.Srg1_12_2)
    public static Field speedInAir;
    @WrapMethod(mcpName = "attackTargetEntityWithCurrentItem", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "attackTargetEntityWithCurrentItem", targetMap = Maps.Srg1_12_2)
    public static Method attackTargetEntityWithCurrentItem;
    @WrapMethod(mcpName = "clonePlayer", targetMap = Maps.Srg1_8_9)
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
        return new InventoryPlayer(ReflectUtil.getField(inventory,getWrapObject()));
    }
    public boolean isSpectator(){
        return (boolean) invoke(isSpectator);
    }
    public ItemStack getItemInUse() {
        return new ItemStack(ReflectUtil.getField(itemInUse,getWrapObject()));
    }

    public static boolean isEntityPlayer(Entity entityWrapper) {
        return EntityPlayerClass.isInstance(entityWrapper.getWrapObject());
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
        return new FoodStats(ReflectUtil.getField(foodStats, getWrapObject()));
    }

    public boolean isPlayerSleeping() {
        return (boolean) getField(sleeping);
    }

    public void setItemInUseCount(int i) {
        setField(itemInUseCount, i);
    }
    public boolean isBlocking(){
        return (boolean) invoke(isBlocking);
    }
    public Container getInventoryContainer(){
        return new Container(getField(inventoryContainer));
    }
    public ItemStack getCurrentEquippedItem(){
        return new ItemStack(invoke(getCurrentEquippedItem));
    }
    public Container getOpenContainer(){
        return new Container(getField(openContainer));
    }
    public int getItemInUseDuration(){
        return Wrapper.env.equals(Maps.Srg1_12_2) ? getItemInUseMaxCount () : (int) invoke(getItemInUseDuration);
    }
    public ItemStack getHeldItem(){
        if (Wrapper.env.equals(Maps.Srg1_12_2)) return getHeldItem(EnumHand.MAIN_HAND);
        return new ItemStack(invoke(getHeldItem));
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
        invoke(clonePlayer, oldPlayer.getWrapObject(), respawnFromEnd);
    }
}
