package al.logger.client.features.modules.impls.Player;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventKey;
import al.logger.client.event.client.EventMouse;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.render.EventScreen;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.wrapper.LoggerMC.gui.GuiChat;
import al.logger.client.wrapper.LoggerMC.gui.GuiContainer;
import al.logger.client.wrapper.LoggerMC.gui.GuiInventory;
import al.logger.client.wrapper.LoggerMC.network.client.C0DPacketCloseWindow;
import al.logger.client.wrapper.LoggerMC.network.server.S2EPacketCloseWindow;
import org.lwjgl.input.Keyboard;

public class KeepContainer extends Module {
    private GuiContainer container;

    boolean inv = false;

    public KeepContainer() {
        super("KeepContainer", Category.Player);
    }


    @Override
    public void onDisable() {
        if (container != null) {
            PacketUtil.sendPacket(new C0DPacketCloseWindow(container.getInventorySlots().getWindowId()));
        }
        container = null;
    }

    @Listener
    public void onWorldChange(EventLoadWorld e){
        container = null;
        inv = true;
    }
    @Listener
    public void onGui(EventScreen event) {
        //ChatUtils.message("onGui");
        if (GuiContainer.isGuiContainer(event.getScreen())) {
            //  ChatUtils.message("GuiContainer");
            if (GuiInventory.isGuiInventory(event.getScreen())) {
                inv = true;
            } else {
                inv = false;
                //  ChatUtils.message("Not Inv");
                container = new GuiContainer(event.getScreen().getWrappedObject());
                // ChatUtils.message("Save Screen " + container);
            }

        }

    }

    @Listener
    public void onKey(EventKey event) {
        if (event.getKey() == Keyboard.KEY_INSERT) {
          //  ChatUtils.message("onKey");
            if (container == null) {
                return;
            }
            mc.displayGuiScreen(container);
           // ChatUtils.message("DisPlayGuiScreen");
        }
/*
        if (Keyboard.isKeyDown(Keyboard.KEY_INSERT)) {
            if (container == null) {
                return;
            }
            mc.displayGuiScreen(container);
        }
 */
    }

    /*
    @Listener
    public void onMouseEvent(EventMouse event) {
        if (container != null) {
            event.setDown(false);
          //  ChatUtils.message("Cancel Mouse Event");
        }
    }

     */

    @Listener
    public void onPacket(EventPacket event) {
        if (C0DPacketCloseWindow.isC0DPacketCloseWindow(event.getPacket())) {
         //   ChatUtils.message("Cancel Close Window Packet");
            if (inv){
                return;
            }
            event.cancel();
        } else if (S2EPacketCloseWindow.isS2EPacketCloseWindow(event.getPacket())) {
            S2EPacketCloseWindow packetCloseWindow = new S2EPacketCloseWindow(event.getPacket().getWrappedObject());
            if (container != null && container.getInventorySlots() != null && packetCloseWindow.getWindowId() == container.getInventorySlots().getWindowId()) {
                container = null;
             //   ChatUtils.message("set container null");
            }
        }
    }


}
