package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

@WrapperClass(mcpName = "net.minecraft.block.BlockSlime",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.BlockSlime",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class BlockSlime extends BlockContainer{
    @ClassInstance
    public static Class BlockSlimeClass;
    public BlockSlime(Object obj) {
        super(obj);
    }
    public static boolean isBlockSlime(Block block){
        return BlockSlimeClass.isInstance(block.getWrappedObject());
    }
}
