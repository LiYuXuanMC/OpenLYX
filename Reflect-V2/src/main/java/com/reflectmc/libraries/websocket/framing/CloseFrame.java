package com.reflectmc.libraries.websocket.framing;

import java.nio.ByteBuffer;
import com.reflectmc.libraries.websocket.enums.Opcode;
import com.reflectmc.libraries.websocket.exceptions.InvalidDataException;
import com.reflectmc.libraries.websocket.exceptions.InvalidFrameException;
import com.reflectmc.libraries.websocket.util.ByteBufferUtils;
import com.reflectmc.libraries.websocket.util.Charsetfunctions;


public class CloseFrame extends ControlFrame {

  
  public static final int NORMAL = 1000;
  
  public static final int GOING_AWAY = 1001;
  
  public static final int PROTOCOL_ERROR = 1002;
  
  public static final int REFUSE = 1003;
  
  
  public static final int NOCODE = 1005;
  
  public static final int ABNORMAL_CLOSE = 1006;
  
  public static final int NO_UTF8 = 1007;
  
  public static final int POLICY_VALIDATION = 1008;
  
  public static final int TOOBIG = 1009;
  
  public static final int EXTENSION = 1010;
  
  public static final int UNEXPECTED_CONDITION = 1011;
  
  public static final int SERVICE_RESTART = 1012;
  
  public static final int TRY_AGAIN_LATER = 1013;
  
  public static final int BAD_GATEWAY = 1014;
  
  public static final int TLS_ERROR = 1015;

  
  public static final int NEVER_CONNECTED = -1;

  
  public static final int BUGGYCLOSE = -2;

  
  public static final int FLASHPOLICY = -3;


  
  private int code;

  
  private String reason;

  
  public CloseFrame() {
    super(Opcode.CLOSING);
    setReason("");
    setCode(CloseFrame.NORMAL);
  }

  
  public void setCode(int code) {
    this.code = code;
    // CloseFrame.TLS_ERROR is not allowed to be transferred over the wire
    if (code == CloseFrame.TLS_ERROR) {
      this.code = CloseFrame.NOCODE;
      this.reason = "";
    }
    updatePayload();
  }

  
  public void setReason(String reason) {
    if (reason == null) {
      reason = "";
    }
    this.reason = reason;
    updatePayload();
  }

  
  public int getCloseCode() {
    return code;
  }

  
  public String getMessage() {
    return reason;
  }

  @Override
  public String toString() {
    return super.toString() + "code: " + code;
  }

  @Override
  public void isValid() throws InvalidDataException {
    super.isValid();
    if (code == CloseFrame.NO_UTF8 && reason.isEmpty()) {
      throw new InvalidDataException(CloseFrame.NO_UTF8, "Received text is no valid utf8 string!");
    }
    if (code == CloseFrame.NOCODE && 0 < reason.length()) {
      throw new InvalidDataException(PROTOCOL_ERROR,
          "A close frame must have a closecode if it has a reason");
    }
    //Intentional check for code != CloseFrame.TLS_ERROR just to make sure even if the code earlier changes
    if ((code > CloseFrame.TLS_ERROR && code < 3000)) {
      throw new InvalidDataException(PROTOCOL_ERROR, "Trying to send an illegal close code!");
    }
    if (code == CloseFrame.ABNORMAL_CLOSE || code == CloseFrame.TLS_ERROR
        || code == CloseFrame.NOCODE || code > 4999 || code < 1000 || code == 1004) {
      throw new InvalidFrameException("closecode must not be sent over the wire: " + code);
    }
  }

  @Override
  public void setPayload(ByteBuffer payload) {
    code = CloseFrame.NOCODE;
    reason = "";
    payload.mark();
    if (payload.remaining() == 0) {
      code = CloseFrame.NORMAL;
    } else if (payload.remaining() == 1) {
      code = CloseFrame.PROTOCOL_ERROR;
    } else {
      if (payload.remaining() >= 2) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.position(2);
        bb.putShort(payload.getShort());
        bb.position(0);
        code = bb.getInt();
      }
      payload.reset();
      try {
        int mark = payload.position();// because stringUtf8 also creates a mark
        validateUtf8(payload, mark);
      } catch (InvalidDataException e) {
        code = CloseFrame.NO_UTF8;
        reason = null;
      }
    }
  }

  
  private void validateUtf8(ByteBuffer payload, int mark) throws InvalidDataException {
    try {
      payload.position(payload.position() + 2);
      reason = Charsetfunctions.stringUtf8(payload);
    } catch (IllegalArgumentException e) {
      throw new InvalidDataException(CloseFrame.NO_UTF8);
    } finally {
      payload.position(mark);
    }
  }

  
  private void updatePayload() {
    byte[] by = Charsetfunctions.utf8Bytes(reason);
    ByteBuffer buf = ByteBuffer.allocate(4);
    buf.putInt(code);
    buf.position(2);
    ByteBuffer pay = ByteBuffer.allocate(2 + by.length);
    pay.put(buf);
    pay.put(by);
    pay.rewind();
    super.setPayload(pay);
  }

  @Override
  public ByteBuffer getPayloadData() {
    if (code == NOCODE) {
      return ByteBufferUtils.getEmptyByteBuffer();
    }
    return super.getPayloadData();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    CloseFrame that = (CloseFrame) o;

    if (code != that.code) {
      return false;
    }
    return reason != null ? reason.equals(that.reason) : that.reason == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + code;
    result = 31 * result + (reason != null ? reason.hashCode() : 0);
    return result;
  }
}
