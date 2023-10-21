package al.nya.reflect.wrapper.wraps.wrapper.network.C0B;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0BPacketEntityAction", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketEntityAction", targetMap = Maps.Srg1_12_2)
public class C0BPacketEntityAction extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C0BPacketEntityAction", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketEntityAction", targetMap = Maps.Srg1_12_2)
    public static Class<?> C0BPacketEntityActionClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {Entity.class, C0BAction.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {Entity.class, C0BAction.class})
    public static Constructor<?> C0BPacketEntityAction_Entity_Action;
    @WrapField(mcpName = "action", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "action", targetMap = Maps.Srg1_12_2)
    public static Field action;

    public C0BPacketEntityAction(Object obj) {
        super(obj);
    }

    public Enum<?> getAction() {
        return (Enum<?>) getField(action);
    }

    public C0BPacketEntityAction(Entity entity, Enum action) {
        this(ReflectUtil.construction(C0BPacketEntityAction_Entity_Action, entity.getWrapObject(), action));
    }

    public static boolean isC0BPacketEntityActionClass(Packet c) {
        return C0BPacketEntityActionClass.isInstance(c.getWrapObject());
    }
}
