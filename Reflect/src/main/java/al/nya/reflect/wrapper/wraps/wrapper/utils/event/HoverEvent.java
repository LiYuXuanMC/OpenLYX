package al.nya.reflect.wrapper.wraps.wrapper.utils.event;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.event.HoverEvent", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.text.event.HoverEvent", targetMap = Maps.Srg1_12_2)
public class HoverEvent extends IWrapper {
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {HoverEventAction.class, IChatComponent.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {HoverEventAction.class, IChatComponent.class})
    public static Constructor<?> HoverEvent_HoverEventAction_IChatComponent;

    public HoverEvent(Enum<?> e, IChatComponent c) {
        super(ReflectUtil.construction(HoverEvent_HoverEventAction_IChatComponent, e, c.getWrapObject()));
    }

    public HoverEvent(Object obj) {
        super(obj);
    }
}
