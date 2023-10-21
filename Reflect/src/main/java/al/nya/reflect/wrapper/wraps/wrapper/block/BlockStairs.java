package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockStairs", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockStairs", targetMap = Maps.Srg1_12_2)
public class BlockStairs extends Block{
    @WrapClass(mcpName = "net.minecraft.block.BlockStairs", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockStairs", targetMap = Maps.Srg1_12_2)
    public static Class BlockStairsClass;
    public BlockStairs(Object obj) {
        super(obj);
    }
    public static boolean isBlockStairs(Block block){
        return BlockStairsClass.isInstance(block.getWrapObject());
    }
}
