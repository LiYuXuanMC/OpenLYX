package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.event;

import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.ResourceLocation;

import java.lang.reflect.Constructor;

@WrapperClass(deobfName = "net.minecraft.util.SoundEvent",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class SoundEvent extends WrapperBase {
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {ResourceLocation.class})
    public static Constructor SoundEvent_ResourceLocation;
    public SoundEvent(Object obj) {
        super(obj);
    }
    public SoundEvent(ResourceLocation rl){
        super(construct(SoundEvent_ResourceLocation,rl.getWrappedObject()));
    }
}
