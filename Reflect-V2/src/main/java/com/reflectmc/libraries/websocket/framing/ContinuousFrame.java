package com.reflectmc.libraries.websocket.framing;

import com.reflectmc.libraries.websocket.enums.Opcode;


public class ContinuousFrame extends DataFrame {

  
  public ContinuousFrame() {
    super(Opcode.CONTINUOUS);
  }
}
