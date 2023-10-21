package com.reflectmc.verify.proxy;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ProxyConnectionClient extends WebSocketClient {
    public ProxyConnectionClient(String mainServer) throws URISyntaxException {
        super(new URI(mainServer));
    }
    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}
