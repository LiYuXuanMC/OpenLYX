package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockOre",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockOre",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockOre extends Block{
    @ClassInstance
    public static Class<?> BlockOreClass;
    public BlockOre(Object obj) {
        super(obj);
    }
    public static boolean isBlockOre(Block block){
        return BlockOreClass.isInstance(block.getWrappedObject());
    }
}
