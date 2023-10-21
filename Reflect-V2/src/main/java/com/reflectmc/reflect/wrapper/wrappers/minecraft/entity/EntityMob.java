package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.entity.monster.EntityMob",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.entity.monster.EntityMob",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class EntityMob extends EntityCreature {
    @ClassInstance
    public static Class EntityMobClass;
    public EntityMob(Object obj) {
        super(obj);
    }
    public static boolean isEntityMob(Entity o){
        return EntityMobClass.isInstance(o.getWrappedObject());
    }
}
