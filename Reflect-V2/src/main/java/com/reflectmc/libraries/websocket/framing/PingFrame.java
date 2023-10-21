package com.reflectmc.libraries.websocket.framing;

import com.reflectmc.libraries.websocket.enums.Opcode;


public class PingFrame extends ControlFrame {

  
  public PingFrame() {
    super(Opcode.PING);
  }
}
