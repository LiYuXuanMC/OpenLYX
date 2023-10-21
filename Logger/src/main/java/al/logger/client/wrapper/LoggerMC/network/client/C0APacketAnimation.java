package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Constructor;


@WrapperClass(mcpName = "net.minecraft.network.play.client.C0APacketAnimation",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketAnimation",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C0APacketAnimation extends Packet {
@ClassInstance    
public static Class C0APacketAnimationClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Constructor C0APacketAnimation_V;

    public C0APacketAnimation(Object obj) {
        super(obj);
    }
    public C0APacketAnimation(){
        this(ReflectUtil.construction(C0APacketAnimation_V));
    }

    public static boolean isC0APacket(Packet packet) {
        return C0APacketAnimationClass.isInstance(packet.getWrappedObject());
    }
}
