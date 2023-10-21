package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemStack;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.potion.Potion;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.potion.PotionEffect;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.Vec3;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.entity.EntityLivingBase", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.entity.EntityLivingBase", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class EntityLivingBase extends Entity {
    @WrapMethod(deobfName = "isPotionActive", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(Lnet/minecraft/potion/Potion;)Z")
    @WrapMethod(deobfName = "isPotionActive", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(Lnet/minecraft/potion/Potion;)Z")
    public static Method isPotionActive_L;
    @WrapMethod(deobfName = "isPotionActive", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(I)Z")
    public static Method isPotionActive_I;
    @WrapMethod(deobfName = "getActivePotionEffect", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getActivePotionEffect", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getActivePotionEffect;
    @WrapField(deobfName = "moveForward", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "moveForward", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field moveForward;
    @WrapField(deobfName = "moveStrafing", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "moveStrafing", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field moveStrafing;
    @WrapMethod(deobfName = "canEntityBeSeen", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "canEntityBeSeen",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method canEntityBeSeen;
    @WrapMethod(deobfName = "jump",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "jump",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method jump;
    @ClassInstance
    public static Class EntityLivingBaseClass;
    @WrapMethod(deobfName = "getHealth",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getHealth",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getHealth;
    @WrapMethod(deobfName = "getMaxHealth",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getMaxHealth",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getMaxHealth;
    @WrapMethod(deobfName = "swingItem",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "swingArm",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method swingItem;
    @WrapField(deobfName = "rotationYawHead",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "rotationYawHead",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field rotationYawHead;
    @WrapField(deobfName = "renderYawOffset",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "renderYawOffset",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field renderYawOffset;
    @WrapField(deobfName = "hurtTime",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "hurtTime",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field hurtTime;
    @WrapMethod(deobfName = "onLivingUpdate",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "onUpdate",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method onLivingUpdate;
    @WrapField(deobfName = "jumpMovementFactor",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "jumpMovementFactor",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field jumpMovementFactor;
    @WrapMethod(deobfName = "getHeldItem",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getHeldItem;
    @WrapMethod(deobfName = "addPotionEffect", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method addPotionEffect;
    @WrapMethod(deobfName = "isChild", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isChild", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isChild;
    @WrapField(deobfName = "prevRotationYawHead", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapField(deobfName = "prevRotationYawHead", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field prevRotationYawHead;
    @WrapMethod(deobfName = "getItemInUseMaxCount", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getItemInUseMaxCount;
    @WrapField(deobfName = "jumpTicks", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapField(deobfName = "jumpTicks", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field jumpTicks;
    @WrapField(deobfName = "activeItemStack", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field activeItemStack;

    public EntityLivingBase(Object obj) {
        super(obj);
    }

    public boolean isPotionActive(Potion potion) {
        return (boolean) invokeMethod(isPotionActive_L, potion.getWrappedObject());
    }

    public boolean isPotionActive(int id) {
        return (boolean) invokeMethod(isPotionActive_I, id);
    }

    public PotionEffect getActivePotionEffect(Potion potion) {
        return new PotionEffect(invokeMethod(getActivePotionEffect, potion.getWrappedObject()));
    }

    public float getMoveForward() {
        return (float) getField(moveForward);
    }

    public float getMoveStrafing() {
        return (float) getField(moveStrafing);
    }
    public boolean canEntityBeSeen(Entity entity){
        return (boolean) invokeMethod(canEntityBeSeen,entity.getWrappedObject());
    }
    public void jump(){
        invokeMethod(jump);
    }
    public static boolean isEntityLivingBase(Entity o){
        return EntityLivingBaseClass.isInstance(o.getWrappedObject());
    }
    public float getHealth(){
        return (float) invokeMethod(getHealth);
    }
    public boolean isEntityAlive()
    {
        return !this.isDead() && this.getHealth() > 0.0F;
    }
    public void addPotionEffect(PotionEffect effect){
        invokeMethod(addPotionEffect,effect.getWrappedObject());
    }
    public void swingArm(Enum hand){
        invokeMethod(swingItem,hand);
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
        return (int) invokeMethod(getItemInUseMaxCount);
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
        return (float) getField(jumpMovementFactor);
    }
    public float getMaxHealth(){
        return (float) invokeMethod(getMaxHealth);
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
        return new ItemStack(invokeMethod(getHeldItem, enumHand));
    }

    public boolean isChild() {
        return (boolean) invokeMethod(isChild);
    }

    public ItemStack getActiveItemStack() {
        return new ItemStack(getField(activeItemStack));
    }

    public void setJumpTicks(int n) {
        setField(jumpTicks, n);
    }
}
