package al.logger.client.wrapper.LoggerMC.network.client.C0B;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.network.Packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0BPacketEntityAction", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketEntityAction", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C0BPacketEntityAction extends Packet {
@ClassInstance    
public static Class<?> C0BPacketEntityActionClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {Entity.class, C0BAction.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = {Entity.class, C0BAction.class})
    public static Constructor<?> C0BPacketEntityAction_Entity_Action;
    @WrapField(mcpName = "action", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "action", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field action;

    public C0BPacketEntityAction(Object obj) {
        super(obj);
    }

    public Enum<?> getAction() {
        return (Enum<?>) getField(action);
    }

    public C0BPacketEntityAction(Entity entity, Enum action) {
        this(construction(C0BPacketEntityAction_Entity_Action, entity.getWrappedObject(), action));
    }

    public static boolean isC0BPacketEntityActionClass(Packet c) {
        return C0BPacketEntityActionClass.isInstance(c.getWrappedObject());
    }
}
