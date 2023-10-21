package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.block.BlockGrass",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockGrass",targetMap = Maps.Srg1_12_2)
public class BlockGrass extends BlockContainer{
    @WrapClass(mcpName = "net.minecraft.block.BlockGrass",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockGrass",targetMap = Maps.Srg1_12_2)
    public static Class BlockGrassClass;
    public BlockGrass(Object obj) {
        super(obj);
    }
    public static boolean isBlockGrass(Block block){
        return BlockGrassClass.isInstance(block.getWrapObject());
    }
    @WrapMethod(mcpName = "getBlockLayer",targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "getBlockLayer",targetMap = Maps.Srg1_8_9)
    public static Method getBlockLayer;
}
