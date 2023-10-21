package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.entity.passive.EntityAnimal",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.entity.passive.EntityAnimal",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class EntityAnimal extends EntityAgeable {
    @ClassInstance
    public static Class EntityAnimalClass;
    public EntityAnimal(Object obj) {
        super(obj);
    }
    public static boolean isEntityAnimal(Entity o){
        return EntityAnimalClass.isInstance(o.getWrappedObject());
    }
}
