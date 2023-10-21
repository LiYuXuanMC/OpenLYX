package com.reflectmc.verify.packet.client;

import com.google.gson.Gson;
import com.reflectmc.verify.UserController;
import com.reflectmc.verify.data.LoginData;
import com.reflectmc.verify.data.UserData;
import com.reflectmc.verify.packet.Packet;
import com.reflectmc.verify.packet.PacketBuffer;
import com.reflectmc.verify.packet.PacketID;
import com.reflectmc.verify.packet.PacketType;
import com.reflectmc.verify.resource.SendResourceRunnable;
import com.reflectmc.verify.utils.AES_CFB8_NoPadding;
import com.reflectmc.verify.utils.EncryptUtil;
import lombok.Getter;
import lombok.Setter;
import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class ClientHandler {
    @Getter
    private WebSocket connection;
    @Setter
    private Consumer<ClientHandler> deathHook;
    private AES_CFB8_NoPadding crypt = new AES_CFB8_NoPadding("5ca37899z7b0f22e");
    @Getter
    private ClientStage stage;
    private static Logger logger = new SimpleLoggerFactory().getLogger("IRCHandler");
    private Gson gson = new Gson();
    @Getter
    private long ping;
    private Timer timer = new Timer();
    @Getter
    private UserData user;
    public ClientHandler(WebSocket connection){
        this.connection = connection;
        stage = ClientStage.Handshake;
    }
    public void onPacket(byte[] bytes){
        PacketBuffer full = new PacketBuffer();
        full.writeByte(crypt.decrypt(bytes));
        PacketBuffer packetBuf = new PacketBuffer();
        int length = full.readInt();
        packetBuf.writeByte(full.read(length));
        full.closeBuffer();
        Packet packet = processPacket(packetBuf,PacketType.Client);
        if (packet != null){
            if (stage == ClientStage.Handshake){
                if (packet instanceof C00Hello){
                    sendPacket(new S00Hello());
                }
                if (packet instanceof C01Handshake){
                    String newKey = EncryptUtil.createRandomString(16);
                    sendPacket(new S01UpdateKey(newKey));
                    stage = ClientStage.Login;
                    crypt = new AES_CFB8_NoPadding(newKey);
                    logger.info(String.format("%s Finish handshake(new CryptKey:%s)",connection.getRemoteSocketAddress().getAddress().getHostAddress(),newKey));
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            sendHeartBeat();
                        }
                    },5000);
                }
            }
            if (stage == ClientStage.Login){
                if (packet instanceof C02Login){
                    LoginData data = UserController.verifyUser(((C02Login) packet).getUsername(),((C02Login) packet).getPasswd());
                    if (data.getStatus() == 200){
                        sendPacket(new S02LoginResponse(200,gson.toJson(data.getUserData())));
                        logger.info(String.format("Verify %s successfully (Login as %s)"
                                ,connection.getRemoteSocketAddress().getAddress().getHostAddress()
                                ,data.getUserData().getUsername()));
                        stage = ClientStage.Verified;
                        user = data.getUserData();
                    }else {
                        sendPacket(new S02LoginResponse(data.getStatus(),data.getReason()));
                        logger.error(String.format("Verify %s fail Reason:%s"
                                ,connection.getRemoteSocketAddress().getAddress().getHostAddress(),
                                data.getReason()));
                    }
                }
            }
            if (stage == ClientStage.Verified){
                if (packet instanceof C04RequestDownloadResource){
                    new Thread(new SendResourceRunnable(this,((C04RequestDownloadResource) packet).getResource())).start();
                }
            }
            if (packet instanceof C03Pong){
                ping = (System.currentTimeMillis() - ((C03Pong) packet).getTimestamp()) / 2;
            }
        }
    }
    public void sendHeartBeat(){
        sendPacket(new S03Ping(System.currentTimeMillis()));
    }
    public void connectClose(){
        deathHook.accept(this);
        timer.cancel();
    }
    private Packet processPacket(PacketBuffer buffer, PacketType type) {
        byte[] seq = buffer.read(2);
        for (PacketID value : PacketID.values()) {
            if (Arrays.equals(value.getSeq(), seq) && value.getType() == type){
                try {
                    Packet packet = value.getTarget().newInstance();
                    packet.process(buffer);
                    buffer.closeBuffer();
                    return packet;
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public void sendPacket(Packet packet){
        PacketBuffer packetData = packet.create();
        PacketBuffer fullPacket = new PacketBuffer();
        fullPacket.writeInt(packetData.getLength());
        fullPacket.appendBuffer(packetData);
        packetData.closeBuffer();
        connection.send(crypt.encrypt(fullPacket.getData()));
        fullPacket.closeBuffer();
    }
}
