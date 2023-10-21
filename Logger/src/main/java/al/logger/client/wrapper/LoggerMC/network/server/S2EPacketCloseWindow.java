package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S2EPacketCloseWindow" , targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Vanilla,Environment.MINECRAFT_VERSION_1_8_9_Forge})
public class S2EPacketCloseWindow extends Packet {

    @ClassInstance
    public static Class S2EPacketCloseWindowClass;
    @WrapField(mcpName = "windowId",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field windowId;
    public S2EPacketCloseWindow(Object obj) {
        super(obj);
    }

    public int getWindowId(){
        return (int) getField(windowId);
    }
    public static boolean isS2EPacketCloseWindow(Packet packet){
        return S2EPacketCloseWindowClass.isInstance(packet.getWrappedObject());
    }
}
