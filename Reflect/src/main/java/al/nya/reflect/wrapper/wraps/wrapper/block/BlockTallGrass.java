package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockTallGrass",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockTallGrass",targetMap = Maps.Srg1_12_2)
public class BlockTallGrass extends BlockBush{
    @WrapClass(mcpName = "net.minecraft.block.BlockTallGrass",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockTallGrass",targetMap = Maps.Srg1_12_2)
    public static Class BlockTallGrassClass;
    public BlockTallGrass(Object obj) {
        super(obj);
    }
    public static boolean isBlockTallGrass(Block block){
        return BlockTallGrassClass.isInstance(block);
    }
}
