package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;

@WrapperClass(deobfName = "net.minecraft.client.renderer.texture.DynamicTexture",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.texture.DynamicTexture",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class DynamicTexture extends AbstractTexture {
    @ClassInstance
    public static Class DynamicTextureClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {BufferedImage.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {BufferedImage.class})
    public static Constructor DynamicTexture_bi;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {int.class,int.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {int.class,int.class})
    public static Constructor DynamicTexture_II;
    public DynamicTexture(Object obj) {
        super(obj);
    }
    public DynamicTexture(BufferedImage bufferedImage)
    {
        super(construct(DynamicTexture_bi,bufferedImage));
    }
    public DynamicTexture(int textureWidth, int textureHeight)
    {
        super(construct(DynamicTexture_II,textureWidth,textureHeight));
    }
}
