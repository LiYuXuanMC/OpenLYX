package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.monster.EntityMob",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.monster.EntityMob",targetMap = Maps.Srg1_12_2)
public class EntityMob extends EntityCreature {
    @WrapClass(mcpName = "net.minecraft.entity.monster.EntityMob",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.monster.EntityMob",targetMap = Maps.Srg1_12_2)
    public static Class EntityMobClass;
    public EntityMob(Object obj) {
        super(obj);
    }
    public static boolean isEntityMob(Entity o){
        return EntityMobClass.isInstance(o.getWrapObject());
    }
}
