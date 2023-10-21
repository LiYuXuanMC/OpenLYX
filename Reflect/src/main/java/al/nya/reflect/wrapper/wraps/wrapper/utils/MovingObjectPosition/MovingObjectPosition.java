package al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.MovingObjectPosition",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.math.RayTraceResult",targetMap = Maps.Srg1_12_2)
public class MovingObjectPosition extends IWrapper {
    @WrapField(mcpName = "typeOfHit",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "typeOfHit",targetMap = Maps.Srg1_12_2)
    public static Field typeOfHit;
    @WrapField(mcpName = "blockPos",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "blockPos",targetMap = Maps.Srg1_12_2)
    public static Field blockPos;
    @WrapField(mcpName = "entityHit",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "entityHit",targetMap = Maps.Srg1_12_2)
    public static Field entityHit;
    @WrapField(mcpName = "hitVec",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "hitVec",targetMap = Maps.Srg1_12_2)
    public static Field hitVec;
    @WrapField(mcpName = "sideHit",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "sideHit",targetMap = Maps.Srg1_12_2)
    public static Field sideHit;
    @WrapClass(mcpName = "net.minecraft.util.MovingObjectPosition", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.util.math.RayTraceResult", targetMap = Maps.Srg1_12_2)
    public static Class MovingObjectPositionClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {Entity.class, Vec3.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {Entity.class, Vec3.class})
    public static Constructor MovingObjectPosition_Entity_Vec3;
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {MovingObjectType.class, Vec3.class, EnumFacing.class, BlockPos.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {MovingObjectType.class, Vec3.class, EnumFacing.class, BlockPos.class})
    public static Constructor MovingObjectPosition_MovingObjectType_Vec3_EnumFacing_BlockPos;
    public MovingObjectPosition(Object obj) {
        super(obj);
    }
    public MovingObjectPosition(Entity e,Vec3 v){
        this(ReflectUtil.construction(MovingObjectPosition_Entity_Vec3,e.getWrapObject(),v.getWrapObject()));
    }
    public MovingObjectPosition(Enum typeOfHitIn, Vec3 hitVecIn, Enum sideHitIn, BlockPos blockPosIn)
    {
        this(ReflectUtil.construction(MovingObjectPosition_MovingObjectType_Vec3_EnumFacing_BlockPos,
                typeOfHitIn,
                hitVecIn.getWrapObject(),
                sideHitIn,
                blockPosIn.getWrapObject()));
    }
    public Enum getTypeOfHit(){
        return (Enum) getField(typeOfHit);
    }
    public BlockPos getBlockPos(){
        return new BlockPos(getField(blockPos));
    }
    public void setEntityHit(Entity e){
        setField(entityHit,e.getWrapObject());
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
    public Enum getSideHit(){
        return (Enum) getField(sideHit);
    }
}
