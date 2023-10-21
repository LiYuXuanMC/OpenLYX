package al.nya.reflect.wrapper.wraps.wrapper.network.C02;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.*;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C02PacketUseEntity",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketUseEntity",targetMap = Maps.Srg1_12_2)
public class C02PacketUseEntity extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C02PacketUseEntity",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketUseEntity",targetMap = Maps.Srg1_12_2)
    public static Class C02PacketUseEntityClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {Entity.class, C02Action.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {Entity.class, C02Action.class})
    public static Constructor C02PacketUseEntity_Entity_Action;
    @WrapField(mcpName = "action",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "action",targetMap = Maps.Srg1_12_2)
    public static Field action;
    @WrapMethod(mcpName = "getEntityFromWorld",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getEntityFromWorld",targetMap = Maps.Srg1_12_2)
    public static Method getEntityFromWorld;
    public C02PacketUseEntity(Object obj) {
        super(obj);
    }
    public C02PacketUseEntity(Entity entity, Enum action)
    {
        this(ReflectUtil.construction(C02PacketUseEntity_Entity_Action,entity.getWrapObject(),action));

    }
    public static boolean isC02PacketUseEntity(Packet packet){
        return C02PacketUseEntityClass.isInstance(packet.getWrapObject());
    }
    public Enum getAction(){
        return (Enum) getField(action);
    }
    public Entity getEntityFromWorld(World world){
        return new Entity(invoke(getEntityFromWorld,world.getWrapObject()));
    }
}
