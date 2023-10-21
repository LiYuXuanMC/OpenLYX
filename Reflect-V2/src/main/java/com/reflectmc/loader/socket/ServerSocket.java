package com.reflectmc.loader.socket;

import com.reflectmc.libraries.websocket.client.WebSocketClient;
import com.reflectmc.libraries.websocket.handshake.ServerHandshake;
import com.reflectmc.loader.Loader;
import com.reflectmc.loader.crypt.AES_CFB8_NoPadding;
import com.reflectmc.loader.packet.EnumServerChannel;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.irc.ServerPacketProcessor;
import com.reflectmc.loader.utils.UnsafeCast;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

public class ServerSocket extends WebSocketClient {
    private AES_CFB8_NoPadding crypt;
    private byte[] vi = new byte[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
    @Setter
    @Getter
    private ServerPacketProcessor packetProcessor = new ServerPacketProcessor(this);
    private static int serverChannel;
    @Getter
    private static boolean reconnect;
    public ServerSocket(int channel) {
        super(UnsafeCast.url2URI(EnumServerChannel.getByOrder(channel).getAddress()));
        serverChannel = channel;
        crypt = new AES_CFB8_NoPadding("5ca37899z7b0f22e",vi);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        packetProcessor.onConnect();
    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(crypt.decrypt(bytes.array()));
        packetProcessor.process(buffer);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        packetProcessor.onDisconnect();
    }

    @Override
    public void onError(Exception ex) {

    }
    public void updateKey(String newKey){
        crypt = new AES_CFB8_NoPadding(newKey);
    }
    public void sendPacket(PacketBuffer buffer){
        sendPacket(buffer.getData());
        buffer.closeBuffer();
    }
    public void sendPacket(byte[] bytes){
        send(crypt.encrypt(bytes));
    }
    public static void reconnectIRC(){
        reconnect = true;
        Loader.getINSTANCE().getServerSocket().close();
        Loader.getINSTANCE().setServerSocket(new ServerSocket(serverChannel));
        Loader.getINSTANCE().getServerSocket().connect();
    }
}
