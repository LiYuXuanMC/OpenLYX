package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.Wrapper;
import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.AxisAlignedBB;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.Vec3;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.mouse.MovingObjectPosition;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.IChatComponent;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

@WrapperClass(deobfName = "net.minecraft.entity.Entity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.entity.Entity",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Entity extends WrapperBase {
    @WrapField(deobfName = "rotationYaw",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "rotationYaw",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field rotationYaw;
    @WrapField(deobfName = "rotationPitch",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "rotationPitch",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field rotationPitch;
    @WrapMethod(deobfName = "onUpdate",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "onUpdate",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method onUpdate;
    @WrapField(deobfName = "ticksExisted",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "ticksExisted",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field ticksExisted;
    @WrapMethod(deobfName = "playSound",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "playSound",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method playSound;
    @WrapField(deobfName = "posX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "posX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field posX;
    @WrapField(deobfName = "posY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "posY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field posY;
    @WrapField(deobfName = "posZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "posZ",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field posZ;
    @WrapField(deobfName = "lastTickPosX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "lastTickPosX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field lastTickPosX;
    @WrapField(deobfName = "lastTickPosY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "lastTickPosY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field lastTickPosY;
    @WrapField(deobfName = "lastTickPosZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "lastTickPosZ",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field lastTickPosZ;
    @WrapField(deobfName = "boundingBox",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "boundingBox",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field boundingBox;
    @ClassInstance
    public static Class EntityClass;
    @WrapField(deobfName = "onGround",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "onGround",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field onGround;
    @WrapMethod(deobfName = "getDisplayName",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getDisplayName",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getDisplayName;
    @WrapField(deobfName = "isDead",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "isDead",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field isDead;
    @WrapMethod(deobfName = "getName",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getName",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getName;
    @WrapMethod(deobfName = "getDistanceToEntity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getDistance",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/entity/Entity;)F")
    public static Method getDistanceToEntity;
    @WrapMethod(deobfName = "getDistance",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(DDD)D")
    public static Method getDistance_DDD;
    @WrapField(deobfName = "motionX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "motionX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field motionX;
    @WrapField(deobfName = "motionY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "motionY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field motionY;
    @WrapField(deobfName = "motionZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "motionZ",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field motionZ;
    @WrapMethod(deobfName = "isSprinting",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isSprinting",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isSprinting;
    @WrapField(deobfName = "prevPosX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "prevPosX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field prevPosX;
    @WrapField(deobfName = "prevPosY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "prevPosY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field prevPosY;
    @WrapField(deobfName = "prevPosZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "prevPosZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field prevPosZ;
    @WrapField(deobfName = "height",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "height",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field height;
    @WrapMethod(deobfName = "getEyeHeight",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getEyeHeight",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getEyeHeight;
    @WrapMethod(deobfName = "moveEntity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "move",targetEnvironment = {Environment.Forge1122,Environment.Forge1122})
    public static Method moveEntity;
    @WrapMethod(deobfName = "isRiding",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isRiding",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isRiding;
    @WrapField(deobfName = "stepHeight",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "stepHeight",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field stepHeight;
    @WrapField(deobfName = "isCollidedVertically",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "collidedVertically",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field isCollidedVertically;
    @WrapMethod(deobfName = "canBeCollidedWith",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "canBeCollidedWith",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method canBeCollidedWith;
    @WrapMethod(deobfName = "setPositionAndRotation",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setPositionAndRotation",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setPositionAndRotation;
    @WrapField(deobfName = "width",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "width",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field width;
    @WrapField(deobfName = "isInWeb",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "isInWeb",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field isInWeb;
    @WrapField(deobfName = "fallDistance",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "fallDistance",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field fallDistance;
    @WrapMethod(deobfName = "getHorizontalFacing",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getHorizontalFacing",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getHorizontalFacing;
    @WrapMethod(deobfName = "setPosition",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setPosition",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setPosition;
    @WrapMethod(deobfName = "setPositionAndUpdate",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setPositionAndUpdate",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setPositionAndUpdate;
    @WrapField(deobfName = "worldObj",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "world",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field worldObj;
    @WrapMethod(deobfName = "copyDataFromOld",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "copyDataFromOld",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method copyDataFromOld;
    @WrapMethod(deobfName = "copyLocationAndAnglesFrom",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "copyLocationAndAnglesFrom",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method copyLocationAndAnglesFrom;
    @WrapMethod(deobfName = "rayTrace",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "rayTrace",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method rayTrace;
    @WrapMethod(deobfName = "getPositionEyes",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getPositionEyes",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getPositionEyes;
    @WrapMethod(deobfName = "getLook",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getLook",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getLook;
    @WrapMethod(deobfName = "getCollisionBorderSize",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getCollisionBorderSize",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getCollisionBorderSize;
    @WrapField(deobfName = "ridingEntity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "ridingEntity",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field ridingEntity;
    @WrapMethod(deobfName = "getDistanceSqToEntity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getDistanceSq",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/entity/Entity;)D")
    public static Method getDistanceSqToEntity;
    @WrapMethod(deobfName = "getDistanceSq",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(DDD)D")
    @WrapMethod(deobfName = "getDistanceSq",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(DDD)D")
    public static Method getDistanceSq_D;
    @WrapMethod(deobfName = "canRiderInteract",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method canRiderInteract;
    @WrapMethod(deobfName = "isInvisible", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isInvisible",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isInvisible;
    @WrapMethod(deobfName = "isSneaking", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isSneaking",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isSneaking;
    @WrapField(deobfName = "isCollidedHorizontally",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field isCollidedHorizontally;
    @WrapMethod(deobfName = "getEntityBoundingBox",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getEntityBoundingBox;
    @WrapMethod(deobfName = "getUniqueID",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getUniqueID",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getUniqueID;
    @WrapField(deobfName = "inWater",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "inWater",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field isInWater;
    @WrapField(deobfName = "noClip", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "noClip", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field noClip;
    @WrapField(deobfName = "prevRotationYaw", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "prevRotationYaw", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field prevRotationYaw;
    @WrapField(deobfName = "prevRotationPitch", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "prevRotationPitch", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field prevRotationPitch;
    @WrapMethod(deobfName = "getPositionVector", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getPositionVector", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getPositionVector;
    @WrapField(deobfName = "entityId", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "entityId", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field entityId;
    @WrapField(deobfName = "dimension", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "dimension", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field dimension;
    @WrapMethod(deobfName = "addVelocity", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "addVelocity", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method addVelocity;
    @WrapMethod(deobfName = "getRotationYawHead", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getRotationYawHead", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getRotationYawHead;
    @WrapMethod(deobfName = "isInLava", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isInLava", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isInLava;
    @WrapField(deobfName = "isAirBorne", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field isAirBorne;

    public Entity(Object obj) {
        super(obj);
    }

    public int getDimension() {
        return (int) getField(dimension);
    }
    public void setDimension(int i1) {
        setField(dimension, i1);
    }
    public float getPrevRotationYaw() {
        return (float) getField(prevRotationYaw);
    }
    public void setPrevRotationYaw(float f) {
        setField(prevRotationYaw, f);
    }
    public float getPrevRotationPitch() {
        return (float) getField(prevRotationPitch);
    }
    public void setPrevRotationPitch(float f) {
        setField(prevRotationPitch, f);
    }
    public boolean getNoClip() {
        return (boolean)getField(noClip);
    }
    public void setNoClip(boolean b) {
        setField(noClip,b);
    }
    public boolean isInWater() {
        return (boolean) getField(isInWater);
    }
    public float getRotationPitch() {
        return (float) getField(rotationPitch);
    }
    public float getRotationYaw() {
        return (float) getField(rotationYaw);
    }
    public int getTicksExisted(){
        return (int) getField(ticksExisted);
    }
    public double getDistanceSq(double x,double y,double z){
        return (double) invokeMethod(getDistanceSq_D,x,y,z);
    }
    public World getWorld(){
        return new World(getField(worldObj));
    }
    public MovingObjectPosition rayTrace(double d, float f){
        return new MovingObjectPosition(invokeMethod(rayTrace,d,f));
    }
    public Vec3 getPositionEyes(float partialTicks)
    {
        return new Vec3(invokeMethod(getPositionEyes,partialTicks));
    }
    public Vec3 getLook(float partialTicks)
    {
        return new Vec3(invokeMethod(getLook,partialTicks));
    }
    public double getPosX(){
        return (double) getField(posX);
    }
    public double getPosY(){
        return (double) getField(posY);
    }
    public double getPosZ(){
        return (double) getField(posZ);
    }
    public double getLastTickPosX(){
        return (double) getField(lastTickPosX);
    }
    public double getLastTickPosY(){
        return (double) getField(lastTickPosY);
    }
    public double getLastTickPosZ(){
        return (double) getField(lastTickPosZ);
    }
    public AxisAlignedBB getEntityBoundingBox() {
        return new AxisAlignedBB(getField(boundingBox));
    }
    public void copyLocationAndAnglesFrom(Entity entity){
        invokeMethod(copyLocationAndAnglesFrom,entity.getWrappedObject());
    }
    public void setRotationYaw(float yaw){
        setField(rotationYaw,yaw);
    }
    public void setRotationPitch(float pitch){
        setField(rotationPitch,pitch);
    }
    public void setOnGround(boolean isOnGround){
        setField(onGround,isOnGround);
    }
    public boolean isOnGround(){
        return (boolean) getField(onGround);
    }
    public IChatComponent getDisplayName(){
        return new IChatComponent(invokeMethod(getDisplayName));
    }
    public boolean isDead(){
        return (boolean) getField(isDead);
    }
    public String getName(){
        return (String) invokeMethod(getName);
    }
    public float getDistanceToEntity(Entity entity){
        return (float) invokeMethod(getDistanceToEntity,entity.getWrappedObject());
    }
    public void copyDataFromOld(Entity entity){
        invokeMethod(copyDataFromOld,entity.getWrappedObject());
    }
    public double getMotionX(){
        return (double) getField(motionX);
    }
    public double getMotionY(){
        return (double) getField(motionY);
    }
    public double getMotionZ(){
        return (double) getField(motionZ);
    }
    public void setPrevPosX(double d){
        setField(prevPosX,d);
    }
    public void setPrevPosY(double d){
        setField(prevPosY,d);
    }
    public void setPrevPosZ(double d){
        setField(prevPosZ,d);
    }
    public boolean getIsInWeb() {
        return (boolean) getField(isInWeb);
    }
    public void setIsInWeb(boolean isIn) {
        setField(isInWeb,isIn);
    }
    public boolean isSprinting(){
        return (boolean) invokeMethod(isSprinting);
    }
    public double getPrevPosX(){
        return (double) getField(prevPosX);
    }
    public double getPrevPosY(){
        return (double) getField(prevPosY);
    }
    public double getPrevPosZ(){
        return (double) getField(prevPosZ);
    }
    public float getHeight(){
        return (float) getField(height);
    }
    public float getEyeHeight(){
        return (float) invokeMethod(getEyeHeight);
    }
    public void setMotionX(double d){
        setField(motionX,d);
    }
    public void setMotionY(double d){
        setField(motionY,d);
    }
    public void setMotionZ(double d){
        setField(motionZ,d);
    }
    public boolean isRiding(){
        return (boolean) invokeMethod(isRiding);
    }
    public void setStepHeight(float f){
        setField(stepHeight,f);
    }
    public float getStepHeight(){
        return (float) getField(stepHeight);
    }
    public boolean isCollidedVertically(){
        return (boolean) getField(isCollidedVertically);
    }
    public boolean isCollidedHorizontally(){
        return (boolean) getField(isCollidedHorizontally);
    }
    public boolean canBeCollidedWith(){
        return (boolean) invokeMethod(canBeCollidedWith);
    }
    public void setWidth(float f){
        setField(width,f);
    }
    public float getWidth(){
        return (float) getField(width);
    }
    public double getDistance(double x,double y,double z){
        return (double) invokeMethod(getDistance_DDD,x,y,z);
    }
    public float getFallDistance(){
        return (float) getField(fallDistance);
    }
    public void setPositionAndRotation(double x, double y, double z, float rotationYaw, float rotationPitch) {
        invokeMethod(setPositionAndRotation,x,y,z,rotationYaw,rotationPitch);
    }
    public Enum getHorizontalFacing(){
        return (Enum) invokeMethod(getHorizontalFacing);
    }
    public void setPosition(double x,double y,double z){
        invokeMethod(setPosition,x,y,z);
    }
    public void setPositionAndUpdate(double x,double y,double z){
        invokeMethod(setPositionAndUpdate,x,y,z);
    }
    public float getCollisionBorderSize(){
        return (float) invokeMethod(getCollisionBorderSize);
    }
    public Entity getRidingEntity(){
        return new Entity(getField(ridingEntity));
    }
    public boolean canRiderInteract(){
        return Wrapper.getMapper().isVersionMatch(Environment.Forge1122,Environment.Vanilla1122) && (boolean) invokeMethod(canRiderInteract);
    }
    public double getDistanceSqToEntity(Entity entity){
        return (double) invokeMethod(getDistanceSqToEntity,entity.getWrappedObject());
    }
    public boolean isInvisible(){
        return (boolean) invokeMethod(isInvisible);
    }
    public boolean isSneaking() {
        return (boolean) invokeMethod(isSneaking);
    }
    public UUID getUniqueID() {
        return (UUID) invokeMethod(getUniqueID);
    }
    public int getEntityId() {
        return (int) getField(entityId);
    }
    public float getRotationYawHead() {
        return (float) invokeMethod(getRotationYawHead);
    }
    public boolean isInLava() {
        return (boolean) invokeMethod(isInLava);
    }
    public boolean isAirBorne() {
        return (boolean) getField(isAirBorne);
    }
    public void playSound(String name,int volume,int pitch){
        invokeMethod(playSound,name,volume,pitch);
    }
}
