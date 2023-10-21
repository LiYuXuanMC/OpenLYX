package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockSnow",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockSnow",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockSnow extends Block{
    @ClassInstance
    public static Class BlockSnowClass;
    public BlockSnow(Object obj) {
        super(obj);
    }
    public static boolean isBlockSnow(Block block){
        return BlockSnowClass.isInstance(block.getWrappedObject());
    }
}
