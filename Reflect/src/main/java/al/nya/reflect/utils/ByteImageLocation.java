package al.nya.reflect.utils;

import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.render.DynamicTexture;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ResourceLocation;

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

    public ByteImageLocation(byte[] base64) {
        this.base64 = base64;
    }

    public ResourceLocation getResourceLocation() {
        if (resourceLocation == null) {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(base64);
                bufferedImage = ImageIO.read(bais);
                resourceLocation = new ResourceLocation("REFLECT_RES_" + new Random().nextInt(1000));
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
