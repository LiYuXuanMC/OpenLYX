package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.network.C01PacketChatMessage;

public class Test extends Module {
    public Test() {
        super("Test", ModuleType.Player);
        addValues(checkServer,checkClient,checkPacket);
    }
    public static OptionValue checkServer = new OptionValue("checkServer", false);
    public static OptionValue checkClient = new OptionValue("checkClient", false);
    public static OptionValue checkPacket = new OptionValue("checkPacket", false);

    @Override
    public void onEnable() {
        ClientUtil.printChat("send a message!!! 1");
        mc.getThePlayer().sendChatMessage("我是傻逼我是矩阵我是网骗神呵呵呵呵呵呵我就是个傻逼呵呵你妈死了呵呵LLLFW");
        ClientUtil.printChat("send a message!!! 2");
        mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("这是第二个消息wwwww!"));
        ClientUtil.printChat("send a message!!! 3");
        mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C01PacketChatMessage("这是第三个消息wwwww!"));
        mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C01PacketChatMessage("这是第三个消息wwwww!"));
        mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C01PacketChatMessage("这是第三个消息wwwww!"));
    }


    @EventTarget
    public void onPacket(EventPacket packet) {
        if (checkClient.getValue() && packet.getEventType() == EventType.SendPacket) return;
        if (checkServer.getValue() && packet.getEventType() == EventType.RecievePacket) return;

        if (C01PacketChatMessage.isC01PacketChatMessage(packet.getPacket())) {
            C01PacketChatMessage packet1 = new C01PacketChatMessage(packet.getPacket().getWrapObject());
            ClientUtil.printChat("收到消息:" + packet1.getMessage());
        }
        if (checkPacket.getValue()) {
            ClientUtil.printChat("processed " + packet.getPacket().getWrapObject().getClass().getName());
        }

    }

}
