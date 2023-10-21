package com.reflectmc.libraries.websocket.exceptions;

import com.reflectmc.libraries.websocket.framing.CloseFrame;


public class InvalidFrameException extends InvalidDataException {

  
  private static final long serialVersionUID = -9016496369828887591L;

  
  public InvalidFrameException() {
    super(CloseFrame.PROTOCOL_ERROR);
  }

  
  public InvalidFrameException(String s) {
    super(CloseFrame.PROTOCOL_ERROR, s);
  }

  
  public InvalidFrameException(Throwable t) {
    super(CloseFrame.PROTOCOL_ERROR, t);
  }

  
  public InvalidFrameException(String s, Throwable t) {
    super(CloseFrame.PROTOCOL_ERROR, s, t);
  }
}
