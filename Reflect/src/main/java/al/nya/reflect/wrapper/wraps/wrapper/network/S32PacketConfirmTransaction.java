package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S32PacketConfirmTransaction",targetMap = Maps.Srg1_8_9)
public class S32PacketConfirmTransaction extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S32PacketConfirmTransaction", targetMap = Maps.Srg1_8_9)
    public static Class S32PacketConfirmTransactionClass;
    @WrapField(mcpName = "windowId", targetMap = Maps.Srg1_8_9)
    public static Field windowId;
    @WrapField(mcpName = "actionNumber", targetMap = Maps.Srg1_8_9)
    public static Field actionNumber;

    public S32PacketConfirmTransaction(Object obj) {
        super(obj);
    }

    public S32PacketConfirmTransaction(Packet e) {
        super(e.getWrapObject());
    }

    public static boolean isS32PacketConfirmTransaction(Packet p) {
        return S32PacketConfirmTransactionClass.isInstance(p.getWrapObject());
    }

    public int getWindowId() {
        return (int) getField(windowId);
    }

    public short getActionNumber() {
        return (short) getField(actionNumber);
    }
}
