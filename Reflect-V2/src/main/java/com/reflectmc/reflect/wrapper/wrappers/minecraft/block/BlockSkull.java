package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockSkull",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockSkull",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockSkull extends BlockContainer{
    @ClassInstance
    public static Class BlockSkullClass;
    public BlockSkull(Object obj) {
        super(obj);
    }
    public static boolean isBlockSkull(Block block){
        return BlockSkullClass.isInstance(block.getWrappedObject());
    }
}
