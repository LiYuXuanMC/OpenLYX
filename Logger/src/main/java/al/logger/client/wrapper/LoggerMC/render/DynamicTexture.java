package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class DynamicTexture extends AbstractTexture {
    @ClassInstance
    public static Class DynamicTextureClass;
    @WrapConstructor(signature = {BufferedImage.class},targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Constructor<?> DynamicTexture_BufferedImage;
    @WrapConstructor(signature = {int.class,int.class},targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Constructor<?> DynamicTexture_II;
    public DynamicTexture(Object obj) {
        super(obj);
    }
    public DynamicTexture(BufferedImage bufferedImage)
    {
        super(construction(DynamicTexture_BufferedImage,bufferedImage));
    }

    public DynamicTexture(int textureWidth, int textureHeight)
    {
        super(construction(DynamicTexture_II,textureWidth,textureHeight));
    }
}
