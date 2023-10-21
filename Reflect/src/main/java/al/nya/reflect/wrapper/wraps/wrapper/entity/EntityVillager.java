package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.passive.EntityVillager",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.passive.EntityVillager",targetMap = Maps.Srg1_12_2)
public class EntityVillager extends EntityAgeable{
    @WrapClass(mcpName = "net.minecraft.entity.passive.EntityVillager",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.passive.EntityVillager",targetMap = Maps.Srg1_12_2)
    public static Class EntityVillagerClass;
    public EntityVillager(Object obj) {
        super(obj);
    }
    public static boolean isEntityVillager(Entity o){
        return EntityVillagerClass.isInstance(o.getWrapObject());
    }
}
