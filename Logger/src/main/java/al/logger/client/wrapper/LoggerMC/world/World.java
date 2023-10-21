package al.logger.client.wrapper.LoggerMC.world;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.LoggerMC.utils.scoreboard.Scoreboard;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.world.World",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.world.World",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class World extends IBlockAccess {
    @WrapField(mcpName = "loadedEntityList",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "loadedEntityList",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field loadedEntityList;
    @WrapMethod(mcpName = "getCollidingBoundingBoxes",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getCollisionBoxes",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getCollidingBoundingBoxes;
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;ZZZ)Lnet/minecraft/util/MovingObjectPosition;")
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;ZZZ)Lnet/minecraft/util/math/RayTraceResult;")
    public static Method rayTraceBlocks_VecZZZ;
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;)Lnet/minecraft/util/MovingObjectPosition;")
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;")
    public static Method rayTraceBlocks_Vec;
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;Z)Lnet/minecraft/util/MovingObjectPosition;")
    @WrapMethod(mcpName = "rayTraceBlocks",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Lnet/minecraft/util/math/Vec3;Lnet/minecraft/util/Vec3;Z)Lnet/minecraft/util/math/RayTraceResult;")
    public static Method rayTraceBlocks_VecZ;
    @WrapMethod(mcpName = "getChunkFromChunkCoords",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getChunkFromChunkCoords",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getChunkFromChunkCoords;
    @ClassInstance
    public static Class WorldClass;
    @WrapMethod(mcpName = "getEntitiesInAABBexcluding", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getEntitiesInAABBexcluding", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEntitiesInAABBexcluding;
    @WrapField(mcpName = "playerEntities", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "playerEntities", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field playerEntities;
    @WrapMethod(mcpName = "setWorldTime", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setWorldTime", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setWorldTime;
    @WrapField(mcpName = "worldScoreboard", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "worldScoreboard", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field worldScoreboard;
    @WrapMethod(mcpName = "isAirBlock", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isAirBlock", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isAirBlock;
    @WrapMethod(mcpName = "checkBlockCollision", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
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
        invoke(setWorldTime,time);
    }
    public List<AxisAlignedBB> getCollidingBoundingBoxes(Entity entity,AxisAlignedBB aabb){
        List<Object> objects = (List<Object>) invoke(getCollidingBoundingBoxes,entity.getWrappedObject(),aabb.getWrappedObject());
        List<AxisAlignedBB> wrapped = new ArrayList<AxisAlignedBB>();
        for (Object object : objects) {
            wrapped.add(new AxisAlignedBB(object));
        }
        return wrapped;
    }

    public MovingObjectPosition rayTraceBlocks(Vec3 v1,Vec3 v2,boolean b1,boolean b2,boolean b3){
        return new MovingObjectPosition(invoke(rayTraceBlocks_VecZZZ,v1.getWrappedObject(),v2.getWrappedObject(),b1,b2,b3));
    }
    public MovingObjectPosition rayTraceBlocks(Vec3 v1,Vec3 v2){
        return new MovingObjectPosition(invoke(rayTraceBlocks_Vec,v1.getWrappedObject(),v2.getWrappedObject()));
    }
    public MovingObjectPosition rayTraceBlocks(Vec3 v1,Vec3 v2,boolean b1){
        return new MovingObjectPosition(invoke(rayTraceBlocks_VecZ,v1.getWrappedObject(),v2.getWrappedObject(),b1));
    }
    public Chunk getChunkFromChunkCoords(int x,int y){
        return new Chunk(invoke(getChunkFromChunkCoords,x,y));
    }
    public List<Entity> getEntitiesInAABBexcluding(Entity entityIn, AxisAlignedBB boundingBox, Object predicate){
        List<Object> objects = (List<Object>) invoke(getEntitiesInAABBexcluding,entityIn.getWrappedObject(),boundingBox.getWrappedObject(),predicate);
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
        return (boolean) invoke(checkBlockCollision, aabb.getWrappedObject());
    }

    public Scoreboard getScoreboard() {
        return new Scoreboard(getField(worldScoreboard));
    }

    public boolean isAirBlock(BlockPos blockPos) {
        return (boolean) invoke(isAirBlock, blockPos.getWrappedObject());
    }
}
