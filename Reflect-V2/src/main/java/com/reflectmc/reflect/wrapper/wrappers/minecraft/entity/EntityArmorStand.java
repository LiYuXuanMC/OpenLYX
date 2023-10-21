package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.entity.item.EntityArmorStand",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.entity.item.EntityArmorStand",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class EntityArmorStand extends EntityLivingBase {
    @ClassInstance
    public static Class EntityArmorStandClass;
    public EntityArmorStand(Object obj) {
        super(obj);
    }
    public static boolean isEntityArmorStand(Entity entity){
        return EntityArmorStandClass.isInstance(entity.getWrappedObject());
    }
}
