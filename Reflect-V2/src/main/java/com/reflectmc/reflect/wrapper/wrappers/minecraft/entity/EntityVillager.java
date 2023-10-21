package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.entity.passive.EntityVillager",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.entity.passive.EntityVillager",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class EntityVillager extends EntityAgeable{
    @ClassInstance
    public static Class EntityVillagerClass;
    public EntityVillager(Object obj) {
        super(obj);
    }
    public static boolean isEntityVillager(Entity o){
        return EntityVillagerClass.isInstance(o.getWrappedObject());
    }
}
