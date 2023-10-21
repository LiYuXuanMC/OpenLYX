package al.logger.client.wrapper.LoggerMC.network.client.C03;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.network.Packet;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer$Rotation", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C06PacketPlayerPositionLook extends C03PacketPlayer {
@ClassInstance    
public static Class<?> C06PacketPlayerPositionLookClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = {double.class, double.class, double.class, float.class, float.class, boolean.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {double.class, double.class, double.class, float.class, float.class, boolean.class})
    public static Constructor<?> C06PacketPlayerPositionLookConstructor;

    public C06PacketPlayerPositionLook(Object obj) {
        super(obj);
    }

    public C06PacketPlayerPositionLook(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        super(ReflectUtil.construction(C06PacketPlayerPositionLookConstructor, x, y, z, yaw, pitch, onGround));
    }

    public static boolean isPacketPlayerPositionLook(Packet packet) {
        return C06PacketPlayerPositionLookClass.isInstance(packet.getWrappedObject());
    }
}
