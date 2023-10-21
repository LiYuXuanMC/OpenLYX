package com.reflectmc.libraries.websocket.framing;

import com.reflectmc.libraries.websocket.enums.Opcode;
import com.reflectmc.libraries.websocket.exceptions.InvalidDataException;


public abstract class DataFrame extends FramedataImpl1 {

  
  public DataFrame(Opcode opcode) {
    super(opcode);
  }

  @Override
  public void isValid() throws InvalidDataException {
    //Nothing specific to check
  }
}
