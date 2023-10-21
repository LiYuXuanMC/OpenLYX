package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;


import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockStaticLiquid", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockStaticLiquid", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockStaticLiquid extends BlockLiquid {
    public BlockStaticLiquid(Object obj) {
        super(obj);
    }
}
