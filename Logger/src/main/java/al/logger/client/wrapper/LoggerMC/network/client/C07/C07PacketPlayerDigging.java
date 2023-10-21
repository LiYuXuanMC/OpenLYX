package al.logger.client.wrapper.LoggerMC.network.client.C07;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C07PacketPlayerDigging", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayerDigging", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C07PacketPlayerDigging extends Packet {
@ClassInstance    
public static Class C07PacketPlayerDiggingClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {C07Action.class, BlockPos.class, EnumFacing.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = {C07Action.class, BlockPos.class, EnumFacing.class})
    public static Constructor C07PacketPlayerDigging_Action_BlockPos_EnumFacing;
    @WrapField(mcpName = "status", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field status;

    public C07PacketPlayerDigging(Object obj) {
        super(obj);
    }

    public C07PacketPlayerDigging(Enum statusIn, BlockPos posIn, Enum facingIn) {
        this(ReflectUtil.construction(C07PacketPlayerDigging_Action_BlockPos_EnumFacing, statusIn, posIn.getWrappedObject(), facingIn));
    }

    public static boolean isCPacketPlayerDigging(Packet packet) {
        return C07PacketPlayerDiggingClass.isInstance(packet.getWrappedObject());
    }

    public Enum getStatus() {
        return (Enum) getField(status);
    }
}
