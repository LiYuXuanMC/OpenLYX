package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

@WrapperClass(deobfName = "net.minecraft.block.state.BlockStateContainer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockStateContainer extends WrapperBase {
    public BlockStateContainer(Object obj) {
        super(obj);
    }
}
