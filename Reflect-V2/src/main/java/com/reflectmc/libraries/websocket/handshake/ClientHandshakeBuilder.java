package com.reflectmc.libraries.websocket.handshake;


public interface ClientHandshakeBuilder extends HandshakeBuilder, ClientHandshake {

  
  void setResourceDescriptor(String resourceDescriptor);
}
