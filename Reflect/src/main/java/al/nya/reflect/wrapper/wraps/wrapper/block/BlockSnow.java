package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockSnow",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockSnow",targetMap = Maps.Srg1_12_2)
public class BlockSnow extends Block{
    @WrapClass(mcpName = "net.minecraft.block.BlockSnow",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockSnow",targetMap = Maps.Srg1_12_2)
    public static Class BlockSnowClass;
    public BlockSnow(Object obj) {
        super(obj);
    }
    public static boolean isBlockSnow(Block block){
        return BlockSnowClass.isInstance(block.getWrapObject());
    }
}
