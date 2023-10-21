package com.reflectmc.libraries.websocket.framing;

import com.reflectmc.libraries.websocket.enums.Opcode;


public class BinaryFrame extends DataFrame {

  
  public BinaryFrame() {
    super(Opcode.BINARY);
  }
}
