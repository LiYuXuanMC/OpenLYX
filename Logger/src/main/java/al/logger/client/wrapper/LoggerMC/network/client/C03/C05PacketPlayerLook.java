package al.logger.client.wrapper.LoggerMC.network.client.C03;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.network.Packet;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer$Rotation",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C05PacketPlayerLook extends C03PacketPlayer{
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {float.class,float.class,boolean.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {float.class,float.class,boolean.class})
    public static Constructor C04PacketPlayerPosition_FFZ;
    public C05PacketPlayerLook(Object obj) {
        super(obj);
    }
@ClassInstance    
public static Class<?> C05PacketPlayerLookClass;
    public static boolean isC05PacketPlayerLook(Packet packet){
        return C05PacketPlayerLookClass.isInstance(packet.getWrappedObject());
    }
    public C05PacketPlayerLook(float playerYaw, float playerPitch, boolean isOnGround)
    {
        super(ReflectUtil.construction(C04PacketPlayerPosition_FFZ,playerYaw,playerPitch,isOnGround));
    }
}
