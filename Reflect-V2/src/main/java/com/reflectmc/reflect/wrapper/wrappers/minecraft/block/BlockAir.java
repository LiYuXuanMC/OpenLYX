package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockAir", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockAir", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockAir extends Block{
    @ClassInstance
    public static Class BlockAirClass;
    public BlockAir(Object obj) {
        super(obj);
    }
    public static boolean isBlockAir(Block block){
        return BlockAirClass.isInstance(block.getWrappedObject());
    }
}
