package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S45PacketTitle", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketTitle", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S45PacketTitle extends Packet {
@ClassInstance    
public static Class<?> S45PacketTitleClass;
    @WrapField(mcpName = "message", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "message", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field message;
    public S45PacketTitle(Object obj) {
        super(obj);
    }
    public static boolean isS45PacketTitle(Packet p){
        return S45PacketTitleClass.isInstance(p.getWrappedObject());
    }
    public IChatComponent getMessage(){
        return new IChatComponent(getField(message));
    }
    public String getFormattedText(){
        return getMessage().getFormattedText();
    }
}
