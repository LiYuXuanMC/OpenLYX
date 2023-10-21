package al.logger.libs.java_websocket;

import java.util.List;
import al.logger.libs.java_websocket.drafts.Draft;

public interface WebSocketFactory {

  /**
   * Create a new Websocket with the provided listener, drafts and socket
   *
   * @param a The Listener for the WebsocketImpl
   * @param d The draft which should be used
   * @return A WebsocketImpl
   */
  WebSocket createWebSocket(WebSocketAdapter a, Draft d);

  /**
   * Create a new Websocket with the provided listener, drafts and socket
   *
   * @param a      The Listener for the WebsocketImpl
   * @param drafts The drafts which should be used
   * @return A WebsocketImpl
   */
  WebSocket createWebSocket(WebSocketAdapter a, List<Draft> drafts);

}
