package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.VertexFormat",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.VertexFormat",targetMap = Maps.Srg1_12_2)
public class VertexFormat extends IWrapper {
    public VertexFormat(Object obj) {
        super(obj);
    }
}
