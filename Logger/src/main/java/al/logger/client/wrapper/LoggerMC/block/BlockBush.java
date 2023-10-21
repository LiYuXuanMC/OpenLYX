package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockBush",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.BlockBush",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class BlockBush extends Block{
    public BlockBush(Object obj) {
        super(obj);
    }
}
