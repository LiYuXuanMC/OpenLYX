package net.optifine.shaders;

import java.nio.ByteBuffer;
import net.optifine.texture.InternalFormat;
import net.optifine.texture.PixelFormat;
import net.optifine.texture.PixelType;
import net.optifine.texture.TextureType;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class CustomTextureRaw implements ICustomTexture
{
    private TextureType type;
    private int textureUnit;
    private int textureId;

    public CustomTextureRaw(TextureType type, InternalFormat internalFormat, int width, int height, int depth, PixelFormat pixelFormat, PixelType pixelType, ByteBuffer data, int textureUnit)
    {
        this.type = type;
        this.textureUnit = textureUnit;
        this.textureId = GL11.glGenTextures();
        GL11.glBindTexture(this.getTarget(), this.textureId);

        switch (type)
        {
            case TEXTURE_1D:
                GL11.glTexImage1D(GL11.GL_TEXTURE_1D, 0, internalFormat.getId(), width, 0, pixelFormat.getId(), pixelType.getId(), (ByteBuffer)data);
                GL11.glTexParameteri(GL11.GL_TEXTURE_1D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
                GL11.glTexParameteri(GL11.GL_TEXTURE_1D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
                GL11.glTexParameteri(GL11.GL_TEXTURE_1D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
                break;

            case TEXTURE_2D:
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, internalFormat.getId(), width, height, 0, pixelFormat.getId(), pixelType.getId(), (ByteBuffer)data);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
                break;

            case TEXTURE_3D:
                GL12.glTexImage3D(GL12.GL_TEXTURE_3D, 0, internalFormat.getId(), width, height, depth, 0, pixelFormat.getId(), pixelType.getId(), (ByteBuffer)data);
                GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
                GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
                GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL12.GL_TEXTURE_WRAP_R, GL12.GL_CLAMP_TO_EDGE);
                GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
                GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
                break;

            case TEXTURE_RECTANGLE:
                GL11.glTexImage2D(34037, 0, internalFormat.getId(), width, height, 0, pixelFormat.getId(), pixelType.getId(), (ByteBuffer)data);
                GL11.glTexParameteri(34037, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
                GL11.glTexParameteri(34037, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
                GL11.glTexParameteri(34037, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
                GL11.glTexParameteri(34037, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        }

        GL11.glBindTexture(this.getTarget(), 0);
    }

    public int getTarget()
    {
        return this.type.getId();
    }

    public int getTextureId()
    {
        return this.textureId;
    }

    public int getTextureUnit()
    {
        return this.textureUnit;
    }

    public void deleteTexture()
    {
        if (this.textureId > 0)
        {
            GL11.glDeleteTextures(this.textureId);
            this.textureId = 0;
        }
    }
}
