package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C01PacketChatMessage",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketChatMessage",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C01PacketChatMessage extends Packet {
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {String.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {String.class})
    public static Constructor<?> C01PacketChatMessage_String;

    @WrapField(mcpName = "message",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "message",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field message;

@ClassInstance    
public static Class<?> C01PacketChatMessageClass;

    public C01PacketChatMessage(Object obj) {
        super(obj);
    }
    public C01PacketChatMessage(String s){
        this(ReflectUtil.construction(C01PacketChatMessage_String,s));
    }

    public String getMessage(){
        return (String) ReflectUtil.getField(message,getWrappedObject());
    }

    public static boolean isC01PacketChatMessage(Packet c) {
        return C01PacketChatMessageClass.isInstance(c.getWrappedObject());
    }
}
