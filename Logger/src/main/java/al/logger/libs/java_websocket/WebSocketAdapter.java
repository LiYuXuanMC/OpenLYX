package al.logger.libs.java_websocket;

import al.logger.libs.java_websocket.drafts.Draft;
import al.logger.libs.java_websocket.exceptions.InvalidDataException;
import al.logger.libs.java_websocket.framing.Framedata;
import al.logger.libs.java_websocket.framing.PingFrame;
import al.logger.libs.java_websocket.framing.PongFrame;
import al.logger.libs.java_websocket.handshake.ClientHandshake;
import al.logger.libs.java_websocket.handshake.HandshakeImpl1Server;
import al.logger.libs.java_websocket.handshake.ServerHandshake;
import al.logger.libs.java_websocket.handshake.ServerHandshakeBuilder;

/**
 * This class default implements all methods of the WebSocketListener that can be overridden
 * optionally when advances functionalities is needed.<br>
 **/
public abstract class WebSocketAdapter implements WebSocketListener {

  private PingFrame pingFrame;

  /**
   * This default implementation does not do anything. Go ahead and overwrite it.
   *
   * @see al.logger.libs.java_websocket.WebSocketListener#onWebsocketHandshakeReceivedAsServer(WebSocket,
   * Draft, ClientHandshake)
   */
  @Override
  public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft,
      ClientHandshake request) throws InvalidDataException {
    return new HandshakeImpl1Server();
  }

  @Override
  public void onWebsocketHandshakeReceivedAsClient(WebSocket conn, ClientHandshake request,
      ServerHandshake response) throws InvalidDataException {
    //To overwrite
  }

  /**
   * This default implementation does not do anything which will cause the connections to always
   * progress.
   *
   * @see al.logger.libs.java_websocket.WebSocketListener#onWebsocketHandshakeSentAsClient(WebSocket,
   * ClientHandshake)
   */
  @Override
  public void onWebsocketHandshakeSentAsClient(WebSocket conn, ClientHandshake request)
      throws InvalidDataException {
    //To overwrite
  }

  /**
   * This default implementation will send a pong in response to the received ping. The pong frame
   * will have the same payload as the ping frame.
   *
   * @see al.logger.libs.java_websocket.WebSocketListener#onWebsocketPing(WebSocket, Framedata)
   */
  @Override
  public void onWebsocketPing(WebSocket conn, Framedata f) {
    conn.sendFrame(new PongFrame((PingFrame) f));
  }

  /**
   * This default implementation does not do anything. Go ahead and overwrite it.
   *
   * @see al.logger.libs.java_websocket.WebSocketListener#onWebsocketPong(WebSocket, Framedata)
   */
  @Override
  public void onWebsocketPong(WebSocket conn, Framedata f) {
    //To overwrite
  }

  /**
   * Default implementation for onPreparePing, returns a (cached) PingFrame that has no application
   * data.
   *
   * @param conn The <tt>WebSocket</tt> connection from which the ping frame will be sent.
   * @return PingFrame to be sent.
   * @see al.logger.libs.java_websocket.WebSocketListener#onPreparePing(WebSocket)
   */
  @Override
  public PingFrame onPreparePing(WebSocket conn) {
    if (pingFrame == null) {
      pingFrame = new PingFrame();
    }
    return pingFrame;
  }
}
