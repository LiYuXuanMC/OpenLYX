package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketKeepAlive", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.C00PacketKeepAlive", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class C00PacketKeepAlive extends Packet {
@ClassInstance    
public static Class<?> C00PacketKeepAliveClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Constructor C00Con;

    public C00PacketKeepAlive(Object obj) {
        super(obj);
    }

    public C00PacketKeepAlive() {
        super(ReflectUtil.construction(C00Con));
    }

    public static boolean isC00PacketKeepAlive(Packet packet) {
        return C00PacketKeepAliveClass.isInstance(packet.getWrappedObject());
    }
}
