package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.potion.Potion;
import al.logger.client.wrapper.LoggerMC.potion.PotionEffect;
import al.logger.client.wrapper.LoggerMC.utils.EnumHand;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.LoggerMC.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.EntityLivingBase", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.EntityLivingBase", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityLivingBase extends Entity {

    @WrapMethod(mcpName = "getTotalArmorValue", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getTotalArmorValue", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getTotalArmorValue;
    @WrapMethod(mcpName = "getSwingProgress", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getSwingProgress", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getSwingProgress;
    @WrapMethod(mcpName = "isPotionActive", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(Lnet/minecraft/potion/Potion;)Z")
    @WrapMethod(mcpName = "isPotionActive", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(Lnet/minecraft/potion/Potion;)Z")
    public static Method isPotionActive_L;
    @WrapMethod(mcpName = "isPotionActive", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(I)Z")
    public static Method isPotionActive_I;
    @WrapMethod(mcpName = "getActivePotionEffect", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getActivePotionEffect", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getActivePotionEffect;
    @WrapField(mcpName = "moveForward", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "moveForward", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field moveForward;
    @WrapField(mcpName = "moveStrafing", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "moveStrafing", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field moveStrafing;
    @WrapMethod(mcpName = "canEntityBeSeen", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "canEntityBeSeen", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method canEntityBeSeen;
    @WrapMethod(mcpName = "jump", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "jump", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method jump;
    @WrapMethod(mcpName = "getEquipmentInSlot", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getEquipmentInSlot", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEquipmentInSlot;
    @ClassInstance
    public static Class EntityLivingBaseClass;
    @WrapMethod(mcpName = "getHealth", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getHealth", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getHealth;
    @WrapMethod(mcpName = "getMaxHealth", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getMaxHealth", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getMaxHealth;
    @WrapMethod(mcpName = "swingItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "swingArm", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method swingItem;
    @WrapField(mcpName = "rotationYawHead", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "rotationYawHead", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field rotationYawHead;
    @WrapField(mcpName = "renderYawOffset", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderYawOffset", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderYawOffset;
    @WrapField(mcpName = "hurtTime", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "hurtTime", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field hurtTime;
    @WrapMethod(mcpName = "onLivingUpdate", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "onUpdate", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method onLivingUpdate;
    @WrapField(mcpName = "jumpMovementFactor", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "jumpMovementFactor", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field jumpMovementFactor;
    @WrapMethod(mcpName = "getHeldItem", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getHeldItem;
    @WrapMethod(mcpName = "addPotionEffect", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method addPotionEffect;
    @WrapMethod(mcpName = "isChild", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isChild", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isChild;
    @WrapField(mcpName = "prevRotationYawHead", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "prevRotationYawHead", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field prevRotationYawHead;
    @WrapMethod(mcpName = "getItemInUseMaxCount", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getItemInUseMaxCount;
    @WrapField(mcpName = "jumpTicks", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "jumpTicks", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field jumpTicks;
    @WrapField(mcpName = "activeItemStack", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field activeItemStack;
    @WrapField(mcpName = "isSwingInProgress", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field isSwingInProgress;
    @WrapField(mcpName = "prevRenderYawOffset", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field prevRenderYawOffset;
    @WrapMethod(mcpName = "isPlayerSleeping", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method isPlayerSleeping;

    public EntityLivingBase(Object obj) {
        super(obj);
    }

    public boolean isPotionActive(Potion potion) {
        return (boolean) invoke(isPotionActive_L, potion.getWrappedObject());
    }

    public boolean isPotionActive(int id) {
        return (boolean) invoke(isPotionActive_I, id);
    }

    public PotionEffect getActivePotionEffect(Potion potion) {
        return new PotionEffect(ReflectUtil.invoke(getActivePotionEffect, getWrappedObject(), potion.getWrappedObject()));
    }

    public float getPrevRenderYawOffset() {
        return (float) getField(prevRenderYawOffset);
    }

    public void setPrevRenderYawOffset(float yaw) {
        setField(prevRenderYawOffset, yaw);
    }

    public float getMoveForward() {
        return (float) ReflectUtil.getField(moveForward, getWrappedObject());
    }

    public float getMoveStrafing() {
        return (float) ReflectUtil.getField(moveStrafing, getWrappedObject());
    }

    public boolean canEntityBeSeen(Entity entity) {
        return (boolean) invoke(canEntityBeSeen, entity.getWrappedObject());
    }

    public void jump() {
        invoke(jump);
    }

    public static boolean isEntityLivingBase(Entity o) {
        return EntityLivingBaseClass.isInstance(o.getWrappedObject());
    }

    public float getHealth() {
        return (float) invoke(getHealth);
    }

    public boolean isEntityAlive() {
        return !this.isDead() && this.getHealth() > 0.0F;
    }

    public void addPotionEffect(PotionEffect effect) {
        invoke(addPotionEffect, effect.getWrappedObject());
    }

    /**
     * If target env 1.12.2 auto swing Main_Hand
     */
    public void swingItem() {
        invoke(swingItem);
    }

    public void swingArm(Enum hand) {
        invoke(swingItem, hand);
    }

    public float getSwingProgress(float partialTicks) {
        return (float) invoke(getSwingProgress, partialTicks);
    }

    public void setRotationYawHead(float yaw) {
        setField(rotationYawHead, yaw);
    }

    public void setRenderYawOffset(float yaw) {
        setField(renderYawOffset, yaw);
    }

    public float getRenderYawOffset() {
        return (float) getField(renderYawOffset);
    }

    public float getRotationYawHead() {
        return (float) getField(rotationYawHead);
    }

    public int getHurtTime() {
        return (int) getField(hurtTime);
    }

    public int getItemInUseMaxCount() {
        return (int) invoke(getItemInUseMaxCount);
    }

    public void setPrevRotationYawHead(float yaw) {
        setField(prevRotationYawHead, yaw);
    }

    public float getPrevRotationYawHead() {
        return (float) getField(prevRotationYawHead);
    }

    public int getTotalArmorValue() {
        return (int) invoke(getTotalArmorValue);
    }

    public void setJumpMovementFactor(float speed) {
        setField(jumpMovementFactor, speed);
    }

    public float getJumpMovementFactor() {
        return (float) ReflectUtil.getField(jumpMovementFactor, getWrappedObject());
    }

    public float getMaxHealth() {
        return (float) invoke(getMaxHealth);
    }

    public boolean canEntityBeSeenFixed(Entity entityIn) {
        Vec3 vec3 = new Vec3(this.getPosX(), this.getPosY() + (double) this.getEyeHeight(), this.getPosZ());
        double entityPosX = entityIn.getPosX();
        double entityPosY = entityIn.getPosY();
        double entityPosZ = entityIn.getPosZ();
        World worldObj = getWorld();
        return worldObj.rayTraceBlocks(vec3, new Vec3(entityPosX, entityPosY + (double) entityIn.getEyeHeight(), entityPosZ)).isNull()
                || worldObj.rayTraceBlocks(vec3, new Vec3(entityPosX, entityPosY, entityPosZ)).isNull();
    }

    public ItemStack getHeldItem(Enum<?> enumHand) {
        return new ItemStack(invoke(getHeldItem, enumHand));
    }

    public boolean isChild() {
        return (boolean) invoke(isChild);
    }

    public ItemStack getActiveItemStack() {
        return new ItemStack(getField(activeItemStack));
    }

    public ItemStack getEquipmentInSlot(int slot) {
        return new ItemStack(invoke(getEquipmentInSlot, slot));
    }

    public void setJumpTicks(int n) {
        setField(jumpTicks, n);
    }

    public boolean isSwingInProgress() {
        return (boolean) getField(isSwingInProgress);
    }

    public boolean isPlayerSleeping(){
        return (boolean) invoke(isPlayerSleeping);
    }
}
