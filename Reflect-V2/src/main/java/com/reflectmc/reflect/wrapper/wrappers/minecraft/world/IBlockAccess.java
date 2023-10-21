package com.reflectmc.reflect.wrapper.wrappers.minecraft.world;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.block.IBlockState;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.BlockPos;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.world.IBlockAccess",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.world.IBlockAccess",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class IBlockAccess extends WrapperBase {
    @ClassInstance
    public static Class<?> IBlockAccessClass;
    @WrapMethod(deobfName = "getBlockState",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getBlockState",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getBlockState;
    public IBlockAccess(Object obj) {
        super(obj);
    }
    public IBlockState getBlockState(BlockPos blockPos){
        return new IBlockState(invokeMethod(getBlockState,blockPos.getWrappedObject()));
    }
}
