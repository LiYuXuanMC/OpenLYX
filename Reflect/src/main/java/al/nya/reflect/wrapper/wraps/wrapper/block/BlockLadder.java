package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockLadder",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockLadder",targetMap = Maps.Srg1_12_2)
public class BlockLadder extends Block{
    @WrapClass(mcpName = "net.minecraft.block.BlockLadder",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockLadder",targetMap = Maps.Srg1_12_2)
    public static Class BlockLadderClass;
    public BlockLadder(Object obj) {
        super(obj);
    }
    public static boolean isBlockLaddeer(Block block){
        return BlockLadderClass.isInstance(block.getWrapObject());
    }
}
