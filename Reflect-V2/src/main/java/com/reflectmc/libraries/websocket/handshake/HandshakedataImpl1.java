package com.reflectmc.libraries.websocket.handshake;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;


public class HandshakedataImpl1 implements HandshakeBuilder {

  
  private byte[] content;

  
  private TreeMap<String, String> map;

  
  public HandshakedataImpl1() {
    map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
  }

  @Override
  public Iterator<String> iterateHttpFields() {
    return Collections.unmodifiableSet(map.keySet()).iterator();// Safety first
  }

  @Override
  public String getFieldValue(String name) {
    String s = map.get(name);
    if (s == null) {
      return "";
    }
    return s;
  }

  @Override
  public byte[] getContent() {
    return content;
  }

  @Override
  public void setContent(byte[] content) {
    this.content = content;
  }

  @Override
  public void put(String name, String value) {
    map.put(name, value);
  }

  @Override
  public boolean hasFieldValue(String name) {
    return map.containsKey(name);
  }
}
