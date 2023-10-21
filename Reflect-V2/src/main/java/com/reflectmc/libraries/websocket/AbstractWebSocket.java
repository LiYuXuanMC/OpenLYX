package com.reflectmc.libraries.websocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.reflectmc.libraries.slf4j.Logger;
import com.reflectmc.libraries.slf4j.LoggerFactory;
import com.reflectmc.libraries.websocket.framing.CloseFrame;
import com.reflectmc.libraries.websocket.util.NamedThreadFactory;



public abstract class AbstractWebSocket extends WebSocketAdapter {

  
  private final Logger log = LoggerFactory.getLogger(AbstractWebSocket.class);

  
  private boolean tcpNoDelay;

  
  private boolean reuseAddr;

  
  private ScheduledExecutorService connectionLostCheckerService;
  
  private ScheduledFuture<?> connectionLostCheckerFuture;

  
  private long connectionLostTimeout = TimeUnit.SECONDS.toNanos(60);

  
  private boolean websocketRunning = false;

  
  private final Object syncConnectionLost = new Object();

  
  public int getConnectionLostTimeout() {
    synchronized (syncConnectionLost) {
      return (int) TimeUnit.NANOSECONDS.toSeconds(connectionLostTimeout);
    }
  }

  
  public void setConnectionLostTimeout(int connectionLostTimeout) {
    synchronized (syncConnectionLost) {
      this.connectionLostTimeout = TimeUnit.SECONDS.toNanos(connectionLostTimeout);
      if (this.connectionLostTimeout <= 0) {
        log.trace("Connection lost timer stopped");
        cancelConnectionLostTimer();
        return;
      }
      if (this.websocketRunning) {
        log.trace("Connection lost timer restarted");
        //Reset all the pings
        try {
          ArrayList<WebSocket> connections = new ArrayList<>(getConnections());
          WebSocketImpl webSocketImpl;
          for (WebSocket conn : connections) {
            if (conn instanceof WebSocketImpl) {
              webSocketImpl = (WebSocketImpl) conn;
              webSocketImpl.updateLastPong();
            }
          }
        } catch (Exception e) {
          log.error("Exception during connection lost restart", e);
        }
        restartConnectionLostTimer();
      }
    }
  }

  
  protected void stopConnectionLostTimer() {
    synchronized (syncConnectionLost) {
      if (connectionLostCheckerService != null || connectionLostCheckerFuture != null) {
        this.websocketRunning = false;
        log.trace("Connection lost timer stopped");
        cancelConnectionLostTimer();
      }
    }
  }

  
  protected void startConnectionLostTimer() {
    synchronized (syncConnectionLost) {
      if (this.connectionLostTimeout <= 0) {
        log.trace("Connection lost timer deactivated");
        return;
      }
      log.trace("Connection lost timer started");
      this.websocketRunning = true;
      restartConnectionLostTimer();
    }
  }

  
  private void restartConnectionLostTimer() {
    cancelConnectionLostTimer();
    connectionLostCheckerService = Executors
        .newSingleThreadScheduledExecutor(new NamedThreadFactory("connectionLostChecker"));
    Runnable connectionLostChecker = new Runnable() {

      
      private ArrayList<WebSocket> connections = new ArrayList<>();

      @Override
      public void run() {
        connections.clear();
        try {
          connections.addAll(getConnections());
          long minimumPongTime;
          synchronized (syncConnectionLost) {
            minimumPongTime = (long) (System.nanoTime() - (connectionLostTimeout * 1.5));
          }
          for (WebSocket conn : connections) {
            executeConnectionLostDetection(conn, minimumPongTime);
          }
        } catch (Exception e) {
          //Ignore this exception
        }
        connections.clear();
      }
    };

    connectionLostCheckerFuture = connectionLostCheckerService
        .scheduleAtFixedRate(connectionLostChecker, connectionLostTimeout, connectionLostTimeout,
            TimeUnit.NANOSECONDS);
  }

  
  private void executeConnectionLostDetection(WebSocket webSocket, long minimumPongTime) {
    if (!(webSocket instanceof WebSocketImpl)) {
      return;
    }
    WebSocketImpl webSocketImpl = (WebSocketImpl) webSocket;
    if (webSocketImpl.getLastPong() < minimumPongTime) {
      log.trace("Closing connection due to no pong received: {}", webSocketImpl);
      webSocketImpl.closeConnection(CloseFrame.ABNORMAL_CLOSE,
          "The connection was closed because the other endpoint did not respond with a pong in time. For more information check: https://github.com/TooTallNate/Java-WebSocket/wiki/Lost-connection-detection");
    } else {
      if (webSocketImpl.isOpen()) {
        webSocketImpl.sendPing();
      } else {
        log.trace("Trying to ping a non open connection: {}", webSocketImpl);
      }
    }
  }

  
  protected abstract Collection<WebSocket> getConnections();

  
  private void cancelConnectionLostTimer() {
    if (connectionLostCheckerService != null) {
      connectionLostCheckerService.shutdownNow();
      connectionLostCheckerService = null;
    }
    if (connectionLostCheckerFuture != null) {
      connectionLostCheckerFuture.cancel(false);
      connectionLostCheckerFuture = null;
    }
  }

  
  public boolean isTcpNoDelay() {
    return tcpNoDelay;
  }

  
  public void setTcpNoDelay(boolean tcpNoDelay) {
    this.tcpNoDelay = tcpNoDelay;
  }

  
  public boolean isReuseAddr() {
    return reuseAddr;
  }

  
  public void setReuseAddr(boolean reuseAddr) {
    this.reuseAddr = reuseAddr;
  }

}
