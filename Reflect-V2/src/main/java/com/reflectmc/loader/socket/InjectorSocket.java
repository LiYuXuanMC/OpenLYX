package com.reflectmc.loader.socket;

import com.reflectmc.libraries.websocket.client.WebSocketClient;
import com.reflectmc.libraries.websocket.handshake.ServerHandshake;
import com.reflectmc.loader.crypt.AES_CFB8_NoPadding;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.local.LocalPacketProcessor;
import com.reflectmc.loader.packet.local.PacketG02InjectProgress;
import com.reflectmc.loader.packet.local.PacketG03InjectStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

public class InjectorSocket extends WebSocketClient{

    private AES_CFB8_NoPadding crypt;
    private byte[] vi = new byte[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
    private LocalPacketProcessor packetProcessor = new LocalPacketProcessor(this);

    public InjectorSocket() throws URISyntaxException {
        super(new URI("ws://127.0.0.1:9990"));
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

    }

    @Override
    public void onError(Exception ex) {

    }
    public void updateProgress(String type,int now,int max){
        if (max == 0) max = 1;
        packetProcessor.send(new PacketG02InjectProgress(type,now,max));
    }
    public void sendPacket(PacketBuffer buffer){
        sendPacket(buffer.getData());
        buffer.closeBuffer();
    }
    public void injectFail(String reason){
        packetProcessor.send(new PacketG03InjectStatus(reason));
    }
    public void sendPacket(byte[] bytes){
        send(crypt.encrypt(bytes));
    }
}
