package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.features.modules.Module;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Constructor;


@WrapperClass(mcpName = "net.minecraft.network.play.client.C17PacketCustomPayload",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketTabComplete",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C17PacketCustomPayload extends Packet {
    @ClassInstance
    public static Class C17PacketCustomPayload;

    public C17PacketCustomPayload(Object obj) {
        super(obj);
    }

    public static boolean isC17PacketCustomPayload(Packet packet){
        return C17PacketCustomPayload.isInstance(packet.getWrappedObject());
    }
}
