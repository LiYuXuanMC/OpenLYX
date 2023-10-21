package com.reflectmc.loader.packet.local;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;
import lombok.Getter;

public class PacketG03InjectStatus implements Packet {
    @Getter
    private Status status;
    @Getter
    private String reason;
    public PacketG03InjectStatus(){
        status = Status.Success;
    }
    public PacketG03InjectStatus(String reason){
        status = Status.Fail;
        this.reason = reason;
    }
    @Override
    public void process(PacketBuffer buffer) {
        status = Status.getByOrder(buffer.readInt());
        if (status == Status.Fail){
            reason = buffer.readString();
        }
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.G03InjectStatus.getSeq());
        buffer.writeInt(status.getOrder());
        if (status == Status.Fail){
            buffer.writeString(reason);
        }
        return buffer;
    }
    public enum Status {
        Success(0),
        Fail(1)
        ;
        @Getter
        private int order;
        Status(int order){
            this.order = order;
        }
        public static Status getByOrder(int i){
            for (Status value : values()) {
                if (value.getOrder() == i){
                    return value;
                }
            }
            return null;
        }
    }
}
