package com.reflectmc.libraries.websocket.exceptions;


public class IncompleteHandshakeException extends RuntimeException {

  
  private static final long serialVersionUID = 7906596804233893092L;

  
  private final int preferredSize;

  
  public IncompleteHandshakeException(int preferredSize) {
    this.preferredSize = preferredSize;
  }

  
  public IncompleteHandshakeException() {
    this.preferredSize = 0;
  }

  
  public int getPreferredSize() {
    return preferredSize;
  }

}
