package com.reflectmc.libraries.websocket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import com.reflectmc.libraries.websocket.AbstractWebSocket;
import com.reflectmc.libraries.websocket.WebSocket;
import com.reflectmc.libraries.websocket.WebSocketImpl;
import com.reflectmc.libraries.websocket.drafts.Draft;
import com.reflectmc.libraries.websocket.drafts.Draft_6455;
import com.reflectmc.libraries.websocket.enums.Opcode;
import com.reflectmc.libraries.websocket.enums.ReadyState;
import com.reflectmc.libraries.websocket.exceptions.InvalidHandshakeException;
import com.reflectmc.libraries.websocket.framing.CloseFrame;
import com.reflectmc.libraries.websocket.framing.Framedata;
import com.reflectmc.libraries.websocket.handshake.HandshakeImpl1Client;
import com.reflectmc.libraries.websocket.handshake.Handshakedata;
import com.reflectmc.libraries.websocket.handshake.ServerHandshake;
import com.reflectmc.libraries.websocket.protocols.IProtocol;

public abstract class WebSocketClient extends AbstractWebSocket implements Runnable, WebSocket {

  protected URI uri = null;
  private WebSocketImpl engine = null;
  private Socket socket = null;
  private SocketFactory socketFactory = null;
  private OutputStream ostream;
  private Proxy proxy = Proxy.NO_PROXY;
  private Thread writeThread;
  private Thread connectReadThread;
  private Draft draft;
  private Map<String, String> headers;
  private CountDownLatch connectLatch = new CountDownLatch(1);
  private CountDownLatch closeLatch = new CountDownLatch(1);
  private int connectTimeout = 0;
  private DnsResolver dnsResolver = null;

  public WebSocketClient(URI serverUri) {
    this(serverUri, new Draft_6455());
  }
  public WebSocketClient(URI serverUri, Draft protocolDraft) {
    this(serverUri, protocolDraft, null, 0);
  }
  public WebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
    this(serverUri, new Draft_6455(), httpHeaders);
  }
  public WebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
    this(serverUri, protocolDraft, httpHeaders, 0);
  }

  public WebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders,
      int connectTimeout) {
    if (serverUri == null) {
      throw new IllegalArgumentException();
    } else if (protocolDraft == null) {
      throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
    }
    this.uri = serverUri;
    this.draft = protocolDraft;
    this.dnsResolver = new DnsResolver() {
      @Override
      public InetAddress resolve(URI uri) throws UnknownHostException {
        return InetAddress.getByName(uri.getHost());
      }
    };
    if (httpHeaders != null) {
      headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
      headers.putAll(httpHeaders);
    }
    this.connectTimeout = connectTimeout;
    setTcpNoDelay(false);
    setReuseAddr(false);
    this.engine = new WebSocketImpl(this, protocolDraft);
  }

  public URI getURI() {
    return uri;
  }

  public Draft getDraft() {
    return draft;
  }

  public Socket getSocket() {
    return socket;
  }

  public void addHeader(String key, String value) {
    if (headers == null) {
      headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }
    headers.put(key, value);
  }

  public String removeHeader(String key) {
    if (headers == null) {
      return null;
    }
    return headers.remove(key);
  }

  public void clearHeaders() {
    headers = null;
  }

  public void setDnsResolver(DnsResolver dnsResolver) {
    this.dnsResolver = dnsResolver;
  }

  public void reconnect() {
    reset();
    connect();
  }

  
  public boolean reconnectBlocking() throws InterruptedException {
    reset();
    return connectBlocking();
  }

  
  private void reset() {
    Thread current = Thread.currentThread();
    if (current == writeThread || current == connectReadThread) {
      throw new IllegalStateException(
          "You cannot initialize a reconnect out of the websocket thread. Use reconnect in another thread to ensure a successful cleanup.");
    }
    try {
      closeBlocking();
      if (writeThread != null) {
        this.writeThread.interrupt();
        this.writeThread = null;
      }
      if (connectReadThread != null) {
        this.connectReadThread.interrupt();
        this.connectReadThread = null;
      }
      this.draft.reset();
      if (this.socket != null) {
        this.socket.close();
        this.socket = null;
      }
    } catch (Exception e) {
      onError(e);
      engine.closeConnection(CloseFrame.ABNORMAL_CLOSE, e.getMessage());
      return;
    }
    connectLatch = new CountDownLatch(1);
    closeLatch = new CountDownLatch(1);
    this.engine = new WebSocketImpl(this, this.draft);
  }

  
  public void connect() {
    if (connectReadThread != null) {
      throw new IllegalStateException("WebSocketClient objects are not reuseable");
    }
    connectReadThread = new Thread(this);
    connectReadThread.setName("WebSocketConnectReadThread-" + connectReadThread.getId());
    connectReadThread.start();
  }

  
  public boolean connectBlocking() throws InterruptedException {
    connect();
    connectLatch.await();
    return engine.isOpen();
  }

  
  public boolean connectBlocking(long timeout, TimeUnit timeUnit) throws InterruptedException {
    connect();
    return connectLatch.await(timeout, timeUnit) && engine.isOpen();
  }

  
  public void close() {
    if (writeThread != null) {
      engine.close(CloseFrame.NORMAL);
    }
  }

  
  public void closeBlocking() throws InterruptedException {
    close();
    closeLatch.await();
  }

  
  public void send(String text) {
    engine.send(text);
  }

  
  public void send(byte[] data) {
    engine.send(data);
  }

  @Override
  public <T> T getAttachment() {
    return engine.getAttachment();
  }

  @Override
  public <T> void setAttachment(T attachment) {
    engine.setAttachment(attachment);
  }

  @Override
  protected Collection<WebSocket> getConnections() {
    return Collections.singletonList((WebSocket) engine);
  }

  @Override
  public void sendPing() {
    engine.sendPing();
  }

  public void run() {
    InputStream istream;
    try {
      boolean upgradeSocketToSSLSocket = prepareSocket();

      socket.setTcpNoDelay(isTcpNoDelay());
      socket.setReuseAddress(isReuseAddr());

      if (!socket.isConnected()) {
        InetSocketAddress addr = dnsResolver == null ? InetSocketAddress.createUnresolved(uri.getHost(), getPort()) : new InetSocketAddress(dnsResolver.resolve(uri), this.getPort());
        socket.connect(addr, connectTimeout);
      }

      // if the socket is set by others we don't apply any TLS wrapper
      if (upgradeSocketToSSLSocket && "wss".equals(uri.getScheme())) {
        upgradeSocketToSSL();
      }

      if (socket instanceof SSLSocket) {
        SSLSocket sslSocket = (SSLSocket) socket;
        SSLParameters sslParameters = sslSocket.getSSLParameters();
        onSetSSLParameters(sslParameters);
        sslSocket.setSSLParameters(sslParameters);
      }

      istream = socket.getInputStream();
      ostream = socket.getOutputStream();

      sendHandshake();
    } catch (Exception e) {
      onWebsocketError(engine, e);
      engine.closeConnection(CloseFrame.NEVER_CONNECTED, e.getMessage());
      return;
    } catch (InternalError e) {
      // https://bugs.openjdk.java.net/browse/JDK-8173620
      if (e.getCause() instanceof InvocationTargetException && e.getCause()
          .getCause() instanceof IOException) {
        IOException cause = (IOException) e.getCause().getCause();
        onWebsocketError(engine, cause);
        engine.closeConnection(CloseFrame.NEVER_CONNECTED, cause.getMessage());
        return;
      }
      throw e;
    }

    writeThread = new Thread(new WebsocketWriteThread(this));
    writeThread.start();

    byte[] rawbuffer = new byte[WebSocketImpl.RCVBUF];
    int readBytes;

    try {
      while (!isClosing() && !isClosed() && (readBytes = istream.read(rawbuffer)) != -1) {
        engine.decode(ByteBuffer.wrap(rawbuffer, 0, readBytes));
      }
      engine.eot();
    } catch (IOException e) {
      handleIOException(e);
    } catch (RuntimeException e) {
      // this catch case covers internal errors only and indicates a bug in this websocket implementation
      onError(e);
      engine.closeConnection(CloseFrame.ABNORMAL_CLOSE, e.getMessage());
    }
    connectReadThread = null;
  }

  private void upgradeSocketToSSL()
      throws NoSuchAlgorithmException, KeyManagementException, IOException {
    SSLSocketFactory factory;
    // Prioritise the provided socketfactory
    // Helps when using web debuggers like Fiddler Classic
    if (socketFactory instanceof SSLSocketFactory) {
      factory = (SSLSocketFactory) socketFactory;
    } else {
      SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
      sslContext.init(null, null, null);
      factory = sslContext.getSocketFactory();
    }
    socket = factory.createSocket(socket, uri.getHost(), getPort(), true);
  }

  private boolean prepareSocket() throws IOException {
    boolean upgradeSocketToSSLSocket = false;
    // Prioritise a proxy over a socket factory and apply the socketfactory later
    if (proxy != Proxy.NO_PROXY) {
      socket = new Socket(proxy);
      upgradeSocketToSSLSocket = true;
    } else if (socketFactory != null) {
      socket = socketFactory.createSocket();
    } else if (socket == null) {
      socket = new Socket(proxy);
      upgradeSocketToSSLSocket = true;
    } else if (socket.isClosed()) {
      throw new IOException();
    }
    return upgradeSocketToSSLSocket;
  }

  
  protected void onSetSSLParameters(SSLParameters sslParameters) {
    // If you run into problem on Android (NoSuchMethodException), check out the wiki https://github.com/TooTallNate/Java-WebSocket/wiki/No-such-method-error-setEndpointIdentificationAlgorithm
    // Perform hostname validation
    sslParameters.setEndpointIdentificationAlgorithm("HTTPS");
  }

  
  private int getPort() {
    int port = uri.getPort();
    String scheme = uri.getScheme();
    if ("wss".equals(scheme)) {
      return port == -1 ? WebSocketImpl.DEFAULT_WSS_PORT : port;
    } else if ("ws".equals(scheme)) {
      return port == -1 ? WebSocketImpl.DEFAULT_PORT : port;
    } else {
      throw new IllegalArgumentException("unknown scheme: " + scheme);
    }
  }

  
  private void sendHandshake() throws InvalidHandshakeException {
    String path;
    String part1 = uri.getRawPath();
    String part2 = uri.getRawQuery();
    if (part1 == null || part1.length() == 0) {
      path = "/";
    } else {
      path = part1;
    }
    if (part2 != null) {
      path += '?' + part2;
    }
    int port = getPort();
    String host = uri.getHost() + (
        (port != WebSocketImpl.DEFAULT_PORT && port != WebSocketImpl.DEFAULT_WSS_PORT)
            ? ":" + port
            : "");

    HandshakeImpl1Client handshake = new HandshakeImpl1Client();
    handshake.setResourceDescriptor(path);
    handshake.put("Host", host);
    if (headers != null) {
      for (Map.Entry<String, String> kv : headers.entrySet()) {
        handshake.put(kv.getKey(), kv.getValue());
      }
    }
    engine.startHandshake(handshake);
  }

  
  public ReadyState getReadyState() {
    return engine.getReadyState();
  }

  
  @Override
  public final void onWebsocketMessage(WebSocket conn, String message) {
    onMessage(message);
  }

  @Override
  public final void onWebsocketMessage(WebSocket conn, ByteBuffer blob) {
    onMessage(blob);
  }

  
  @Override
  public final void onWebsocketOpen(WebSocket conn, Handshakedata handshake) {
    startConnectionLostTimer();
    onOpen((ServerHandshake) handshake);
    connectLatch.countDown();
  }

  
  @Override
  public final void onWebsocketClose(WebSocket conn, int code, String reason, boolean remote) {
    stopConnectionLostTimer();
    if (writeThread != null) {
      writeThread.interrupt();
    }
    onClose(code, reason, remote);
    connectLatch.countDown();
    closeLatch.countDown();
  }

  
  @Override
  public final void onWebsocketError(WebSocket conn, Exception ex) {
    onError(ex);
  }

  @Override
  public final void onWriteDemand(WebSocket conn) {
    // nothing to do
  }

  @Override
  public void onWebsocketCloseInitiated(WebSocket conn, int code, String reason) {
    onCloseInitiated(code, reason);
  }

  @Override
  public void onWebsocketClosing(WebSocket conn, int code, String reason, boolean remote) {
    onClosing(code, reason, remote);
  }

  
  public void onCloseInitiated(int code, String reason) {
    //To overwrite
  }

  
  public void onClosing(int code, String reason, boolean remote) {
    //To overwrite
  }

  
  public WebSocket getConnection() {
    return engine;
  }

  @Override
  public InetSocketAddress getLocalSocketAddress(WebSocket conn) {
    if (socket != null) {
      return (InetSocketAddress) socket.getLocalSocketAddress();
    }
    return null;
  }

  @Override
  public InetSocketAddress getRemoteSocketAddress(WebSocket conn) {
    if (socket != null) {
      return (InetSocketAddress) socket.getRemoteSocketAddress();
    }
    return null;
  }

  // ABSTRACT METHODS /////////////////////////////////////////////////////////

  
  public abstract void onOpen(ServerHandshake handshakedata);

  
  public abstract void onMessage(String message);

  
  public abstract void onClose(int code, String reason, boolean remote);

  
  public abstract void onError(Exception ex);

  
  public void onMessage(ByteBuffer bytes) {
    //To overwrite
  }


  private class WebsocketWriteThread implements Runnable {

    private final WebSocketClient webSocketClient;

    WebsocketWriteThread(WebSocketClient webSocketClient) {
      this.webSocketClient = webSocketClient;
    }

    @Override
    public void run() {
      Thread.currentThread().setName("WebSocketWriteThread-" + Thread.currentThread().getId());
      try {
        runWriteData();
      } catch (IOException e) {
        handleIOException(e);
      } finally {
        closeSocket();
        writeThread = null;
      }
    }

    
    private void runWriteData() throws IOException {
      try {
        while (!Thread.interrupted()) {
          ByteBuffer buffer = engine.outQueue.take();
          ostream.write(buffer.array(), 0, buffer.limit());
          ostream.flush();
        }
      } catch (InterruptedException e) {
        for (ByteBuffer buffer : engine.outQueue) {
          ostream.write(buffer.array(), 0, buffer.limit());
          ostream.flush();
        }
        Thread.currentThread().interrupt();
      }
    }

    
    private void closeSocket() {
      try {
        if (socket != null) {
          socket.close();
        }
      } catch (IOException ex) {
        onWebsocketError(webSocketClient, ex);
      }
    }
  }


  
  public void setProxy(Proxy proxy) {
    if (proxy == null) {
      throw new IllegalArgumentException();
    }
    this.proxy = proxy;
  }

  
  @Deprecated
  public void setSocket(Socket socket) {
    if (this.socket != null) {
      throw new IllegalStateException("socket has already been set");
    }
    this.socket = socket;
  }

  
  public void setSocketFactory(SocketFactory socketFactory) {
    this.socketFactory = socketFactory;
  }

  @Override
  public void sendFragmentedFrame(Opcode op, ByteBuffer buffer, boolean fin) {
    engine.sendFragmentedFrame(op, buffer, fin);
  }

  @Override
  public boolean isOpen() {
    return engine.isOpen();
  }

  @Override
  public boolean isFlushAndClose() {
    return engine.isFlushAndClose();
  }

  @Override
  public boolean isClosed() {
    return engine.isClosed();
  }

  @Override
  public boolean isClosing() {
    return engine.isClosing();
  }

  @Override
  public boolean hasBufferedData() {
    return engine.hasBufferedData();
  }

  @Override
  public void close(int code) {
    engine.close(code);
  }

  @Override
  public void close(int code, String message) {
    engine.close(code, message);
  }

  @Override
  public void closeConnection(int code, String message) {
    engine.closeConnection(code, message);
  }

  @Override
  public void send(ByteBuffer bytes) {
    engine.send(bytes);
  }

  @Override
  public void sendFrame(Framedata framedata) {
    engine.sendFrame(framedata);
  }

  @Override
  public void sendFrame(Collection<Framedata> frames) {
    engine.sendFrame(frames);
  }

  @Override
  public InetSocketAddress getLocalSocketAddress() {
    return engine.getLocalSocketAddress();
  }

  @Override
  public InetSocketAddress getRemoteSocketAddress() {
    return engine.getRemoteSocketAddress();
  }

  @Override
  public String getResourceDescriptor() {
    return uri.getPath();
  }

  @Override
  public boolean hasSSLSupport() {
    return socket instanceof SSLSocket;
  }

  @Override
  public SSLSession getSSLSession() {
    if (!hasSSLSupport()) {
      throw new IllegalArgumentException(
          "This websocket uses ws instead of wss. No SSLSession available.");
    }
    return ((SSLSocket)socket).getSession();
  }

  @Override
  public IProtocol getProtocol() {
    return engine.getProtocol();
  }

  
  private void handleIOException(IOException e) {
    if (e instanceof SSLException) {
      onError(e);
    }
    engine.eot();
  }
}
