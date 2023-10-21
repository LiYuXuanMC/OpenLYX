package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.EntityAgeable",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.EntityAgeable",targetMap = Maps.Srg1_12_2)
public class EntityAgeable extends EntityCreature {
    public EntityAgeable(Object obj) {
        super(obj);
    }
}
