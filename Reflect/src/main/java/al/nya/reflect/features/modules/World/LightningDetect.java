package al.nya.reflect.features.modules.World;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.Player.UHCHelper;
import al.nya.reflect.features.modules.World.waypoint.WaypointManager;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.ServerData;
import al.nya.reflect.wrapper.wraps.wrapper.network.S2CPacketSpawnGlobalEntity;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ChatStyle;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.HoverEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.HoverEventAction;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEventAction;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.ChatComponentText;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LightningDetect extends Module {
    private final OptionValue chat = new OptionValue("LogChat", false);
    private final DecimalFormat decimalFormat = new DecimalFormat("0.0");
    GregorianCalendar calendar = new GregorianCalendar();

    public LightningDetect() {
        super("LightningDetect", "雷击检测", ModuleType.World);
        addValues(chat);
    }

    @EventTarget
    public void onPacket(EventPacket e) {
        if (e.getEventType() == EventType.RecievePacket) {
            if (S2CPacketSpawnGlobalEntity.isS2CPacketSpawnGlobalEntity(e.getPacket())) {
                S2CPacketSpawnGlobalEntity packet = new S2CPacketSpawnGlobalEntity(e.getPacket().getWrapObject());
                if (packet.getType() != 1) return;

                if (chat.getValue())
                    mc.getIngameGUI().getGuiChat().printChatMessage(
                            new ChatComponentText("Lightning at X:" + decimalFormat.format(packet.getX() / 32.0) + " Y:" + decimalFormat.format(packet.getY() / 32.0) + " Z:" + decimalFormat.format(packet.getZ() / 32.0) + " ")
                                    .appendSibling(new ChatComponentText("\2477[\247c" + "Teleport" + "\2477] ")
                                            .setChatStyle(new ChatStyle()
                                                    .setHoverEvent(new HoverEvent(HoverEventAction.SHOW_TEXT, new ChatComponentText("\247c\247lClick to Teleport")))
                                                    .setClickEvent(new ClickEvent(ClickEventAction.RUN_COMMAND, ".tp " + decimalFormat.format(packet.getX() / 32.0) + " " + decimalFormat.format(packet.getY() / 32.0) + " " + decimalFormat.format(packet.getZ() / 32.0))))));

                if (ModuleManager.getModule(UHCHelper.class).lightningHelper.getValue()) {
                    ServerData serverData = mc.getCurrentServerData();
                    EntityPlayerSP thePlayer = mc.getThePlayer();
                    final String host = !serverData.isNull() ? serverData.getServerIP() : "localhost";
                    calendar = new GregorianCalendar();
                    WaypointManager.INSTANCE.getWaypointDataList().add(new Waypoints.WaypointData(host, "LIGHTNING_" + calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) + calendar.get(Calendar.SECOND) + calendar.get(Calendar.MILLISECOND), thePlayer.getDimension(), Double.parseDouble(decimalFormat.format(packet.getX() / 32.0)), Double.parseDouble(decimalFormat.format(packet.getY() / 32.0)), Double.parseDouble(decimalFormat.format(packet.getZ() / 32.0))));

                }

                NotificationPublisher.queue(getName(), "Lightning at X:" + decimalFormat.format(packet.getX() / 32.0) + " Y:" + decimalFormat.format(packet.getY() / 32.0) + " Z:" + decimalFormat.format(packet.getZ() / 32.0), 6000, NotificationType.SUCCESS);
            }
        }
    }

}
