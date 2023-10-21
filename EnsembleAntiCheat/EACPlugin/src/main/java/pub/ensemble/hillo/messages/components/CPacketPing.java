package pub.ensemble.hillo.messages.components;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import pub.ensemble.hillo.EACCore;
import pub.ensemble.hillo.messages.IMessage;
import pub.ensemble.hillo.objects.WrapEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class CPacketPing implements IMessage {

    @Getter
    @Setter
    private UUID uuid;

    @Getter
    @Setter
    private Long timestamp;

    @Override
    public IMessage onMessage(WrapEntity player) {
        Date date = new Date(timestamp);
        long mostSignificantBits = timestamp >>> 4;
        long leastSignificantBits = date.getTime() << 32;
        UUID uuid = new UUID(mostSignificantBits, leastSignificantBits);
        if(!uuid.equals(this.uuid)){
            player.getPlayer().kickPlayer("UUID不匹配");
            return null;
        }
        if(System.currentTimeMillis() - timestamp > 60000){
            player.getPlayer().kickPlayer("超时,或检查您的电脑时间是否准确");
            return null;
        }else if (System.currentTimeMillis() - timestamp < 0){
            player.getPlayer().kickPlayer("时间戳错误");
            return null;
        }
        player.setLastJoinTime(System.currentTimeMillis());
        return null;
    }

    @Override
    public void read(ByteBuf buf,WrapEntity player) throws IOException {
        int packetSize = buf.readInt();
        byte[] saltBytes = new byte[packetSize];
        buf.readBytes(saltBytes);
        this.uuid = UUID.fromString(new String(saltBytes, StandardCharsets.UTF_8));
        this.timestamp = buf.readLong();
    }

    @Override
    public void write(ByteBuf buf) throws IOException {

    }
}
