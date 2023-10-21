package com.reflectmc.libraries.websocket.handshake;


public class HandshakeImpl1Client extends HandshakedataImpl1 implements ClientHandshakeBuilder {

  
  private String resourceDescriptor = "*";

  @Override
  public void setResourceDescriptor(String resourceDescriptor) {
    if (resourceDescriptor == null) {
      throw new IllegalArgumentException("http resource descriptor must not be null");
    }
    this.resourceDescriptor = resourceDescriptor;
  }

  @Override
  public String getResourceDescriptor() {
    return resourceDescriptor;
  }
}
