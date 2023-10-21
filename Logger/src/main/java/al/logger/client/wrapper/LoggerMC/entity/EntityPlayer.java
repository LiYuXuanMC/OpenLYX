package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.GameProfile;
import al.logger.client.wrapper.LoggerMC.item.Container;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.utils.EnumHand;
import al.logger.client.wrapper.LoggerMC.utils.FoodStats;
import al.logger.client.wrapper.environment.EnvironmentDetector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.player.EntityPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.player.EntityPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityPlayer extends EntityLivingBase {
    @WrapField(mcpName = "inventory",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "inventory",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field inventory;
    @WrapField(mcpName = "itemInUse",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "itemStackMainHand",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})//?
    public static Field itemInUse;
    @ClassInstance
    public static Class EntityPlayerClass;
    @WrapField(mcpName = "foodStats",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "foodStats",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field foodStats;
    @WrapField(mcpName = "sleeping",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "sleeping",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field sleeping;
    @WrapField(mcpName = "inventoryContainer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "inventoryContainer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field inventoryContainer;
    @WrapField(mcpName = "openContainer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "openContainer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field openContainer;
    @WrapField(mcpName = "gameProfile",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "gameProfile",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field gameProfile;
    @WrapMethod(mcpName = "isSpectator",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isSpectator",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isSpectator;
    @WrapMethod(mcpName = "getCurrentEquippedItem",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getHeldItemMainhand",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getCurrentEquippedItem;
    @WrapField(mcpName = "itemInUseCount",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field itemInUseCount;
    @WrapMethod(mcpName = "isBlocking",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method isBlocking;
    @WrapMethod(mcpName = "getItemInUseDuration",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getItemInUseDuration;
    @WrapMethod(mcpName = "getHeldItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getHeldItem;
    @WrapField(mcpName = "capabilities", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "capabilities", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field capabilities;
    @WrapField(mcpName = "getItemStackFromSlot", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getItemStackFromSlot;
    @WrapField(mcpName = "cameraYaw", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "cameraYaw", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field cameraYaw;
    @WrapField(mcpName = "speedInAir", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "speedInAir", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field speedInAir;
    @WrapMethod(mcpName = "attackTargetEntityWithCurrentItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "attackTargetEntityWithCurrentItem", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method attackTargetEntityWithCurrentItem;
    @WrapMethod(mcpName = "clonePlayer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method clonePlayer;
    @WrapMethod(mcpName = "isWearing", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isWearing", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isWearing;
    @WrapField(mcpName = "prevChasingPosX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevChasingPosX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevChasingPosX;
    @WrapField(mcpName = "prevChasingPosY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevChasingPosY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevChasingPosY;
    @WrapField(mcpName = "prevChasingPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevChasingPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevChasingPosZ;

    @WrapField(mcpName = "chasingPosX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "chasingPosX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field chasingPosX;
    @WrapField(mcpName = "chasingPosY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "chasingPosY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field chasingPosY;
    @WrapField(mcpName = "chasingPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "chasingPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field chasingPosZ;
    @WrapField(mcpName = "prevCameraYaw",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevCameraYaw",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevCameraYaw;
    @WrapMethod(mcpName = "isUser",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isUser",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isUser;
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
        return new InventoryPlayer(ReflectUtil.getField(inventory,getWrappedObject()));
    }
    public boolean isSpectator(){
        return (boolean) invoke(isSpectator);
    }
    public ItemStack getItemInUse() {
        return new ItemStack(ReflectUtil.getField(itemInUse,getWrappedObject()));
    }

    public static boolean isEntityPlayer(Entity entityWrapper) {
        return EntityPlayerClass.isInstance(entityWrapper.getWrappedObject());
    }
    public boolean isUser() {
        return (boolean) invoke(isUser);
    }
    public boolean isWearing(Enum modelPart){
        return (boolean) invoke(isWearing,modelPart);
    }

    public GameProfile getGameProfile() {
        return new GameProfile(getField(gameProfile));
    }

    public boolean isUsingItem() {
        //1.8.9 Only
        return !getItemInUse().isNull();
    }

    public ItemStack getItemStackFromSlot(Enum ew){
        //1.12.2 Only
        return new ItemStack(invoke(getItemStackFromSlot,ew));
    }
    public ItemStack getItemStackMainHand() {
        return new ItemStack(getField(itemInUse));
    }

    public FoodStats getFoodStats() {
        return new FoodStats(ReflectUtil.getField(foodStats, getWrappedObject()));
    }

    public boolean isPlayerSleeping() {
        return (boolean) getField(sleeping);
    }
    public int getItemInUseCount(){
        return (int) getField(itemInUseCount);
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
        return Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft()) ? getItemInUseMaxCount () : (int) invoke(getItemInUseDuration);
    }
    public ItemStack getHeldItem(){
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())) return getHeldItem(EnumHand.MAIN_HAND);
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
        invoke(clonePlayer, oldPlayer.getWrappedObject(), respawnFromEnd);
    }

    public double getPrevChasingPosX() {
        return (double) getField(prevChasingPosX);
    }

    public double getPrevChasingPosY() {
        return (double) getField(prevChasingPosY);
    }

    public double getPrevChasingPosZ() {
        return (double) getField(prevChasingPosZ);
    }

    public double getChasingPosX() {
        return (double) getField(chasingPosX);
    }

    public double getChasingPosY() {
        return (double) getField(chasingPosY);
    }

    public double getChasingPosZ() {
        return (double) getField(chasingPosZ);
    }

    public float getPrevCameraYaw() {
        return (float) getField(prevCameraYaw);
    }
}
