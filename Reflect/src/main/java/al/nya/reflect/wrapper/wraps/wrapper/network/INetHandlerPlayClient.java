package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.INetHandlerPlayClient",targetMap = Maps.Srg1_8_9)
public class INetHandlerPlayClient extends INetHandler{
    public INetHandlerPlayClient(Object obj) {
        super(obj);
    }
}
