package al.nya.reflect.events;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.events.*;
import al.nya.reflect.events.events.world.EventBlockRenderSide;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.command.CommandManager;
import al.nya.reflect.features.modules.Ghost.Reach;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Movement.KeepSprint;
import al.nya.reflect.features.modules.Movement.NoSlow;
import al.nya.reflect.features.modules.Visual.AntiScreenshot;
import al.nya.reflect.features.modules.Visual.HUD;
import al.nya.reflect.features.modules.Visual.InputFix;
import al.nya.reflect.features.modules.Visual.XRay;
import al.nya.reflect.features.modules.Visual.hud.HUDObject;
import al.nya.reflect.features.modules.World.disablers.MargelesAntiCheatDisabler;
import al.nya.reflect.gui.GuiCommandReplace;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.key.KeyBoard;
import al.nya.reflect.utils.LWJGLKeyBoard;
import al.nya.reflect.utils.render.AnimationUtils;
import al.nya.reflect.wrapper.bridge.BridgeUtil;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.impl.GuiScreenImpl;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiChat;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.render.entity.RenderLivingEntity;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ChatStyle;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumWorldBlockLayer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.HoverEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;
import al.nya.reflect.wrapper.wraps.wrapper.world.IBlockAccess;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import java.awt.image.BufferedImage;
import java.util.*;

public class EventBus {
    private final Map<Class<? extends Event>, List<EventListener>> listeners = new HashMap<Class<? extends Event>, List<EventListener>>();
    public static List<Object> noEventPackets = new ArrayList<Object>();
    public boolean init = false;
    public static EventPreUpdate eventPreUpdate;
    public static GuiScreenImpl guiScreen;

    public EventBus() {

    }

    public void addEvents(Class... events) {
        for (Class event : events) {
            listeners.computeIfAbsent(event, k -> new ArrayList<EventListener>());
        }
    }

    public static boolean showScoreboard() {
        return HUD.scoreboardHUD.enable.getValue();
    }

    public static void onGuiChatDrawScreen(int n1, int n2, float f1) {
        for (HUDObject object : HUD.hudObjects) {
            object.doDrag(n1, n2);
        }
    }

    public static void onGuiChatMouseClicked(int n1, int n2, int n3) {
        for (HUDObject object : HUD.hudObjects) {
            object.mouseClick(n1, n2, n3);
        }
    }

    public static boolean pushOutOfBlocks() {
        EventPushBlock pushBlock = new EventPushBlock();
        Reflect.Instance.eventBus.callEvent(pushBlock);
        return pushBlock.isCancel();
    }

    public static void handleComponentHover(Object object1, Object object2, int i1, int i2) {
        if (object2 == null) return;
        IChatComponent component = new IChatComponent(object2);
        if (component.getChatStyle().getChatClickEvent().isNull()) return;
        final ChatStyle chatStyle = component.getChatStyle();

        final ClickEvent clickEvent = chatStyle.getChatClickEvent();
        final HoverEvent hoverEvent = chatStyle.getChatHoverEvent();

        GuiScreen screen = new GuiScreen(object1);

        screen.drawHoveringText(Collections.singletonList("§c§l" + clickEvent.getAction().name().toUpperCase() + ": §a" + clickEvent.getValue()), i1, i2 - (hoverEvent != null ? 17 : 0));
    }

    public static boolean hookFunction_shouldSideBeRendered(Object Block_block, Object IBlockAccess_worldIn, Object BlockPos_pos, Enum EnumFacing_side,
                                                            double maxX, double maxY, double maxZ, double minX, double minY, double minZ) {
        Block block = new Block(Block_block);
        IBlockAccess blockAccess = new IBlockAccess(IBlockAccess_worldIn);
        BlockPos pos = new BlockPos(BlockPos_pos);
        Enum<?> side = EnumFacing_side;
        EventBlockRenderSide event = new EventBlockRenderSide(block, blockAccess, pos, side, maxX, maxY, maxZ, minX, minY, minZ);
        Reflect.Instance.eventBus.callEvent(event);
        if (XRay.isEnable) {
            if (!XRay.cave.getValue()) {
                for (Integer integer : XRay.antiXRayBlocks) {
                    if (Block.getIdFromBlock(block) == integer) return true;
//                    if (block.equals(Block.getBlockById(integer))) continue;
//                    ClientUtil.printChat("Render: " + block.getWrapObject().getClass().getSimpleName());
                }
            } else return event.isShouldRender();
        }
        return false;
    }
    public static boolean getXRayState(){
        return ModuleManager.getModule(XRay.class).isEnable();
    }
    public static Object hookFunction_getBlockLayer(Object Block_block){
        return ModuleManager.getModule(XRay.class).isEnable() ? (XRay.getXrayBlocks().contains(Block_block) ? EnumWorldBlockLayer.SOLID : EnumWorldBlockLayer.TRANSLUCENT) : EnumWorldBlockLayer.SOLID;
    }
    public static Object TRANSLUCENT(){
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
    public static boolean hookFunction_putColorMultiplier(){
        return ModuleManager.getModule(XRay.class).isEnable() && XRay.translucent.getValue();
    }
    public static int getXRayColor(int i){
        return ModuleManager.getModule(XRay.class).isEnable() ? XRay.getColor(i) : i;
    }
    public static void loadWorld(Object world){
        Reflect.Instance.eventBus.callEvent(new EventWorldLoad(new World(world)));
    }
    public static void windowClick(int windowId, int slotId, int mouseButtonClicked, int mode, Object playerIn){
        Reflect.Instance.eventBus.callEvent(new EventWindowClick(windowId,slotId,mouseButtonClicked,mode));
    }
    public static void jump(){
        Reflect.Instance.eventBus.callEvent(new EventJump());
    }
    public static float getReachRange(){
        return Reach.getRange();
    }
    public static float getBlockReach(){
        return Reach.getBlockRange();
    }
    public static EventText onRenderString(String text, float x, float y, int color, boolean dropShadow){
        EventText eventText = new EventText(text,x,y,color,dropShadow,true);
        Reflect.Instance.eventBus.callEvent(eventText);
        return eventText;
    }
    public static EventText onGetStringWidth(String text){
        EventText eventText = new EventText(text,0,0,0,false,false);
        Reflect.Instance.eventBus.callEvent(eventText);
        return eventText;
    }
    public static boolean recievePacketEvent(Object o){
        EventPacket ep = new EventPacket(EventType.RecievePacket,new Packet(o));
        Reflect.Instance.eventBus.callEvent(ep);
        return ep.isCancel();
    }
    public static boolean canKeepSprint(){
        return !ModuleManager.getModule(KeepSprint.class).isEnable();
    }
    public static boolean sendPacketEvent(Object o){
        if (!noEventPackets.isEmpty()){
            boolean cancel = false;
            for (Object noEventPacket : noEventPackets) {
                if (noEventPacket.equals(o)){
                    cancel = true;
                    break;
                }
            }
            noEventPackets.remove(o);
            if (cancel) return false;
        }
        EventPacket ep = new EventPacket(EventType.SendPacket,new Packet(o));
        Reflect.Instance.eventBus.callEvent(ep);
        return ep.isCancel();
    }
    public static boolean onMessage(String s){
        return CommandManager.getInstance().onMessage(s);
    }
    public static void onScreenShotAntiCheatPre(){
        AntiScreenshot.onScreenShotAntiCheatPre();
    }
    public static BufferedImage onScreenShotAntiCheatPost(BufferedImage bufferedImage){
        return AntiScreenshot.onScreenShotAntiCheatPost(bufferedImage);
    }
    public static boolean guiChatKeyTyped(int keyCode){
        if (keyCode == 28 || keyCode == 156){
            GuiChat guiChat = new GuiChat(Minecraft.getMinecraft().getCurrentScreen().getWrapObject());
            return CommandManager.getInstance().onMessage(guiChat.getInputField().getText().trim());
        }
        return true;
    }
    public static boolean canReach(){
        return ModuleManager.getModule(Reach.class) == null && !ModuleManager.getModule(Reach.class).isEnable();
    }
    public static boolean onPreRenderLiving(Object Entity,Object Renderer, double X,double Y,double Z,float Yaw,float PartialTicks) {
        if (!Reflect.Instance.eventBus.init) return false;
        EventPreRenderLiving event = new EventPreRenderLiving(new EntityLivingBase(Entity), new RenderLivingEntity(Renderer), X, Y, Z, Yaw, PartialTicks);
        Reflect.Instance.eventBus.callEvent(event);
        return event.isCancel();
    }

    public static void onPostRenderLiving(Object Entity, Object Renderer, double X, double Y, double Z, float Yaw, float PartialTicks) {
        if (!Reflect.Instance.eventBus.init) return;
        EventPostRenderLiving event = new EventPostRenderLiving(new EntityLivingBase(Entity), new RenderLivingEntity(Renderer), X, Y, Z, Yaw, PartialTicks);
        Reflect.Instance.eventBus.callEvent(event);
    }

    public static boolean isInputFix() {
        return ModuleManager.getModule(InputFix.class).isEnable();
    }

    public static void inputFix(Object screen) {
        final char c0 = Keyboard.getEventCharacter();
        if ((Keyboard.getEventKey() == 0 && c0 >= ' ') || Keyboard.getEventKeyState()) {
            new GuiScreen(screen).keyTyped(c0, Keyboard.getEventKey());
        }
        Minecraft.getMinecraft().dispatchKeypresses();
    }

    public static boolean preUpdate() {
        if (!Reflect.Instance.eventBus.init) return false;
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP p = mc.getThePlayer();
        eventPreUpdate = new EventPreUpdate(p.getRotationYaw(), p.getRotationPitch(), p.getPosX(), p.getPosY(), p.getPosZ(), p.isOnGround());
        Reflect.Instance.eventBus.callEvent(eventPreUpdate);
        if (eventPreUpdate.isCancel()) return true;
        p.setOnGround(eventPreUpdate.isOnGround());
        p.setRotationYaw(eventPreUpdate.getYaw());
        p.setRotationPitch(eventPreUpdate.getPitch());
        p.setPosition(eventPreUpdate.getX(),eventPreUpdate.getY(),eventPreUpdate.getZ());
        if (ModuleManager.getModuleByName("RotationAnimation").isEnable()){
            p.setRotationYawHead(eventPreUpdate.getYaw());
            p.setRenderYawOffset(eventPreUpdate.getYaw());
        }
        return false;
    }
    public static boolean isNoSlow(){
        return ModuleManager.getModule(NoSlow.class).isEnable();
    }
    public static void preStep(Object e){
        Entity ent = new Entity(e);
        if (!EntityPlayerSP.isEntityPlayerSP(ent)){return;}
        EventStep step = new EventStep(EventType.Pre,ent.getStepHeight());
        Reflect.Instance.eventBus.callEvent(step);
        ent.setStepHeight(step.getHeight());
    }

    public static void postStep(Object e) {
        Entity ent = new Entity(e);
        if (!EntityPlayerSP.isEntityPlayerSP(ent)) {
            return;
        }
        EventStep step = new EventStep(EventType.Post, ent.getStepHeight());
        Reflect.Instance.eventBus.callEvent(step);
        ent.setStepHeight(step.getHeight());
    }

    public static void asyncHandleHyCraftScreenshot(String key) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AntiScreenshot.onScreenShotAntiCheatPre();
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    Object INSTANCE = ReflectUtil.getField("cn.hycraft.core.module.impl.game.ScoreBoard", "INSTANCE", null);
                    ReflectUtil.callMethod("cn.hycraft.core.module.impl.game.ScoreBoard", "cacheImage", INSTANCE, key);
                });
            }
        }).start();
    }

    public static BufferedImage asyncEndHyCraftScreenshot(BufferedImage image) {
        return AntiScreenshot.onScreenShotAntiCheatPost(image);
    }

    public static void postUpdate() {
        if (!Reflect.Instance.eventBus.init) return;
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP p = mc.getThePlayer();
        Reflect.Instance.eventBus.callEvent(new EventPostUpdate(p.getRotationYaw(), p.getRotationPitch()));
        p.setRotationYaw(eventPreUpdate.getYawOld());
        p.setRotationPitch(eventPreUpdate.getPitchOld());
        p.setOnGround(eventPreUpdate.isGroundOld());
    }

    public static float getAttackReach() {
        return Reach.getAttackRange();
    }
    public static void replaceGuiChat(){
        Minecraft.getMinecraft().displayGuiScreenBypass(BridgeUtil.createGuiChat(new GuiCommandReplace()));
    }
    //Only Margeles anti cheat
    public static boolean getChatOpen(){
        return BridgeUtil.GuiChatReplace.isInstance(Minecraft.getMinecraft().getCurrentScreen().getWrapObject());
    }
    public static EventMove move(Object ent,double x,double y,double z){
        Entity entity = new Entity(ent);
        EventMove move = new EventMove(x,y,z);
        if (EntityPlayerSP.isEntityPlayerSP(entity)){
            Reflect.Instance.eventBus.callEvent(move);
            return move;
        }
        return move;
    }
    public static boolean macPacket(Object unwrappedPacket,boolean isRead){
        if (isRead){
            return recievePacketEvent(unwrappedPacket);
        }else {
            return sendPacketEvent(unwrappedPacket);
        }
    }
    public static void attack(Object player, Object entity){
        Reflect.Instance.eventBus.callEvent(new EventAttack(new EntityPlayer(player),new Entity(entity)));
    }
    public static boolean macProcessSendPacket(Object packet){
        return MargelesAntiCheatDisabler.macProcessSendPacket(new Packet(packet));
    }
    public static boolean macProcessRevPacket(Object packet){
        return MargelesAntiCheatDisabler.macProcessRevPacket(new Packet(packet));
    }
    public static boolean onCloseChannel(Object reasonObj) {
        return MargelesAntiCheatDisabler.onCloseChannel(new IChatComponent(reasonObj));
    }
    public static boolean getMouseOverHook(float partialTick){
        return MargelesAntiCheatDisabler.getMouseOverHook(partialTick);
    }
    public static boolean macUpdate(){
        return MargelesAntiCheatDisabler.macUpdate();
    }
    private long lastFrame;
    public void callEvent(Event event){
        if (event == null){
            return;
        }
        if (!init)
            return;
        if (event instanceof EventRender && AntiScreenshot.isGetingSS) {
            //Block renderEvent
            return;
        }
        if (event instanceof EventRender2D) {
            final long currentTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();
            final double deltaTime = (int) (currentTime - lastFrame);
            lastFrame = currentTime;
            AnimationUtils.delta = deltaTime;
            HUD.visibleWaterMark();
        }
        if (event != null){
            if (event instanceof EventKey && Objects.isNull(Minecraft.getMinecraft().getCurrentScreen().getWrapObject())
                    && LWJGLKeyBoard.getEventKeyState()){
                KeyBoard.onKey(EnumKey.getKey(((EventKey) event).getKey()));
            }
            listeners.forEach((E,L) -> {
                if (E == event.getClass()){
                    L.forEach(EL -> {
                        if (EL.getFrom().isEnable()){
                            EL.getReceive().receive(event);
                        }
                    });
                }
            });
        }
    }
    public void registerListener(EventListener eventListener){
        listeners.forEach((E,L) ->{
            if (E == eventListener.getRequireEvent()){
                L.add(eventListener);
            }
        });
    }
    public boolean isRegisteredEvent(Class<?> clazz){
        for (Class<? extends Event> aClass : listeners.keySet()) {
            if (aClass == clazz)return true;
        }
        return false;
    }
    public static boolean printChatMessageWithOptionalDeletion(Object chatComponent){
        EventChat ec = new EventChat(new IChatComponent(chatComponent));
        Reflect.Instance.eventBus.callEvent(ec);
        return !ec.isCancel();
    }
}