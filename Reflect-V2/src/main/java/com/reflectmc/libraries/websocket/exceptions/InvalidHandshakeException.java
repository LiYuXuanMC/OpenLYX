package com.reflectmc.libraries.websocket.exceptions;

import com.reflectmc.libraries.websocket.framing.CloseFrame;


public class InvalidHandshakeException extends InvalidDataException {

  
  private static final long serialVersionUID = -1426533877490484964L;

  
  public InvalidHandshakeException() {
    super(CloseFrame.PROTOCOL_ERROR);
  }

  
  public InvalidHandshakeException(String s, Throwable t) {
    super(CloseFrame.PROTOCOL_ERROR, s, t);
  }

  
  public InvalidHandshakeException(String s) {
    super(CloseFrame.PROTOCOL_ERROR, s);
  }

  
  public InvalidHandshakeException(Throwable t) {
    super(CloseFrame.PROTOCOL_ERROR, t);
  }

}
