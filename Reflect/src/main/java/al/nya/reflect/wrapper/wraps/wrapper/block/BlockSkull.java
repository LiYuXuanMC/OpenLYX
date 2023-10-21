package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockSkull",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockSkull",targetMap = Maps.Srg1_12_2)
public class BlockSkull extends BlockContainer{
    @WrapClass(mcpName = "net.minecraft.block.BlockSkull",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockSkull",targetMap = Maps.Srg1_12_2)
    public static Class BlockSkullClass;
    public BlockSkull(Object obj) {
        super(obj);
    }
    public static boolean isBlockSkull(Block block){
        return BlockSkullClass.isInstance(block.getWrapObject());
    }
}
