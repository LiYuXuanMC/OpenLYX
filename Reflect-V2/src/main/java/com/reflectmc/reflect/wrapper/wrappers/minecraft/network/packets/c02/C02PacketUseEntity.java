package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c02;

import com.reflectmc.reflect.wrapper.annotation.*;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.world.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.network.play.client.C02PacketUseEntity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.client.CPacketUseEntity",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class C02PacketUseEntity extends Packet {
    @ClassInstance
    public static Class C02PacketUseEntityClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {Entity.class, C02Action.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {Entity.class, C02Action.class})
    public static Constructor C02PacketUseEntity_Entity_Action;
    @WrapField(deobfName = "action",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "action",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field action;
    @WrapMethod(deobfName = "getEntityFromWorld",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getEntityFromWorld",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getEntityFromWorld;
    public C02PacketUseEntity(Object obj) {
        super(obj);
    }
    public C02PacketUseEntity(Entity entity, C02Action action)
    {
        this(construct(C02PacketUseEntity_Entity_Action,entity.getWrappedObject(),action.getWrappedObject()));

    }
    public static boolean isC02PacketUseEntity(Packet packet){
        return C02PacketUseEntityClass.isInstance(packet.getWrappedObject());
    }
    public C02Action getAction(){
        return new C02Action(getField(action));
    }
    public Entity getEntityFromWorld(World world){
        return new Entity(invokeMethod(getEntityFromWorld,world.getWrappedObject()));
    }
}
