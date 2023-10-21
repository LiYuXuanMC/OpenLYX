package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.EntityCreature",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.EntityCreature",targetMap = Maps.Srg1_12_2)
public class EntityCreature extends EntityLivingBase {
    public EntityCreature(Object obj) {
        super(obj);
    }
}
