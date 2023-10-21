package al.nya.reflect.wrapper.wraps.wrapper.shader;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.client.shader.ShaderGroup", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.shader.ShaderGroup", targetMap = Maps.Srg1_12_2)
public class ShaderGroup extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.shader.ShaderGroup", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.shader.ShaderGroup", targetMap = Maps.Srg1_12_2)
    public static Class<?> ShaderGroupClass;

    public ShaderGroup(Object obj) {
        super(obj);
    }


}
