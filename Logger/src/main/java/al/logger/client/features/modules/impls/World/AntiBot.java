package al.logger.client.features.modules.impls.World;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.components.Notification;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.network.NetworkPlayerInfo;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.server.S38.AddPlayerData;
import al.logger.client.wrapper.LoggerMC.network.server.S38.S38PacketPlayerListItem;
import al.logger.client.wrapper.LoggerMC.network.server.S41PacketServerDifficulty;
import al.logger.client.wrapper.LoggerMC.world.GameType;
import by.radioegor146.nativeobfuscator.Native;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Native
public class AntiBot extends Module {
    private ModeValue mode = new ModeValue("Mode", AntiBotMode.Normal, AntiBotMode.values());

    private DoubleValue time = new DoubleValue("Time" , 5000 , 100 , 1000 ,1);
    private OptionValue inair = new OptionValue("InAir", false);
    private OptionValue pingCheck = new OptionValue("PingCheck", true);
    private OptionValue gmCheck = new OptionValue("GamemodeCheck", true);
    private OptionValue DoubleCheck = new OptionValue("DoubleCheck" , false);
    private boolean wasAdded = false;

    boolean Working = false;

    private static List<EntityPlayer> removed = new CopyOnWriteArrayList<>();
    TimerUtils timer = new TimerUtils();
    public AntiBot() {
        super("AntiBot", Category.World);
        addValues(mode ,time ,inair,pingCheck,gmCheck,DoubleCheck);
        pingCheck.addCallBack(() -> mode.getValue() == AntiBotMode.Matrix);
        gmCheck.addCallBack(() -> mode.getValue() == AntiBotMode.Matrix);
        DoubleCheck.addCallBack(() -> mode.getValue() == AntiBotMode.Matrix);
    }

    @Override
    public void onDisable() {
        removed.clear();
        timer.reset();
        super.onDisable();
    }

    @Listener
    public void onWorldLoad(EventLoadWorld e){
        removed.clear();
        timer.reset();
    }

    @Listener
    public void onTick(EventTick e){
        if (!mc.getThePlayer().isNull() && !mc.getTheWorld().isNull()){
            if (mode.getValue() == AntiBotMode.Hypixel){
                for (EntityPlayer i : mc.getTheWorld().getPlayerEntities()){
                    if (!isInTablist(i)){
                        removed.add(i);
                        mc.getTheWorld().removeEntityFromWorld(i.getEntityId());
                    }
                }
            }

        }
    }


    @Listener
    public void onPacket(EventPacket evt){
        if (mode.getValue() == AntiBotMode.Matrix){
            Packet packet = evt.getPacket();
            if (S41PacketServerDifficulty.isS41PacketServerDifficulty(packet)){
                wasAdded = false;
                return;
            }
            if (S38PacketPlayerListItem.isS38PacketPlayerListItem(packet)){
                S38PacketPlayerListItem s38 = new S38PacketPlayerListItem(packet.getWrappedObject());
                AddPlayerData data = s38.getPlayers().get(0);

                if (DoubleCheck.getValue()){
                        for (EntityPlayer entity: mc.getTheWorld().getPlayerEntities()){
                            if (data.isNull() || entity.isNull() || entity.getEntityId() == mc.getThePlayer().getEntityId()){
                                return;
                            }
                            if (entity.getName().equals(data.getProfile().getName())){

                                evt.cancel();
                                Logger.getInstance().notificationManager.addNotification(new Notification("[AntiBot] Prevented " + data.getProfile().getName() + " from spawning.", Notification.NotificationType.Success));
                            }
                        }
                }


                if (!data.getProfile().isNull() && data.getProfile().getName() != null){
                    if (!wasAdded) {
                        wasAdded = data.getProfile().getName().equals(mc.getThePlayer().getName());
                    } else if (!mc.getThePlayer().isSpectator() && !mc.getThePlayer().getCapabilities().isAllowFlying() && (!pingCheck.getValue() || data.getPing() != 0) && (!gmCheck.getValue() || data.getGameMode() != GameType.NOT_SET)) {
                        evt.cancel();
                        Logger.getInstance().notificationManager.addNotification(new Notification("[AntiBot] Prevented " + data.getProfile().getName() + " from spawning.", Notification.NotificationType.Success));
                    }
                }
                return;
            }
        }
    }

    public static boolean isInTablist(EntityPlayer player) {
        if (Minecraft.getMinecraft().isSingleplayer()) {
            return true;
        }
        for (NetworkPlayerInfo o : Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap()) {
            NetworkPlayerInfo playerInfo = o;
            if (!playerInfo.getGameProfile().getName().equalsIgnoreCase(player.getName())) continue;
            return true;
        }
        return false;
    }


    public enum AntiBotMode {
        Normal,
        Matrix,
        Hypixel

    }

}
