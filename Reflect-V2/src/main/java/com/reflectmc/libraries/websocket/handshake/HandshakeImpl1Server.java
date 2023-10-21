package com.reflectmc.libraries.websocket.handshake;


public class HandshakeImpl1Server extends HandshakedataImpl1 implements ServerHandshakeBuilder {

  
  private short httpstatus;

  
  private String httpstatusmessage;

  @Override
  public String getHttpStatusMessage() {
    return httpstatusmessage;
  }

  @Override
  public short getHttpStatus() {
    return httpstatus;
  }

  @Override
  public void setHttpStatusMessage(String message) {
    this.httpstatusmessage = message;
  }

  @Override
  public void setHttpStatus(short status) {
    httpstatus = status;
  }
}
