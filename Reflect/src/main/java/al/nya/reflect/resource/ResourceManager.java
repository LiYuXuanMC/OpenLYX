package al.nya.reflect.resource;

import al.nya.reflect.Reflect;
import al.nya.reflect.socket.CommandDownload;
import al.nya.reflect.socket.ControlCommand;
import al.nya.reflect.utils.AntiDump;
import al.nya.reflect.utils.ByteImageLocation;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    public static List<ResourceInfo> resources = new ArrayList<ResourceInfo>();
    public static ByteImageLocation logo;
    public static ByteImageLocation clickGuiSword;
    public static ByteImageLocation clickGuiMovement;
    public static ByteImageLocation clickGuiVisual;
    public static ByteImageLocation clickGuiPerson;
    public static ByteImageLocation clickGuiWorld;
    public static ByteImageLocation clickGuiGhost;
    public static ByteImageLocation clickGuiMinigame;
    public static ByteImageLocation clickGuiStar;
    public static ByteImageLocation clickGuiInfo;
    public static ByteImageLocation clickGuiFillStar;
    public static ByteImageLocation clickGuiTrue;
    public static ByteImageLocation clickGuiKeyboard;
    public static ByteImageLocation clickGuiDown;
    public static ByteImageLocation clickGuiUp;
    private static String map = "";
    public static void init(){
        if (!Reflect.debug)
            resources.add(new ResourceInfo(Reflect.USER.isBeta() ? "runtimeBeta.dll" : "runtimeRelease.dll"));
        resources.add(new ResourceInfo("Baloo.ttf"));
        resources.add(new ResourceInfo("Comfortaa.ttf"));
        resources.add(new ResourceInfo("Arial.ttf"));
        resources.add(new ResourceInfo("Verdana.ttf"));
        resources.add(new ResourceInfo("RobotoLight.ttf"));
        resources.add(new ResourceInfo("4399.png"));
        resources.add(new ResourceInfo("ganga.png"));
        resources.add(new ResourceInfo("matrix.png"));
        resources.add(new ResourceInfo("taijun.png"));
        resources.add(new ResourceInfo("yaoer.png"));
        resources.add(new ResourceInfo("wanghang.png"));
        resources.add(new ResourceInfo("shishengkai.png"));
        resources.add(new ResourceInfo("tianhuang.png"));
        resources.add(new ResourceInfo("logo.png"));
        resources.add(new ResourceInfo("sword.png"));
        resources.add(new ResourceInfo("movement.png"));
        resources.add(new ResourceInfo("visual.png"));
        resources.add(new ResourceInfo("person.png"));
        resources.add(new ResourceInfo("world.png"));
        resources.add(new ResourceInfo("ghost.png"));
        resources.add(new ResourceInfo("minigame.png"));
        resources.add(new ResourceInfo("star.png"));
        resources.add(new ResourceInfo("info.png"));
        resources.add(new ResourceInfo("star-fill.png"));
        resources.add(new ResourceInfo("true.png"));
        resources.add(new ResourceInfo("keyboard.png"));
        resources.add(new ResourceInfo("down.png"));
        resources.add(new ResourceInfo("up.png"));
        resources.add(new ResourceInfo("shaders/bloom.frag"));
        resources.add(new ResourceInfo("shaders/gaussian.frag"));
        resources.add(new ResourceInfo("shaders/glow.frag"));
        resources.add(new ResourceInfo("shaders/gradient.frag"));
        resources.add(new ResourceInfo("shaders/gradientMask.frag"));
        resources.add(new ResourceInfo("shaders/kawaseDown.frag"));
        resources.add(new ResourceInfo("shaders/kawaseUp.frag"));
        resources.add(new ResourceInfo("shaders/outline.frag"));
        resources.add(new ResourceInfo("shaders/roundRectOutline.frag"));
        resources.add(new ResourceInfo("shaders/roundRectTextured.frag"));
        resources.add(new ResourceInfo("shaders/vertex.vsh"));
        payloadDownload(resources.get(0));
    }
    public static void afterDownload(){
        logo = new ByteImageLocation(getRes("logo.png"));
        clickGuiSword = new ByteImageLocation(getRes("sword.png"));
        clickGuiMovement = new ByteImageLocation(getRes("movement.png"));
        clickGuiVisual = new ByteImageLocation(getRes("visual.png"));
        clickGuiPerson = new ByteImageLocation(getRes("person.png"));
        clickGuiWorld = new ByteImageLocation(getRes("world.png"));
        clickGuiGhost = new ByteImageLocation(getRes("ghost.png"));
        clickGuiSword = new ByteImageLocation(getRes("sword.png"));
        clickGuiMinigame = new ByteImageLocation(getRes("minigame.png"));
        clickGuiStar = new ByteImageLocation(getRes("star.png"));
        clickGuiInfo = new ByteImageLocation(getRes("info.png"));
        clickGuiFillStar = new ByteImageLocation(getRes("star-fill.png"));
        clickGuiTrue = new ByteImageLocation(getRes("true.png"));
        clickGuiKeyboard = new ByteImageLocation(getRes("keyboard.png"));
        clickGuiDown = new ByteImageLocation(getRes("down.png"));
        clickGuiUp = new ByteImageLocation(getRes("up.png"));
    }
    public static void payloadDownload(ResourceInfo info){
        System.out.println("Payload downloading "+info.name);
        Reflect.ircClient.send(new Gson().toJson(new ControlCommand("DownloadData",new Gson().toJson(new CommandDownload(info.name)))));
    }
    public static void setMap(String s){
        map = s;
        resources.add(new ResourceInfo(map));
    }
    public static byte[] getMap(){
        return getRes(map);
    }
    public static byte[] getRes(String name){
        for (ResourceInfo resource : resources) {
            if (resource.name.equals(name)){
                return resource.bytes;
            }
        }
        System.out.println("Resource not found: " + name);
        return new byte[0];
    }
}
