package com.reflectmc.libraries.websocket.exceptions;

import java.io.IOException;
import com.reflectmc.libraries.websocket.WebSocket;


public class WrappedIOException extends Exception {

  
  private final transient WebSocket connection;

  
  private final IOException ioException;

  
  public WrappedIOException(WebSocket connection, IOException ioException) {
    this.connection = connection;
    this.ioException = ioException;
  }

  
  public WebSocket getConnection() {
    return connection;
  }

  
  public IOException getIOException() {
    return ioException;
  }
}
