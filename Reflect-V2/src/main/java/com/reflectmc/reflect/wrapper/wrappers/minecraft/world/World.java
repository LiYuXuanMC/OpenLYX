package com.reflectmc.reflect.wrapper.wrappers.minecraft.world;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.AxisAlignedBB;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.BlockPos;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.Vec3;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.mouse.MovingObjectPosition;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.scoreboard.Scoreboard;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(deobfName = "net.minecraft.world.World",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.world.World",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class World extends IBlockAccess {
    @WrapField(deobfName = "loadedEntityList",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "loadedEntityList",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field loadedEntityList;
    @WrapMethod(deobfName = "getCollidingBoundingBoxes",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getCollisionBoxes",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getCollidingBoundingBoxes;
    @WrapMethod(deobfName = "rayTraceBlocks",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;ZZZ)Lnet/minecraft/util/MovingObjectPosition;")
    @WrapMethod(deobfName = "rayTraceBlocks",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;ZZZ)Lnet/minecraft/util/math/RayTraceResult;")
    public static Method rayTraceBlocks_VecZZZ;
    @WrapMethod(deobfName = "rayTraceBlocks",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;)Lnet/minecraft/util/MovingObjectPosition;")
    @WrapMethod(deobfName = "rayTraceBlocks",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;")
    public static Method rayTraceBlocks_Vec;
    @WrapMethod(deobfName = "rayTraceBlocks",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;Z)Lnet/minecraft/util/MovingObjectPosition;")
    @WrapMethod(deobfName = "rayTraceBlocks",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/util/math/Vec3;Lnet/minecraft/util/Vec3;Z)Lnet/minecraft/util/math/RayTraceResult;")
    public static Method rayTraceBlocks_VecZ;
    @WrapMethod(deobfName = "getChunkFromChunkCoords",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getChunkFromChunkCoords",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getChunkFromChunkCoords;
    @ClassInstance
    public static Class WorldClass;
    @WrapMethod(deobfName = "getEntitiesInAABBexcluding", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getEntitiesInAABBexcluding", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getEntitiesInAABBexcluding;
    @WrapField(deobfName = "playerEntities", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "playerEntities", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field playerEntities;
    @WrapMethod(deobfName = "setWorldTime", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setWorldTime", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setWorldTime;
    @WrapField(deobfName = "worldScoreboard", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "worldScoreboard", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field worldScoreboard;
    @WrapMethod(deobfName = "isAirBlock", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isAirBlock", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isAirBlock;
    @WrapMethod(deobfName = "checkBlockCollision", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method checkBlockCollision;

    public World(Object obj) {
        super(obj);
    }

    public List<Entity> getLoadedEntityList() {
        List<Object> objects = (List<Object>) getField(loadedEntityList);
        List<Entity> entities = new ArrayList<Entity>();
        for (Object object : objects) {
            entities.add(new Entity(object));
        }
        return entities;
    }
    public void setWorldTime(long time){
        invokeMethod(setWorldTime,time);
    }
    public List<AxisAlignedBB> getCollidingBoundingBoxes(Entity entity, AxisAlignedBB aabb){
        List<Object> objects = (List<Object>) invokeMethod(getCollidingBoundingBoxes,entity.getWrappedObject(),aabb.getWrappedObject());
        List<AxisAlignedBB> wrapped = new ArrayList<AxisAlignedBB>();
        for (Object object : objects) {
            wrapped.add(new AxisAlignedBB(object));
        }
        return wrapped;
    }
    public MovingObjectPosition rayTraceBlocks(Vec3 v1, Vec3 v2, boolean b1, boolean b2, boolean b3){
        return new MovingObjectPosition(invokeMethod(rayTraceBlocks_VecZZZ,v1.getWrappedObject(),v2.getWrappedObject(),b1,b2,b3));
    }
    public MovingObjectPosition rayTraceBlocks(Vec3 v1,Vec3 v2){
        return new MovingObjectPosition(invokeMethod(rayTraceBlocks_Vec,v1.getWrappedObject(),v2.getWrappedObject()));
    }
    public MovingObjectPosition rayTraceBlocks(Vec3 v1,Vec3 v2,boolean b1){
        return new MovingObjectPosition(invokeMethod(rayTraceBlocks_VecZ,v1.getWrappedObject(),v2.getWrappedObject(),b1));
    }
    public Chunk getChunkFromChunkCoords(int x,int y){
        return new Chunk(invokeMethod(getChunkFromChunkCoords,x,y));
    }
    public List<Entity> getEntitiesInAABBexcluding(Entity entityIn, AxisAlignedBB boundingBox, Object predicate){
        List<Object> objects = (List<Object>) invokeMethod(getEntitiesInAABBexcluding,entityIn.getWrappedObject(),boundingBox.getWrappedObject(),predicate);
        List<Entity> entities = new ArrayList<Entity>();
        for (Object object : objects) {
            entities.add(new Entity(object));
        }
        return entities;
    }
    public List<EntityPlayer> getPlayerEntities() {
        List<Object> objects = (List<Object>) getField(playerEntities);
        List<EntityPlayer> entities = new ArrayList<EntityPlayer>();
        for (Object object : objects) {
            entities.add(new EntityPlayer(object));
        }
        return entities;
    }
    public boolean checkBlockCollision(AxisAlignedBB aabb) {
        return (boolean) invokeMethod(checkBlockCollision, aabb.getWrappedObject());
    }
    public Scoreboard getScoreboard() {
        return new Scoreboard(getField(worldScoreboard));
    }
    public boolean isAirBlock(BlockPos blockPos) {
        return (boolean) invokeMethod(isAirBlock, blockPos.getWrappedObject());
    }
}
