package al.nya.reflect.wrapper.wraps.wrapper.utils.event.click;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.event.ClickEvent", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.text.event.ClickEvent", targetMap = Maps.Srg1_12_2)
public class ClickEvent extends IWrapper {
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {ClickEventAction.class, String.class})
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {ClickEventAction.class, String.class})
    public static Constructor<?> ClickEvent_ClickEventAction_String;
    @WrapField(mcpName = "action", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "action", targetMap = Maps.Srg1_12_2)
    public static Field action;
    @WrapField(mcpName = "value", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "value", targetMap = Maps.Srg1_12_2)
    public static Field value;

    public ClickEvent(Object obj) {
        super(obj);
    }

    public ClickEvent(Enum<?> e, String s) {
        super(ReflectUtil.construction(ClickEvent_ClickEventAction_String, e, s));
    }

    public Enum getAction() {
        return (Enum) getField(action);
    }

    public String getValue() {
        return (String) getField(value);
    }
}
