package al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.MovingObjectPosition",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.math.RayTraceResult",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class MovingObjectPosition extends IWrapper {
    @WrapField(mcpName = "typeOfHit",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "typeOfHit",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field typeOfHit;
    @WrapField(mcpName = "blockPos",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "blockPos",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field blockPos;
    @WrapField(mcpName = "entityHit",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "entityHit",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field entityHit;
    @WrapField(mcpName = "hitVec",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "hitVec",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field hitVec;
    @WrapField(mcpName = "sideHit",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "sideHit",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field sideHit;
    @ClassInstance
    public static Class MovingObjectPositionClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {Entity.class, Vec3.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = {Entity.class, Vec3.class})
    public static Constructor MovingObjectPosition_Entity_Vec3;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {MovingObjectType.class, Vec3.class, EnumFacing.class, BlockPos.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = {MovingObjectType.class, Vec3.class, EnumFacing.class, BlockPos.class})
    public static Constructor MovingObjectPosition_MovingObjectType_Vec3_EnumFacing_BlockPos;

    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {MovingObjectType.class, Vec3.class, EnumFacing.class, BlockPos.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = {MovingObjectType.class, Vec3.class, EnumFacing.class, BlockPos.class})
    public static Constructor MovingObjectPosition_Vec3_EnumFacing_BlockPos;
    public MovingObjectPosition(Object obj) {
        super(obj);
    }
    public MovingObjectPosition(Entity e,Vec3 v){
        this(ReflectUtil.construction(MovingObjectPosition_Entity_Vec3,e.getWrappedObject(),v.getWrappedObject()));
    }
    public MovingObjectPosition(Enum typeOfHitIn, Vec3 hitVecIn, Enum sideHitIn, BlockPos blockPosIn)
    {
        this(ReflectUtil.construction(MovingObjectPosition_MovingObjectType_Vec3_EnumFacing_BlockPos,
                typeOfHitIn,
                hitVecIn.getWrappedObject(),
                sideHitIn,
                blockPosIn.getWrappedObject()));
    }

    public MovingObjectPosition(Vec3 vec3, Enum down, BlockPos pos) {
        this(ReflectUtil.construction(MovingObjectPosition_Vec3_EnumFacing_BlockPos,
                vec3.getWrappedObject(),
                down,
                pos.getWrappedObject()));
    }
    public Enum getTypeOfHit(){
        return (Enum) getField(typeOfHit);
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

    public EnumFacing getSideHit(){return new EnumFacing(getField(sideHit));}

    public Enum getSideHit_Enum(){return (Enum) getField(sideHit);}

}
