package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

@WrapperClass(deobfName = "net.minecraft.entity.passive.IAnimals", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
@WrapperClass(deobfName = "net.minecraft.entity.passive.IAnimals", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
public class IAnimals extends WrapperBase {
    @ClassInstance
    public static Class IAnimalsClass;
    public IAnimals(Object obj) {
        super(obj);
    }
    public static boolean isIAnimals(Entity o){
        return IAnimalsClass.isInstance(o.getWrappedObject());
    }
}
