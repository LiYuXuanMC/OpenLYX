package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ResourceLocation;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.SoundEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

@WrapperClass(mcpName = "net.minecraft.entity.Entity",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.Entity",targetMap = Maps.Srg1_12_2)
public class Entity extends IWrapper {
    @WrapField(mcpName = "rotationYaw",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "rotationYaw",targetMap = Maps.Srg1_12_2)
    public static Field rotationYaw;
    @WrapField(mcpName = "rotationPitch",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "rotationPitch",targetMap = Maps.Srg1_12_2)
    public static Field rotationPitch;
    @WrapMethod(mcpName = "onUpdate",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "onUpdate",targetMap = Maps.Srg1_12_2)
    public static Method onUpdate;
    @WrapField(mcpName = "ticksExisted",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "ticksExisted",targetMap = Maps.Srg1_12_2)
    public static Field ticksExisted;
    @WrapMethod(mcpName = "playSound",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "playSound",targetMap = Maps.Srg1_12_2)
    public static Method playSound;
    @WrapField(mcpName = "posX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "posX",targetMap = Maps.Srg1_12_2)
    public static Field posX;
    @WrapField(mcpName = "posY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "posY",targetMap = Maps.Srg1_12_2)
    public static Field posY;
    @WrapField(mcpName = "posZ",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "posZ",targetMap = Maps.Srg1_12_2)
    public static Field posZ;
    @WrapField(mcpName = "lastTickPosX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "lastTickPosX",targetMap = Maps.Srg1_12_2)
    public static Field lastTickPosX;
    @WrapField(mcpName = "lastTickPosY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "lastTickPosY",targetMap = Maps.Srg1_12_2)
    public static Field lastTickPosY;
    @WrapField(mcpName = "lastTickPosZ",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "lastTickPosZ",targetMap = Maps.Srg1_12_2)
    public static Field lastTickPosZ;
    @WrapField(mcpName = "boundingBox",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "boundingBox",targetMap = Maps.Srg1_12_2)
    public static Field boundingBox;
    @WrapClass(mcpName = "net.minecraft.entity.Entity",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.Entity",targetMap = Maps.Srg1_12_2)
    public static Class EntityClass;
    @WrapField(mcpName = "onGround",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "onGround",targetMap = Maps.Srg1_12_2)
    public static Field onGround;
    @WrapMethod(mcpName = "getDisplayName",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getDisplayName",targetMap = Maps.Srg1_12_2)
    public static Method getDisplayName;
    @WrapField(mcpName = "isDead",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "isDead",targetMap = Maps.Srg1_12_2)
    public static Field isDead;
    @WrapMethod(mcpName = "getName",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getName",targetMap = Maps.Srg1_12_2)
    public static Method getName;
    @WrapMethod(mcpName = "getDistanceToEntity",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getDistance",targetMap = Maps.Srg1_12_2,signature = "(Lnet/minecraft/entity/Entity;)F")
    public static Method getDistanceToEntity;
    @WrapMethod(mcpName = "getDistance",targetMap = Maps.Srg1_8_9,signature = "(DDD)D")
    public static Method getDistance_DDD;
    @WrapField(mcpName = "motionX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "motionX",targetMap = Maps.Srg1_12_2)
    public static Field motionX;
    @WrapField(mcpName = "motionY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "motionY",targetMap = Maps.Srg1_12_2)
    public static Field motionY;
    @WrapField(mcpName = "motionZ",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "motionZ",targetMap = Maps.Srg1_12_2)
    public static Field motionZ;
    @WrapMethod(mcpName = "isSprinting",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isSprinting",targetMap = Maps.Srg1_12_2)
    public static Method isSprinting;
    @WrapField(mcpName = "prevPosX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "prevPosX",targetMap = Maps.Srg1_12_2)
    public static Field prevPosX;
    @WrapField(mcpName = "prevPosY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "prevPosY",targetMap = Maps.Srg1_12_2)
    public static Field prevPosY;
    @WrapField(mcpName = "prevPosZ",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "prevPosZ",targetMap = Maps.Srg1_12_2)
    public static Field prevPosZ;
    @WrapField(mcpName = "height",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "height",targetMap = Maps.Srg1_12_2)
    public static Field height;
    @WrapMethod(mcpName = "getEyeHeight",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getEyeHeight",targetMap = Maps.Srg1_12_2)
    public static Method getEyeHeight;
    @WrapMethod(mcpName = "moveEntity",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "move",targetMap = Maps.Srg1_12_2)
    public static Method moveEntity;
    @WrapMethod(mcpName = "isRiding",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isRiding",targetMap = Maps.Srg1_12_2)
    public static Method isRiding;
    @WrapField(mcpName = "stepHeight",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "stepHeight",targetMap = Maps.Srg1_12_2)
    public static Field stepHeight;
    @WrapField(mcpName = "isCollidedVertically",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "collidedVertically",targetMap = Maps.Srg1_12_2)
    public static Field isCollidedVertically;
    @WrapMethod(mcpName = "canBeCollidedWith",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "canBeCollidedWith",targetMap = Maps.Srg1_12_2)
    public static Method canBeCollidedWith;
    @WrapMethod(mcpName = "setPositionAndRotation",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setPositionAndRotation",targetMap = Maps.Srg1_12_2)
    public static Method setPositionAndRotation;
    @WrapField(mcpName = "width",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "width",targetMap = Maps.Srg1_12_2)
    public static Field width;
    @WrapField(mcpName = "isInWeb",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "isInWeb",targetMap = Maps.Srg1_12_2)
    public static Field isInWeb;
    @WrapField(mcpName = "fallDistance",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "fallDistance",targetMap = Maps.Srg1_12_2)
    public static Field fallDistance;
    @WrapMethod(mcpName = "getHorizontalFacing",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getHorizontalFacing",targetMap = Maps.Srg1_12_2)
    public static Method getHorizontalFacing;
    @WrapMethod(mcpName = "setPosition",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setPosition",targetMap = Maps.Srg1_12_2)
    public static Method setPosition;
    @WrapMethod(mcpName = "setPositionAndUpdate",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setPositionAndUpdate",targetMap = Maps.Srg1_12_2)
    public static Method setPositionAndUpdate;
    @WrapField(mcpName = "worldObj",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "world",targetMap = Maps.Srg1_12_2)
    public static Field worldObj;
    @WrapMethod(mcpName = "copyDataFromOld",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "copyDataFromOld",targetMap = Maps.Srg1_12_2)
    public static Method copyDataFromOld;
    @WrapMethod(mcpName = "copyLocationAndAnglesFrom",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "copyLocationAndAnglesFrom",targetMap = Maps.Srg1_12_2)
    public static Method copyLocationAndAnglesFrom;
    @WrapMethod(mcpName = "rayTrace",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "rayTrace",targetMap = Maps.Srg1_12_2)
    public static Method rayTrace;
    @WrapMethod(mcpName = "getPositionEyes",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getPositionEyes",targetMap = Maps.Srg1_12_2)
    public static Method getPositionEyes;
    @WrapMethod(mcpName = "getLook",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getLook",targetMap = Maps.Srg1_12_2)
    public static Method getLook;
    @WrapMethod(mcpName = "getCollisionBorderSize",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getCollisionBorderSize",targetMap = Maps.Srg1_12_2)
    public static Method getCollisionBorderSize;
    @WrapField(mcpName = "ridingEntity",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "ridingEntity",targetMap = Maps.Srg1_12_2)
    public static Field ridingEntity;
    @WrapMethod(mcpName = "getDistanceSqToEntity",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getDistanceSq",targetMap = Maps.Srg1_12_2,signature = "(Lnet/minecraft/entity/Entity;)D")
    public static Method getDistanceSqToEntity;
    @WrapMethod(mcpName = "getDistanceSq",targetMap = Maps.Srg1_8_9,signature = "(DDD)D")
    @WrapMethod(mcpName = "getDistanceSq",targetMap = Maps.Srg1_12_2,signature = "(DDD)D")
    public static Method getDistanceSq_D;
    @WrapMethod(mcpName = "canRiderInteract",targetMap = Maps.Srg1_12_2)
    public static Method canRiderInteract;
    @WrapMethod(mcpName = "isInvisible", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isInvisible", targetMap = Maps.Srg1_12_2)
    public static Method isInvisible;
    @WrapMethod(mcpName = "isSneaking",targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "isSneaking",targetMap = Maps.Srg1_8_9)
    public static Method isSneaking;
    @WrapField(mcpName = "isCollidedHorizontally",targetMap = Maps.Srg1_8_9)
    public static Field isCollidedHorizontally;
    @WrapMethod(mcpName = "getEntityBoundingBox",targetMap = Maps.Srg1_8_9)
    public static Method getEntityBoundingBox;
    @WrapMethod(mcpName = "getUniqueID",targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "getUniqueID",targetMap = Maps.Srg1_8_9)
    public static Method getUniqueID;
    @WrapField(mcpName = "inWater",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "inWater",targetMap = Maps.Srg1_12_2)
    public static Field isInWater;
    @WrapField(mcpName = "dataWatcher",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "dataManager", targetMap = Maps.Srg1_12_2)
    public static Field dataManager;
    @WrapMethod(mcpName = "setNoGravity", targetMap = Maps.Srg1_12_2)
    public static Method setNoGravity;
    @WrapField(mcpName = "noClip", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "noClip", targetMap = Maps.Srg1_8_9)
    public static Field noClip;
    @WrapField(mcpName = "prevRotationYaw", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "prevRotationYaw", targetMap = Maps.Srg1_12_2)
    public static Field prevRotationYaw;
    @WrapField(mcpName = "prevRotationPitch", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "prevRotationPitch", targetMap = Maps.Srg1_12_2)
    public static Field prevRotationPitch;
    @WrapMethod(mcpName = "getPositionVector", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getPositionVector", targetMap = Maps.Srg1_12_2)
    public static Method getPositionVector;
    @WrapField(mcpName = "entityId", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "entityId", targetMap = Maps.Srg1_12_2)
    public static Field entityId;
    @WrapField(mcpName = "dimension", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "dimension", targetMap = Maps.Srg1_12_2)
    public static Field dimension;
    @WrapMethod(mcpName = "addVelocity", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "addVelocity", targetMap = Maps.Srg1_12_2)
    public static Method addVelocity;
    @WrapMethod(mcpName = "getRotationYawHead", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getRotationYawHead", targetMap = Maps.Srg1_12_2)
    public static Method getRotationYawHead;
    @WrapMethod(mcpName = "isInLava", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isInLava", targetMap = Maps.Srg1_12_2)
    public static Method isInLava;
    @WrapField(mcpName = "isAirBorne", targetMap = Maps.Srg1_8_9)
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

    public DataWatcher getDataWatcher() {
        return new DataWatcher(getField(dataManager));
    }

    public void setNoGravity(boolean b) {
        if (!Wrapper.env.equals(Maps.Srg1_12_2)) {
            setNoClip(b);
            DataWatcher dataWatcher = getDataWatcher();
            byte Byte = dataWatcher.getWatchableObjectByte(10);
            if (b) {
                Byte = (byte)(Byte | 2);
            } else {
                Byte &= -3;
            }

            dataWatcher.updateObject(10, Byte);
            return;
        }
        invoke(setNoGravity,b);
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
        return (float) ReflectUtil.getField(rotationYaw,getWrapObject());
    }
    public int getTicksExisted(){
        return (int) ReflectUtil.getField(ticksExisted,getWrapObject());
    }
    public void playSound(String name,int volume,int pitch){
        if (Wrapper.env.equals(Maps.Srg1_12_2)){
            invoke(playSound,new SoundEvent(new ResourceLocation(name)).getWrapObject(),volume,pitch);
        }else {
            invoke(playSound,name,volume,pitch);
        }
    }
    public double getDistanceSq(double x,double y,double z){
        return (double) invoke(getDistanceSq_D,x,y,z);
    }
    public World getWorld(){
        return new World(getField(worldObj));
    }
    public MovingObjectPosition rayTrace(double d,float f){
        return new MovingObjectPosition(invoke(rayTrace,d,f));
    }
    public Vec3 getPositionEyes(float partialTicks)
    {
        return new Vec3(invoke(getPositionEyes,partialTicks));
    }
    public Vec3 getLook(float partialTicks)
    {
        return new Vec3(invoke(getLook,partialTicks));
    }
    public double getPosX(){
        return (double) ReflectUtil.getField(posX,getWrapObject());
    }
    public double getPosY(){
        return (double) ReflectUtil.getField(posY,getWrapObject());
    }
    public double getPosZ(){
        return (double) ReflectUtil.getField(posZ,getWrapObject());
    }
    public double getLastTickPosX(){
        return (double) ReflectUtil.getField(lastTickPosX,getWrapObject());
    }
    public double getLastTickPosY(){
        return (double) ReflectUtil.getField(lastTickPosY,getWrapObject());
    }
    public double getLastTickPosZ(){
        return (double) ReflectUtil.getField(lastTickPosZ,getWrapObject());
    }
    public AxisAlignedBB getEntityBoundingBox() {
        return new AxisAlignedBB(ReflectUtil.getField(boundingBox,getWrapObject()));
    }
    public void copyLocationAndAnglesFrom(Entity entity){
        invoke(copyLocationAndAnglesFrom,entity.getWrapObject());
    }
    public void setRotationYaw(float yaw){
        ReflectUtil.setField(rotationYaw,yaw,getWrapObject());
    }
    public void setRotationPitch(float pitch){
        ReflectUtil.setField(rotationPitch,pitch,getWrapObject());
    }
    public void setOnGround(boolean isOnGround){
        ReflectUtil.setField(onGround,isOnGround,getWrapObject());
    }
    public boolean isOnGround(){
        return (boolean) ReflectUtil.getField(onGround,getWrapObject());
    }
    public IChatComponent getDisplayName(){
        return new IChatComponent(ReflectUtil.invoke(getDisplayName,getWrapObject()));
    }
    public boolean isDead(){
        return (boolean) ReflectUtil.getField(isDead,getWrapObject());
    }
    public String getName(){
        return (String) ReflectUtil.invoke(getName,getWrapObject());
    }
    public float getDistanceToEntity(Entity entity){
        return (float) invoke(getDistanceToEntity,entity.getWrapObject());
    }
    public void copyDataFromOld(Entity entity){
        invoke(copyDataFromOld,entity.getWrapObject());
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
        return (boolean) invoke(isSprinting);
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
        return (float) invoke(getEyeHeight);
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
        return (boolean) invoke(isRiding);
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
        return (boolean) invoke(canBeCollidedWith);
    }
    public void setWidth(float f){
        setField(width,f);
    }
    public float getWidth(){
        return (float) getField(width);
    }
    public double getDistance(double x,double y,double z){
        return (double) invoke(getDistance_DDD,x,y,z);
    }
    public float getFallDistance(){
        return (float) getField(fallDistance);
    }
    public void setPositionAndRotation(double x, double y, double z, float rotationYaw, float rotationPitch) {
        invoke(setPositionAndRotation,x,y,z,rotationYaw,rotationPitch);
    }
    public Enum getHorizontalFacing(){
        return (Enum) invoke(getHorizontalFacing);
    }
    public void setPosition(double x,double y,double z){
        invoke(setPosition,x,y,z);
    }
    public void setPositionAndUpdate(double x,double y,double z){
        invoke(setPositionAndUpdate,x,y,z);
    }
    public float getCollisionBorderSize(){
        return (float) invoke(getCollisionBorderSize);
    }
    public Entity getRidingEntity(){
        return new Entity(getField(ridingEntity));
    }
    public boolean canRiderInteract(){
        return Wrapper.env.equals(Maps.Srg1_12_2) && (boolean) invoke(canRiderInteract);
    }
    public double getDistanceSqToEntity(Entity entity){
        return (double) invoke(getDistanceSqToEntity,entity.getWrapObject());
    }
    public boolean isInvisible(){
        return (boolean) invoke(isInvisible);
    }

    public boolean isSneaking() {
        return (boolean) invoke(isSneaking);
    }

    public UUID getUniqueID() {
        return (UUID) invoke(getUniqueID);
    }

    public Vec3 getPositionVector() {
        return new Vec3(invoke(getPositionVector));
    }

    public int getEntityId() {
        return (int) getField(entityId);
    }

    public float getRotationYawHead() {
        return (float) invoke(getRotationYawHead);
    }

    public boolean isInLava() {
        return (boolean) invoke(isInLava);
    }

    public boolean isAirBorne() {
        return (boolean) getField(isAirBorne);
    }
}
