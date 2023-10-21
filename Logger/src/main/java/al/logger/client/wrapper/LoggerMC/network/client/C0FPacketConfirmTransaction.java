package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0FPacketConfirmTransaction", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class C0FPacketConfirmTransaction extends Packet {
@ClassInstance    
public static Class C0FPacketConfirmTransactionClass;
    @WrapField(mcpName = "uid", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field uid;
    @WrapField(mcpName = "windowId", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field windowId;

    public C0FPacketConfirmTransaction(Object obj) {
        super(obj);
    }

    public static boolean isPacketConfirmTransaction(Packet packet) {
        return C0FPacketConfirmTransactionClass.isInstance(packet.getWrappedObject());
    }

    public short getUID() {
        return (short) getField(uid);
    }


    public int getWindowId() {
        return (int) getField(windowId);
    }
}
