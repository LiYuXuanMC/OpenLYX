package al.nya.reflect;


import al.nya.reflect.config.ConfigManager;
import al.nya.reflect.config.UserConfigManager;
import al.nya.reflect.events.EventBus;
import al.nya.reflect.events.events.*;
import al.nya.reflect.events.events.client.EventLoadConfig;
import al.nya.reflect.events.events.client.EventSaveConfig;
import al.nya.reflect.events.events.client.EventShader;
import al.nya.reflect.events.events.world.EventBlockRenderSide;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.gui.swing.ReflectLoading;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.srgreader.map.MapFindList;
import al.nya.reflect.libraries.srgreader.map.MapNode;
import al.nya.reflect.libraries.srgreader.map.MethodNode;
import al.nya.reflect.libraries.srgreader.map.NodeType;
import al.nya.reflect.resource.ResourceManager;
import al.nya.reflect.socket.Cryptor_AES_CFB8_NoPadding;
import al.nya.reflect.socket.IRCClient;
import al.nya.reflect.socket.User;
import al.nya.reflect.transform.ReflectNative;
import al.nya.reflect.transform.TransformManager;
import al.nya.reflect.utils.AntiDump;
import al.nya.reflect.utils.ClientInfo;
import al.nya.reflect.utils.FileUtil;
import al.nya.reflect.utils.client.*;
import al.nya.reflect.wrapper.Wrapper;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reflect {
    public static Reflect Instance;
    public EventBus eventBus;
    public ClassWriter classWriter;
    public ClassReader classReader;
    public ConfigManager configManager;
    public static Color ColorStyle = new Color(0, 162, 232);
    public static String CLIENT_VERSION = "Rapidly";
    public static String CLIENT_SUB_VERSION = "1.2";
    public static IRCClient ircClient;
    public static boolean debug = true;
    public static User USER = null;
    public static String password = "";
    public static ReflectLoading loading;
    public static String releaseServer = "ws://irc.sd.dustella.net";
    public static String debugServer = "ws://127.0.0.1:6669";
    public static String token = "";
    public static String key = "";
    public static Cryptor_AES_CFB8_NoPadding cryptor = new Cryptor_AES_CFB8_NoPadding("5ca37899z7b0f22e");
    public static Socket injector;
    public static String ReflectDir = System.getProperty("user.home") + "/AppData/Roaming/Reflect";
    public static long startTime;
    public static Map<String, String> nacData = null;
    public static ClientInfo info;
    public Reflect() {
        //if (!Reflect.debug)AntiDump.check();
        Instance = this;
        startTime = System.currentTimeMillis();
        new File(ReflectDir).mkdir();
        loading = new ReflectLoading();
        if (!Reflect.debug) receiveToken();
        if (!Reflect.debug && token.equals("")) loading.FailLoading("Cannot get token");
        System.out.println("Reconnect token:" + token);
        EnvDetector.detect();
        new Thread(new DownloadResource()).start();
    }
    public void receiveToken(){
        StringBuilder receiveMsg = new StringBuilder();
        try {
            injector = new Socket("127.0.0.1",12123);
            //从输入流中解析数据，输入流来自服务端的响应
            InputStream in = injector.getInputStream();
            for (int c = in.read(); c != '#'; c = in.read()) {
                if(c==-1)
                    break;
                receiveMsg.append((char)c);
            }
        }catch (Exception e){
            e.printStackTrace();
            File file = new File(ReflectDir+"/Data.dat");
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] bytes = FileUtil.readStream(fis);
                receiveMsg.append(new String(bytes));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String[] a = receiveMsg.toString().split("/");
        token = a[0];
        key = a[1];
        cryptor = new Cryptor_AES_CFB8_NoPadding(key);
    }
    private void loadRuntime(){
        try {
            FileUtil.writeFile(new File(ReflectDir+"/runtime.dll"),ResourceManager.getRes(Reflect.USER.isBeta() ? "runtimeBeta.dll" :"runtimeRelease.dll"));
            System.load(ReflectDir+"/runtime.dll");
            System.out.println("Runtime loaded");
        } catch (IOException e) {
            e.printStackTrace();
            loading.FailLoading("Fail load Runtime");
        }
    }
    public void startInit(){
        new Thread(new InitReflect()).start();
    }
    public static class DownloadResource implements Runnable{
        @Override
        public void run() {
            try {
                //ReflectLoading.loadingProgress.setString("Connecting");
                loading.setLoadingType("Reconnecting");
                ircClient = new IRCClient(new URI(debug ? debugServer : releaseServer));
                ircClient.setConnectionLostTimeout(60000);
                ircClient.connect();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    public class InitReflect implements Runnable {
        @Override
        public void run() {
            Reflect.loading.setLoadingType("Decoding Resource");
            ResourceManager.afterDownload();
            Reflect.loading.setLoadingType("Loading Runtime");
            if (!debug) {
                loadRuntime();
            }
            try {
                Wrapper.readMap();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            Reflect.loading.setLoadingType("Detecting AC");
            if (NACFucker.NACDetect()) {
                Reflect.loading.setLoadingType("Decrypting");
                Map<String, String> decrypted = NACFucker.decrypt();
                nacData = decrypted;
                MapFindList mapNodes = Wrapper.getReader().getMapNodes();
                List<MapNode> list = new ArrayList<MapNode>();
                List<MapNode> remove = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    ReflectNative.log("Decrypting: " + i);
                    decrypted.forEach((key, value) -> {
                        for (MapNode mapNode : mapNodes) {
                            if (mapNode.getSrg().endsWith(key)) {
                                if (mapNode instanceof MethodNode) {
                                    list.add(new MethodNode(mapNode.getNodeType(), mapNode.getMcp(), value, ((MethodNode) mapNode).getSignature(), ((MethodNode) mapNode).getOrgSig(), ((MethodNode) mapNode).getDeobfSig()));
                                } else {
                                    list.add(new MapNode(mapNode.getNodeType(), mapNode.getMcp(), value));
                                }
                                remove.add(mapNode);
                                return;
                            }
                        }
                        ReflectNative.log(key + " can't find in map");
                    });
                    for (MapNode mapNode : remove) {
                        mapNodes.remove(mapNode);
                    }
                    mapNodes.addAll(list);
                }
            }
            Reflect.loading.setLoadingType("Init Wrapper");
            Wrapper.initWrapper();
            Reflect.loading.setLoadingType("Init Events");
            eventBus = new EventBus();
            eventBus.addEvents(EventAttack.class, EventJump.class, EventKey.class, EventLoop.class, EventMouse.class, EventPacket.class,
                    EventPostUpdate.class, EventPreUpdate.class, EventPullback.class, EventPushBlock.class, EventRender2D.class, EventRender3D.class,
                    EventSoundPlay.class, EventStep.class, EventTick.class, EventUpdate.class, EventMove.class, EventWorldLoad.class,
                    EventWindowClick.class, EventChat.class, EventBlockRenderSide.class, EventText.class, EventPreRenderLiving.class, EventPostRenderLiving.class,
                    EventLoadConfig.class, EventSaveConfig.class, EventShader.class, EventNameTag.class);
            Reflect.loading.setLoadingType("Init Modules");
            ModuleManager.init();
            Reflect.loading.setLoadingType("Transforming");
            TransformManager.init();
            classWriter = new ClassWriter(0);
            if (classWriter != null) {
                System.out.println("ClassWriter created");
            }
            try {
                classReader = new ClassReader(new byte[0]);
            } catch (Exception e) {

            }
            TransformManager.transform();
            configManager = new ConfigManager();
            configManager.init();
            UserConfigManager.init();
            if (NeteaseDetector.isFilter()) {
                NeteaseDetector.writeChatFilterWords();
            }
            info = new ClientInfo();
            //KeyManager.init();
        }
    }
}
