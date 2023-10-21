package com.reflectmc.libraries.websocket.framing;

import com.reflectmc.libraries.websocket.enums.Opcode;
import com.reflectmc.libraries.websocket.exceptions.InvalidDataException;
import com.reflectmc.libraries.websocket.util.Charsetfunctions;


public class TextFrame extends DataFrame {

  
  public TextFrame() {
    super(Opcode.TEXT);
  }

  @Override
  public void isValid() throws InvalidDataException {
    super.isValid();
    if (!Charsetfunctions.isValidUTF8(getPayloadData())) {
      throw new InvalidDataException(CloseFrame.NO_UTF8, "Received text is no valid utf8 string!");
    }
  }
}
