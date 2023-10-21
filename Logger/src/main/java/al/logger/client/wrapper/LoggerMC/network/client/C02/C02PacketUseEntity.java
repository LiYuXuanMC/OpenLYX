package al.logger.client.wrapper.LoggerMC.network.client.C02;

import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.world.World;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C02PacketUseEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketUseEntity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C02PacketUseEntity extends Packet {
@ClassInstance
public static Class C02PacketUseEntityClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {Entity.class, C02Action.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {Entity.class, C02Action.class})
    public static Constructor C02PacketUseEntity_Entity_Action;

    @WrapField(mcpName = "action",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "action",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field action;
    @WrapMethod(mcpName = "getEntityFromWorld",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getEntityFromWorld",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEntityFromWorld;
    @WrapField(mcpName = "entityId",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field entityId;

    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {Entity.class, Vec3.class})
    public static Constructor C02PacketUseEntity_Entity_Vec3;
    public C02PacketUseEntity(Object obj) {
        super(obj);
    }
    public C02PacketUseEntity(Entity entity, Enum action)
    {
        this(construction(C02PacketUseEntity_Entity_Action,entity.getWrappedObject(),action));
    }

    public C02PacketUseEntity(Entity entity , Vec3 vec3){
        this(construction(C02PacketUseEntity_Entity_Vec3,entity.getWrappedObject(),vec3.getWrappedObject()));
    }


    public static boolean isC02PacketUseEntity(Packet packet){
        return C02PacketUseEntityClass.isInstance(packet.getWrappedObject());
    }
    public Enum getAction(){
        return (Enum) getField(action);
    }

    public int getEntity(){return (int)getField(entityId);}
    public Entity getEntityFromWorld(World world){
        return new Entity(invoke(getEntityFromWorld,world.getWrappedObject()));
    }
}
