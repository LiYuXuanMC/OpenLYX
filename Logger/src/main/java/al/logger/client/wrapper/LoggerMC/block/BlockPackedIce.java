package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

@WrapperClass(mcpName = "net.minecraft.block.BlockPackedIce",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.BlockPackedIce",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class BlockPackedIce extends BlockContainer{
    @ClassInstance
    public static Class BlockPackedIceClass;
    public BlockPackedIce(Object obj) {
        super(obj);
    }
    public static boolean isBlockPackedIce(Block block){
        return BlockPackedIceClass.isInstance(block.getWrappedObject());
    }
}