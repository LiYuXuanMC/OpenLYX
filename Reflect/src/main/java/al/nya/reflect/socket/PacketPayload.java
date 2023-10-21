package al.nya.reflect.socket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;

public class PacketPayload {
    @Getter
    private String packetId;
    @Getter
    private JsonObject packetData;

    private static final JsonParser parser = new JsonParser();
    private static final Gson gson = new Gson();

    public PacketPayload(String packetId, JsonObject packetData) {
        this.packetId = packetId;
        this.packetData = packetData;
    }

    public static PacketPayload read(String packet) {
        System.out.println(packet);
        JsonElement element = parser.parse(packet);
        if (!element.isJsonObject()) {
            return null;
        }
        JsonObject object = element.getAsJsonObject();
        String packetId = object.getAsJsonPrimitive("packetId").getAsString();
        JsonObject packetData = object.getAsJsonObject("packetData");
        if (packetId == null || packetData == null) {
            return null;
        }
        return new PacketPayload(packetId, packetData);
    }

    public String flush() {
        JsonObject object = new JsonObject();
        object.addProperty("packetId", packetId);
        object.add("packetData", packetData);
        return gson.toJson(object);
    }
}
