package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.item.EntityItemFrame", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.item.EntityItemFrame", targetMap = Maps.Srg1_12_2)
public class EntityItemFrame extends Entity {
    @WrapClass(mcpName = "net.minecraft.entity.item.EntityItemFrame", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.item.EntityItemFrame", targetMap = Maps.Srg1_12_2)
    public static Class EntityItemFrameClass;
    public EntityItemFrame(Object obj) {
        super(obj);
    }
    public static boolean isEntityItemFrame(Entity entity){
        return EntityItemFrameClass.isInstance(entity.getWrapObject());
    }
}
