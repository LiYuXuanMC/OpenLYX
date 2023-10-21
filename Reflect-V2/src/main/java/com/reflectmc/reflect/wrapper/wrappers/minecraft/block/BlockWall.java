package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockWall",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockWall",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockWall extends Block {
    @ClassInstance
    public static Class BlockWallClass;

    public BlockWall(Object obj) {
        super(obj);
    }

    public static boolean isBlockWall(Block block) {
        return BlockWallClass.isInstance(block.getWrappedObject());
    }
}
