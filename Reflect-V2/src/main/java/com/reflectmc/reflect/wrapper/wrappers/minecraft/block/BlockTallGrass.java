package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockTallGrass",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockTallGrass",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockTallGrass extends BlockBush{
    @ClassInstance
    public static Class BlockTallGrassClass;
    public BlockTallGrass(Object obj) {
        super(obj);
    }
    public static boolean isBlockTallGrass(Block block){
        return BlockTallGrassClass.isInstance(block.getWrappedObject());
    }
}
