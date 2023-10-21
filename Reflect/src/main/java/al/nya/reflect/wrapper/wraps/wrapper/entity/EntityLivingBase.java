package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;
import al.nya.reflect.wrapper.wraps.wrapper.potion.PotionEffect;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumHand;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.EntityLivingBase", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.EntityLivingBase", targetMap = Maps.Srg1_12_2)
public class EntityLivingBase extends Entity {
    @WrapMethod(mcpName = "isPotionActive", targetMap = Maps.Srg1_8_9, signature = "(Lnet/minecraft/potion/Potion;)Z")
    @WrapMethod(mcpName = "isPotionActive", targetMap = Maps.Srg1_12_2, signature = "(Lnet/minecraft/potion/Potion;)Z")
    public static Method isPotionActive_L;
    @WrapMethod(mcpName = "isPotionActive", targetMap = Maps.Srg1_8_9, signature = "(I)Z")
    public static Method isPotionActive_I;
    @WrapMethod(mcpName = "getActivePotionEffect", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getActivePotionEffect", targetMap = Maps.Srg1_12_2)
    public static Method getActivePotionEffect;
    @WrapField(mcpName = "moveForward", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "moveForward", targetMap = Maps.Srg1_12_2)
    public static Field moveForward;
    @WrapField(mcpName = "moveStrafing", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "moveStrafing", targetMap = Maps.Srg1_12_2)
    public static Field moveStrafing;
    @WrapMethod(mcpName = "canEntityBeSeen", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "canEntityBeSeen",targetMap = Maps.Srg1_12_2)
    public static Method canEntityBeSeen;
    @WrapMethod(mcpName = "jump",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "jump",targetMap = Maps.Srg1_12_2)
    public static Method jump;
    @WrapClass(mcpName = "net.minecraft.entity.EntityLivingBase",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.EntityLivingBase",targetMap = Maps.Srg1_12_2)
    public static Class EntityLivingBaseClass;
    @WrapMethod(mcpName = "getHealth",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getHealth",targetMap = Maps.Srg1_12_2)
    public static Method getHealth;
    @WrapMethod(mcpName = "getMaxHealth",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getMaxHealth",targetMap = Maps.Srg1_12_2)
    public static Method getMaxHealth;
    @WrapMethod(mcpName = "swingItem",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "swingArm",targetMap = Maps.Srg1_12_2)
    public static Method swingItem;
    @WrapField(mcpName = "rotationYawHead",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "rotationYawHead",targetMap = Maps.Srg1_12_2)
    public static Field rotationYawHead;
    @WrapField(mcpName = "renderYawOffset",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderYawOffset",targetMap = Maps.Srg1_12_2)
    public static Field renderYawOffset;
    @WrapField(mcpName = "hurtTime",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "hurtTime",targetMap = Maps.Srg1_12_2)
    public static Field hurtTime;
    @WrapMethod(mcpName = "onLivingUpdate",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "onUpdate",targetMap = Maps.Srg1_12_2)
    public static Method onLivingUpdate;
    @WrapField(mcpName = "jumpMovementFactor",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "jumpMovementFactor",targetMap = Maps.Srg1_12_2)
    public static Field jumpMovementFactor;
    @WrapMethod(mcpName = "getHeldItem",targetMap = Maps.Srg1_12_2)
    public static Method getHeldItem;
    @WrapMethod(mcpName = "addPotionEffect", targetMap = Maps.Srg1_8_9)
    public static Method addPotionEffect;
    @WrapMethod(mcpName = "isChild", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isChild", targetMap = Maps.Srg1_12_2)
    public static Method isChild;
    @WrapField(mcpName = "prevRotationYawHead", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "prevRotationYawHead", targetMap = Maps.Srg1_8_9)
    public static Field prevRotationYawHead;
    @WrapMethod(mcpName = "getItemInUseMaxCount", targetMap = Maps.Srg1_12_2)
    public static Method getItemInUseMaxCount;
    @WrapField(mcpName = "jumpTicks", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "jumpTicks", targetMap = Maps.Srg1_8_9)
    public static Field jumpTicks;
    @WrapField(mcpName = "activeItemStack", targetMap = Maps.Srg1_12_2)
    public static Field activeItemStack;

    public EntityLivingBase(Object obj) {
        super(obj);
    }

    public boolean isPotionActive(Potion potion) {
        return (boolean) invoke(isPotionActive_L, potion.getWrapObject());
    }

    public boolean isPotionActive(int id) {
        return (boolean) invoke(isPotionActive_I, id);
    }

    public PotionEffect getActivePotionEffect(Potion potion) {
        return new PotionEffect(ReflectUtil.invoke(getActivePotionEffect, getWrapObject(), potion.getWrapObject()));
    }

    public float getMoveForward() {
        return (float) ReflectUtil.getField(moveForward, getWrapObject());
    }

    public float getMoveStrafing() {
        return (float) ReflectUtil.getField(moveStrafing, getWrapObject());
    }
    public boolean canEntityBeSeen(Entity entity){
        return (boolean) invoke(canEntityBeSeen,entity.getWrapObject());
    }
    public void jump(){
        invoke(jump);
    }
    public static boolean isEntityLivingBase(Entity o){
        return EntityLivingBaseClass.isInstance(o.getWrapObject());
    }
    public float getHealth(){
        return (float) invoke(getHealth);
    }
    public boolean isEntityAlive()
    {
        return !this.isDead() && this.getHealth() > 0.0F;
    }
    public void addPotionEffect(PotionEffect effect){
        invoke(addPotionEffect,effect.getWrapObject());
    }
    /**
     * If target env 1.12.2 auto swing Main_Hand
     */
    public void swingItem(){
        if (Wrapper.env.equals(Maps.Srg1_12_2)){
            swingArm(EnumHand.MAIN_HAND);
            return;
        }
        invoke(swingItem);
    }
    public void swingArm(Enum hand){
        invoke(swingItem,hand);
    }
    public void setRotationYawHead(float yaw){
        setField(rotationYawHead,yaw);
    }
    public void setRenderYawOffset(float yaw){
        setField(renderYawOffset,yaw);
    }
    public float getRenderYawOffset(){
        return (float) getField(renderYawOffset);
    }
    public float getRotationYawHead(){
        return (float) getField(rotationYawHead);
    }
    public int getHurtTime(){
        return (int) getField(hurtTime);
    }
    public int getItemInUseMaxCount(){
        return (int) invoke(getItemInUseMaxCount);
    }
    public void setPrevRotationYawHead(float yaw){
        setField(prevRotationYawHead,yaw);
    }
    public float getPrevRotationYawHead(){
        return (float) getField(prevRotationYawHead);
    }

    public void setJumpMovementFactor(float speed) {
        setField(jumpMovementFactor,speed);
    }
    public float getJumpMovementFactor() {
        return (float) ReflectUtil.getField(jumpMovementFactor,getWrapObject());
    }
    public float getMaxHealth(){
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

    public void setJumpTicks(int n) {
        setField(jumpTicks, n);
    }
}
