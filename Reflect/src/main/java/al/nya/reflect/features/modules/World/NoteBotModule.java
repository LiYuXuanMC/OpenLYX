package al.nya.reflect.features.modules.World;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.*;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.notebot.Note;
import al.nya.reflect.features.modules.World.notebot.NotePlayer;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.utils.math.TimerUtil;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.entity.PlayerCapabilities;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.PlayerControllerMP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07PacketPlayerDigging;
import al.nya.reflect.wrapper.wraps.wrapper.network.S24PacketBlockAction;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;
import lombok.Getter;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class NoteBotModule extends Module {
    @Getter
    private final NotePlayer notePlayer = new NotePlayer();
    @Getter
    private final NoteReceiver receiver = new NoteReceiver();
    private final TimerUtil discoverTimer = new TimerUtil();
    private final TimerUtil tuneTimer = new TimerUtil();
    private final TimerUtil stateTimer = new TimerUtil();
    public ModeValue state = new ModeValue("State", BotState.IDLE, BotState.values());
    public ModeValue mode = new ModeValue("Mode", Mode.NORMAL, Mode.values());
    public OptionValue rotate = new OptionValue("Rotate", false);

    private final int[] positionOffsets = new int[]{2, 1, 2};
    public OptionValue swing = new OptionValue("Swing", false);
    public DoubleValue discoverDelay = new DoubleValue("Discover Delay", 1000D, 0D, 50D, "0");
    public DoubleValue tuneDelay = new DoubleValue("Tune Delay", 1000D, 0D, 200D, "0");
    private BlockPos currentBlock;

    public NoteBotModule() {
        super("NoteBot", "音符盒机器人", ModuleType.World);
        addValues(state, mode, rotate, swing, discoverDelay, tuneDelay);
    }

    private final List<BlockPos> blocks = new ArrayList<>();
    private final List<BlockPos> tunedBlocks = new ArrayList<>();
    private final Map<BlockPos, Note> discoveredBlocks = new HashMap<>();

    private final int BLOCK_AREA = 25;

    @Override
    public void onEnable() {
        super.onEnable();
        if (mc.getTheWorld().isNull()) return;

        IntStream.range(0, BLOCK_AREA).forEach(note -> {
            int[] area = this.blockArea(note);
            this.blocks.add(new BlockPos(area[0], area[1], area[2]));
        });
        if (this.mode.getValue() == Mode.NORMAL) {
            this.state.setValue(BotState.DISCOVERING);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (mc.getTheWorld().isNull()) return;
        this.clearData();
    }

    @Override
    public String getSuffix() {
        return state.getValue().name();
    }

    @EventTarget
    public void onWorldLoad(EventWorldLoad event) {
        if (!event.getWorld().isNull()) {
            this.clearData();
            this.setEnableNoNotification(false); // toggle off
            NotificationPublisher.queue("NoteBot", "You've loaded into a new world.", NotificationType.INFO);
        }
    }

    @EventTarget
    public void onPacket(EventPacket event) {
        if (event.getEventType() == EventType.SendPacket) return;
        if (!S24PacketBlockAction.isS24PacketBlockAction(event.getPacket())) return;
        S24PacketBlockAction packet = new S24PacketBlockAction(event.getPacket().getWrapObject());
        BlockPos position = packet.getBlockPosition();
        this.blocks.stream().filter(blockPos -> this.correctPosition(position, this.blocks.indexOf(blockPos))).forEach(blockPos -> {
            final Note note = new Note(this.blocks.indexOf(blockPos), position, packet.getData1(), packet.getData2());
            this.discoveredBlocks.put(blockPos, note);

            if (!this.tunedBlocks.contains(blockPos) && this.blocks.indexOf(blockPos) == packet.getData2()) {
                this.tunedBlocks.add(blockPos);
            }

            if (!(this.mode.getValue() == Mode.DEBUG) && !(this.state.getValue() == BotState.PLAYING)) {
                if (!(this.state.getValue() == BotState.TUNING)) {
                    if (this.discoveredBlocks.size() != BLOCK_AREA) {
                        this.stateTimer.reset();
                    } else if (this.tunedBlocks.size() == BLOCK_AREA) {
                        this.state.setValue(BotState.IDLE);
                    }
                }
            }
        });
    }

    @EventTarget
    public void onPlayerPreUpdate(EventPreUpdate e) {
        onPlayerUpdate(e);
    }

    @EventTarget
    public void onPlayerPostUpdate(EventPostUpdate e) {
        onPlayerUpdate(e);
    }

    private void onPlayerUpdate(Object e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient theWorld = mc.getTheWorld();
        PlayerControllerMP controller = mc.getPlayerController();
        PlayerCapabilities capabilities = thePlayer.getCapabilities();
        if (thePlayer.isNull() || theWorld.isNull()) return;

        if (capabilities.isCreativeMode()) {
            return;
        }

        if (e instanceof EventPreUpdate) {
            if (this.mode.getValue() == Mode.NORMAL) {
                if (this.state.getValue() == BotState.TUNING) {
                    if (this.discoveredBlocks.size() == BLOCK_AREA && this.tunedBlocks.size() == BLOCK_AREA) {
                        this.state.setValue(BotState.IDLE);
                    }
                }
            }

            if (this.currentBlock == null && !(this.state.getValue() == BotState.PLAYING)) {
                this.blocks.stream().filter(blockPos -> (!this.discoveredBlocks.containsKey(blockPos) || !this.tunedBlocks.contains(blockPos))).forEach(blockPos -> {
                    final BlockPos workPos = new BlockPos(this.getPosition(this.blocks.indexOf(blockPos)).getWrapObject());
                    if (!this.discoveredBlocks.containsKey(blockPos)) {
                        if (!(this.mode.getValue() == Mode.DEBUG)) {
                            this.state.setValue(BotState.DISCOVERING);
                        }
                        this.setCurrentNoteBlock(workPos, e);
                    } else if (!this.tunedBlocks.contains(workPos)) {
                        if (!(this.mode.getValue() == Mode.DEBUG)) {
                            if (this.stateTimer.hasPassed(1000)) {
                                this.state.setValue(BotState.TUNING);
                                this.setCurrentNoteBlock(workPos, e);
                            }
                        } else {
                            this.setCurrentNoteBlock(workPos, e);
                        }
                    }
                });
            }
        } else if (e instanceof EventPostUpdate) {

            final Enum<?> direction = EnumFacing.UP;

            if (this.state.getValue() == BotState.IDLE) {
            } else if (this.state.getValue() == BotState.DISCOVERING) {
                if (this.discoveredBlocks.size() != BLOCK_AREA) {
                    if (this.discoverTimer.hasPassed(this.discoverDelay.getValue().longValue())) {
                        thePlayer.getSendQueue().addToSendQueue(new C07PacketPlayerDigging(C07Action.START_DESTROY_BLOCK, this.currentBlock, direction));
                        thePlayer.getSendQueue().addToSendQueue(new C07PacketPlayerDigging(C07Action.ABORT_DESTROY_BLOCK, this.currentBlock, direction));
                        if (this.swing.getValue()) {
                            thePlayer.swingItem();
                        }
                        this.discoveredBlocks.put(this.currentBlock, new Note(this.blocks.indexOf(this.currentBlock), this.currentBlock, 0, this.blocks.indexOf(this.currentBlock)));
                        this.currentBlock = null;
                        this.discoverTimer.reset();
                    }
                }
            } else if (this.state.getValue() == BotState.TUNING) {
                if (!this.mode.getValue().equals(Mode.DEBUG)) {
                    if (this.discoveredBlocks.size() != BLOCK_AREA) {
                        this.state.setValue(BotState.DISCOVERING);
                        return;
                    }
                }
                if (this.tunedBlocks.size() != BLOCK_AREA && !this.tunedBlocks.contains(this.currentBlock)) {
                    if (this.tuneTimer.hasPassed(this.tuneDelay.getValue().longValue())) {
//                            controller.processRightClickBlock(thePlayer, theWorld, this.currentBlock, direction, new Vec3(0.5F, 0.5F, 0.5F), EnumHand.MAIN_HAND);
                        controller.processRightClickBlock(thePlayer, theWorld, thePlayer.getCurrentEquippedItem(), this.currentBlock, direction, new Vec3(0.5F, 0.5F, 0.5F));
                        if (this.swing.getValue()) {
                            thePlayer.swingItem();
                        }
                        this.currentBlock = null;
                        this.tuneTimer.reset();
                    }
                } else {
                    this.currentBlock = null;
                }
            } else if (this.state.getValue() == BotState.PLAYING) {
                if (this.currentBlock != null) {
                    thePlayer.getSendQueue().addToSendQueue(new C07PacketPlayerDigging(C07Action.START_DESTROY_BLOCK, this.currentBlock, direction));
                    thePlayer.getSendQueue().addToSendQueue(new C07PacketPlayerDigging(C07Action.ABORT_DESTROY_BLOCK, this.currentBlock, direction));
                    if (this.swing.getValue()) {
                        thePlayer.swingItem();
                    }
                    this.currentBlock = null;
                }
            }

        }


    }

    @EventTarget
    public void onRender3D(EventRender3D e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient theWorld = mc.getTheWorld();
        if (!thePlayer.isNull() && !theWorld.isNull() && !mc.getRenderViewEntity().isNull()) {
            RenderUtil.begin3D();
            for (int note = 0; note < BLOCK_AREA; note++) {
                int[] area = this.blockArea(note);
                final BlockPos pos = new BlockPos(area[0], area[1], area[2]);

                float[] color = new float[]{128.0f, 128.0f, 128.0f};

                if (this.tunedBlocks.contains(pos)) {
                    float mappedColor = (float) MathUtil.map(note, 0.0D, BLOCK_AREA, 0.0D, 255.0D);
                    color = new float[]{255.0F - mappedColor, mappedColor, 0.0F};
                } else if (this.discoveredBlocks.containsKey(pos)) {
                    float mappedColor = (float) MathUtil.map(this.discoveredBlocks.get(pos).getPitch(), 0.0D, BLOCK_AREA, 0.0D, 255.0D);
                    color = new float[]{255.0F - mappedColor, mappedColor, 0.0F};
                }

                final AxisAlignedBB bb = new AxisAlignedBB(
                        pos.getX() - mc.getRenderManager().getViewerPosX(), pos.getY() + 1.0f - mc.getRenderManager().getViewerPosY(), pos.getZ() - mc.getRenderManager().getViewerPosZ(),
                        pos.getX() + 1.0f - mc.getRenderManager().getViewerPosX(), pos.getY() + 1.0f - mc.getRenderManager().getViewerPosY(), pos.getZ() + 1.0f - mc.getRenderManager().getViewerPosZ());
                GlStateManager.color(color[0] / 255.0F, color[1] / 255.0F, color[2] / 255.0F, 0.2F);
                RenderUtil.drawFilledBox(bb);
                //GlStateManager.color(color[0] / 255.0F, color[1] / 255.0F, color[2] / 255.0F, 0.6F);
                //RenderUtil.drawBoundingBox(bb, 1.0f);
            }
            RenderUtil.end3D();
        }
    }

    private int[] blockArea(int index) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        int[] positions = {(int) Math.floor(thePlayer.getPosX()) - this.positionOffsets[0], (int) Math.floor(thePlayer.getPosY()) - this.positionOffsets[1], (int) Math.floor(thePlayer.getPosZ()) - this.positionOffsets[2]};
        return new int[]{positions[0] + index % 5, positions[1], positions[2] + index / 5};
    }

    private void lookAtPosition(BlockPos position, Event e) {
        final float[] angle = MathUtil.calcAngle(mc.getThePlayer().getPositionEyes(mc.getTimer().getRenderPartialTicks()), new Vec3(position.getX() + 0.5f, position.getY() + 0.5f, position.getZ() + 0.5f));
        if (e == null) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            thePlayer.setRotationYaw(angle[0]);
            thePlayer.setRotationPitch(angle[1]);
            return;
        }
        if (e instanceof EventPreUpdate) {
            ((EventPreUpdate) e).setPitch(angle[1]);
            ((EventPreUpdate) e).setYaw(angle[0]);
        } else if (e instanceof EventPostUpdate) {
            ((EventPostUpdate) e).setPitch(angle[0]);
            ((EventPostUpdate) e).setYaw(angle[1]);
        }

    }

    private BlockPos getPosition(int note) {
        int[] blocks = this.blockArea(note);
        return new BlockPos(blocks[0], blocks[1], blocks[2]);
    }

    private boolean correctPosition(BlockPos blockPos, int index) {
        int[] blocks = this.blockArea(index);
        return (blockPos.getX() == blocks[0] && blockPos
                .getY() == blocks[1] && blockPos
                .getZ() == blocks[2]);
    }

    private void setCurrentNoteBlock(BlockPos pos, Object e) {
        this.currentBlock = new BlockPos(pos.getWrapObject());
        if (this.rotate.getValue()) {
            this.lookAtPosition(pos, (Event) e);
        }
    }

    private void clearData() {
        this.currentBlock = null;
        this.discoveredBlocks.clear();
        if (!this.mode.getValue().equals(Mode.DEBUG)) { // is not debug, so let's wipe our previously tuned blocks data
            this.tunedBlocks.clear();
        }
        this.blocks.clear();
        this.notePlayer.end();
    }


    public enum BotState {
        IDLE, DISCOVERING, TUNING, PLAYING
    }

    public enum Mode {
        NORMAL, DEBUG
    }

    public class NoteReceiver implements Receiver {
        @Override
        public void send(MidiMessage midiMessage, long l) {
            if (midiMessage instanceof ShortMessage) {
                ShortMessage shortMessage = (ShortMessage) midiMessage;
                if (shortMessage.getCommand() == ShortMessage.NOTE_ON) {
                    int key = shortMessage.getData1() - 6;
                    int octave = (key / 12) - 1;
                    int note = key > 12 ? key % 24 : key % 12;
                    int velocity = shortMessage.getData2();
                    if (velocity > 0) {
                        NoteBotModule.this.setCurrentNoteBlock(new BlockPos(getPosition(note).getWrapObject()), null);
                        //mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, playPos, EnumFacing.UP));
                        //mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, playPos, EnumFacing.UP));
                    }
                }
            }
        }

        @Override
        public void close() {

        }
    }
}
