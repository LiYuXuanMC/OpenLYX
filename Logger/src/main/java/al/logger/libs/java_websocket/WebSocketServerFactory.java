package al.logger.libs.java_websocket;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import al.logger.libs.java_websocket.drafts.Draft;

public interface WebSocketServerFactory extends WebSocketFactory {

  @Override
  WebSocketImpl createWebSocket(WebSocketAdapter a, Draft d);
  @Override
  WebSocketImpl createWebSocket(WebSocketAdapter a, List<Draft> drafts);
  ByteChannel wrapChannel(SocketChannel channel, SelectionKey key) throws IOException;
  void close();
}
