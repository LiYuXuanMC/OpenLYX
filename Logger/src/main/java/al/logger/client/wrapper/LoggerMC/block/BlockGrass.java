package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.block.BlockGrass",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.BlockGrass",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class BlockGrass extends BlockContainer{
@ClassInstance    
public static Class BlockGrassClass;
    public BlockGrass(Object obj) {
        super(obj);
    }
    public static boolean isBlockGrass(Block block){
        return BlockGrassClass.isInstance(block.getWrappedObject());
    }
    @WrapMethod(mcpName = "getBlockLayer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "getBlockLayer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlockLayer;
}
