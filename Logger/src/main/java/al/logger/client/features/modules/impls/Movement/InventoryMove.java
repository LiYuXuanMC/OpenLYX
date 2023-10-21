package al.logger.client.features.modules.impls.Movement;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventWindowClick;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.event.client.player.EventPostUpdate;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.GameSettings;
import al.logger.client.wrapper.LoggerMC.KeyBinding;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.gui.GuiChat;
import al.logger.client.wrapper.LoggerMC.gui.GuiContainer;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BAction;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BPacketEntityAction;
import al.logger.client.wrapper.LoggerMC.network.client.C16.C16PacketClientStatus;
import al.logger.client.wrapper.LoggerMC.network.client.C16.C16State;
import al.logger.client.wrapper.LoggerMC.network.server.S2DPacketOpenWindow;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class InventoryMove extends Module {
    private OptionValue noDetectableValue = new OptionValue("NoDetectable", false);
    private ModeValue bypassValue = new ModeValue("Bypass", BypassMode.None, BypassMode.values());
    private OptionValue rotateValue = new OptionValue("Rotate", true);
    private ModeValue noSprintValue = new ModeValue("NoSprint", NoSprint.None, NoSprint.values());
    private boolean lastInvOpen = false;
    private boolean invOpen = false;
    private List<C03PacketPlayer> blinkPacketList = new ArrayList<>();

    public InventoryMove() {
        super("InventoryMove", Category.Movement);
        addValues(noDetectableValue, bypassValue, rotateValue, noSprintValue);
    }



    private void updateKeyStats() {
        GuiScreen currentScreen = mc.getCurrentScreen();
        if (!currentScreen.isNull() && !GuiChat.isGuiChat(currentScreen) && (!noDetectableValue.getValue() || !GuiContainer.isGuiContainer(currentScreen))) {
            GameSettings gameSetting = mc.getGameSettings();
            gameSetting.getKeyBindForward().setPressed(Keyboard.isKeyDown(gameSetting.getKeyBindForward().getKeyCode()));
            gameSetting.getKeyBindBack().setPressed(Keyboard.isKeyDown(gameSetting.getKeyBindBack().getKeyCode()));
            gameSetting.getKeyBindRight().setPressed(Keyboard.isKeyDown(gameSetting.getKeyBindRight().getKeyCode()));
            gameSetting.getKeyBindLeft().setPressed(Keyboard.isKeyDown(gameSetting.getKeyBindLeft().getKeyCode()));
            gameSetting.getKeyBindJump().setPressed(Keyboard.isKeyDown(gameSetting.getKeyBindJump().getKeyCode()));
            gameSetting.getKeyBindSprint().setPressed(Keyboard.isKeyDown(gameSetting.getKeyBindSprint().getKeyCode()));

            if (rotateValue.getValue()) {
                EntityPlayerSP thePlayer = this.mc.getThePlayer();
                float rotationPitch = thePlayer.getRotationPitch();
                if (Keyboard.isKeyDown(Keyboard.KEY_UP) ) {
                    if (rotationPitch > -90) {
                        thePlayer.setRotationPitch(rotationPitch - 5);
                    }
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    if (rotationPitch < 90) {
                        thePlayer.setRotationPitch(rotationPitch+5);
                    }
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    thePlayer.setRotationYaw(thePlayer.getRotationYaw() -5);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    thePlayer.setRotationYaw(thePlayer.getRotationYaw() +5);
                }
            }
        }
    }

    @Listener
    public void onPre(final EventPreUpdate event){
        updateKeyStats();
    }

    @Listener
    public void onPost(final EventPostUpdate event) {
        updateKeyStats();
    }

    @Listener
    public void onWorldLoad(final EventLoadWorld event) {
        blinkPacketList.clear();
        invOpen = false;
        lastInvOpen = false;
    }

    @Listener
    public void onPacket(final EventPacket event) {
        Packet packet = new Packet(event.getPacket().getWrappedObject());
        lastInvOpen = invOpen;
        if (S2DPacketOpenWindow.isS2DPacketOpenWindow(packet) || (C16PacketClientStatus.isC16PacketClientStatus(packet)
                && new C16PacketClientStatus(packet.getWrappedObject()).getStatus() == C16State.OPEN_INVENTORY_ACHIEVEMENT)) {
            invOpen = true;
            if (noSprintValue.getValue() == NoSprint.PacketSpoof) {
                if (this.mc.getThePlayer().isSprinting()) {
                    this.mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.getThePlayer(), C0BAction.STOP_SPRINTING));
                }
                if (this.mc.getThePlayer().isSneaking()) {
                    this.mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.getThePlayer(),C0BAction.STOP_SNEAKING));
                }
            }
        }
    }





    public enum BypassMode {
        NoOpenPacket,
        Blink,
        None
    }

    public enum NoSprint {
        Real,
        PacketSpoof,
        None
    }
}
