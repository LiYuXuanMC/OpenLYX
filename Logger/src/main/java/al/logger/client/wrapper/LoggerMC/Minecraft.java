package al.logger.client.wrapper.LoggerMC;

import al.logger.client.wrapper.IWrapper;

import al.logger.client.wrapper.LoggerMC.resource.IReloadableResourceManager;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.gui.GuiIngame;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;
import al.logger.client.wrapper.LoggerMC.multiplayer.PlayerControllerMP;
import al.logger.client.wrapper.LoggerMC.multiplayer.ServerData;
import al.logger.client.wrapper.LoggerMC.network.NetHandlerPlayClient;
import al.logger.client.wrapper.LoggerMC.render.*;
import al.logger.client.wrapper.LoggerMC.shader.Framebuffer;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.Session;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.Minecraft", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.Minecraft", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Minecraft extends IWrapper {

    @ClassInstance
    public static Class<?> MinecraftClass;
    @WrapField(mcpName = "theMinecraft", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "instance", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field theMinecraft;
    @WrapField(mcpName = "framebufferMc", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "framebuffer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field framebufferMc;
    @WrapMethod(mcpName = "getMinecraft", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getMinecraft", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getMinecraft;
    @WrapField(mcpName = "timer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "timer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field timer;
    @WrapMethod(mcpName = "displayGuiScreen", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "displayGuiScreen", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method displayGuiScreen;
    @WrapField(mcpName = "theWorld", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "world",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field theWorld;
    @WrapField(mcpName = "thePlayer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "player",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field thePlayer;
    @WrapField(mcpName = "currentScreen",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "currentScreen",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field currentScreen;
    @WrapField(mcpName = "ingameGUI",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "ingameGUI",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field ingameGUI;
    @WrapField(mcpName = "serverName",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "serverName",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field serverName;
    @WrapField(mcpName = "serverPort",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "serverPort",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field serverPort;
    @WrapField(mcpName = "gameSettings",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "gameSettings",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field gameSettings;
    @WrapField(mcpName = "playerController",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "playerController",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field playerController;
    @WrapField(mcpName = "leftClickCounter",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "leftClickCounter",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field leftClickCounter;
    @WrapMethod(mcpName = "runTick",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "runTick",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method runTick;
    @WrapMethod(mcpName = "dispatchKeypresses",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "dispatchKeypresses",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method dispatchKeypresses;
    @WrapField(mcpName = "debugFPS",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "debugFPS",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field debugFPS;
    @WrapField(mcpName = "renderManager",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderManager",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderManager;
    @WrapField(mcpName = "renderEngine",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderEngine",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderEngine;
    @WrapField(mcpName = "displayHeight",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "displayHeight",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field displayHeight;
    @WrapField(mcpName = "session",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "session",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field session;
    @WrapMethod(mcpName = "setIngameNotInFocus",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setIngameNotInFocus",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setIngameNotInFocus;
    @WrapMethod(mcpName = "runGameLoop",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "runGameLoop",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method runGameLoop;


    @WrapMethod(mcpName = "getNetHandler",signature = "()Lnet/minecraft/client/network/NetHandlerPlayClient;",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getConnection",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method getNetHandler;
    @WrapField(mcpName = "currentServerData",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "currentServerData",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field currentServerData;
    @WrapField(mcpName = "objectMouseOver",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "objectMouseOver",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field objectMouseOver;
    @WrapField(mcpName = "fontRendererObj",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "fontRenderer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field fontRendererObj;
    @WrapField(mcpName = "pointedEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "pointedEntity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field pointedEntity;
    @WrapField(mcpName = "renderViewEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderViewEntity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderViewEntity;
    @WrapMethod(mcpName = "loadWorld",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "loadWorld",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method loadWorld;
    @WrapField(mcpName = "entityRenderer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "entityRenderer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field entityRenderer;
    @WrapField(mcpName = "renderGlobal", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderGlobal", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderGlobal;
    @WrapMethod(mcpName = "addScheduledTask",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Ljava/lang/Runnable;)Lcom/google/common/util/concurrent/ListenableFuture;")
    @WrapMethod(mcpName = "addScheduledTask",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Ljava/lang/Runnable;)Lcom/google/common/util/concurrent/ListenableFuture;")
    public static Method addScheduledTask;
    @WrapField(mcpName = "displayWidth", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "displayWidth", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field displayWidth;


    @WrapMethod(mcpName = "getRenderViewEntity", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getRenderViewEntity", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getRenderViewEntity;
    @WrapField(mcpName = "mcProfiler", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "mcProfiler", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field mcProfiler;
    @WrapField(mcpName = "rightClickDelayTimer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "rightClickDelayTimer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field rightClickDelayTimer;
    @WrapMethod(mcpName = "isSingleplayer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isSingleplayer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isSingleplayer;
    @WrapField(mcpName = "renderItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderItem", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderItem;
    @WrapMethod(mcpName = "getItemRenderer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getItemRenderer;

    @WrapField(mcpName = "mcResourceManager", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field mcResourceManager;
    public Minecraft(Object obj) {
        super(obj);
    }
    public static int getDebugFPS() {
        return (int) getStatic(debugFPS);
    }
    public EntityRenderer getEntityRenderer(){
        return new EntityRenderer(getField(entityRenderer));
    }
    public static Minecraft getMinecraft(){
        return new Minecraft(getStatic(theMinecraft));
    }

    public void displayGuiScreen(GuiScreen screen){
        ReflectUtil.invoke(displayGuiScreen,getWrappedObject(),screen.getWrappedObject());
    }

    public IReloadableResourceManager getResourceManager(){
        return new IReloadableResourceManager(getField(mcResourceManager));
    }
    public Object addScheduledTask(Runnable runnable) {
        return invoke(addScheduledTask,runnable);
    }
    public RenderGlobal getRenderGlobal(){
        return new RenderGlobal(getField(renderGlobal));
    }
    public void displayGuiScreenBypass(GuiScreen screen){
        ReflectUtil.setField(currentScreen,screen.getWrappedObject(),getWrappedObject());
        ReflectUtil.invoke(setIngameNotInFocus, getWrappedObject());
        ScaledResolution scaledresolution = new ScaledResolution(this);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        getCurrentScreen().setMc(this);
        getCurrentScreen().setHeight(j);
        getCurrentScreen().setWidth(i);
        getCurrentScreen().initGui();
    }

    public ItemRenderer getItemRenderer(){
        return new ItemRenderer(invoke(getItemRenderer));
    }
    public Framebuffer getFramebuffer() {
        return new Framebuffer(getField(framebufferMc));
    }

    public void setPointedEntity(Entity entity) {
        setField(pointedEntity, entity.getWrappedObject());
    }

    public WorldClient getTheWorld() {
        return new WorldClient(getField(theWorld));
    }

    public void setTheWorld(WorldClient w) {
        setField(theWorld, w.getWrappedObject());
    }

    public EntityPlayerSP getThePlayer() {
        return new EntityPlayerSP(getField(thePlayer));
    }

    public void dispatchKeypresses() {
        invoke(dispatchKeypresses);
    }

    public GuiScreen getCurrentScreen() {
        return new GuiScreen(getField(currentScreen));
    }

    public GuiIngame getIngameGUI() {
        return new GuiIngame(getField(ingameGUI));
    }

    public String getServerName() {
        return (String) getField(serverName);
    }
    public int getServerPort() {
        return (int) getField(serverPort);
    }
    public GameSettings getGameSettings() {
        return new GameSettings(getField(gameSettings));
    }
    public PlayerControllerMP getPlayerController() {
        return new PlayerControllerMP(getField(playerController));
    }
    public Timer getTimer() {
        return new Timer(getField(timer));
    }
    public int getLeftClickCounter() {
        return (int) getField(leftClickCounter);
    }
    public void setLeftClickCounter(int leftClickCounter_) {
        setField(leftClickCounter,leftClickCounter_);
    }


    public RenderManager getRenderManager(){
        return new RenderManager(getField(renderManager));
    }
    public TextureManager getTextureManager(){
        return new TextureManager(getField(renderEngine));
    }
    public int getDisplayHeight(){
        return (int) getField(displayHeight);
    }
    public int getDisplayWidth() {
        return (int) getField(displayWidth);
    }
    public Session getSession(){
        return new Session(getField(session));
    }
    public void setSession(Session session_){
        setField(session,session_.getWrappedObject());
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
        setField(objectMouseOver,mop.getWrappedObject());
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
