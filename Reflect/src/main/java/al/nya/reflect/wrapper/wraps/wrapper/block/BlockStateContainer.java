package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.block.state.BlockStateContainer", targetMap = Maps.Srg1_12_2)
public class BlockStateContainer extends IWrapper {
    public BlockStateContainer(Object obj) {
        super(obj);
    }
}
