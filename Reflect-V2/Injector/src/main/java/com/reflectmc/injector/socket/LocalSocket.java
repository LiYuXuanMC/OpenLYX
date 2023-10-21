package com.reflectmc.injector.socket;

import com.reflectmc.injector.crypt.AES_CFB8_NoPadding;
import com.reflectmc.injector.packet.PacketBuffer;
import com.reflectmc.injector.packet.local.InjectorPacketProcessor;
import lombok.Getter;
import lombok.Setter;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class LocalSocket extends WebSocketServer {
    @Getter
    @Setter
    private AES_CFB8_NoPadding crypt;
    @Getter
    private byte[] vi = new byte[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
    private InjectorPacketProcessor processor;
    public LocalSocket(){
        super(new InetSocketAddress(9990));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        processor = new InjectorPacketProcessor(conn);
        processor.onConnect();
        System.out.println("New game");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
    }
    public void onMessage(WebSocket conn, ByteBuffer message) {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(message.array());
        processor.process(buffer);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {

    }

    @Override
    public void onStart() {
        System.out.println("Websocket Service started");
    }
}
