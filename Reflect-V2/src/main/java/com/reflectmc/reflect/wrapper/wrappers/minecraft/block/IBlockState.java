package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.block.state.IBlockState",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.state.IBlockState",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class IBlockState extends WrapperBase {
    @WrapMethod(deobfName = "getBlock",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getBlock",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getBlock;
    public IBlockState(Object obj) {
        super(obj);
    }
    public Block getBlock(){
        return new Block(invokeMethod(getBlock));
    }
}
