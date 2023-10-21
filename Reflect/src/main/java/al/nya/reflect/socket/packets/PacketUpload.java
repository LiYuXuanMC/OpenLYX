package al.nya.reflect.socket.packets;

import com.google.gson.JsonObject;
import lombok.Getter;

public class PacketUpload implements Packet {
    @Getter
    private int size;

    public PacketUpload(int size) {
        this.size = size;
    }

    public PacketUpload() {
    }

    @Override
    public void read(JsonObject object) {
        object.entrySet().forEach((entry) -> {
            if (entry.getKey().equals("size")) {
                this.size = entry.getValue().getAsInt();
            }
        });
    }

    @Override
    public JsonObject write() {
        JsonObject object = new JsonObject();
        object.addProperty("size", size);
        return object;
    }
}
