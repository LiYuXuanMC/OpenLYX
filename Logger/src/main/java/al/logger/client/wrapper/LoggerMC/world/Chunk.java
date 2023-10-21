package al.logger.client.wrapper.LoggerMC.world;

import al.logger.client.wrapper.LoggerMC.block.IBlockState;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@WrapperClass(mcpName = "net.minecraft.world.chunk.Chunk",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.world.chunk.Chunk",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Chunk extends IWrapper {
    @WrapMethod(mcpName = "getEntitiesWithinAABBForEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getEntitiesWithinAABBForEntity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEntitiesWithinAABBForEntity;
    @WrapMethod(mcpName = "isLoaded",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method isLoaded;
    @WrapMethod(mcpName = "getBlockState",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlockState;
    public Chunk(Object obj) {
        super(obj);
    }
    public void getEntitiesWithinAABBForEntity(Entity entityIn, AxisAlignedBB aabb, List<Entity> listToFill, Predicate<?> p_177414_4_)
    {
        List<Object> listToFillObj = new ArrayList<Object>();
        invoke(getEntitiesWithinAABBForEntity,entityIn.getWrappedObject(),aabb.getWrappedObject(),listToFillObj,p_177414_4_);
        //Try faster
        for (Object o : listToFillObj) {
            listToFill.add(new Entity(o));
        }
    }

    public boolean isLoaded(){
        return (Boolean)invoke(isLoaded,getWrappedObject());
    }

    public IBlockState getBlockState(BlockPos pos){
        return new IBlockState(invoke(getBlockState,getWrappedObject(),pos.getWrappedObject()));
    }

}
