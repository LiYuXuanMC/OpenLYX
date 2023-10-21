package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockContainer",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockContainer",targetMap = Maps.Srg1_12_2)
public class BlockContainer extends Block{
    @WrapClass(mcpName = "net.minecraft.block.BlockContainer",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockContainer",targetMap = Maps.Srg1_12_2)
    public static Class BlockContainerClass;
    public BlockContainer(Object obj) {
        super(obj);
    }
    public static boolean isBlockContainer(Block block){
        return BlockContainerClass.isInstance(block.getWrapObject());
    }
}
