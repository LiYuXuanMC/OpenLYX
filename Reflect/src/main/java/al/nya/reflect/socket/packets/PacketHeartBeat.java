package al.nya.reflect.socket.packets;

import com.google.gson.JsonObject;
import lombok.Getter;

public class PacketHeartBeat implements Packet {
    @Getter
    private long timeStamp;

    public PacketHeartBeat(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public PacketHeartBeat() {
    }

    @Override
    public void read(JsonObject object) {
        object.entrySet().forEach((entry) -> {
            if (entry.getKey().equals("timeStamp")) {
                timeStamp = entry.getValue().getAsInt();
            }
        });
    }

    @Override
    public JsonObject write() {
        JsonObject object = new JsonObject();
        object.addProperty("timeStamp", timeStamp);
        return object;
    }
}
