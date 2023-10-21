package com.reflectmc.libraries.websocket.framing;

import com.reflectmc.libraries.websocket.enums.Opcode;


public class PongFrame extends ControlFrame {

  
  public PongFrame() {
    super(Opcode.PONG);
  }

  
  public PongFrame(PingFrame pingFrame) {
    super(Opcode.PONG);
    setPayload(pingFrame.getPayloadData());
  }
}
