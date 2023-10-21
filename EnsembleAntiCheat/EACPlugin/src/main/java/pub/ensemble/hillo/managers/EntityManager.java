package pub.ensemble.hillo.managers;

import org.bukkit.entity.Player;
import pub.ensemble.hillo.objects.WrapEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityManager {

    private static Map<Player, WrapEntity> entitys = new HashMap<>();

    public static void addWrapEntity(Player player) {
        entitys.put(player, new WrapEntity(player));
    }

    public static void removeWrapEntity(Player player) {
        entitys.remove(player);
    }

    public static WrapEntity getWrapEntity(Player player) {
        return entitys.get(player);
    }

    public static void onProcessPlayers() {
        //类似于CopyOnWriteArrayList，防止多线程访问爆炸
        new ArrayList<>(entitys.values()).forEach(WrapEntity::onProcessPlayers);
    }

}
