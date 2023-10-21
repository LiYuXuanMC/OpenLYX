package com.reflectmc.verify.socket;

import com.reflectmc.verify.packet.client.ClientHandler;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ClientSocket extends WebSocketServer {
    private Logger logger = new SimpleLoggerFactory().getLogger("IRCServer");
    private ArrayList<ClientHandler> handlers = new ArrayList<>();
    public ClientSocket(){
        super(new InetSocketAddress(9894));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        logger.info("New connect "+conn.getRemoteSocketAddress().getAddress().getHostAddress());
        ClientHandler handler = new ClientHandler(conn);
        handlers.add(handler);
        handler.setDeathHook(h -> {
            handlers.remove(h);
        });
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        ClientHandler handler = findHandler(conn);
        if (handler != null){
            handler.connectClose();
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        ClientHandler handler = findHandler(conn);
        if (handler != null){

        }
    }
    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        ClientHandler handler = findHandler(conn);
        if (handler != null){
            handler.onPacket(message.array());
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ClientHandler handler = findHandler(conn);
        if (handler != null){

        }
    }

    @Override
    public void onStart() {
        logger.info("IRC Server started");
    }

    private ClientHandler findHandler(WebSocket socket){
        ClientHandler handler = null;
        for (ClientHandler clientHandler : handlers) {
            if (clientHandler.getConnection() == socket){
                handler = clientHandler;
            }
        }
        return handler;
    }
}
