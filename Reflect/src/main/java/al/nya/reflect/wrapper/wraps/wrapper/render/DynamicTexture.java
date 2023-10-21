package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.awt.image.BufferedImage;
@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture",targetMap = Maps.Srg1_12_2)
public class DynamicTexture extends AbstractTexture {
    @WrapClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture",targetMap = Maps.Srg1_12_2)
    public static Class DynamicTextureClass;
    public DynamicTexture(Object obj) {
        super(obj);
    }
    public DynamicTexture(BufferedImage bufferedImage)
    {
        super(ReflectUtil.construction(DynamicTextureClass,bufferedImage));
    }

    public DynamicTexture(int textureWidth, int textureHeight)
    {
        super(ReflectUtil.construction(DynamicTextureClass,textureWidth,textureHeight));
    }
}
