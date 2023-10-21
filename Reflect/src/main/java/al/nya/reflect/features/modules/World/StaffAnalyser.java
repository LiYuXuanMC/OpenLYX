package al.nya.reflect.features.modules.World;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.HTTPUtil;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.network.C01PacketChatMessage;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;
import al.nya.reflect.wrapper.wraps.wrapper.network.S02PacketChat;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

public class StaffAnalyser extends Module {
    public static StaffAnalyser INSTANCE;
    public static ModeValue mode = new ModeValue("Mode", Mode.Hypixel, Mode.values());
    public static DoubleValue delay = new DoubleValue("Delay",300D,10D,60D,"0");
    CheckThread thread;
    public int lastBanned = 0;
    public static String key = null;
    public StaffAnalyser() {
        super("StaffAnalyser", ModuleType.World);
        INSTANCE = this;
        addValues(mode,delay);
    }

    @Override
    public void onEnable() {
        if (thread != null && thread.isAlive()) {
            thread.stop();
            thread = null;
        }
        thread = new CheckThread();
        thread.start();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (thread != null && thread.isAlive()) {
            thread.stop();
            thread = null;
        }
        super.onDisable();
    }

    @EventTarget
    public void onPacket(EventPacket eventPacket){
        if (mode.getValue() == Mode.Hypixel && eventPacket.getEventType() == EventType.RecievePacket) {
            if (S02PacketChat.isS02PacketChat(eventPacket.getPacket())) {
                S02PacketChat chatPacket = new S02PacketChat(eventPacket.getPacket().getWrapObject());
                String chatMessage = chatPacket.getChatComponent().getUnformattedText();
                if (chatMessage.matches("Your new API key is ........-....-....-....-............")) {
                    eventPacket.setCancel(true);
                    key = chatMessage.replace("Your new API key is ", "");
                    ClientUtil.printChat(ClientUtil.Level.DEBUG, "Get API Key: " + key);
                }
            }

        }
    }

    @EventTarget
    public void onWorldLoad(EventWorldLoad worldLoad) {
        if (StaffAnalyser.key == null && mode.getValue() == Mode.Hypixel) {
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C01PacketChatMessage("/api new"));
        }
    }

    enum Mode {
        Hypixel
    }

    class CheckThread extends Thread {
        int lastBannedCount = 0;

        @Override
        public void run() {
            while (true) {
                try {
                    if (StaffAnalyser.key == null) {
                        sleep(1000);
                        continue;
                    }
                    sleep(StaffAnalyser.delay.getValue().intValue() * 1000L);
                    String result = HTTPUtil.httpGet("https://api.hypixel.net/watchdogStats?key=" + StaffAnalyser.key);

                    Gson gson = new Gson();
                    BanQuantityListJSON banQuantityListJSON = gson.fromJson(result, BanQuantityListJSON.class);
                    // int watchdogLastMinute = banQuantityListJSON.getWatchdogLastMinute(); //1分钟前内狗ban的人数
                    // int watchdogTotal = banQuantityListJSON.getWatchdogTotal(); //共狗ban的人数
                    int staffTotal = banQuantityListJSON.getStaffTotal(); //共客服ban的人数

                    if (lastBannedCount == 0) {
                        lastBannedCount = staffTotal;
                    } else {
                        int banned = staffTotal - lastBannedCount;
                        lastBannedCount = staffTotal;

                        if (banned > 1) {
                            NotificationPublisher.queue("Staff Warning", "Staff banned " + banned + " players in " + StaffAnalyser.delay.getValue().intValue() + "s.",10000, banned > 3 ? NotificationType.SUCCESS : NotificationType.ERROR);
                        } else {
                            NotificationPublisher.queue("Staff Warning", "Staff didn't ban any player in " + StaffAnalyser.delay.getValue().intValue() + "s.",10000, NotificationType.SUCCESS);
                        }

                        StaffAnalyser.INSTANCE.lastBanned = banned;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @NoArgsConstructor
    @Data
    class BanQuantityListJSON {
        @SerializedName("success")
        boolean success;
        @SerializedName("watchdog_lastMinute")
        int watchdogLastMinute;
        @SerializedName("staff_rollingDaily")
        int staffRollingDaily;
        @SerializedName("watchdog_total")
        int watchdogTotal;
        @SerializedName("watchdog_rollingDaily")
        int watchdogRollingDaily;
        @SerializedName("staff_total")
        int staffTotal;
    }
}
