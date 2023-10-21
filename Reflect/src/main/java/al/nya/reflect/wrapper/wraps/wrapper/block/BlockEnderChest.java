package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockEnderChest", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockEnderChest", targetMap = Maps.Srg1_12_2)
public class BlockEnderChest extends Block {
    @WrapClass(mcpName = "net.minecraft.block.BlockEnderChest", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockEnderChest", targetMap = Maps.Srg1_12_2)
    public static Class BlockEnderChestClass;

    public BlockEnderChest(Object obj) {
        super(obj);
    }

    public static boolean isBlockEnderChest(Block block) {
        return BlockEnderChestClass.isInstance(block.getWrapObject());
    }
}
