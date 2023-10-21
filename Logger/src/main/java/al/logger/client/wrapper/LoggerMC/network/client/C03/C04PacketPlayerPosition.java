package al.logger.client.wrapper.LoggerMC.network.client.C03;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.network.Packet;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer$Position",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C04PacketPlayerPosition extends C03PacketPlayer{
@ClassInstance    
public static Class C04PacketPlayerPositionClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {double.class,double.class,double.class,boolean.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {double.class,double.class,double.class,boolean.class})
    public static Constructor C04PacketPlayerPosition_DDDZ;
    public C04PacketPlayerPosition(Object obj) {
        super(obj);
    }
    public static boolean isPacketPlayerPosition(Packet packet){
        return C04PacketPlayerPositionClass.isInstance(packet.getWrappedObject());
    }
    public C04PacketPlayerPosition(double posX, double posY, double posZ, boolean isOnGround)
    {
        super(ReflectUtil.construction(C04PacketPlayerPosition_DDDZ,posX,posY,posZ,isOnGround));
    }
}
