package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.utils.player.Vector3d;
import al.logger.client.wrapper.LoggerMC.utils.MathHelper;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.LoggerMC.utils.event.SoundEvent;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;
import al.logger.client.wrapper.LoggerMC.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

@WrapperClass(mcpName = "net.minecraft.entity.Entity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.Entity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Entity extends IWrapper {
    @WrapField(mcpName = "rotationYaw",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "rotationYaw",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field rotationYaw;
    @WrapField(mcpName = "rotationPitch",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "rotationPitch",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field rotationPitch;
    @WrapMethod(mcpName = "onUpdate",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "onUpdate",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method onUpdate;
    @WrapField(mcpName = "ticksExisted",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "ticksExisted",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field ticksExisted;
    @WrapMethod(mcpName = "playSound",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "playSound",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method playSound;
    @WrapField(mcpName = "posX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "posX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field posX;
    @WrapField(mcpName = "posY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "posY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field posY;
    @WrapField(mcpName = "posZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "posZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field posZ;
    @WrapField(mcpName = "lastTickPosX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "lastTickPosX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field lastTickPosX;
    @WrapField(mcpName = "lastTickPosY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "lastTickPosY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field lastTickPosY;
    @WrapField(mcpName = "lastTickPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "lastTickPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field lastTickPosZ;
    @WrapField(mcpName = "boundingBox",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "boundingBox",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field boundingBox;
    @ClassInstance
    public static Class EntityClass;
    @WrapField(mcpName = "onGround",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "onGround",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field onGround;
    @WrapMethod(mcpName = "getDisplayName",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getDisplayName",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getDisplayName;
    @WrapField(mcpName = "isDead",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "isDead",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field isDead;
    @WrapMethod(mcpName = "getName",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getName",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getName;
    @WrapMethod(mcpName = "getDistanceToEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getDistance",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Lnet/minecraft/entity/Entity;)F")
    public static Method getDistanceToEntity;
    @WrapMethod(mcpName = "getDistance",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(DDD)D")
    public static Method getDistance_DDD;
    @WrapField(mcpName = "motionX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "motionX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field motionX;
    @WrapField(mcpName = "motionY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "motionY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field motionY;
    @WrapField(mcpName = "motionZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "motionZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field motionZ;
    @WrapMethod(mcpName = "isSprinting",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isSprinting",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isSprinting;
    @WrapField(mcpName = "prevPosX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevPosX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevPosX;
    @WrapField(mcpName = "prevPosY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevPosY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevPosY;
    @WrapField(mcpName = "prevPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevPosZ;
    @WrapField(mcpName = "height",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "height",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field height;
    @WrapMethod(mcpName = "getEyeHeight",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getEyeHeight",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEyeHeight;
    @WrapMethod(mcpName = "moveEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "move",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method moveEntity;
    @WrapMethod(mcpName = "isRiding",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isRiding",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isRiding;
    @WrapField(mcpName = "stepHeight",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "stepHeight",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field stepHeight;
    @WrapField(mcpName = "isCollidedVertically",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "collidedVertically",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field isCollidedVertically;
    @WrapMethod(mcpName = "canBeCollidedWith",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "canBeCollidedWith",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method canBeCollidedWith;
    @WrapMethod(mcpName = "setPositionAndRotation",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setPositionAndRotation",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setPositionAndRotation;
    @WrapField(mcpName = "width",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "width",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field width;
    @WrapField(mcpName = "isInWeb",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "isInWeb",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field isInWeb;
    @WrapField(mcpName = "fallDistance",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "fallDistance",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field fallDistance;
    @WrapMethod(mcpName = "getHorizontalFacing",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getHorizontalFacing",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getHorizontalFacing;
    @WrapMethod(mcpName = "setPosition",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setPosition",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setPosition;
    @WrapMethod(mcpName = "setPositionAndUpdate",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setPositionAndUpdate",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setPositionAndUpdate;
    @WrapField(mcpName = "worldObj",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "world",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field worldObj;
    @WrapMethod(mcpName = "copyDataFromOld",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "copyDataFromOld",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method copyDataFromOld;
    @WrapMethod(mcpName = "copyLocationAndAnglesFrom",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "copyLocationAndAnglesFrom",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method copyLocationAndAnglesFrom;
    @WrapMethod(mcpName = "rayTrace",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "rayTrace",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method rayTrace;
    @WrapMethod(mcpName = "getPositionEyes",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getPositionEyes",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getPositionEyes;
    @WrapMethod(mcpName = "getLook",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getLook",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getLook;
    @WrapMethod(mcpName = "getCollisionBorderSize",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getCollisionBorderSize",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getCollisionBorderSize;
    @WrapField(mcpName = "ridingEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "ridingEntity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field ridingEntity;
    @WrapMethod(mcpName = "getDistanceSqToEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getDistanceSq",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Lnet/minecraft/entity/Entity;)D")
    public static Method getDistanceSqToEntity;
    @WrapMethod(mcpName = "getDistanceSq",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(DDD)D")
    @WrapMethod(mcpName = "getDistanceSq",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(DDD)D")
    public static Method getDistanceSq_D;
    @WrapMethod(mcpName = "canRiderInteract",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method canRiderInteract;
    @WrapMethod(mcpName = "isInvisible", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isInvisible", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isInvisible;
    @WrapMethod(mcpName = "isSneaking",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "isSneaking",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method isSneaking;
    @WrapField(mcpName = "isCollidedHorizontally",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field isCollidedHorizontally;
    @WrapMethod(mcpName = "getEntityBoundingBox",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getEntityBoundingBox;
    @WrapMethod(mcpName = "getUniqueID",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "getUniqueID",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getUniqueID;
    @WrapField(mcpName = "inWater",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "inWater",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field isInWater;
    @WrapField(mcpName = "dataWatcher",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "dataManager", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field dataManager;
    @WrapMethod(mcpName = "setNoGravity", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setNoGravity;
    @WrapField(mcpName = "noClip", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "noClip", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field noClip;
    @WrapField(mcpName = "prevRotationYaw", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevRotationYaw", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevRotationYaw;
    @WrapField(mcpName = "prevRotationPitch", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevRotationPitch", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevRotationPitch;
    @WrapMethod(mcpName = "getPositionVector", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getPositionVector", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getPositionVector;
    @WrapField(mcpName = "entityId", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "entityId", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field entityId;
    @WrapField(mcpName = "dimension", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "dimension", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field dimension;
    @WrapMethod(mcpName = "addVelocity", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "addVelocity", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method addVelocity;
    @WrapMethod(mcpName = "getRotationYawHead", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getRotationYawHead", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getRotationYawHead;
    @WrapMethod(mcpName = "isInLava", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isInLava", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isInLava;
    @WrapField(mcpName = "hurtResistantTime", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "hurtResistantTime", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field hurtResistantTime;
    @WrapField(mcpName = "isAirBorne", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field isAirBorne;
    @WrapField(mcpName = "prevDistanceWalkedModified", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevDistanceWalkedModified", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevDistanceWalkedModified;

    @WrapField(mcpName = "distanceWalkedModified", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "distanceWalkedModified", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field distanceWalkedModified;
    @WrapField(mcpName = "distanceWalkedOnStepModified", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "distanceWalkedOnStepModified", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field distanceWalkedOnStepModified;

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

    //moveEntity
    public void moveEntity(double x,double y,double z){
        invoke(moveEntity,x,y,z);
    }

    public void setNoGravity(boolean b) {
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
        return (float) ReflectUtil.getField(rotationYaw,getWrappedObject());
    }
    public int getTicksExisted(){
        return (int) ReflectUtil.getField(ticksExisted,getWrappedObject());
    }
    //1.8.9
    @Deprecated
    public void playSound(String name,int volume,int pitch){
        invoke(playSound,name,volume,pitch);
    }
    //1.12.2
    @Deprecated
    public void playSound(SoundEvent event){
        invoke(playSound,event.getWrappedObject());
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
        return (double) ReflectUtil.getField(posX,getWrappedObject());
    }
    public double getPosY(){
        return (double) ReflectUtil.getField(posY,getWrappedObject());
    }
    public double getPosZ(){
        return (double) ReflectUtil.getField(posZ,getWrappedObject());
    }
    public double getLastTickPosX(){
        return (double) ReflectUtil.getField(lastTickPosX,getWrappedObject());
    }
    public double getLastTickPosY(){
        return (double) ReflectUtil.getField(lastTickPosY,getWrappedObject());
    }
    public double getLastTickPosZ(){
        return (double) ReflectUtil.getField(lastTickPosZ,getWrappedObject());
    }
    public AxisAlignedBB getEntityBoundingBox() {
        return new AxisAlignedBB(ReflectUtil.getField(boundingBox,getWrappedObject()));
    }
    public void setEntityBoundingBox(AxisAlignedBB bb) {
        ReflectUtil.setField(boundingBox,bb,getWrappedObject());
    }
    public void copyLocationAndAnglesFrom(Entity entity){
        invoke(copyLocationAndAnglesFrom,entity.getWrappedObject());
    }
    public void setRotationYaw(float yaw){
        ReflectUtil.setField(rotationYaw,yaw,getWrappedObject());
    }
    public void setRotationPitch(float pitch){
        ReflectUtil.setField(rotationPitch,pitch,getWrappedObject());
    }
    public void setOnGround(boolean isOnGround){
        ReflectUtil.setField(onGround,isOnGround,getWrappedObject());
    }
    public boolean isOnGround(){
        return (boolean) ReflectUtil.getField(onGround,getWrappedObject());
    }
    public IChatComponent getDisplayName(){
        return new IChatComponent(ReflectUtil.invoke(getDisplayName,getWrappedObject()));
    }
    public boolean isDead(){
        return (boolean) ReflectUtil.getField(isDead,getWrappedObject());
    }
    public String getName(){
        return (String) ReflectUtil.invoke(getName,getWrappedObject());
    }
    public float getDistanceToEntity(Entity entity){
        return (float) invoke(getDistanceToEntity,entity.getWrappedObject());
    }
    public void copyDataFromOld(Entity entity){
        invoke(copyDataFromOld,entity.getWrappedObject());
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
    //1.12.2
    @Deprecated
    public boolean canRiderInteract(){
        return (boolean) invoke(canRiderInteract);
    }
    public double getDistanceSqToEntity(Entity entity){
        return (double) invoke(getDistanceSqToEntity,entity.getWrappedObject());
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
    public int getHurtResistantTime(){
        return (int) getField(hurtResistantTime);
    }

    public MovingObjectPosition rayTraceCustom(double blockReachDistance, float yaw, float pitch) {
        final Vec3 vec3 = getPositionEyes(1.0F);
        final Vec3 vec31 = this.getLookCustom(yaw, pitch);
        final Vec3 vec32 = vec3.addVector(vec31.getXCoord() * blockReachDistance, vec31.getYCoord() * blockReachDistance, vec31.getZCoord() * blockReachDistance);
        return this.getWorld().rayTraceBlocks(vec3, vec32, false, false, true);
    }

    public Vec3 getLookCustom(float yaw, float pitch) {
        return this.getVectorForRotation(pitch, yaw);
    }
    public final Vec3 getVectorForRotation(float pitch, float yaw) {
        float f = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3((double) (f1 * f2), (double) f3, (double) (f * f2));
    }
    public Vector3d getCustomPositionVector() {
        return new Vector3d(getPosX(), getPosY(), getPosZ());
    }
    public float getPrevDistanceWalkedModified(){
        return (float) getField(prevDistanceWalkedModified);
    }
    public float getDistanceWalkedModified(){
        return (float) getField(distanceWalkedModified);
    }
    public void setDistanceWalkedModified(float f){
        setField(distanceWalkedModified,f);
    }
}
