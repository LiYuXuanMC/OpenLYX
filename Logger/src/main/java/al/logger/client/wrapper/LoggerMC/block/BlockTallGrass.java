package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockTallGrass",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.BlockTallGrass",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class BlockTallGrass extends BlockBush{
@ClassInstance    
public static Class BlockTallGrassClass;
    public BlockTallGrass(Object obj) {
        super(obj);
    }
    public static boolean isBlockTallGrass(Block block){
        return BlockTallGrassClass.isInstance(block);
    }
}
