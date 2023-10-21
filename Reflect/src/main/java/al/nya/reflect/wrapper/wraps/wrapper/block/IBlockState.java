package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.block.state.IBlockState",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.state.IBlockState",targetMap = Maps.Srg1_12_2)
public class IBlockState extends IWrapper {
    @WrapMethod(mcpName = "getBlock",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getBlock",targetMap = Maps.Srg1_12_2)
    public static Method getBlock;
    public IBlockState(Object obj) {
        super(obj);
    }
    public Block getBlock(){
        return new Block(invoke(getBlock));
    }
}
