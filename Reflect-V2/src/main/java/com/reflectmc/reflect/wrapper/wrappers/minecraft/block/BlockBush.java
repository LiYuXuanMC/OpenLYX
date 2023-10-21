package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockBush",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockBush",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockBush extends Block{
    public BlockBush(Object obj) {
        super(obj);
    }
}
