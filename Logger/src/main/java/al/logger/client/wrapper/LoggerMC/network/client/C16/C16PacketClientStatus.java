package al.logger.client.wrapper.LoggerMC.network.client.C16;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.network.Packet;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C16PacketClientStatus",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class C16PacketClientStatus extends Packet {
    @ClassInstance
    public static Class C16PacketClientStatusClass;
    @WrapField(mcpName = "status",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field status;
    public C16PacketClientStatus(Object obj) {
        super(obj);
    }
    public static boolean isC16PacketClientStatus(Packet packet){
        return C16PacketClientStatusClass.isInstance(packet.getWrappedObject());
    }
    public Enum getStatus(){
        return (Enum) getField(status);
    }
}
