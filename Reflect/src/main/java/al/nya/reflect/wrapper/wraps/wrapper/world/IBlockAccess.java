package al.nya.reflect.wrapper.wraps.wrapper.world;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.block.IBlockState;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.world.IBlockAccess",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.world.IBlockAccess",targetMap = Maps.Srg1_12_2)
public class IBlockAccess extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.world.IBlockAccess",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.world.IBlockAccess",targetMap = Maps.Srg1_12_2)
    public static Class<?> IBlockAccessClass;
    @WrapMethod(mcpName = "getBlockState",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getBlockState",targetMap = Maps.Srg1_12_2)
    public static Method getBlockState;
    public IBlockAccess(Object obj) {
        super(obj);
    }
    public IBlockState getBlockState(BlockPos blockPos){
        return new IBlockState(invoke(getBlockState,blockPos.getWrapObject()));
    }
}
