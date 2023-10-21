package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0FPacketConfirmTransaction", targetMap = Maps.Srg1_8_9)
public class C0FPacketConfirmTransaction extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C0FPacketConfirmTransaction", targetMap = Maps.Srg1_8_9)
    public static Class C0FPacketConfirmTransactionClass;
    @WrapField(mcpName = "uid", targetMap = Maps.Srg1_8_9)
    public static Field uid;
    @WrapField(mcpName = "windowId", targetMap = Maps.Srg1_8_9)
    public static Field windowId;

    public C0FPacketConfirmTransaction(Object obj) {
        super(obj);
    }

    public static boolean isPacketConfirmTransaction(Packet packet) {
        return C0FPacketConfirmTransactionClass.isInstance(packet.getWrapObject());
    }

    public short getUID() {
        return (short) getField(uid);
    }

    public int getWindowId() {
        return (int) getField(windowId);
    }
}
