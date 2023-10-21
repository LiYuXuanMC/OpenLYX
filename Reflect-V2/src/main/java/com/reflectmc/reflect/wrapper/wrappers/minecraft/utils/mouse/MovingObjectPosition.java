package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.mouse;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.BlockPos;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.EnumFacing;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.Vec3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.util.MovingObjectPosition",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.math.RayTraceResult",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class MovingObjectPosition extends WrapperBase {
    @WrapField(deobfName = "typeOfHit",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "typeOfHit",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field typeOfHit;
    @WrapField(deobfName = "blockPos",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "blockPos",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field blockPos;
    @WrapField(deobfName = "entityHit",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "entityHit",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field entityHit;
    @WrapField(deobfName = "hitVec",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "hitVec",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field hitVec;
    @WrapField(deobfName = "sideHit",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "sideHit",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field sideHit;
    @ClassInstance
    public static Class MovingObjectPositionClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = {Entity.class, Vec3.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = {Entity.class, Vec3.class})
    public static Constructor MovingObjectPosition_Entity_Vec3;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = {MovingObjectType.class, Vec3.class, EnumFacing.class, BlockPos.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = {MovingObjectType.class, Vec3.class, EnumFacing.class, BlockPos.class})
    public static Constructor MovingObjectPosition_MovingObjectType_Vec3_EnumFacing_BlockPos;
    public MovingObjectPosition(Object obj) {
        super(obj);
    }
    public MovingObjectPosition(Entity e,Vec3 v){
        this(construct(MovingObjectPosition_Entity_Vec3,e.getWrappedObject(),v.getWrappedObject()));
    }
    public MovingObjectPosition(MovingObjectType typeOfHitIn, Vec3 hitVecIn, EnumFacing sideHitIn, BlockPos blockPosIn)
    {
        this(construct(MovingObjectPosition_MovingObjectType_Vec3_EnumFacing_BlockPos,
                typeOfHitIn,
                hitVecIn.getWrappedObject(),
                sideHitIn.getWrappedObject(),
                blockPosIn.getWrappedObject()));
    }
    public MovingObjectType getTypeOfHit(){
        return new MovingObjectType(getField(typeOfHit));
    }
    public BlockPos getBlockPos(){
        return new BlockPos(getField(blockPos));
    }
    public void setEntityHit(Entity e){
        setField(entityHit,e.getWrappedObject());
    }
    public void setTypeOfHit(Enum e){
        setField(typeOfHit,e);
    }
    public Entity getEntityHit(){
        return new Entity(getField(entityHit));
    }
    public Vec3 getHitVec(){
        return new Vec3(getField(hitVec));
    }
    public EnumFacing getSideHit(){
        return new EnumFacing(getField(sideHit));
    }
}
