package pub.ensemble.hillo;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pub.ensemble.hillo.enums.EAC_STATUS;
import pub.ensemble.hillo.handlers.EACHandler;
import pub.ensemble.hillo.managers.EntityManager;
import pub.ensemble.hillo.objects.WrapEntity;

public class EACCore extends JavaPlugin implements Listener {

    public static EACCore INSTANCE;
    public static final String CHANNEL = "EnsembleAntiCheat";

    @Getter
    @Setter
    private EACHandler eacHandler;

    @Override
    public void onEnable() {
        EACCore.INSTANCE = this;
        Bukkit.getPluginManager().registerEvents(this, this);
        this.setEacHandler(new EACHandler());
        this.getServer().getScheduler().runTaskTimer(EACCore.INSTANCE, EntityManager::onProcessPlayers, 20, 20);
        this.getLogger().info("EACCore has been enabled.");
        this.getServer().getMessenger().registerIncomingPluginChannel(EACCore.INSTANCE, CHANNEL, this.getEacHandler());
        this.getServer().getMessenger().registerOutgoingPluginChannel(EACCore.INSTANCE, CHANNEL);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("EACCore has been disabled.");
    }

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        this.getLogger().info("Player(" + event.getPlayer().getClass().getSimpleName() + ") " + event.getPlayer().getName() + " tried to join the server.");
        injectChannel(event.getPlayer(), CHANNEL);
        EntityManager.addWrapEntity(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        EntityManager.removeWrapEntity(event.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        WrapEntity wrapEntity = EntityManager.getWrapEntity(event.getPlayer());
        if (wrapEntity != null) {
            if(wrapEntity.getStatus().equals(EAC_STATUS.HANDSHAKE)){
                event.setTo(event.getFrom());
                wrapEntity.getPlayer().sendMessage("§c§lEAC §7» §f请等待EAC认证完成.");
            }
        }
    }

    public static void injectChannel(Player player, String channelName) {
        try {
            player.getClass().getMethod("addChannel", String.class).invoke(player, channelName);
        } catch (Exception ignored) { }
    }
}