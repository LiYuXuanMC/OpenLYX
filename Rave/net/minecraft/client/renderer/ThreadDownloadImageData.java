package net.minecraft.client.renderer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.Proxy.Type;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.http.HttpPipeline;
import net.optifine.http.HttpRequest;
import net.optifine.http.HttpResponse;
import net.optifine.player.CapeImageBuffer;
import net.optifine.shaders.ShadersTex;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadDownloadImageData extends SimpleTexture
{
    private static final Logger logger = LogManager.getLogger();
    private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
    private final File cacheFile;
    private final String imageUrl;
    private final IImageBuffer imageBuffer;
    private BufferedImage bufferedImage;
    private Thread imageThread;
    private boolean textureUploaded;
    public Boolean imageFound = null;
    public boolean pipeline = false;

    public ThreadDownloadImageData(File cacheFileIn, String imageUrlIn, ResourceLocation textureResourceLocation, IImageBuffer imageBufferIn)
    {
        super(textureResourceLocation);
        this.cacheFile = cacheFileIn;
        this.imageUrl = imageUrlIn;
        this.imageBuffer = imageBufferIn;
    }

    private void checkTextureUploaded()
    {
        if (!this.textureUploaded && this.bufferedImage != null)
        {
            this.textureUploaded = true;

            if (this.textureLocation != null)
            {
                this.deleteGlTexture();
            }

            if (Config.isShaders())
            {
                ShadersTex.loadSimpleTexture(super.getGlTextureId(), this.bufferedImage, false, false, Config.getResourceManager(), this.textureLocation, this.getMultiTexID());
            }
            else
            {
                TextureUtil.uploadTextureImage(super.getGlTextureId(), this.bufferedImage);
            }
        }
    }

    public int getGlTextureId()
    {
        this.checkTextureUploaded();
        return super.getGlTextureId();
    }

    public void setBufferedImage(BufferedImage bufferedImageIn)
    {
        this.bufferedImage = bufferedImageIn;

        if (this.imageBuffer != null)
        {
            this.imageBuffer.skinAvailable();
        }

        this.imageFound = Boolean.valueOf(this.bufferedImage != null);
    }

    public void loadTexture(IResourceManager resourceManager) throws IOException
    {
        if (this.bufferedImage == null && this.textureLocation != null)
        {
            super.loadTexture(resourceManager);
        }

        if (this.imageThread == null)
        {
            if (this.cacheFile != null && this.cacheFile.isFile())
            {
                logger.debug("Loading http texture from local cache ({})", new Object[] {this.cacheFile});

                try
                {
                    this.bufferedImage = ImageIO.read(this.cacheFile);

                    if (this.imageBuffer != null)
                    {
                        this.setBufferedImage(this.imageBuffer.parseUserSkin(this.bufferedImage));
                    }

                    this.loadingFinished();
                }
                catch (IOException ioexception)
                {
                    logger.error((String)("Couldn\'t load skin " + this.cacheFile), (Throwable)ioexception);
                    this.loadTextureFromServer();
                }
            }
            else
            {
                this.loadTextureFromServer();
            }
        }
    }

    protected void loadTextureFromServer()
    {
        this.imageThread = new Thread("Texture Downloader #" + threadDownloadCounter.incrementAndGet())
        {
            public void run()
            {
                HttpURLConnection httpurlconnection = null;
                ThreadDownloadImageData.logger.debug("Downloading http texture from {} to {}", new Object[] {ThreadDownloadImageData.this.imageUrl, ThreadDownloadImageData.this.cacheFile});

                if (ThreadDownloadImageData.this.shouldPipeline())
                {
                    ThreadDownloadImageData.this.loadPipelined();
                }
                else
                {
                    try
                    {
                        httpurlconnection = (HttpURLConnection)(new URL(ThreadDownloadImageData.this.imageUrl)).openConnection(Minecraft.getMinecraft().getProxy());
                        httpurlconnection.setDoInput(true);
                        httpurlconnection.setDoOutput(false);
                        httpurlconnection.connect();

                        if (httpurlconnection.getResponseCode() / 100 != 2)
                        {
                            if (httpurlconnection.getErrorStream() != null)
                            {
                                Config.readAll(httpurlconnection.getErrorStream());
                            }

                            return;
                        }

                        BufferedImage bufferedimage;

                        if (ThreadDownloadImageData.this.cacheFile != null)
                        {
                            FileUtils.copyInputStreamToFile(httpurlconnection.getInputStream(), ThreadDownloadImageData.this.cacheFile);
                            bufferedimage = ImageIO.read(ThreadDownloadImageData.this.cacheFile);
                        }
                        else
                        {
                            bufferedimage = TextureUtil.readBufferedImage(httpurlconnection.getInputStream());
                        }

                        if (ThreadDownloadImageData.this.imageBuffer != null)
                        {
                            bufferedimage = ThreadDownloadImageData.this.imageBuffer.parseUserSkin(bufferedimage);
                        }

                        ThreadDownloadImageData.this.setBufferedImage(bufferedimage);
                    }
                    catch (Exception exception)
                    {
                        ThreadDownloadImageData.logger.error("Couldn\'t download http texture: " + exception.getClass().getName() + ": " + exception.getMessage());
                        return;
                    }
                    finally
                    {
                        if (httpurlconnection != null)
                        {
                            httpurlconnection.disconnect();
                        }

                        ThreadDownloadImageData.this.loadingFinished();
                    }
                }
            }
        };
        this.imageThread.setDaemon(true);
        this.imageThread.start();
    }

    private boolean shouldPipeline()
    {
        if (!this.pipeline)
        {
            return false;
        }
        else
        {
            Proxy proxy = Minecraft.getMinecraft().getProxy();
            return proxy.type() != Type.DIRECT && proxy.type() != Type.SOCKS ? false : this.imageUrl.startsWith("http://");
        }
    }

    private void loadPipelined()
    {
        try
        {
            HttpRequest httprequest = HttpPipeline.makeRequest(this.imageUrl, Minecraft.getMinecraft().getProxy());
            HttpResponse httpresponse = HttpPipeline.executeRequest(httprequest);

            if (httpresponse.getStatus() / 100 != 2)
            {
                return;
            }

            byte[] abyte = httpresponse.getBody();
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte);
            BufferedImage bufferedimage;

            if (this.cacheFile != null)
            {
                FileUtils.copyInputStreamToFile(bytearrayinputstream, this.cacheFile);
                bufferedimage = ImageIO.read(this.cacheFile);
            }
            else
            {
                bufferedimage = TextureUtil.readBufferedImage(bytearrayinputstream);
            }

            if (this.imageBuffer != null)
            {
                bufferedimage = this.imageBuffer.parseUserSkin(bufferedimage);
            }

            this.setBufferedImage(bufferedimage);
        }
        catch (Exception exception)
        {
            logger.error("Couldn\'t download http texture: " + exception.getClass().getName() + ": " + exception.getMessage());
            return;
        }
        finally
        {
            this.loadingFinished();
        }
    }

    private void loadingFinished()
    {
        this.imageFound = Boolean.valueOf(this.bufferedImage != null);

        if (this.imageBuffer instanceof CapeImageBuffer)
        {
            CapeImageBuffer capeimagebuffer = (CapeImageBuffer)this.imageBuffer;
            capeimagebuffer.cleanup();
        }
    }

    public IImageBuffer getImageBuffer()
    {
        return this.imageBuffer;
    }
}
