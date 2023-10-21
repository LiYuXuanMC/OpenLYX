package com.reflectmc.libraries.websocket.protocols;

import java.util.regex.Pattern;


public class Protocol implements IProtocol {

  private static final Pattern patternSpace = Pattern.compile(" ");
  private static final Pattern patternComma = Pattern.compile(",");

  
  private final String providedProtocol;

  
  public Protocol(String providedProtocol) {
    if (providedProtocol == null) {
      throw new IllegalArgumentException();
    }
    this.providedProtocol = providedProtocol;
  }

  @Override
  public boolean acceptProvidedProtocol(String inputProtocolHeader) {
    if ("".equals(providedProtocol)) {
      return true;
    }
    String protocolHeader = patternSpace.matcher(inputProtocolHeader).replaceAll("");
    String[] headers = patternComma.split(protocolHeader);
    for (String header : headers) {
      if (providedProtocol.equals(header)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getProvidedProtocol() {
    return this.providedProtocol;
  }

  @Override
  public IProtocol copyInstance() {
    return new Protocol(getProvidedProtocol());
  }

  @Override
  public String toString() {
    return getProvidedProtocol();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Protocol protocol = (Protocol) o;

    return providedProtocol.equals(protocol.providedProtocol);
  }

  @Override
  public int hashCode() {
    return providedProtocol.hashCode();
  }
}
