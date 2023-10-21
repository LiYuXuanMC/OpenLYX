package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.annotations.VersionOnly;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C14PacketTabComplete",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketTabComplete",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C14PacketTabComplete extends Packet {
    @ClassInstance
    public static Class C14PacketTabComplete;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {String.class,BlockPos.class})
    public static Constructor C14PacketTabComplete_SB;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {String.class,BlockPos.class,boolean.class})
    public static Constructor C14PacketTabComplete_SBB;
    public C14PacketTabComplete(Object obj) {
        super(obj);
    }

    public C14PacketTabComplete(String s, BlockPos bp){
        super(construction(C14PacketTabComplete_SB,s,bp.getWrappedObject()));
    }
    @Deprecated
    public C14PacketTabComplete(String s, BlockPos bp,boolean hasTargetBlock){
        super(construction(C14PacketTabComplete_SBB,s,bp.getWrappedObject(),hasTargetBlock));
    }
}
