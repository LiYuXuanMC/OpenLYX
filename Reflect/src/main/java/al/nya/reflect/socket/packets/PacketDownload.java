package al.nya.reflect.socket.packets;

import com.google.gson.JsonObject;
import lombok.Getter;

public class PacketDownload implements Packet {
    @Getter
    private String fileName;

    public PacketDownload(String fileName) {
        this.fileName = fileName;
    }

    public PacketDownload() {
    }

    @Override
    public void read(JsonObject object) {
        object.entrySet().forEach((entry) -> {
            if (entry.getKey().equals("fileName")) {
                fileName = entry.getValue().getAsString();
            }
        });
    }

    @Override
    public JsonObject write() {
        JsonObject object = new JsonObject();
        object.addProperty("fileName", fileName);
        return object;
    }
}
