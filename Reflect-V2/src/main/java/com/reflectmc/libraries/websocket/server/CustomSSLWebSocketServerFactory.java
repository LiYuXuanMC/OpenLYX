package com.reflectmc.libraries.websocket.server;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import com.reflectmc.libraries.websocket.SSLSocketChannel2;


public class CustomSSLWebSocketServerFactory extends DefaultSSLWebSocketServerFactory {

  
  private final String[] enabledProtocols;

  
  private final String[] enabledCiphersuites;

  
  public CustomSSLWebSocketServerFactory(SSLContext sslContext, String[] enabledProtocols,
      String[] enabledCiphersuites) {
    this(sslContext, Executors.newSingleThreadScheduledExecutor(), enabledProtocols,
        enabledCiphersuites);
  }

  
  public CustomSSLWebSocketServerFactory(SSLContext sslContext, ExecutorService executerService,
      String[] enabledProtocols, String[] enabledCiphersuites) {
    super(sslContext, executerService);
    this.enabledProtocols = enabledProtocols;
    this.enabledCiphersuites = enabledCiphersuites;
  }

  @Override
  public ByteChannel wrapChannel(SocketChannel channel, SelectionKey key) throws IOException {
    SSLEngine e = sslcontext.createSSLEngine();
    if (enabledProtocols != null) {
      e.setEnabledProtocols(enabledProtocols);
    }
    if (enabledCiphersuites != null) {
      e.setEnabledCipherSuites(enabledCiphersuites);
    }
    e.setUseClientMode(false);
    return new SSLSocketChannel2(channel, e, exec, key);
  }

}