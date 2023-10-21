package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0EPacketClickWindow", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class C0EPacketClickWindow extends Packet {
@ClassInstance    
public static Class C0EPacketClickWindowClass;

    public C0EPacketClickWindow(Object obj) {
        super(obj);
    }

    public static boolean isPacketClickWindowClass(Packet packet) {
        return C0EPacketClickWindowClass.isInstance(packet.getWrappedObject());
    }
}
