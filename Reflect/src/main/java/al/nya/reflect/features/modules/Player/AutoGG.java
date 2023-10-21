package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.features.modules.Combat.KillAura;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.Scaffold;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.network.S45PacketTitle;

public class AutoGG extends Module {
    public OptionValue ad = new OptionValue("Ad", false);
    public OptionValue autoDisable = new OptionValue("AutoDisable", true);
    public AutoGG() {
        super("AutoGG", ModuleType.Player);
        addValues(ad,autoDisable);
    }

    @EventTarget
    public void onPacket(EventPacket packet) {
        if (S45PacketTitle.isS45PacketTitle(packet.getPacket())) {
            S45PacketTitle titlePacket = new S45PacketTitle(packet.getPacket().getWrapObject());
            String title = titlePacket.getFormattedText();
            if (title == null) return;
            if ((title.startsWith("§6§l") && title.endsWith("§r")) || (title.startsWith("§c§lYOU") && title.endsWith("§r")) || (title.startsWith("§c§lGame") && title.endsWith("§r")) || (title.startsWith("§c§lWITH") && title.endsWith("§r")) || (title.startsWith("§c§lYARR") && title.endsWith("§r"))) {
                mc.getThePlayer().sendChatMessage("/ac GG" + (ad.getValue() ? "! Get Reflect <dot> nya <dot> al" : ""));
                if (autoDisable.getValue()) {
                    KillAura killAura = ModuleManager.getModule(KillAura.class);
                    if (killAura.isEnable()) killAura.setEnable(false);
                    Scaffold scaffold = ModuleManager.getModule(Scaffold.class);
                    if (scaffold.isEnable()) scaffold.setEnable(false);
                }
                NotificationPublisher.queue("GG!","You won the game!",5000, NotificationType.SUCCESS);
            }
        }
    }
}
