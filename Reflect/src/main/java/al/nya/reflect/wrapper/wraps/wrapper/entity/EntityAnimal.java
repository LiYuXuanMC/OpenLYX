package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.passive.EntityAnimal",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.passive.EntityAnimal",targetMap = Maps.Srg1_12_2)
public class EntityAnimal extends EntityAgeable {
    @WrapClass(mcpName = "net.minecraft.entity.passive.EntityAnimal",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.passive.EntityAnimal",targetMap = Maps.Srg1_12_2)
    public static Class EntityAnimalClass;
    public EntityAnimal(Object obj) {
        super(obj);
    }
    public static boolean isEntityAnimal(Entity o){
        return EntityAnimalClass.isInstance(o.getWrapObject());
    }
}
