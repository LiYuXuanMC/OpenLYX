package pub.ensemble.hillo.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JByteBuf {

    private List<Byte> buffer;

    public JByteBuf() {
        buffer = new ArrayList<>();
    }

    public JByteBuf(int capacity) {
        buffer = new ArrayList<>(capacity);
    }

    public void writeByte(byte b) {
        buffer.add(b);
    }

    public byte readByte() {
        byte b = buffer.get(0);
        buffer.remove(0);
        return b;
    }

    public void writeBytes(byte[] bytes, int length) {
        for (int i = 0; i < length; i++) {
            buffer.add(bytes[i]);
        }
    }

    public void readBytes(byte[] bytes, int length) {
        for (int i = 0; i < length; i++) {
            bytes[i] = buffer.get(i);
        }
        buffer.subList(0, length).clear();
    }

    public byte[] readBytes(int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = buffer.get(i);
        }
        buffer.subList(0, length).clear();
        return bytes;
    }


    public void writeInt(int value) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (value >> 24);
        bytes[1] = (byte) (value >> 16);
        bytes[2] = (byte) (value >> 8);
        bytes[3] = (byte) value;
        writeBytes(bytes, 4);
    }

    public int readInt() {
        byte[] bytes = new byte[4];
        readBytes(bytes, 4);
        int value = bytes[0] + bytes[1] + bytes[2] + bytes[3];
        return value;
    }

    public void writeString(String str) {
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        writeInt(strBytes.length);
        writeBytes(strBytes, strBytes.length);
    }

    public void writeLong(long value) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (value >> (56 - i * 8));
        }
        writeBytes(bytes, 8);
    }

    public long readLong() {
        byte[] bytes = new byte[8];
        readBytes(bytes, 8);
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value += (long) (bytes[i] & 0xFF) << (56 - i * 8);
        }
        return value;
    }

    public String readString() {
        int length = readInt();
        byte[] bytes = new byte[length];
        readBytes(bytes, length);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public int readableBytes() {
        return buffer.size();
    }

}
