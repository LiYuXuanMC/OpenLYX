package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S32PacketConfirmTransaction",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class S32PacketConfirmTransaction extends Packet {
@ClassInstance    
public static Class S32PacketConfirmTransactionClass;
    @WrapField(mcpName = "windowId", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field windowId;
    @WrapField(mcpName = "actionNumber", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field actionNumber;

    public S32PacketConfirmTransaction(Object obj) {
        super(obj);
    }

    public S32PacketConfirmTransaction(Packet e) {
        super(e.getWrappedObject());
    }

    public static boolean isS32PacketConfirmTransaction(Packet p) {
        return S32PacketConfirmTransactionClass.isInstance(p.getWrappedObject());
    }

    public int getWindowId() {
        return (int) getField(windowId);
    }

    public short getActionNumber() {
        return (short) getField(actionNumber);
    }
}
