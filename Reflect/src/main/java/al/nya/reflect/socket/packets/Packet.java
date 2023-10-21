package al.nya.reflect.socket.packets;

import com.google.gson.JsonObject;

public interface Packet {
    void read(JsonObject object);

    JsonObject write();
}
