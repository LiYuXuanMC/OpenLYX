package al.logger.client.resource;

import al.logger.client.Logger;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import by.radioegor146.nativeobfuscator.Native;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Native
public class ResourceManager {

    public HashMap<String, ResourceInfo> resourceInfos = new HashMap<>();
    public String downloadURL = "http://111.180.205.223:22218";
    private HashMap<String, ResourceLocation> pictures = new HashMap<>();
    @Getter
    private HashMap<String, ResourceLocation> skinCapes = new HashMap<>();

    public void init() {

        throw new RuntimeException();
    }

    public ResourceLocation getResourceLocation(String name) {
        name = "resource/" + name;
        if (resourceInfos.containsKey(name)) {
            return resourceInfos.get(name).getResourceMC();
        }

        return null;
    }

    public ResourceLocation getPicture(String username) {
        String getApi = "http://111.180.205.223:22218/profile/picture?username=" + username + "&idun=" + Logger.getInstance().getLoggerUser().getIdunToken() + "&userid=" +
                Logger.getInstance().getLoggerUser().getUserid();
        try {
            if (pictures.containsKey(username)) {
                return pictures.get(username);
            }
            BufferedImage bufferedImage = toCircle(ImageIO.read(new ByteArrayInputStream(ResourceInfo.downloadBytes(getApi))));
            ResourceLocation resourceLocation = new ByteLocation(null, username).getResourceLocation("Profile_Picture_", bufferedImage);
            pictures.put(username, resourceLocation);
            return resourceLocation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removePicture(String username) {
        pictures.remove(username);
    }

    public ResourceLocation getCape(String username) {
        String getApi = "http://111.180.205.223:22218/profile/skin/cape?username=" + username + "&idun=" + Logger.getInstance().getLoggerUser().getIdunToken() + "&userid=" +
                Logger.getInstance().getLoggerUser().getUserid();
        try {
            if (skinCapes.containsKey(username)) {
                return skinCapes.get(username);
            }
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(ResourceInfo.downloadBytes(getApi)));
            ResourceLocation resourceLocation = new ByteLocation(null, username).getResourceLocation("Profile_Skid_Cape_", bufferedImage);
            skinCapes.put(username, resourceLocation);
            return resourceLocation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeCape(String username) {
        skinCapes.remove(username);
    }

    public BufferedImage toCircle(BufferedImage buffImg1) {
        BufferedImage resultImg = null;
        resultImg = new BufferedImage(buffImg1.getWidth(), buffImg1.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resultImg.createGraphics();
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, buffImg1.getWidth(), buffImg1.getHeight());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        resultImg = g.getDeviceConfiguration().createCompatibleImage(buffImg1.getWidth(), buffImg1.getHeight(),
                Transparency.TRANSLUCENT);
        g = resultImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setClip(shape);
        g.drawImage(buffImg1, 0, 0, null);
        g.dispose();
        return resultImg;
    }

    public byte[] getResourceBytes(String name) {
        if (name.equals("map")) {
            name = "resource/" + EnvironmentDetector.getMinecraft().getMapName();
        } else {
            name = "resource/" + name;
        }
        if (resourceInfos.containsKey(name)) {
            return resourceInfos.get(name).bytes;
        }
        System.out.println("Resource not found: " + name);
        return null;
    }

    public InputStream getInputStream(String name) {
        String getName = "resource/" + name;
        if (resourceInfos.containsKey(getName)) {
            return new ByteArrayInputStream(resourceInfos.get(getName).bytes);
        }
        System.out.println("Resource not found: " + name);
        return null;
    }

}
