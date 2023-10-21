package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S02PacketChat", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketChat", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S02PacketChat extends Packet {
@ClassInstance    
public static Class<?> S02PacketChatClass;
    @WrapField(mcpName = "chatComponent",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "chatComponent",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field chatComponent;
    public IChatComponent getChatComponent() {
        return new IChatComponent(getField(chatComponent));
    }
    public S02PacketChat(Object packet){
        super(packet);
    }
    public static boolean isS02PacketChat(Packet packet) {
        return S02PacketChatClass.isInstance(packet.getWrappedObject());
    }
}
