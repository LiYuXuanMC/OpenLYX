package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.block.BlockDynamicLiquid", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.BlockDynamicLiquid", targetMap = Maps.Srg1_12_2)
public class BlockDynamicLiquid extends BlockLiquid {
    public BlockDynamicLiquid(Object obj) {
        super(obj);
    }
}
