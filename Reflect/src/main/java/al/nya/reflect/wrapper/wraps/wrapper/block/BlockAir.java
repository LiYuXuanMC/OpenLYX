package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockAir", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockAir", targetMap = Maps.Srg1_12_2)
public class BlockAir extends Block{
    @WrapClass(mcpName = "net.minecraft.block.BlockAir", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockAir", targetMap = Maps.Srg1_12_2)
    public static Class BlockAirClass;
    public BlockAir(Object obj) {
        super(obj);
    }
    public static boolean isBlockAir(Block block){
        return BlockAirClass.isInstance(block.getWrapObject());
    }
}
