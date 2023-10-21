package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S09PacketHeldItemChange", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketHeldItemChange", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S09PacketHeldItemChange extends Packet {
    @ClassInstance
    public static Class S09PacketHeldItemChangeClass;

    @WrapField(mcpName = "heldItemHotbarIndex", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field heldItemHotbarIndex;
    public S09PacketHeldItemChange(Object obj) {
        super(obj);
    }
    public int getHeldItemHotbarIndex(){
        return (int) getField(heldItemHotbarIndex);
    }


    public static boolean isS09PacketHeldItemChange(Packet packet) {
        return S09PacketHeldItemChangeClass.isInstance(packet.getWrappedObject());
    }

}
