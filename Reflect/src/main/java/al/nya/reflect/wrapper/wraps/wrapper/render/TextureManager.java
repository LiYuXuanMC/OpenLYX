package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ResourceLocation;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.TextureManager",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.TextureManager",targetMap = Maps.Srg1_12_2)
public class TextureManager extends IWrapper {
    @WrapMethod(mcpName = "bindTexture",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "bindTexture",targetMap = Maps.Srg1_12_2)
    public static Method bindTexture;
    @WrapMethod(mcpName = "loadTexture",targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "loadTexture",targetMap = Maps.Srg1_8_9)
    public static Method loadTexture;
    @WrapMethod(mcpName = "getTexture",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getTexture",targetMap = Maps.Srg1_12_2)
    public static Method getTexture;

    public TextureManager(Object obj) {
        super(obj);
    }
    public void bindTexture(ResourceLocation resource){
        ReflectUtil.invoke(bindTexture,getWrapObject(),resource.getWrapObject());
    }
    public void loadTexture(ResourceLocation resourceLocation, DynamicTexture dynamicTexture) {
        invoke(loadTexture,resourceLocation.getWrapObject(),dynamicTexture.getWrapObject());
    }
}
