package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.entity.IProjectile", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.IProjectile", targetMap = Maps.Srg1_12_2)
public class IProjectile extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.entity.IProjectile", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.IProjectile", targetMap = Maps.Srg1_12_2)
    public static Class IProjectileClass;

    public IProjectile(Object obj) {
        super(obj);
    }

    public static boolean isIProjectile(Entity obj) {
        return IProjectileClass.isInstance(obj.getWrapObject());
    }
}
