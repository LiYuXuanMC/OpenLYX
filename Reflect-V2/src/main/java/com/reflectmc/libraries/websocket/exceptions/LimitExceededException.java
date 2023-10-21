package com.reflectmc.libraries.websocket.exceptions;

import com.reflectmc.libraries.websocket.framing.CloseFrame;


public class LimitExceededException extends InvalidDataException {

  
  private static final long serialVersionUID = 6908339749836826785L;

  
  private final int limit;

  
  public LimitExceededException() {
    this(Integer.MAX_VALUE);
  }

  
  public LimitExceededException(int limit) {
    super(CloseFrame.TOOBIG);
    this.limit = limit;
  }

  
  public LimitExceededException(String s, int limit) {
    super(CloseFrame.TOOBIG, s);
    this.limit = limit;
  }

  
  public LimitExceededException(String s) {
    this(s, Integer.MAX_VALUE);
  }

  
  public int getLimit() {
    return limit;
  }
}
