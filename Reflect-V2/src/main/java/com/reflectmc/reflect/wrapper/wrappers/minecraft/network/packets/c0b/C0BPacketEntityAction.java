package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c0b;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C0BPacketEntityAction", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketEntityAction", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C0BPacketEntityAction extends Packet {
    @ClassInstance
    public static Class<?> C0BPacketEntityActionClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = {Entity.class, C0BAction.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = {Entity.class, C0BAction.class})
    public static Constructor<?> C0BPacketEntityAction_Entity_Action;
    @WrapField(deobfName = "action", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "action", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field action;

    public C0BPacketEntityAction(Object obj) {
        super(obj);
    }

    public C0BAction getAction() {
        return new C0BAction(getField(action));
    }

    public C0BPacketEntityAction(Entity entity, C0BAction action) {
        this(construct(C0BPacketEntityAction_Entity_Action, entity.getWrappedObject(), action.getWrappedObject()));
    }

    public static boolean isC0BPacketEntityActionClass(Packet c) {
        return C0BPacketEntityActionClass.isInstance(c.getWrappedObject());
    }
}
