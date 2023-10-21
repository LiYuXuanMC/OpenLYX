package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
@WrapperClass(mcpName = "net.minecraft.entity.item.EntityArmorStand",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.item.EntityArmorStand",targetMap = Maps.Srg1_12_2)
public class EntityArmorStand extends EntityLivingBase {
    @WrapClass(mcpName = "net.minecraft.entity.item.EntityArmorStand",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.item.EntityArmorStand",targetMap = Maps.Srg1_12_2)
    public static Class EntityArmorStandClass;
    public EntityArmorStand(Object obj) {
        super(obj);
    }
    public static boolean isEntityArmorStand(Entity entity){
        return EntityArmorStandClass.isInstance(entity.getWrapObject());
    }
}
