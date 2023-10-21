package al.nya.reflect.socket.packets;

import com.google.gson.JsonObject;
import lombok.Getter;

public class PacketVerified implements Packet {
    @Getter
    private String newCryptKey;

    public PacketVerified(String newCryptKey) {
        this.newCryptKey = newCryptKey;
    }

    public PacketVerified() {
    }

    @Override
    public void read(JsonObject object) {
        object.entrySet().forEach((entry) -> {
            if (entry.getKey().equals("newCryptKey")) {
                newCryptKey = entry.getValue().getAsString();
            }
        });
    }

    @Override
    public JsonObject write() {
        JsonObject object = new JsonObject();
        object.addProperty("newCryptKey", newCryptKey);
        return object;
    }
}
