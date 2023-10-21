package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockOre", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.BlockOre", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class BlockOre extends Block{
@ClassInstance    
public static Class<?> BlockOreClass;
    public BlockOre(Object obj) {
        super(obj);
    }
    public static boolean isBlockOre(Block block){
        return BlockOreClass.isInstance(block.getWrappedObject());
    }
}
