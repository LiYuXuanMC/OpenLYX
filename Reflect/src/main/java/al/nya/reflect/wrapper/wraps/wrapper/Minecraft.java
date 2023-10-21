package al.nya.reflect.wrapper.wraps.wrapper;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiIngame;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.PlayerControllerMP;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.ServerData;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.render.*;
import al.nya.reflect.wrapper.wraps.wrapper.shader.Framebuffer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Session;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.Minecraft", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.Minecraft", targetMap = Maps.Srg1_12_2)
public class Minecraft extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.Minecraft", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.Minecraft", targetMap = Maps.Srg1_12_2)
    public static Class<?> MinecraftClass;
    @WrapField(mcpName = "theMinecraft", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "instance", targetMap = Maps.Srg1_12_2)
    public static Field theMinecraft;
    @WrapField(mcpName = "framebufferMc", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "framebuffer", targetMap = Maps.Srg1_12_2)
    public static Field framebufferMc;
    @WrapMethod(mcpName = "getMinecraft", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getMinecraft", targetMap = Maps.Srg1_12_2)
    public static Method getMinecraft;
    @WrapField(mcpName = "timer", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "timer", targetMap = Maps.Srg1_12_2)
    public static Field timer;
    @WrapMethod(mcpName = "displayGuiScreen", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "displayGuiScreen", targetMap = Maps.Srg1_12_2)
    public static Method displayGuiScreen;
    @WrapField(mcpName = "theWorld", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "world",targetMap = Maps.Srg1_12_2)
    public static Field theWorld;
    @WrapField(mcpName = "thePlayer",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "player",targetMap = Maps.Srg1_12_2)
    public static Field thePlayer;
    @WrapField(mcpName = "currentScreen",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "currentScreen",targetMap = Maps.Srg1_12_2)
    public static Field currentScreen;
    @WrapField(mcpName = "ingameGUI",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "ingameGUI",targetMap = Maps.Srg1_12_2)
    public static Field ingameGUI;
    @WrapField(mcpName = "serverName",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "serverName",targetMap = Maps.Srg1_12_2)
    public static Field serverName;
    @WrapField(mcpName = "serverPort",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "serverPort",targetMap = Maps.Srg1_12_2)
    public static Field serverPort;
    @WrapField(mcpName = "gameSettings",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "gameSettings",targetMap = Maps.Srg1_12_2)
    public static Field gameSettings;
    @WrapField(mcpName = "playerController",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "playerController",targetMap = Maps.Srg1_12_2)
    public static Field playerController;
    @WrapField(mcpName = "leftClickCounter",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "leftClickCounter",targetMap = Maps.Srg1_12_2)
    public static Field leftClickCounter;
    @WrapMethod(mcpName = "runTick",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "runTick",targetMap = Maps.Srg1_12_2)
    public static Method runTick;
    @WrapMethod(mcpName = "dispatchKeypresses",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "dispatchKeypresses",targetMap = Maps.Srg1_12_2)
    public static Method dispatchKeypresses;
    @WrapField(mcpName = "debugFPS",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "debugFPS",targetMap = Maps.Srg1_12_2)
    public static Field debugFPS;
    @WrapField(mcpName = "renderManager",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderManager",targetMap = Maps.Srg1_12_2)
    public static Field renderManager;
    @WrapField(mcpName = "renderEngine",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderEngine",targetMap = Maps.Srg1_12_2)
    public static Field renderEngine;
    @WrapField(mcpName = "displayHeight",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "displayHeight",targetMap = Maps.Srg1_12_2)
    public static Field displayHeight;
    @WrapField(mcpName = "session",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "session",targetMap = Maps.Srg1_12_2)
    public static Field session;
    @WrapMethod(mcpName = "setIngameNotInFocus",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setIngameNotInFocus",targetMap = Maps.Srg1_12_2)
    public static Method setIngameNotInFocus;
    @WrapMethod(mcpName = "runGameLoop",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "runGameLoop",targetMap = Maps.Srg1_12_2)
    public static Method runGameLoop;
    @WrapMethod(mcpName = "getNetHandler",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getConnection",targetMap = Maps.Srg1_12_2)
    public static Method getNetHandler;
    @WrapField(mcpName = "currentServerData",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "currentServerData",targetMap = Maps.Srg1_12_2)
    public static Field currentServerData;
    @WrapField(mcpName = "objectMouseOver",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "objectMouseOver",targetMap = Maps.Srg1_12_2)
    public static Field objectMouseOver;
    @WrapField(mcpName = "fontRendererObj",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "fontRenderer",targetMap = Maps.Srg1_12_2)
    public static Field fontRendererObj;
    @WrapField(mcpName = "pointedEntity",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "pointedEntity",targetMap = Maps.Srg1_12_2)
    public static Field pointedEntity;
    @WrapField(mcpName = "renderViewEntity",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderViewEntity",targetMap = Maps.Srg1_12_2)
    public static Field renderViewEntity;
    @WrapMethod(mcpName = "loadWorld",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "loadWorld",targetMap = Maps.Srg1_12_2)
    public static Method loadWorld;
    @WrapField(mcpName = "entityRenderer",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "entityRenderer",targetMap = Maps.Srg1_12_2)
    public static Field entityRenderer;
    @WrapField(mcpName = "renderGlobal", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderGlobal", targetMap = Maps.Srg1_12_2)
    public static Field renderGlobal;
    @WrapMethod(mcpName = "addScheduledTask",targetMap = Maps.Srg1_12_2,signature = "(Ljava/lang/Runnable;)Lcom/google/common/util/concurrent/ListenableFuture;")
    @WrapMethod(mcpName = "addScheduledTask",targetMap = Maps.Srg1_8_9,signature = "(Ljava/lang/Runnable;)Lcom/google/common/util/concurrent/ListenableFuture;")
    public static Method addScheduledTask;
    @WrapField(mcpName = "displayWidth", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "displayWidth", targetMap = Maps.Srg1_12_2)
    public static Field displayWidth;
    @WrapMethod(mcpName = "getRenderViewEntity", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getRenderViewEntity", targetMap = Maps.Srg1_12_2)
    public static Method getRenderViewEntity;
    @WrapField(mcpName = "mcProfiler", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "mcProfiler", targetMap = Maps.Srg1_12_2)
    public static Field mcProfiler;
    @WrapField(mcpName = "rightClickDelayTimer", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "rightClickDelayTimer", targetMap = Maps.Srg1_12_2)
    public static Field rightClickDelayTimer;
    @WrapMethod(mcpName = "isSingleplayer", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isSingleplayer", targetMap = Maps.Srg1_12_2)
    public static Method isSingleplayer;
    @WrapField(mcpName = "renderItem", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderItem", targetMap = Maps.Srg1_12_2)
    public static Field renderItem;
    public Minecraft(Object obj) {
        super(obj);
    }
    public static int getDebugFPS() {
        return (int) ReflectUtil.getField(debugFPS,null);
    }
    public EntityRenderer getEntityRenderer(){
        return new EntityRenderer(getField(entityRenderer));
    }
    public static Minecraft getMinecraft(){
        return new Minecraft(ReflectUtil.getField(theMinecraft,null));
    }
    public void displayGuiScreen(GuiScreen screen){
        ReflectUtil.invoke(displayGuiScreen,getWrapObject(),screen.getWrapObject());
    }
    public Object addScheduledTask(Runnable runnable) {
        return invoke(addScheduledTask,runnable);
    }
    public RenderGlobal getRenderGlobal(){
        return new RenderGlobal(getField(renderGlobal));
    }
    public void displayGuiScreenBypass(GuiScreen screen){
        ReflectUtil.setField(currentScreen,screen.getWrapObject(),getWrapObject());
        ReflectUtil.invoke(setIngameNotInFocus, getWrapObject());
        ScaledResolution scaledresolution = new ScaledResolution(this);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        getCurrentScreen().setMc(this);
        getCurrentScreen().setHeight(j);
        getCurrentScreen().setWidth(i);
        getCurrentScreen().initGui();
    }

    public Framebuffer getFramebuffer() {
        return new Framebuffer(getField(framebufferMc));
    }

    public void setPointedEntity(Entity entity) {
        setField(pointedEntity, entity.getWrapObject());
    }

    public WorldClient getTheWorld() {
        return new WorldClient(ReflectUtil.getField(theWorld, getWrapObject()));
    }

    public void setTheWorld(WorldClient w) {
        setField(theWorld, w.getWrapObject());
    }

    public EntityPlayerSP getThePlayer() {
        return new EntityPlayerSP(ReflectUtil.getField(thePlayer, getWrapObject()));
    }

    public void dispatchKeypresses() {
        invoke(dispatchKeypresses);
    }

    public GuiScreen getCurrentScreen() {
        return new GuiScreen(ReflectUtil.getField(currentScreen, getWrapObject()));
    }

    public GuiIngame getIngameGUI() {
        return new GuiIngame(ReflectUtil.getField(ingameGUI, getWrapObject()));
    }

    public String getServerName() {
        return (String) ReflectUtil.getField(serverName,getWrapObject());
    }
    public int getServerPort() {
        return (int) ReflectUtil.getField(serverPort,getWrapObject());
    }
    public GameSettings getGameSettings() {
        return new GameSettings(ReflectUtil.getField(gameSettings,getWrapObject()));
    }
    public PlayerControllerMP getPlayerController() {
        return new PlayerControllerMP(ReflectUtil.getField(playerController,getWrapObject()));
    }
    public Timer getTimer() {
        return new Timer(ReflectUtil.getField(timer,getWrapObject()));
    }
    public int getLeftClickCounter() {
        return (int) ReflectUtil.getField(leftClickCounter,getWrapObject());
    }
    public void setLeftClickCounter(int leftClickCounter_) {
        ReflectUtil.setField(leftClickCounter,leftClickCounter_,getWrapObject());
    }
    public RenderManager getRenderManager(){
        return new RenderManager(ReflectUtil.getField(renderManager,getWrapObject()));
    }
    public TextureManager getTextureManager(){
        return new TextureManager(ReflectUtil.getField(renderEngine,getWrapObject()));
    }
    public int getDisplayHeight(){
        return (int) ReflectUtil.getField(displayHeight,getWrapObject());
    }
    public int getDisplayWidth() {
        return (int) getField(displayWidth);
    }
    public Session getSession(){
        return new Session(ReflectUtil.getField(session,getWrapObject()));
    }
    public NetHandlerPlayClient getNetHandler(){
        return new NetHandlerPlayClient(invoke(getNetHandler));
    }
    public ServerData getCurrentServerData(){
        return new ServerData(getField(currentServerData));
    }
    public MovingObjectPosition getObjectMouseOver() {
        return new MovingObjectPosition(getField(objectMouseOver));
    }
    public FontRenderer getFontRenderer(){
        return new FontRenderer(getField(fontRendererObj));
    }
    public void setObjectMouseOver(MovingObjectPosition mop){
        setField(objectMouseOver,mop.getWrapObject());
    }
    public Entity getRenderViewEntity(){
        return new Entity(getField(renderViewEntity));
    }
    public Profiler getMcProfiler(){
        return new Profiler(getField(mcProfiler));
    }
    public int getRightClickDelayTimer(){
        return (int) getField(rightClickDelayTimer);
    }
    public void setRightClickDelayTimer(int i){
        setField(rightClickDelayTimer,i);
    }
    public boolean isSingleplayer(){
        return (boolean) invoke(isSingleplayer);
    }
    public RenderItem getRenderItem(){
        return new RenderItem(getField(renderItem));
    }
}
