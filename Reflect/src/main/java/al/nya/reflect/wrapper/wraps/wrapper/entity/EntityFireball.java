package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.projectile.EntityFireball", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.projectile.EntityFireball", targetMap = Maps.Srg1_12_2)
public class EntityFireball extends Entity {
    @WrapClass(mcpName = "net.minecraft.entity.projectile.EntityFireball", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.projectile.EntityFireball", targetMap = Maps.Srg1_12_2)
    public static Class EntityFireballClass;

    public EntityFireball(Object obj) {
        super(obj);
    }

    public static boolean isEntityFireball(Entity obj) {
        return EntityFireballClass.isInstance(obj.getWrapObject());
    }
}
