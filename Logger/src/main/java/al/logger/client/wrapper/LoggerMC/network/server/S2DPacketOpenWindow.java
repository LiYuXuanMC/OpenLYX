package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
@WrapperClass(mcpName = "net.minecraft.network.play.server.S2DPacketOpenWindow",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class S2DPacketOpenWindow extends IWrapper {
@ClassInstance    
public static Class S2DPacketOpenWindowClass;
    public S2DPacketOpenWindow(Object obj) {
        super(obj);
    }
    public static boolean isS2DPacketOpenWindow(Packet packet){
        return S2DPacketOpenWindowClass.isInstance(packet.getWrappedObject());
    }
}
