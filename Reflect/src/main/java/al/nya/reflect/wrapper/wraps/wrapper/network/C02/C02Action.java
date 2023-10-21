package al.nya.reflect.wrapper.wraps.wrapper.network.C02;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C02PacketUseEntity$Action",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketUseEntity$Action",targetMap = Maps.Srg1_12_2)
public class C02Action extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C02PacketUseEntity$Action",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketUseEntity$Action",targetMap = Maps.Srg1_12_2)
    public static Class ActionClass;
    public C02Action(Object obj) {
        super(obj);
    }
    @WrapEnum(mcpName = "ATTACK",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "ATTACK",targetMap = Maps.Srg1_12_2)
    public static Enum ATTACK;
}
