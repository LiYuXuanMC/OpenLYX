package al.nya.proxy.network.packets.handshake;

import al.nya.proxy.network.EnumPacketDirection;
import al.nya.proxy.network.ProxyClient;
import al.nya.proxy.network.packets.Packet;
import al.nya.proxy.network.packets.annotations.PacketField;
import al.nya.proxy.utils.EnumDataType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

public class S00PacketServerInfo extends Packet {

    @PacketField(seq = 0,type = EnumDataType.String)
    @Getter
    @Setter
    private String json;

    @Override
    public EnumPacketDirection getDirection(ProxyClient client) {
        return super.getDirection(client);
    }

    @Override
    public Packet processPacket(ProxyClient client) {
        String json = this.getJson();
        JsonObject jsonObject = new GsonBuilder().create().fromJson(json, JsonObject.class);
        jsonObject.getAsJsonObject("description").addProperty("text", "Proxy Server");
        this.setJson(new Gson().toJson(jsonObject));
        return this;
    }

}
