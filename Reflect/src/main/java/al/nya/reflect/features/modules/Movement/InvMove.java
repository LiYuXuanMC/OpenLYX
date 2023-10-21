package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventPostUpdate;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.events.events.EventWindowClick;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiChat;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiContainer;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0B.C0BAction;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0B.C0BPacketEntityAction;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16PacketClientStatus;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16State;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.network.S27PacketExplosion;
import al.nya.reflect.wrapper.wraps.wrapper.network.S2DPacketOpenWindow;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class InvMove extends Module {
    private OptionValue noDetectableValue = new OptionValue("NoDetectable", false);
    private ModeValue bypassValue = new ModeValue("Bypass", BypassMode.None, BypassMode.values());
    private OptionValue rotateValue = new OptionValue("Rotate", true);
    private OptionValue noMoveClicksValue = new OptionValue("NoMoveClicks", false);
    private ModeValue noSprintValue = new ModeValue("NoSprint", NoSprint.None, NoSprint.values());
    private boolean lastInvOpen = false;
    private boolean invOpen = false;
    private List<C03PacketPlayer> blinkPacketList = new ArrayList<>();

    public InvMove() {
        super("InvMove", ModuleType.Movement);
        addValues(noDetectableValue, bypassValue, rotateValue, noMoveClicksValue, noSprintValue);
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

    @EventTarget
    public void onPre(final EventPreUpdate event){
        updateKeyStats();
    }

    @EventTarget
    public void onPost(final EventPostUpdate event) {
        updateKeyStats();
    }

    @EventTarget
    public void onWorldLoad(final EventWorldLoad event) {
        blinkPacketList.clear();
        invOpen = false;
        lastInvOpen = false;
    }

    @EventTarget
    public void onPacket(final EventPacket event) {
        Packet packet = event.getPacket();
        lastInvOpen = invOpen;
        if (S2DPacketOpenWindow.isS2DPacketOpenWindow(packet) || (C16PacketClientStatus.isC16PacketClientStatus(packet)
                && new C16PacketClientStatus(packet.getWrapObject()).getStatus() == C16State.OPEN_INVENTORY_ACHIEVEMENT)) {
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


     @EventTarget
    public void onClick(final EventWindowClick event){
        if (noMoveClicksValue.getValue() && MovementUtils.isMoving()) {
            event.setCancel(true);
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
