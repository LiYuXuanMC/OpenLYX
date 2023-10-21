package al.logger.client;

import al.logger.client.agent.NativeAgent;
import al.logger.client.bridge.BridgeManager;
import al.logger.client.config.ConfigManager;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.features.commands.CommandManager;
import al.logger.client.features.modules.GlobalConfiguration;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleCarrier;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.features.modules.impls.World.AuthEntityPlayer;
import al.logger.client.features.modules.lock.LockManager;
import al.logger.client.managers.AuthUserManager;
import al.logger.client.resource.ResourceInfo;
import al.logger.client.resource.ResourceManager;
import al.logger.client.script.ScriptManager;
import al.logger.client.transform.TransformManager;
import al.logger.client.transform.transformers.ClassDumpTransformer;
import al.logger.client.ui.bases.components.InstanceEx;
import al.logger.client.ui.bases.components.NextEx;
import al.logger.client.ui.bases.components.Notification;
import al.logger.client.ui.builders.MessageBuilder;
import al.logger.client.ui.builders.NotificationBuilder;
import al.logger.client.ui.managers.MessageManager;
import al.logger.client.ui.managers.MusicManager;
import al.logger.client.ui.managers.NotificationManager;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.FileUtils;
import al.logger.client.utils.FriendManager;
import al.logger.client.utils.ReflectUtil;
import al.logger.client.utils.anticrack.ClassInfo;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.value.ValueManager;
import al.logger.client.value.bases.AuthUser;
import al.logger.client.value.bases.IRCObject;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.cactus.CactusAdaptor;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import al.logger.client.wrapper.map.MapParser;
import al.logger.libs.asm.ClassReader;
import al.logger.libs.asm.ClassWriter;
import al.logger.libs.asm.tree.ClassNode;
import al.logger.libs.java_websocket.client.WebSocketClient;
import al.logger.libs.java_websocket.handshake.ServerHandshake;
import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.VM;
import com.google.gson.*;
import net.minecraft.client.gui.FontRenderer;
import sun.misc.Unsafe;

import javax.crypto.Cipher;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Native
public class LoggerWS extends WebSocketClient {

    public ArrayList<IRCObject> onlineList = new ArrayList<>();
    protected static ScheduledExecutorService scheduledExecutorService = null;
    protected static URI loggerURI = null;
    protected static ServerHandshake hs = null;
    protected static boolean isReconnect = false;
    protected static boolean inLogin = false;
    protected static int hsCode = 99;
    protected static String RSA_1 = "";
    protected static String RSA_2 = "";
    protected static String RSA_3 = "";
    protected static String RSA_4 = "";
    protected static String RSA_5 = "";
    protected static String RSA_6 = "";
    protected static String RSA_7 = "";
    protected static boolean isLoaded = false;


    public static Queue<Exception> queue = new LinkedList();
    ;

    static {
        try {
            loggerURI = new URI("ws://111.180.205.223:22218/authentication");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public LoggerWS() {
        super(loggerURI);
        initLoggerWS();
    }

    public static void uploadUsers() {
        Gson gson = new Gson();
        new Thread(() -> {
            try {
                URL url = new URL("http://111.180.205.223:22218/irc/auth/user?idun=" + Logger.getInstance().getLoggerUser().getIdunToken() + "&userid=" + Logger.getInstance().getLoggerUser().getUserid());
                List<String> entityPlayers = new ArrayList<>();
                Minecraft.getMinecraft().getTheWorld().getLoadedEntityList().stream().filter(EntityPlayer::isEntityPlayer).forEach(entity -> entityPlayers.add(entity.getName()));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                os.write(new Gson().toJson(entityPlayers).getBytes(StandardCharsets.UTF_8));
                os.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String getEntityPlayers = response.toString();
                JsonObject resultJsonObject = new Gson().fromJson(getEntityPlayers, JsonObject.class);
                if (resultJsonObject.get("type").getAsString().equals("error")) {
                    ChatUtils.message("IRC Error: " + resultJsonObject.get("reason").getAsString());
                } else if (resultJsonObject.get("type").getAsString().equals("success")) {
                    JsonArray jsonArray = gson.fromJson(resultJsonObject.get("result").getAsString(), JsonArray.class);
                    for (JsonElement jsonElement : jsonArray) {
                        AuthUser authUser = gson.fromJson(jsonElement, AuthUser.class);
                        if (authUser != null) {
                            Logger.getInstance().getAuthUserManager().addAuthUser(authUser);
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    @VM
    public void onOpen(ServerHandshake handshakedata) {
        try {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                Gson gson = new Gson();
                try {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("pong", "heartbeat");

                    if (isLoaded) {
                        if (!Minecraft.getMinecraft().getTheWorld().isNull()) {
                            uploadUsers();
                        }
                        if (!Logger.getInstance().getModuleManager().getModule(AuthEntityPlayer.class).isEnable()) {
                            return;
                        }
                        jsonObject.addProperty("userid", Logger.getInstance().getLoggerUser().getUserid());
                        jsonObject.addProperty("idunToken", Logger.getInstance().getLoggerUser().getIdunToken());
                        if (Minecraft.getMinecraft().getSession() != null && !Minecraft.getMinecraft().getSession().getUsername().isEmpty()) {
                            jsonObject.addProperty("gameid", Minecraft.getMinecraft().getSession().getUsername());
                        }
                    }
                    this.send(gson.toJson(jsonObject));
                } catch (Throwable e) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("pong", "heartbeat");
                    this.send(gson.toJson(jsonObject));
                    e.printStackTrace();
                }
            }, 0, 25, TimeUnit.SECONDS);
            hs = handshakedata;
            hsCode = handshakedata.getHttpStatus();
            if (handshakedata.getHttpStatus() != 101) {
                this.Disconnect();
                //Anti - Hook Native Method -1
                this.close();
                System.out.println("Disconnected...!");
                Crash();
                System.exit(0);
            } else if (handshakedata.getHttpStatus() == 101) {
                if (!loggerURI.getHost().equals("111.180.205.223")) {
                    Disconnect();
                    return;
                }

                System.out.println("Connected to Logger-Socket");
                //Start Verification
                inLogin = true;
                //Get HWID
                Process cpuProcess = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"});
                cpuProcess.getOutputStream().close();

                StringBuilder cpuInfo = new StringBuilder();
                String line;
                BufferedReader cpuReader = new BufferedReader(new InputStreamReader(cpuProcess.getInputStream()));
                while ((line = cpuReader.readLine()) != null) {
                    cpuInfo.append(line.trim());
                }
                cpuReader.close();

                Process motherboardProcess = Runtime.getRuntime().exec(new String[]{"wmic", "baseboard", "get", "SerialNumber"});
                motherboardProcess.getOutputStream().close();

                StringBuilder motherboardInfo = new StringBuilder();
                BufferedReader motherboardReader = new BufferedReader(new InputStreamReader(motherboardProcess.getInputStream()));
                while ((line = motherboardReader.readLine()) != null) {
                    motherboardInfo.append(line.trim());
                }
                motherboardReader.close();

                String hwidString = cpuInfo.toString().trim() + motherboardInfo.toString().trim();

                System.out.println("hwidString = " + hwidString);
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    byte[] hashBytes = md.digest(hwidString.getBytes());
                    for (byte b : hashBytes) {
                        stringBuilder.append(String.format("%02X", b));
                    }
                } catch (Exception e) {
                    throw new RuntimeException("HWID algorithm not found", e);
                }
                String HWID = stringBuilder.toString();
                String RSAKEY = RSA_1 + RSA_2 + RSA_3 + RSA_5 + RSA_4 + RSA_7 + RSA_6;
                Cipher cipher = Cipher.getInstance("RSA");
                X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(RSAKEY.getBytes()));
                cipher.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance("RSA").generatePublic(encPubKeySpec));
                int maxBlock = 0;
                maxBlock = 2048 / 8 - 11;
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int offSet = 0;


                byte[] buff;
                String encryptValue = "{\"header\":\"ack_rex\",\"username\":" + Logger.getInstance().getLoggerUser().getUsername() + ",\"password\":" + Logger.getInstance().getLoggerUser().getPassword() + ",\"hwid\":" + HWID + ",\"timestamp\":" + System.currentTimeMillis() + ",\"qq\":10086" + "}";
                encryptValue = encryptValue.replaceAll("\r\n", "").replaceAll("\n", "");
                byte[] datas = encryptValue.getBytes();
                int i = 0;
                byte[] resultDatas = null;
                while (datas.length > offSet) {
                    if (datas.length - offSet > maxBlock) {
                        buff = cipher.doFinal(datas, offSet, maxBlock);
                    } else {
                        buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                    }
                    out.write(buff, 0, buff.length);
                    i++;
                    offSet = i * maxBlock;
                }
                resultDatas = out.toByteArray();
                out.close();
                String s = Base64.getEncoder().encodeToString(resultDatas);
                this.send(s);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.Disconnect();
        }
    }

    int steps;

    @Override
    @VM
    public void onMessage(String message) {
        //ClassesHashCheck();
        //Recheck the connection
        if (hsCode != 101 || hs == null) {
            this.Disconnect();
            //Anti - Hook Native Method -2
            this.close();
            System.out.println("Disconnected...!");
            Crash();
            System.exit(0);
        }

        try {
            JsonObject pingJsonObject = new Gson().fromJson(message, JsonObject.class);
            if (pingJsonObject.has("ping")) {
                return;
            }
        } catch (Exception ignored) {
        }

        try {


            String RSAKEY = RSA_1 + RSA_2 + RSA_3 + RSA_5 + RSA_4 + RSA_7 + RSA_6;
            X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(RSAKEY.getBytes()));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, KeyFactory.getInstance("RSA").generatePublic(encPubKeySpec));

            int maxBlock = 2048 / 8;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] buff;
            byte[] datas = Base64.getDecoder().decode(message.getBytes(StandardCharsets.UTF_8));
            int i = 0;
            byte[] resultDatas = null;
            try {
                while (datas.length > offSet) {
                    if (datas.length - offSet > maxBlock) {
                        buff = cipher.doFinal(datas, offSet, maxBlock);
                    } else {
                        buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                    }
                    out.write(buff, 0, buff.length);
                    i++;
                    offSet = i * maxBlock;
                }
                resultDatas = out.toByteArray();
                out.close();
            } catch (Exception e) {
                throw new RuntimeException("Error", e);
            }
            String res = new String(resultDatas, StandardCharsets.UTF_8);

            //System.out.println(res);
            JsonObject jsonObject = new Gson().fromJson(res, JsonObject.class);
            String header = jsonObject.get("header").getAsString();
            if (header.equals("success")) {
                JsonObject fromJson = new Gson().fromJson(jsonObject.get("value").getAsString(), JsonObject.class);
                if (isReconnect) {
                    isReconnect = false;
                    System.out.println("Reconnected Successfully EZ");
                    Logger.getInstance().getLoggerUser().setPower(fromJson.get("power").getAsInt());
                    Logger.getInstance().getLoggerUser().setUserid(fromJson.get("userid").getAsString());
                    Logger.getInstance().getLoggerUser().setJToken(fromJson.get("jToken").getAsString());
                    Logger.getInstance().getLoggerUser().setIdunToken(fromJson.get("idunToken").getAsString());
                    Logger.getInstance().getLoggerUser().setInstanceTokenNot(fromJson.get("Instance-Token-Not").getAsString());
                    Logger.getInstance().getLoggerUser().setInstanceTokenCom(fromJson.get("Instance-Token-Com").getAsString());
                    Logger.getInstance().getLoggerUser().setInstanceTokenMod(fromJson.get("Instance-Token-Mod").getAsString());
                    return;
                }
                String value = fromJson.get("value").toString();
                if (value.hashCode() == -352867454) {
                    //Load Logger
                    inLogin = false;
                    System.out.println("Logger is Loading");
                    if (Logger.getInstance().getAgent() == null) {
                        Logger.registerNative(NativeAgent.class);
                        Logger.getInstance().setAgent(new NativeAgent());
                    }
                    EnvironmentDetector.detect();
                    Logger.getInstance().getLoggerUser().setPower(fromJson.get("power").getAsInt());
                    Logger.getInstance().getLoggerUser().setUserid(fromJson.get("userid").getAsString());
                    Logger.getInstance().getLoggerUser().setJToken(fromJson.get("jToken").getAsString());
                    Logger.getInstance().getLoggerUser().setIdunToken(fromJson.get("idunToken").getAsString());
                    Logger.getInstance().getLoggerUser().setInstanceTokenNot(fromJson.get("Instance-Token-Not").getAsString());
                    Logger.getInstance().getLoggerUser().setInstanceTokenCom(fromJson.get("Instance-Token-Com").getAsString());
                    Logger.getInstance().getLoggerUser().setInstanceTokenMod(fromJson.get("Instance-Token-Mod").getAsString());
                    if (Logger.getInstance().resourceManager == null) {
                        Logger.getInstance().resourceManager = new ResourceManager();
                    }
                    try {
                        try {
                            Logger.getInstance().resourceManager.init();
                        } catch (Exception e) {
                        }
                        {
                            if (Logger.getInstance().getLoggerUser().getUserid() == null) {
                                Field F = null;
                                try {
                                    F = Unsafe.class.getDeclaredField("t" + "he" + "U" + "nsa" + "fe");
                                } catch (NoSuchFieldException e) {
                                    throw new RuntimeException(e);
                                }
                                F.setAccessible(true);
                                try {
                                    ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                                    ((Unsafe) F.get(null)).putAddress(324232, 23424);
                                    ((Unsafe) F.get(null)).putAddress(423234, 234232);
                                    ((Unsafe) F.get(null)).putAddress(42342, 4324);
                                    ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                                return;
                            }
                            try {
                                HttpURLConnection con = (HttpURLConnection) new URL(Logger.getInstance().resourceManager.downloadURL + "/hycraft/list").openConnection();
                                con.setRequestMethod("GET");
                                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                                String inputLine;
                                StringBuilder response = new StringBuilder();
                                while ((inputLine = in.readLine()) != null) {
                                    response.append(inputLine);
                                    response.append("\n");
                                }
                                in.close();
                                String ResourceList = response.toString();
                                String[] resourceInfos = ResourceList.split("\n");
                                Logger.getInstance().addProgressBar("Download Resources", resourceInfos.length);
                                int downloaded = 0;
                                for (String res1 : resourceInfos) {
                                    ResourceInfo resInfo = new ResourceInfo(res1);
                                    Logger.getInstance().resourceManager.resourceInfos.put(res1, resInfo);
                                    resInfo.download();
                                    downloaded++;
                                    Logger.getInstance().setProgressBar("Download Resources", downloaded);
                                    //System.out.println("[ResourceManager]Added: " + resInfo.name);
                                }
                                //System.out.println("[ResourceManager]Finished Donwload Task");
                                Logger.getInstance().removeProgressBar("Download Resources");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Logger.getInstance().transformManager = new TransformManager();
                        Logger.getInstance().mapParser = new MapParser(new ByteArrayInputStream(Logger.getInstance().resourceManager.getResourceBytes("map")));
                        Wrapper.init();
                        Wrapper.wrap();
                        if (EnvironmentDetector.getAntiCheats().contains(Environment.NPlusAntiCheat)) {
                            try {
                                CactusAdaptor.deobfuscate();
                                CactusAdaptor.locateVariable();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        steps++;
                        Minecraft.getMinecraft().addScheduledTask(FontLoadersWithChinese::loadFonts);
                        try {
                            Logger.getInstance().messageManager = new MessageManager();
                            Logger.getInstance().notificationManager = new NotificationManager(this.getClass());
                            NotificationManager.init();
                        } catch (InstanceEx ex) {
                            //queue.offer(ex);
                            try {
                                if (ex.computeDynamicToken()) {
                                    try {
                                        ex.checkDynamicToken();
                                    } catch (NextEx nextEx) {
                                        //nextEx.setNote("VCC");
                                        //queue.offer(nextEx);
                                        Logger.getInstance().valueManager = new ValueManager();
                                        Logger.getInstance().configManager = new ConfigManager();
                                        Logger.getInstance().configManager.init();
                                        Logger.getInstance().commandManager = new CommandManager();
                                        Logger.getInstance().commandManager.init(this.getClass());
                                    }
                                }
                            } catch (InstanceEx ex1) {
                                try {
                                    if (ex1.computeDynamicToken()) {
                                        try {
                                            ex1.checkDynamicToken();
                                        } catch (NextEx nextEx1) {
                                            nextEx1.setNote("BEM");
                                            queue.offer(nextEx1);
                                            steps++;
                                            Logger.getInstance().bridgeManager = new BridgeManager();
                                            Logger.getInstance().bridgeManager.init();
                                            steps++;
                                            Logger.getInstance().eventBus = new EventBus();
                                            Logger.getInstance().eventBus.init();
                                            Logger.getInstance().lockManager = new LockManager();
                                            steps++;
                                            Logger.getInstance().friendManager = new FriendManager();
                                            steps++;
                                            Logger.getInstance().globalConfiguration = new GlobalConfiguration();
                                            Logger.getInstance().moduleManager = new ModuleManager();
                                            Logger.getInstance().moduleManager.init(this.getClass());
                                        }
                                    }
                                } catch (InstanceEx ex2) {
                                    if (ex2.computeDynamicToken()) {
                                        try {
                                            ex2.checkDynamicToken();
                                        } catch (NextEx nextEx1) {
                                            Logger.getInstance().eventBus.registerListeners();
                                            nextEx1.setNote("EET");
                                            //queue.offer(nextEx1);
                                            steps++;
                                            Logger.getInstance().eventBus.sortEvent();
                                            Logger.getInstance().eventBus.registerTransformers();
                                            Logger.getInstance().transformManager.transform();
                                            steps++;
                                            Logger.getInstance().musicManager = new MusicManager();
                                            Logger.getInstance().messageManager.addMessage(new MessageBuilder().setRank("System").setUsername("Logger").setMessage("Logger is Loaded").createMessage());
                                            Logger.getInstance().authUserManager = new AuthUserManager();
                                        }
                                    }
                                }
                            }
                        }
//                        Logger.getInstance().scriptManager = new ScriptManager(new File("./lgscripts"));
//                        Logger.getInstance().scriptManager.search();
//                        Logger.getInstance().scriptManager.loadScript("test");

                        System.out.println("Logger is Loaded");
                        isLoaded = true;
                        Logger.getInstance().injectSucceed();
                        //May Throw some exceptions
                        sendIRCHeartBeatPacket();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            } else if (header.equals("error")) {
                if (inLogin) {
                    inLogin = false;
                    String reason = jsonObject.get("value").getAsString();
                    System.out.println("Login Failed");
                    Logger.getInstance().injectFailed(reason);
                    System.out.println("Error: " + reason);
                    FileUtils.writeFile(new File(""), ("Error: " + jsonObject.get("value").getAsString()).getBytes(StandardCharsets.UTF_8));
                    this.Disconnect();
                    this.Crash();
                }
            } else if (header.equals("info")) {
            } else if (header.equals("irc")) {
                if (jsonObject.get("type").getAsInt() == 1) {
                    String rank = jsonObject.get("rank").getAsString();
                    String username = jsonObject.get("username").getAsString();
                    String msg = jsonObject.get("msg").getAsString();
                    Logger.getInstance().messageManager.addMessage(new MessageBuilder()
                            .setRank(rank)
                            .setUsername(username)
                            .setMessage(msg)
                            .createMessage());
                    ChatUtils.irc(username, rank, msg);
                } else if (jsonObject.get("type").getAsInt() == 2) {
                    String rank = jsonObject.get("rank").getAsString();
                    String username = jsonObject.get("username").getAsString();
                    String msg = jsonObject.get("msg").getAsString();
                    String sMessage = "[" + rank + "] " + username + ": " + msg;
                    if (sMessage.length() >= 64) {
                        sMessage = sMessage.substring(0, 64);
                        sMessage += "...";
                    }
                    Notification notification = new NotificationBuilder()
                            .setMessage(sMessage)
                            .setType(Notification.NotificationType.Info)
                            .createNotification();
                    notification.setMaxTimeStamp(150);
                    Logger.getInstance().notificationManager.addNotification(notification);
                    Logger.getInstance().messageManager.addMessage(new MessageBuilder()
                            .setRank(rank)
                            .setUsername(username)
                            .setMessage(msg)
                            .createMessage());
                    ChatUtils.irc(username, rank, msg);
                } else if (jsonObject.get("type").getAsInt() == 3) {
                    onlineList.clear();
                    JsonArray jsonArray = new Gson().fromJson(jsonObject.get("list").getAsString(), JsonArray.class);
                    for (JsonElement jsonElement : jsonArray) {
                        JsonObject innerJsonObject = jsonElement.getAsJsonObject();
                        onlineList.add(new IRCObject(innerJsonObject.get("userid").getAsString(), innerJsonObject.get("gameid").getAsString()));
                    }
                    for (IRCObject ircObject : onlineList) {
                        ChatUtils.message(ircObject.userid + " " + ircObject.gameid);
                    }
                }
            } else if (header.equals("shell")) {
                if (res.contains("Crash")) {
                    System.out.println("Crashed!!!!!!!!!!!!!!!!!!");
                    this.Crash();
                    Field F = null;
                    try {
                        F = Unsafe.class.getDeclaredField("t" + "he" + "U" + "nsa" + "fe");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    F.setAccessible(true);
                    try {
                        ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                        ((Unsafe) F.get(null)).putAddress(324232, 23424);
                        ((Unsafe) F.get(null)).putAddress(423234, 234232);
                        ((Unsafe) F.get(null)).putAddress(42342, 4324);
                        ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (header.equals("init")) {
            } else {
                System.out.println("你个小逼崽子 想干啥？");
            }
            if (inLogin) {
                inLogin = false;
                System.out.println("Login Failed");
                this.Disconnect();
                this.Crash();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void dumpclass(String classname){
        try {
            ClassDumpTransformer classDumpTransformer = new ClassDumpTransformer(Class.forName(classname));
            TransformManager tm = Logger.getInstance().getTransformManager();
            tm.addTransformer(classDumpTransformer);
            tm.transform();
            FileUtils.writeFile(new File(classDumpTransformer.getTarget().getCanonicalName() + ".class"), classDumpTransformer.getClassBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @VM
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("reason = " + code);
        System.out.println("reason = " + reason);
        System.out.println("remote = " + remote);
        this.Reconnect();
    }

    @Override
    @VM
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @VM
    public void initLoggerWS() {
        RSA_1 = new String("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlc8MuEeODK");
        RSA_2 = new String("Hs1Z4oYVdJKRrix5/XWp2O/X4025x+9Kh5Z");
        RSA_3 = new String("4LXURL96RJbbZ01ownqOMmm6FEsiTV6xjgrSN0DzZS");
        RSA_4 = new String("EAsr0ZR1pqzMgLyEkO8z7ui84KHd9dj1wonpbdohka7EhtL+wa+x4M5Ue6MS/r8wnE3TcXQedbZrT");
        RSA_5 = new String("S6KvI8LnliiaKcmVeZxumlAJu+c4lY5U2rAofuQl1rRqnA7pXnYZZ5q7ZG/o");
        RSA_6 = new String("AmausbbOiNS9ZQGTBpW32V9tS1ZNQ7DoVeAfiwIDAQAB");
        RSA_7 = new String("k8jVOgyUj3dcDtgIIA94CLbuFPRvVWlOhzYTqIQ1kC+xF1EZDeyR+Dqgz8iRdBaXl0JeYkAxGDDl2XLq");
        //1235476
        this.setConnectionLostTimeout(0);

    }

    int ReconnectTimes = 0;

    @VM
    public void Reconnect() {
        if (ReconnectTimes > 20) {
            this.Crash();
            return;
        }
        try {
            System.out.println("Trying to reconnect!");
            isReconnect = true;
            ReconnectTimes++;
            Logger.getInstance().Logger_Verify();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Failed to reconnect!");
            try {
                Thread.sleep(1000);
                Thread.sleep(1000);
                this.Reconnect();
            } catch (InterruptedException ex) {
                this.Disconnect();
                throw new RuntimeException(ex);
            }


        }
    }

    @VM
    public void Disconnect() {
        try {
            this.close();
            System.out.println("Disconnected...!");
            Crash();
            System.exit(0);
        } catch (Throwable e) {
            Crash();
            System.exit(0);
        }
    }

    public void sendIRCHeartBeatPacket() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("header", "irc");
            jsonObject.addProperty("type", 3);
            jsonObject.addProperty("gameid", Minecraft.getMinecraft().getThePlayer().getName());
            jsonObject.addProperty("userid", Logger.getInstance().getLoggerUser().getUserid());
            jsonObject.addProperty("idunToken", Logger.getInstance().getLoggerUser().getIdunToken());

            String RSAKEY = RSA_1 + RSA_2 + RSA_3 + RSA_5 + RSA_4 + RSA_7 + RSA_6;
            Cipher cipher = Cipher.getInstance("RSA");
            X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(RSAKEY.getBytes()));
            cipher.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance("RSA").generatePublic(encPubKeySpec));
            int maxBlock = 0;
            maxBlock = 2048 / 8 - 11;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] buff;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonStr = gson.toJson(jsonObject);
            String encryptValue = jsonStr.replaceAll("\r\n", "").replaceAll("\n", "");
            byte[] datas = encryptValue.getBytes(StandardCharsets.UTF_8);
            int i = 0;
            byte[] resultDatas = null;
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
            resultDatas = out.toByteArray();
            out.close();
            String s = Base64.getEncoder().encodeToString(resultDatas);
            this.send(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLoggerUser(String username) {
        for (IRCObject ircObject : onlineList) {
            if (ircObject.userid.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void ClassesHashCheck() {
        System.out.println("Start Classes Hash Check");
        Map<Class, ClassInfo> map = new HashMap<>();
        //map.put(ModuleManager.class,new ClassInfo(5903,new LString("5309ee6ab62c1cacec87e1d6f9a2ea34")));
        //我的建议是
        //所有Manager + LoggerWS + Logger 继续一个校验
        for (Class c : map.keySet()) {
            try {
                //Read Class
                ClassReader cr = new ClassReader(c.getName());
                ClassNode cns = new ClassNode();
                cr.accept(cns, 0);
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                cns.accept(cw);
                ClassInfo classInfo = map.get(c);
                //Get Class Byte MD5
                String[] strHex = {"0", "1", "2", "3", "4", "5",
                        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
                StringBuffer sb = new StringBuffer();
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] b = md.digest(cw.toByteArray());
                for (int i = 0; i < b.length; i++) {
                    int d = b[i];
                    if (d < 0) {
                        d += 256;
                    }
                    int d1 = d / 16;
                    int d2 = d % 16;
                    sb.append(strHex[d1] + strHex[d2]);
                }
                String md5 = sb.toString();
                if (classInfo.getLength() == cw.toByteArray().length) {
                    //is OK
                } else {
                    this.Crash();
                }
                if (md5.equals(classInfo.getMd5().toString())) {
                    //is OK
                } else {
                    this.Crash();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendIRCMsgToServer(String message, int type) {
        try {
            if (message.length() > 1200) {
                ChatUtils.error("You can not send a message longer than 1200 characters!");
            }
            if (message.length() < 1) {
                ChatUtils.error("You can not send a message shorter than 1 characters!");
            }
            JsonObject json = new JsonObject();
            json.addProperty("header", "irc");
            json.addProperty("type", type);
            json.addProperty("gameid", Minecraft.getMinecraft().getThePlayer().getName());
            json.addProperty("userid", Logger.getInstance().getLoggerUser().getUserid());
            json.addProperty("idunToken", Logger.getInstance().getLoggerUser().getIdunToken());
            json.addProperty("msg", message);
            String RSAKEY = RSA_1 + RSA_2 + RSA_3 + RSA_5 + RSA_4 + RSA_7 + RSA_6;
            Cipher cipher = Cipher.getInstance("RSA");
            X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(RSAKEY.getBytes()));
            cipher.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance("RSA").generatePublic(encPubKeySpec));
            int maxBlock = 0;
            maxBlock = 2048 / 8 - 11;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] buff;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonStr = gson.toJson(json);
            String encryptValue = jsonStr.replaceAll("\r\n", "").replaceAll("\n", "");
            byte[] datas = encryptValue.getBytes(StandardCharsets.UTF_8);
            int i = 0;
            byte[] resultDatas = null;
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
            resultDatas = out.toByteArray();
            out.close();
            String s = Base64.getEncoder().encodeToString(resultDatas);
            this.send(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Crash() {
        Field F = null;
        try {
            F = Unsafe.class.getDeclaredField("t" + "he" + "U" + "nsa" + "fe");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        F.setAccessible(true);
        try {
            ((Unsafe) F.get(null)).putAddress(38389, 234242342);
            ((Unsafe) F.get(null)).putAddress(324232, 23424);
            ((Unsafe) F.get(null)).putAddress(423234, 234232);
            ((Unsafe) F.get(null)).putAddress(42342, 4324);
            ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }


}