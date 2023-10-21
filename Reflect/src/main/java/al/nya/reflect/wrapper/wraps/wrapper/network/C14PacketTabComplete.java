package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C14PacketTabComplete",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketTabComplete",targetMap = Maps.Srg1_12_2)
public class C14PacketTabComplete extends Packet{
    @WrapClass(mcpName = "net.minecraft.network.play.client.C14PacketTabComplete",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketTabComplete",targetMap = Maps.Srg1_12_2)
    public static Class C14PacketTabComplete;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {String.class,BlockPos.class})
    public static Constructor C14PacketTabComplete_SB;
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {String.class,BlockPos.class,boolean.class})
    public static Constructor C14PacketTabComplete_SBB;
    public C14PacketTabComplete(Object obj) {
        super(obj);
    }
    public C14PacketTabComplete(String s, BlockPos bp){
        super(Wrapper.env.equals(Maps.Srg1_12_2) ? new C14PacketTabComplete(s,bp,false)
                : ReflectUtil.construction(C14PacketTabComplete_SB,s,bp.getWrapObject()));
    }
    public C14PacketTabComplete(String s, BlockPos bp,boolean hasTargetBlock){
        super(ReflectUtil.construction(C14PacketTabComplete_SBB,s,bp.getWrapObject(),hasTargetBlock));
    }
}
