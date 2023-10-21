package al.nya.reflect.wrapper.wraps.wrapper.world;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.Scoreboard;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.world.World",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.world.World",targetMap = Maps.Srg1_12_2)
public class World extends IBlockAccess {
    @WrapField(mcpName = "loadedEntityList",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "loadedEntityList",targetMap = Maps.Srg1_12_2)
    public static Field loadedEntityList;
    @WrapMethod(mcpName = "getCollidingBoundingBoxes",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getCollisionBoxes",targetMap = Maps.Srg1_12_2)
    public static Method getCollidingBoundingBoxes;
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = Maps.Srg1_8_9,signature = "(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;ZZZ)Lnet/minecraft/util/MovingObjectPosition;")
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = Maps.Srg1_12_2,signature = "(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;ZZZ)Lnet/minecraft/util/math/RayTraceResult;")
    public static Method rayTraceBlocks_VecZZZ;
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = Maps.Srg1_8_9,signature = "(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;)Lnet/minecraft/util/MovingObjectPosition;")
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = Maps.Srg1_12_2,signature = "(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;")
    public static Method rayTraceBlocks_Vec;
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = Maps.Srg1_8_9,signature = "(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;Z)Lnet/minecraft/util/MovingObjectPosition;")
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = Maps.Srg1_12_2,signature = "(Lnet/minecraft/util/math/Vec3;Lnet/minecraft/util/Vec3;Z)Lnet/minecraft/util/math/RayTraceResult;")
    public static Method rayTraceBlocks_VecZ;
    @WrapMethod(mcpName = "getChunkFromChunkCoords",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getChunkFromChunkCoords",targetMap = Maps.Srg1_12_2)
    public static Method getChunkFromChunkCoords;
    @WrapClass(mcpName = "net.minecraft.world.World",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.world.World",targetMap = Maps.Srg1_12_2)
    public static Class WorldClass;
    @WrapMethod(mcpName = "getEntitiesInAABBexcluding", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getEntitiesInAABBexcluding", targetMap = Maps.Srg1_12_2)
    public static Method getEntitiesInAABBexcluding;
    @WrapField(mcpName = "playerEntities", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "playerEntities", targetMap = Maps.Srg1_12_2)
    public static Field playerEntities;
    @WrapMethod(mcpName = "setWorldTime", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setWorldTime", targetMap = Maps.Srg1_12_2)
    public static Method setWorldTime;
    @WrapField(mcpName = "worldScoreboard", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "worldScoreboard", targetMap = Maps.Srg1_12_2)
    public static Field worldScoreboard;
    @WrapMethod(mcpName = "isAirBlock", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isAirBlock", targetMap = Maps.Srg1_12_2)
    public static Method isAirBlock;
    @WrapMethod(mcpName = "checkBlockCollision", targetMap = Maps.Srg1_8_9)
    public static Method checkBlockCollision;

    public World(Object obj) {
        super(obj);
    }

    public List<Entity> getLoadedEntityList() {
        List<Object> objects = (List<Object>) ReflectUtil.getField(loadedEntityList, getWrapObject());
        List<Entity> entities = new ArrayList<Entity>();
        for (Object object : objects) {
            entities.add(new Entity(object));
        }
        return entities;
    }
    public void setWorldTime(long time){
        invoke(setWorldTime,time);
    }
    public List<AxisAlignedBB> getCollidingBoundingBoxes(Entity entity,AxisAlignedBB aabb){
        List<Object> objects = (List<Object>) invoke(getCollidingBoundingBoxes,entity.getWrapObject(),aabb.getWrapObject());
        List<AxisAlignedBB> wrapped = new ArrayList<AxisAlignedBB>();
        for (Object object : objects) {
            wrapped.add(new AxisAlignedBB(object));
        }
        return wrapped;
    }
    public MovingObjectPosition rayTraceBlocks(Vec3 v1,Vec3 v2,boolean b1,boolean b2,boolean b3){
        return new MovingObjectPosition(invoke(rayTraceBlocks_VecZZZ,v1.getWrapObject(),v2.getWrapObject(),b1,b2,b3));
    }
    public MovingObjectPosition rayTraceBlocks(Vec3 v1,Vec3 v2){
        return new MovingObjectPosition(invoke(rayTraceBlocks_Vec,v1.getWrapObject(),v2.getWrapObject()));
    }
    public MovingObjectPosition rayTraceBlocks(Vec3 v1,Vec3 v2,boolean b1){
        return new MovingObjectPosition(invoke(rayTraceBlocks_VecZ,v1.getWrapObject(),v2.getWrapObject(),b1));
    }
    public Chunk getChunkFromChunkCoords(int x,int y){
        return new Chunk(invoke(getChunkFromChunkCoords,x,y));
    }
    public List<Entity> getEntitiesInAABBexcluding(Entity entityIn, AxisAlignedBB boundingBox, Object predicate){
        List<Object> objects = (List<Object>) invoke(getEntitiesInAABBexcluding,entityIn.getWrapObject(),boundingBox.getWrapObject(),predicate);
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
        return (boolean) invoke(checkBlockCollision, aabb.getWrapObject());
    }

    public Scoreboard getScoreboard() {
        return new Scoreboard(getField(worldScoreboard));
    }

    public boolean isAirBlock(BlockPos blockPos) {
        return (boolean) invoke(isAirBlock, blockPos.getWrapObject());
    }
}
