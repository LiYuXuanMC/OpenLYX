package al.logger.client.resource;


import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.render.DynamicTexture;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;


public class ByteLocation {
    ResourceLocation resourceLocation;
    BufferedImage bufferedImage;
    int width, height;
    byte[] base64;
    String name;

    public ByteLocation(byte[] base64, String name) {
        if (base64 == null) {
            base64 = new byte[0];
        }
        this.base64 = base64;
        this.name = name;
    }


    public ResourceLocation getResourceLocation() {
        return this.getResourceLocation("LOGGER_RES_");
    }

    public ResourceLocation getResourceLocation(String addName) {
        if (resourceLocation == null) {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(base64);
                bufferedImage = ImageIO.read(bais);
                this.resourceLocation = new ResourceLocation(addName + name);
                if (bufferedImage == null) {
                    bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                }
                width = bufferedImage.getWidth();
                height = bufferedImage.getHeight();
                Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, new DynamicTexture(bufferedImage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resourceLocation;
    }

    public ResourceLocation getResourceLocation(String addName, BufferedImage bufferedImage) {
        if (resourceLocation == null) {
            try {
                this.bufferedImage = bufferedImage;
                this.resourceLocation = new ResourceLocation(addName + name);
                if (bufferedImage == null) {
                    bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                }
                this.width = bufferedImage.getWidth();
                this.height = bufferedImage.getHeight();
                Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, new DynamicTexture(bufferedImage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resourceLocation;
    }
}
