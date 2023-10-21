package com.reflectmc.libraries.websocket.framing;

import java.nio.ByteBuffer;
import com.reflectmc.libraries.websocket.enums.Opcode;


public interface Framedata {

  
  boolean isFin();

  
  boolean isRSV1();

  
  boolean isRSV2();

  
  boolean isRSV3();

  
  boolean getTransfereMasked();

  
  Opcode getOpcode();

  
  ByteBuffer getPayloadData();// TODO the separation of the application data and the extension data is yet to be done

  
  void append(Framedata nextframe);
}
