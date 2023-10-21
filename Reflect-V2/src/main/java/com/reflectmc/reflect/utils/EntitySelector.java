package com.reflectmc.reflect.utils;

import com.reflectmc.reflect.features.values.MultiSelectValue;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.potion.Potion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntitySelector {
    @Getter
    private MultiSelectValue value;
    public EntitySelector(String name){
        List<Enum<?>> enums = new ArrayList<>(Arrays.asList(Target.values()));
        value = new MultiSelectValue(name,enums,Target.values());
    }
    public boolean check(Entity entity){
        if (entity.isNull()) return false;
        if (EntityPlayerSP.isEntityPlayerSP(entity)) return false;
        if (!EntityLivingBase.isEntityLivingBase(entity)) return false;
        if (EntityArmorStand.isEntityArmorStand(entity)) return false;
        if (EntityPlayer.isEntityPlayer(entity)) {
            return value.isValueToggle(Target.Player);
        }
        if (new EntityLivingBase(entity.getWrappedObject()).isPotionActive(Potion.invisibility) && !value.isValueToggle(Target.Invisible))
            return false;
        if (EntityMob.isEntityMob(entity)) return value.isValueToggle(Target.Mob);
        if (IAnimals.isIAnimals(entity)) return value.isValueToggle(Target.Animal);
        if (EntityVillager.isEntityVillager(entity)) return value.isValueToggle(Target.Animal);
        return true;
    }

    enum Target {
        Player,
        Invisible,
        Mob,
        Animal
    }
}
