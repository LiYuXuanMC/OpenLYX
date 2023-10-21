package al.logger.client.wrapper.LoggerMC.world;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.block.IBlockState;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.world.IBlockAccess",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.world.IBlockAccess",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class IBlockAccess extends IWrapper {
    @ClassInstance
    public static Class<?> IBlockAccessClass;
    @WrapMethod(mcpName = "getBlockState",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getBlockState",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getBlockState;
    public IBlockAccess(Object obj) {
        super(obj);
    }
    public IBlockState getBlockState(BlockPos blockPos){
        return new IBlockState(invoke(getBlockState,blockPos.getWrappedObject()));
    }
}
