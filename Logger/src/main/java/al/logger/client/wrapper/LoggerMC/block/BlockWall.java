package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockWall", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.BlockWall", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class BlockWall extends Block {
@ClassInstance    
public static Class BlockWallClass;

    public BlockWall(Object obj) {
        super(obj);
    }

    public static boolean isBlockWall(Block block) {
        return BlockWallClass.isInstance(block.getWrappedObject());
    }
}
