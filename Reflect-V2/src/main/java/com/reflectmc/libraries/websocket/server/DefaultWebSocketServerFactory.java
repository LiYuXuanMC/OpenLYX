package com.reflectmc.libraries.websocket.server;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import com.reflectmc.libraries.websocket.WebSocketAdapter;
import com.reflectmc.libraries.websocket.WebSocketImpl;
import com.reflectmc.libraries.websocket.WebSocketServerFactory;
import com.reflectmc.libraries.websocket.drafts.Draft;

public class DefaultWebSocketServerFactory implements WebSocketServerFactory {

  @Override
  public WebSocketImpl createWebSocket(WebSocketAdapter a, Draft d) {
    return new WebSocketImpl(a, d);
  }

  @Override
  public WebSocketImpl createWebSocket(WebSocketAdapter a, List<Draft> d) {
    return new WebSocketImpl(a, d);
  }

  @Override
  public SocketChannel wrapChannel(SocketChannel channel, SelectionKey key) {
    return channel;
  }

  @Override
  public void close() {
    //Nothing to do for a normal ws factory
  }
}