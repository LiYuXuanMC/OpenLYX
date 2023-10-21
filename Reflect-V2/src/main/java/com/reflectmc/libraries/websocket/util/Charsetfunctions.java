package com.reflectmc.libraries.websocket.util;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import com.reflectmc.libraries.websocket.exceptions.InvalidDataException;
import com.reflectmc.libraries.websocket.framing.CloseFrame;

public class Charsetfunctions {

  
  private Charsetfunctions() {
  }

  private static final CodingErrorAction codingErrorAction = CodingErrorAction.REPORT;

  
  public static byte[] utf8Bytes(String s) {
    return s.getBytes(StandardCharsets.UTF_8);
  }

  
  public static byte[] asciiBytes(String s) {
    return s.getBytes(StandardCharsets.US_ASCII);
  }

  public static String stringAscii(byte[] bytes) {
    return stringAscii(bytes, 0, bytes.length);
  }

  public static String stringAscii(byte[] bytes, int offset, int length) {
    return new String(bytes, offset, length, StandardCharsets.US_ASCII);
  }

  public static String stringUtf8(byte[] bytes) throws InvalidDataException {
    return stringUtf8(ByteBuffer.wrap(bytes));
  }

  public static String stringUtf8(ByteBuffer bytes) throws InvalidDataException {
    CharsetDecoder decode = StandardCharsets.UTF_8.newDecoder();
    decode.onMalformedInput(codingErrorAction);
    decode.onUnmappableCharacter(codingErrorAction);
    String s;
    try {
      bytes.mark();
      s = decode.decode(bytes).toString();
      bytes.reset();
    } catch (CharacterCodingException e) {
      throw new InvalidDataException(CloseFrame.NO_UTF8, e);
    }
    return s;
  }

  
  private static final int[] utf8d = {
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, // 00..1f
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, // 20..3f
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, // 40..5f
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, // 60..7f
      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
      9, // 80..9f
      7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
      7, // a0..bf
      8, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
      2, // c0..df
      0xa, 0x3, 0x3, 0x3, 0x3, 0x3, 0x3, 0x3, 0x3, 0x3, 0x3, 0x3, 0x3, 0x4, 0x3, 0x3, // e0..ef
      0xb, 0x6, 0x6, 0x6, 0x5, 0x8, 0x8, 0x8, 0x8, 0x8, 0x8, 0x8, 0x8, 0x8, 0x8, 0x8, // f0..ff
      0x0, 0x1, 0x2, 0x3, 0x5, 0x8, 0x7, 0x1, 0x1, 0x1, 0x4, 0x6, 0x1, 0x1, 0x1, 0x1, // s0..s0
      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1,
      1, // s1..s2
      1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1,
      1, // s3..s4
      1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1,
      1, // s5..s6
      1, 3, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
      // s7..s8
  };

  
  public static boolean isValidUTF8(ByteBuffer data, int off) {
    int len = data.remaining();
    if (len < off) {
      return false;
    }
    int state = 0;
    for (int i = off; i < len; ++i) {
      state = utf8d[256 + (state << 4) + utf8d[(0xff & data.get(i))]];
      if (state == 1) {
        return false;
      }
    }
    return true;
  }

  
  public static boolean isValidUTF8(ByteBuffer data) {
    return isValidUTF8(data, 0);
  }

}
