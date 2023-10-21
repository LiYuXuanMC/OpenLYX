package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C09PacketHeldItemChange",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketHeldItemChange",targetMap = Maps.Srg1_12_2)
public class C09PacketHeldItemChange extends Packet{
    @WrapClass(mcpName = "net.minecraft.network.play.client.C09PacketHeldItemChange",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketHeldItemChange",targetMap = Maps.Srg1_12_2)
    public static Class C09PacketHeldItemChangeClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {int.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {int.class})
    public static Constructor C09PacketHeldItemChange_I;
    @WrapField(mcpName = "slotId",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "slotId",targetMap = Maps.Srg1_12_2)
    public static Field slotId;
    public C09PacketHeldItemChange(Object obj) {
        super(obj);
    }
    public C09PacketHeldItemChange(int i){
        this(ReflectUtil.construction(C09PacketHeldItemChange_I,i));
    }
    public static boolean isC09PacketHeldItemChange(Packet packet){
        return C09PacketHeldItemChangeClass.isInstance(packet.getWrapObject());
    }
    public int getSlotId(){
        return (int) getField(slotId);
    }
}
