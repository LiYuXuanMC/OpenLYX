package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.item.EntityBoat",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.item.EntityBoat",targetMap = Maps.Srg1_12_2)
public class EntityBoat extends Entity {
    @WrapClass(mcpName = "net.minecraft.entity.item.EntityBoat",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.item.EntityBoat",targetMap = Maps.Srg1_12_2)
    public static Class<?> EntityBoatClass;
    public EntityBoat(Object entity) {
        super(entity);
    }

    public static boolean isEntityBoat(Entity entity) {
        return EntityBoatClass.isInstance(entity.getWrapObject());
    }

}
