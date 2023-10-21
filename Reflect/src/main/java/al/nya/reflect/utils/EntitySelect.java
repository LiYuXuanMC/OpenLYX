package al.nya.reflect.utils;

import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.*;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;

public class EntitySelect {
    private OptionValue playerValue = new OptionValue("Player",true);
    //@OptionInfo(description = "Attack animal.", name = "Animal", priority = -1)
    private OptionValue animalValue = new OptionValue("Animal",false);
    //@OptionInfo(description = "Attack mobs.", name = "Mob", priority = -1)
    private OptionValue mobValue = new OptionValue("Mob",false);
    //@OptionInfo(description = "Attack invisible.", name = "Invisible", priority = -1)
    private OptionValue invisibleValue = new OptionValue("Invisible",true);
    public EntitySelect(boolean player,boolean animal,boolean mob,boolean invisible){
        playerValue.setValue(player);
        animalValue.setValue(animal);
        mobValue.setValue(mob);
        invisibleValue.setValue(invisible);
    }
    public OptionValue[] getValues(){
        return new OptionValue[]{playerValue,animalValue,mobValue,invisibleValue};
    }
    public boolean check(Entity entity){
        if (entity.isNull()) return false;
        if (!EntityLivingBase.isEntityLivingBase(entity)) return false;
        if (EntityPlayer.isEntityPlayer(entity)) {
            return playerValue.getValue();
        }
        if (new EntityLivingBase(entity.getWrapObject()).isPotionActive(Potion.invisibility) && !invisibleValue.getValue())
            return false;
        if (EntityMob.isEntityMob(entity)) return mobValue.getValue();
        if (IAnimals.isIAnimals(entity)) return animalValue.getValue();
        if (EntityVillager.isEntityVillager(entity)) return animalValue.getValue();
        return true;
    }
}
