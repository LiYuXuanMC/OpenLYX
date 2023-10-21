package al.nya.reflect.socket.packets;

import com.google.gson.JsonObject;
import lombok.Getter;

public class PacketIRCChat implements Packet {
    @Getter
    private String user;
    @Getter
    private String rank;
    @Getter
    private String text;

    public PacketIRCChat(String user, String rank, String text) {
        this.user = user;
        this.rank = rank;
        this.text = text;
    }

    public PacketIRCChat() {
    }

    @Override
    public void read(JsonObject object) {
        object.entrySet().forEach((entry) -> {
            if (entry.getKey().equals("user")) {
                user = entry.getValue().getAsString();
            }
            if (entry.getKey().equals("rank")) {
                rank = entry.getValue().getAsString();
            }
            if (entry.getKey().equals("text")) {
                text = entry.getValue().getAsString();
            }
        });
    }

    @Override
    public JsonObject write() {
        JsonObject object = new JsonObject();
        object.addProperty("user", user);
        object.addProperty("rank", rank);
        object.addProperty("text", text);
        return object;
    }
}
