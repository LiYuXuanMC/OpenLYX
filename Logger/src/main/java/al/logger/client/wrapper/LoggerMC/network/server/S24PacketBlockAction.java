package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S24PacketBlockAction", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketBlockAction", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S24PacketBlockAction extends Packet {
@ClassInstance    
public static Class<?> S24PacketBlockActionClass;
    @WrapMethod(mcpName = "getBlockPosition", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getBlockPosition", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getBlockPosition;
    @WrapMethod(mcpName = "getData1", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getData1", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getData1;
    @WrapMethod(mcpName = "getData2", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getData2", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getData2;

    public S24PacketBlockAction(Object obj) {
        super(obj);
    }

    public static boolean isS24PacketBlockAction(Packet e) {
        return S24PacketBlockActionClass.isInstance(e.getWrappedObject());
    }

    public BlockPos getBlockPosition() {
        return new BlockPos(invoke(getBlockPosition));
    }

    public int getData1() {
        return (int) invoke(getData1);
    }

    public int getData2() {
        return (int) invoke(getData2);
    }
}
