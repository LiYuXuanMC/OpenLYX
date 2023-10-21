package com.reflectmc.libraries.websocket.server;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import com.reflectmc.libraries.websocket.SSLSocketChannel2;
import com.reflectmc.libraries.websocket.WebSocketAdapter;
import com.reflectmc.libraries.websocket.WebSocketImpl;
import com.reflectmc.libraries.websocket.WebSocketServerFactory;
import com.reflectmc.libraries.websocket.drafts.Draft;

public class DefaultSSLWebSocketServerFactory implements WebSocketServerFactory {

  protected SSLContext sslcontext;
  protected ExecutorService exec;

  public DefaultSSLWebSocketServerFactory(SSLContext sslContext) {
    this(sslContext, Executors.newSingleThreadScheduledExecutor());
  }

  public DefaultSSLWebSocketServerFactory(SSLContext sslContext, ExecutorService exec) {
    if (sslContext == null || exec == null) {
      throw new IllegalArgumentException();
    }
    this.sslcontext = sslContext;
    this.exec = exec;
  }

  @Override
  public ByteChannel wrapChannel(SocketChannel channel, SelectionKey key) throws IOException {
    SSLEngine e = sslcontext.createSSLEngine();
    
    List<String> ciphers = new ArrayList<>(Arrays.asList(e.getEnabledCipherSuites()));
    ciphers.remove("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
    e.setEnabledCipherSuites(ciphers.toArray(new String[ciphers.size()]));
    e.setUseClientMode(false);
    return new SSLSocketChannel2(channel, e, exec, key);
  }

  @Override
  public WebSocketImpl createWebSocket(WebSocketAdapter a, Draft d) {
    return new WebSocketImpl(a, d);
  }

  @Override
  public WebSocketImpl createWebSocket(WebSocketAdapter a, List<Draft> d) {
    return new WebSocketImpl(a, d);
  }

  @Override
  public void close() {
    exec.shutdown();
  }
}