package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.renderer.ItemRenderer", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.ItemRenderer", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ItemRenderer extends WrapperBase {
    @WrapMethod(deobfName = "resetEquippedProgress", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "resetEquippedProgress", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method resetEquippedProgress;

    public ItemRenderer(Object obj) {
        super(obj);
    }

    public void resetEquippedProgress() {
        invokeMethod(resetEquippedProgress);
    }
//    public void resetEquippedProgress(EnumHand enumHand) {
//        invoke(resetEquippedProgress, enumHand);
//    }
}
