package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockOre", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockOre", targetMap = Maps.Srg1_12_2)
public class BlockOre extends Block{
    @WrapClass(mcpName = "net.minecraft.block.BlockOre", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockOre", targetMap = Maps.Srg1_12_2)
    public static Class<?> BlockOreClass;
    public BlockOre(Object obj) {
        super(obj);
    }
    public static boolean isBlockOre(Block block){
        return BlockOreClass.isInstance(block.getWrapObject());
    }
}
