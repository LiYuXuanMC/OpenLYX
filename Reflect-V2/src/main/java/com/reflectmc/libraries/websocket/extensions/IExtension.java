package com.reflectmc.libraries.websocket.extensions;

import com.reflectmc.libraries.websocket.exceptions.InvalidDataException;
import com.reflectmc.libraries.websocket.framing.Framedata;


public interface IExtension {

  
  void decodeFrame(Framedata inputFrame) throws InvalidDataException;

  
  void encodeFrame(Framedata inputFrame);

  
  boolean acceptProvidedExtensionAsServer(String inputExtensionHeader);

  
  boolean acceptProvidedExtensionAsClient(String inputExtensionHeader);

  
  void isFrameValid(Framedata inputFrame) throws InvalidDataException;

  
  String getProvidedExtensionAsClient();

  
  String getProvidedExtensionAsServer();

  
  IExtension copyInstance();

  
  void reset();

  
  String toString();
}
