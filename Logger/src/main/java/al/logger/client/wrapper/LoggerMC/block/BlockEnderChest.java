package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockEnderChest", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.BlockEnderChest", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class BlockEnderChest extends Block {
@ClassInstance
public static Class BlockEnderChestClass;

    public BlockEnderChest(Object obj) {
        super(obj);
    }

    public static boolean isBlockEnderChest(Block block) {
        return BlockEnderChestClass.isInstance(block.getWrappedObject());
    }
}
