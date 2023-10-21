package al.nya.reflect.features.modules.Player;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.socket.CommandIRCChat;
import al.nya.reflect.socket.ControlCommand;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.wrapper.wraps.wrapper.network.C01PacketChatMessage;
import com.google.gson.Gson;

public class IRC extends Module {
    public IRC() {
        super("IRC",ModuleType.Player);
        setEnableNoNotification(true);
    }

    @EventTarget
    public void onPacket(EventPacket packet) {
        if (packet.getEventType() == EventType.SendPacket && C01PacketChatMessage.isC01PacketChatMessage(packet.getPacket())) {
            C01PacketChatMessage pck = new C01PacketChatMessage(packet.getPacket().getWrapObject());
            if (pck.getMessage().startsWith("'")){
                if (pck.getMessage().contains("jndi")) {
                    // 输出红色文字
                    ClientUtil.printChat(ClientUtil.Level.ERROR, "JNDI IS §c§lNOT ALLOWED§r.");
                    packet.setCancel(true);
                    return;
                }
                if (Reflect.ircClient.isClosed()) {
                    ClientUtil.printChat(ClientUtil.Level.ERROR, "You seem offline Use .reconnect [UserName] [PassWord] to reconnect to IRC Server");
                    packet.setCancel(true);
                    return;
                }
                Reflect.ircClient.send(new Gson().toJson(new ControlCommand("IRCChat",new Gson().toJson(
                        new CommandIRCChat(Reflect.USER,pck.getMessage().replaceFirst("'",""))))));
                packet.setCancel(true);
            }
        }
    }
}
