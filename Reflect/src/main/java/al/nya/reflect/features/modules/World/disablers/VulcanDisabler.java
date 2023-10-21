package al.nya.reflect.features.modules.World.disablers;

import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventRender2D;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.timer.MSTimer;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0FPacketConfirmTransaction;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;

import java.util.LinkedList;

public class VulcanDisabler extends DisableSubModule{

    private int currentTrans = 0;
    private int vulTickCounterUID = 0;
    private LinkedList packetBuffer = new LinkedList<Packet>();
    private MSTimer lagTimer = new MSTimer();

    private String notification = "VulcanCombat Disabler §c§lONLY §r§awork when you rejoined the server!";
    private String working = "VulcanCombat Disabler §r§aworking!";

    private boolean reconnected = false;

    public VulcanDisabler() {
        super(DisablerMode.Vulcan);
    }

    @Override
    public void enable(){
        vulTickCounterUID = -25767;
        NotificationPublisher.queue("Vulcan Disable","Client will auto disconnect after 5s", 5000, NotificationType.INFO);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mc.getTheWorld().sendQuittingDisconnectingPacket();
            }
        }).start();
    }

    @Override
    public void update(EventUpdate update){
        NetworkManager networkManager = mc.getNetHandler().getNetworkManager();
        if(lagTimer.hasTimePassed(5000L) && packetBuffer.size() > 4) {
            lagTimer.reset();
            while (packetBuffer.size() > 4) {
                networkManager.sendPacketNoEvent((Packet) packetBuffer.poll());
            }
        }
    }
    @Override
    public void packet(EventPacket evt){
        Packet packet = evt.getPacket();
        if (C0FPacketConfirmTransaction.isPacketConfirmTransaction(packet)) {
            C0FPacketConfirmTransaction c0f = new C0FPacketConfirmTransaction(packet.getWrapObject());
            if (Math.abs((Math.abs((c0f.getUID())) - Math.abs(vulTickCounterUID))) <= 4) {
                vulTickCounterUID = c0f.getUID();
                packetBuffer.add(packet);
                evt.setCancel(true);
                ClientUtil.printChat(ClientUtil.Level.DEBUG,"C0F-PingTickCounter IN ${packetBuffer.size}");
            }else if (Math.abs((Math.abs((c0f.getUID())) - 25767)) <= 4) {
                vulTickCounterUID = (c0f.getUID());
                ClientUtil.printChat(ClientUtil.Level.DEBUG,"C0F-PingTickCounter RESETED");
            }
        }
    }
    @Override
    public void onWorld(EventWorldLoad worldLoad){
        currentTrans = 0;
        packetBuffer.clear();
        lagTimer.reset();
        vulTickCounterUID = -25767;
        reconnected = true;
    }
    @Override
    public void render2D(EventRender2D eventRender2D) {
        ScaledResolution sr = new ScaledResolution(mc);
        if (!reconnected){
            mc.getFontRenderer().drawStringWithShadow(notification,sr.getScaledWidth()/2 - mc.getFontRenderer().getStringWidth(notification)/2,(sr.getScaledHeight()/2)/2,0xFFFFFF);
        }else if (mc.getThePlayer().getTicksExisted() < 80){
            mc.getFontRenderer().drawStringWithShadow(working,sr.getScaledWidth()/2 - mc.getFontRenderer().getStringWidth(working)/2,(sr.getScaledHeight()/2)/2,0xFFFFFF);
        }
    }
}
