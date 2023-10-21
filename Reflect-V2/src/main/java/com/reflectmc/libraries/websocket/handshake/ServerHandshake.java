package com.reflectmc.libraries.websocket.handshake;


public interface ServerHandshake extends Handshakedata {

  
  short getHttpStatus();

  
  String getHttpStatusMessage();
}
