package com.reflectmc.libraries.websocket.handshake;


public interface HandshakeBuilder extends Handshakedata {

  
  void setContent(byte[] content);

  
  void put(String name, String value);
}
