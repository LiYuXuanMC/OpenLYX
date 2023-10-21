package com.reflectmc.loader.packet.irc;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;
import lombok.Getter;

public class S04ServerData implements Packet {
    @Getter
    private Status status;
    @Getter
    private String name;
    @Getter
    private String md5;
    @Getter
    private int length;
    @Getter
    private byte[] bytes;
    public S04ServerData(){

    }
    public S04ServerData(Status status,String name,String md5,int length){
        this.status = status;
        this.name = name;
        this.md5 = md5;
        this.length = length;
    }
    public S04ServerData(Status status,String name,byte[] bytes){
        this.status = status;
        this.name = name;
        this.bytes = bytes;
        this.length = bytes.length;
    }
    public S04ServerData(Status status,String name){
        this.status = status;
        this.name = name;
    }
    @Override
    public void process(PacketBuffer buffer) {
        status = Status.getStatus(buffer.readInt());
        if (status == Status.INFO){
            name = buffer.readString();
            md5 = buffer.readString();
            length = buffer.readInt();
        }
        if (status == Status.DATA){
            name = buffer.readString();
            length = buffer.readInt();
            bytes = buffer.read(length);
        }
        if (status == Status.END){
            name = buffer.readString();
        }
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.PacketS04ServerData.getSeq());
        buffer.writeInt(status.getOrder());
        if (status == Status.INFO){
            buffer.writeString(name);
            buffer.writeString(md5);
            buffer.writeInt(length);
        }
        if (status == Status.DATA){
            buffer.writeString(name);
            buffer.writeInt(length);
            buffer.writeByte(bytes);
        }
        if (status == Status.END){
            buffer.writeString(name);
        }
        return buffer;
    }
    public enum Status {
        INFO(0),
        DATA(1),
        END(2),
        ;
        @Getter
        private int order;
        Status(int order){
            this.order = order;
        }
        public static Status  getStatus(int order){
            for (Status value : values()) {
               if (value.order == order)
                   return value;
            }
            return null;
        }
    }
}
