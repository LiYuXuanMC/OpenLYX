package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockCarpet",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.BlockCarpet",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class BlockCarpet extends Block{
@ClassInstance
public static Class BlockCarpetClass;
    public BlockCarpet(Object obj) {
        super(obj);
    }
    public static boolean isBlockCarpet(Block block){
        return BlockCarpetClass.isInstance(block.getWrappedObject());
    }
}
