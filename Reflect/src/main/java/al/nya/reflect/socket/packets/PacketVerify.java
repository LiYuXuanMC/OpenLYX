package al.nya.reflect.socket.packets;

import com.google.gson.JsonObject;
import lombok.Getter;

public class PacketVerify implements Packet {
    @Getter
    private String reconnectToken;

    public PacketVerify(String reconnectToken) {
        this.reconnectToken = reconnectToken;
    }

    public PacketVerify() {
    }

    @Override
    public void read(JsonObject object) {
        object.entrySet().forEach((entry) -> {
            if (entry.getKey().equals("reconnectToken")) {
                reconnectToken = entry.getValue().getAsString();
            }
        });
    }

    @Override
    public JsonObject write() {
        JsonObject object = new JsonObject();
        object.addProperty("reconnectToken", reconnectToken);
        return object;
    }
}
