package al.logger.client.wrapper.LoggerMC.network.server.S38;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S38PacketPlayerListItem",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class S38PacketPlayerListItem extends Packet {
    @ClassInstance
    public static Class S38PacketPlayerListItemClass;
    @WrapField(mcpName = "action",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field action;
    @WrapField(mcpName = "players",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field players;



    public S38PacketPlayerListItem(Object obj) {
        super(obj);
    }

    public static boolean isS38PacketPlayerListItem(Packet p) {
        return S38PacketPlayerListItemClass.isInstance(p.getWrappedObject());
    }
    public List<AddPlayerData> getPlayers(){
        List<?> playersList = (List<?>) getField(players);
        List<AddPlayerData> convert = new CopyOnWriteArrayList<>();
        playersList.forEach((player) -> {
            convert.add(new AddPlayerData(player));
        });
        return convert;
    }
}
