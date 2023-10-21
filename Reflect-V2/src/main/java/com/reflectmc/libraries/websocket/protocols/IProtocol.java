package com.reflectmc.libraries.websocket.protocols;

public interface IProtocol {
  boolean acceptProvidedProtocol(String inputProtocolHeader);
  String getProvidedProtocol();
  IProtocol copyInstance();
  String toString();
}
