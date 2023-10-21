package com.reflectmc.injector.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PacketBuffer {
    private final ByteArrayOutputStream stream;
    private ByteArrayInputStream readStream;
    public PacketBuffer(){
        stream = new ByteArrayOutputStream();
    }

    /**
     * append
     * @param buffer
     */
    public PacketBuffer(PacketBuffer buffer){
        stream = new ByteArrayOutputStream();
        appendBuffer(buffer);
    }

    public void appendBuffer(PacketBuffer buffer){
        writeByte(buffer.getData());
    }

    public void writeByte(byte[] bytes){
        try {
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeString(String s){
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        writeInt(bytes.length);
        writeByte(bytes);
    }
    public String readString(){
        int length = readInt();
        byte[] bytes = read(length);
        return new String(bytes,StandardCharsets.UTF_8);
    }
    public void writeInt(Integer integer){
        writeByte(intToByteArray(integer));
    }
    public int readInt(){
        return byteArrayToInt(read(4));
    }
    public byte[] read(int length){
        if (readStream == null) readStream = new ByteArrayInputStream(stream.toByteArray());
        byte[] bytes = new byte[length];
        try {
            readStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    public byte[] getData(){
        return stream.toByteArray();
    }
    public int getLength(){
        return stream.size();
    }
    public void closeBuffer(){
        try {
            stream.close();
            if (readStream != null) readStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeDouble(double doubleValue){
        writeByte(ByteBuffer.allocate(8).putDouble(doubleValue).array());
    }
    public double readDouble(){
        return ByteBuffer.wrap(read(8)).getDouble();
    }
    public boolean end(){
        return readStream.available() == 0;
    }
    private static int byteArrayToInt(byte[] b) {
        return   b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    private static byte[] intToByteArray(int a) {
        return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
}
