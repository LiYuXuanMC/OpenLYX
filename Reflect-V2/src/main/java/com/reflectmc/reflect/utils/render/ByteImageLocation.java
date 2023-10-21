package com.reflectmc.reflect.utils.render;

import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.DynamicTexture;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

public class ByteImageLocation {
    ResourceLocation resourceLocation;
    BufferedImage bufferedImage;
    int width, height;
    byte[] base64;
    String name;
    public ByteImageLocation(byte[] base64,String name) {
        this.base64 = base64;
        this.name = name;
    }

    public ResourceLocation getResourceLocation() {
        if (resourceLocation == null) {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(base64);
                bufferedImage = ImageIO.read(bais);
                resourceLocation = new ResourceLocation("REFLECT_RES_" + name);
                width = bufferedImage.getWidth();
                height = bufferedImage.getHeight();
                Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, new DynamicTexture(bufferedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resourceLocation;
    }
}
