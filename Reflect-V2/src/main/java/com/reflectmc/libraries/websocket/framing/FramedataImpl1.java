package com.reflectmc.libraries.websocket.framing;

import java.nio.ByteBuffer;
import com.reflectmc.libraries.websocket.enums.Opcode;
import com.reflectmc.libraries.websocket.exceptions.InvalidDataException;
import com.reflectmc.libraries.websocket.util.ByteBufferUtils;


public abstract class FramedataImpl1 implements Framedata {

  
  private boolean fin;
  
  private Opcode optcode;

  
  private ByteBuffer unmaskedpayload;

  
  private boolean transferemasked;

  
  private boolean rsv1;

  
  private boolean rsv2;

  
  private boolean rsv3;

  
  public abstract void isValid() throws InvalidDataException;

  
  public FramedataImpl1(Opcode op) {
    optcode = op;
    unmaskedpayload = ByteBufferUtils.getEmptyByteBuffer();
    fin = true;
    transferemasked = false;
    rsv1 = false;
    rsv2 = false;
    rsv3 = false;
  }

  @Override
  public boolean isRSV1() {
    return rsv1;
  }

  @Override
  public boolean isRSV2() {
    return rsv2;
  }

  @Override
  public boolean isRSV3() {
    return rsv3;
  }

  @Override
  public boolean isFin() {
    return fin;
  }

  @Override
  public Opcode getOpcode() {
    return optcode;
  }

  @Override
  public boolean getTransfereMasked() {
    return transferemasked;
  }

  @Override
  public ByteBuffer getPayloadData() {
    return unmaskedpayload;
  }

  @Override
  public void append(Framedata nextframe) {
    ByteBuffer b = nextframe.getPayloadData();
    if (unmaskedpayload == null) {
      unmaskedpayload = ByteBuffer.allocate(b.remaining());
      b.mark();
      unmaskedpayload.put(b);
      b.reset();
    } else {
      b.mark();
      unmaskedpayload.position(unmaskedpayload.limit());
      unmaskedpayload.limit(unmaskedpayload.capacity());

      if (b.remaining() > unmaskedpayload.remaining()) {
        ByteBuffer tmp = ByteBuffer.allocate(b.remaining() + unmaskedpayload.capacity());
        unmaskedpayload.flip();
        tmp.put(unmaskedpayload);
        tmp.put(b);
        unmaskedpayload = tmp;

      } else {
        unmaskedpayload.put(b);
      }
      unmaskedpayload.rewind();
      b.reset();
    }
    fin = nextframe.isFin();

  }

  @Override
  public String toString() {
    return "Framedata{ opcode:" + getOpcode() + ", fin:" + isFin() + ", rsv1:" + isRSV1()
        + ", rsv2:" + isRSV2() + ", rsv3:" + isRSV3() + ", payload length:[pos:" + unmaskedpayload
        .position() + ", len:" + unmaskedpayload.remaining() + "], payload:" + (
        unmaskedpayload.remaining() > 1000 ? "(too big to display)"
            : new String(unmaskedpayload.array())) + '}';
  }

  
  public void setPayload(ByteBuffer payload) {
    this.unmaskedpayload = payload;
  }

  
  public void setFin(boolean fin) {
    this.fin = fin;
  }

  
  public void setRSV1(boolean rsv1) {
    this.rsv1 = rsv1;
  }

  
  public void setRSV2(boolean rsv2) {
    this.rsv2 = rsv2;
  }

  
  public void setRSV3(boolean rsv3) {
    this.rsv3 = rsv3;
  }

  
  public void setTransferemasked(boolean transferemasked) {
    this.transferemasked = transferemasked;
  }

  
  public static FramedataImpl1 get(Opcode opcode) {
    if (opcode == null) {
      throw new IllegalArgumentException("Supplied opcode cannot be null");
    }
    switch (opcode) {
      case PING:
        return new PingFrame();
      case PONG:
        return new PongFrame();
      case TEXT:
        return new TextFrame();
      case BINARY:
        return new BinaryFrame();
      case CLOSING:
        return new CloseFrame();
      case CONTINUOUS:
        return new ContinuousFrame();
      default:
        throw new IllegalArgumentException("Supplied opcode is invalid");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FramedataImpl1 that = (FramedataImpl1) o;

    if (fin != that.fin) {
      return false;
    }
    if (transferemasked != that.transferemasked) {
      return false;
    }
    if (rsv1 != that.rsv1) {
      return false;
    }
    if (rsv2 != that.rsv2) {
      return false;
    }
    if (rsv3 != that.rsv3) {
      return false;
    }
    if (optcode != that.optcode) {
      return false;
    }
    return unmaskedpayload != null ? unmaskedpayload.equals(that.unmaskedpayload)
        : that.unmaskedpayload == null;
  }

  @Override
  public int hashCode() {
    int result = (fin ? 1 : 0);
    result = 31 * result + optcode.hashCode();
    result = 31 * result + (unmaskedpayload != null ? unmaskedpayload.hashCode() : 0);
    result = 31 * result + (transferemasked ? 1 : 0);
    result = 31 * result + (rsv1 ? 1 : 0);
    result = 31 * result + (rsv2 ? 1 : 0);
    result = 31 * result + (rsv3 ? 1 : 0);
    return result;
  }
}
