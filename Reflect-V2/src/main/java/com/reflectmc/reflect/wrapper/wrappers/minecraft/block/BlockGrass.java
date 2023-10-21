package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.block.BlockGrass",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockGrass",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockGrass extends BlockContainer{
    @ClassInstance
    public static Class BlockGrassClass;
    public BlockGrass(Object obj) {
        super(obj);
    }
    public static boolean isBlockGrass(Block block){
        return BlockGrassClass.isInstance(block.getWrappedObject());
    }
    @WrapMethod(deobfName = "getBlockLayer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapMethod(deobfName = "getBlockLayer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getBlockLayer;
}
