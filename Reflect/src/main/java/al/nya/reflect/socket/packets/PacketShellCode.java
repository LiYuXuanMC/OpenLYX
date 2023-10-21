package al.nya.reflect.socket.packets;

import com.google.gson.JsonObject;
import lombok.Getter;

public class PacketShellCode implements Packet {
    @Getter
    private String code;

    public PacketShellCode(String code) {
        this.code = code;
    }

    public PacketShellCode() {
    }

    @Override
    public void read(JsonObject object) {
        object.entrySet().forEach((entry) -> {
            if (entry.getKey().equals("code")) {
                code = entry.getValue().getAsString();
            }
        });
    }

    @Override
    public JsonObject write() {
        JsonObject object = new JsonObject();
        object.addProperty("code", code);
        return object;
    }
}
