package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.NetworkManager$InboundHandlerTuplePacketListener",targetMap = Maps.Srg1_8_9)
public class InboundHandlerTuplePacketListener extends IWrapper {
    @WrapConstructor(targetMap = Maps.Srg1_8_9)
    public static Constructor InboundHandlerTuplePacketListener;
    public InboundHandlerTuplePacketListener(Object obj) {
        super(obj);
    }
    public InboundHandlerTuplePacketListener(Packet p,Object[] inFutureListeners){
        this(ReflectUtil.construction(InboundHandlerTuplePacketListener,p.getWrapObject(),inFutureListeners));
    }
}
