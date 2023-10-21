package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockCarpet",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockCarpet",targetMap = Maps.Srg1_12_2)
public class BlockCarpet extends Block{
    @WrapClass(mcpName = "net.minecraft.block.BlockCarpet",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockCarpet",targetMap = Maps.Srg1_12_2)
    public static Class BlockCarpetClass;
    public BlockCarpet(Object obj) {
        super(obj);
    }
    public static boolean isBlockCarpet(Block block){
        return BlockCarpetClass.isInstance(block.getWrapObject());
    }
}
