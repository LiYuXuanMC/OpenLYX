package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockWall", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockWall", targetMap = Maps.Srg1_12_2)
public class BlockWall extends Block {
    @WrapClass(mcpName = "net.minecraft.block.BlockWall", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.BlockWall", targetMap = Maps.Srg1_12_2)
    public static Class BlockWallClass;

    public BlockWall(Object obj) {
        super(obj);
    }

    public static boolean isBlockWall(Block block) {
        return BlockWallClass.isInstance(block.getWrapObject());
    }
}
