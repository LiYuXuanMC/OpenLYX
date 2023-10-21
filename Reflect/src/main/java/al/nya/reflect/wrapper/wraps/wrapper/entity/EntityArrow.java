package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.entity.projectile.EntityArrow", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.projectile.EntityArrow", targetMap = Maps.Srg1_12_2)
public class EntityArrow extends Entity {
    @WrapClass(mcpName = "net.minecraft.entity.projectile.EntityArrow", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.projectile.EntityArrow", targetMap = Maps.Srg1_12_2)
    public static Class EntityArrowClass;
    @WrapField(mcpName = "inGround", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "inGround", targetMap = Maps.Srg1_12_2)
    public static Field inGround;

    public EntityArrow(Object obj) {
        super(obj);
    }

    public static boolean isEntityArrow(Entity obj) {
        return EntityArrowClass.isInstance(obj.getWrapObject());
    }

    public boolean isGround() {
        return (boolean) getField(inGround);
    }
}

