package com.reflectmc.reflect.wrapper.wrappers.minecraft;

import com.reflectmc.reflect.wrapper.annotation.*;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.GuiIngame;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.GuiScreen;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.multiplayer.PlayerControllerMP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.multiplayer.ServerData;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.NetHandlerPlayClient;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.EntityRenderer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.FontRenderer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.RenderManager;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.TextureManager;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.world.WorldClient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.Minecraft",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
public class Minecraft extends WrapperBase {
    @ClassInstance
    public static Class<?> MinecraftClass;
    @WrapField(deobfName = "theMinecraft", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "instance", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field theMinecraft;
    @WrapMethod(deobfName = "displayGuiScreen", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "displayGuiScreen", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method displayGuiScreen;
    @WrapField(deobfName = "fontRendererObj",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field fontRendererObj;
    @WrapMethod(deobfName = "addScheduledTask",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Ljava/lang/Runnable;)Lcom/google/common/util/concurrent/ListenableFuture;")
    @WrapMethod(deobfName = "addScheduledTask",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Ljava/lang/Runnable;)Lcom/google/common/util/concurrent/ListenableFuture;")
    public static Method addScheduledTask;
    @WrapField(deobfName = "theWorld", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "world",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field theWorld;
    @WrapField(deobfName = "thePlayer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "player",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field thePlayer;
    @WrapMethod(deobfName = "runTick",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "runTick",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method runTick;
    @WrapMethod(deobfName = "dispatchKeypresses",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "dispatchKeypresses",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method dispatchKeypresses;
    @WrapMethod(deobfName = "runGameLoop",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "runGameLoop",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method runGameLoop;
    @WrapField(deobfName = "ingameGUI",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "ingameGUI",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field ingameGUI;
    @WrapField(deobfName = "debugFPS",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "debugFPS",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field debugFPS;
    @WrapField(deobfName = "displayHeight",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "displayHeight",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field displayHeight;
    @WrapField(deobfName = "renderEngine",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "renderEngine",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field renderEngine;
    @WrapField(deobfName = "currentScreen",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "currentScreen",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field currentScreen;
    @WrapField(deobfName = "gameSettings",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "gameSettings",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field gameSettings;
    @WrapField(deobfName = "currentServerData",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "currentServerData",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field currentServerData;
    @WrapMethod(deobfName = "getNetHandler",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()Lnet/minecraft/client/network/NetHandlerPlayClient;")
    @WrapMethod(deobfName = "getConnection",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @CactusWrapping
    public static Method getNetHandler;
    @WrapField(deobfName = "renderManager",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "renderManager",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field renderManager;
    @WrapField(deobfName = "timer", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "timer", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field timer;
    @WrapField(deobfName = "playerController",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "playerController",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field playerController;
    @WrapField(deobfName = "leftClickCounter",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "leftClickCounter",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field leftClickCounter;
    @WrapField(deobfName = "objectMouseOver",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "objectMouseOver",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field objectMouseOver;
    @WrapField(deobfName = "entityRenderer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "entityRenderer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field entityRenderer;
    @WrapField(deobfName = "rightClickDelayTimer", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "rightClickDelayTimer", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field rightClickDelayTimer;

    public Minecraft(Object wrap) {
        super(wrap);
    }

    public static Minecraft getMinecraft(){
        return new Minecraft(getStaticField(theMinecraft));
    }
    public FontRenderer getFontRenderer(){
        return new FontRenderer(getField(fontRendererObj));
    }
    public Object addScheduledTask(Runnable runnable) {
        return invokeMethod(addScheduledTask,runnable);
    }
    public WorldClient getTheWorld() {
        return new WorldClient(getField(theWorld));
    }
    public EntityPlayerSP getThePlayer() {
        return new EntityPlayerSP(getField(thePlayer));
    }
    public GuiIngame getIngameGUI() {
        return new GuiIngame(getField(ingameGUI));
    }
    public static int getDebugFPS(){
        return (int) getStaticField(debugFPS);
    }
    public int getDisplayHeight(){
        return (int) getField(displayHeight);
    }
    public void displayGuiScreen(GuiScreen screen){
        invokeMethod(displayGuiScreen,screen.getWrappedObject());
    }
    public TextureManager getTextureManager(){
        return new TextureManager(getField(renderEngine));
    }
    public GuiScreen getCurrentScreen(){
        return new GuiScreen(getField(currentScreen));
    }
    public GameSettings getGameSettings(){
        return new GameSettings(getField(gameSettings));
    }
    public ServerData getCurrentServerData(){
        return new ServerData(getField(currentServerData));
    }
    public NetHandlerPlayClient getNetHandler(){
        return new NetHandlerPlayClient(invokeMethod(getNetHandler));
    }
    public RenderManager getRenderManager(){
        return new RenderManager(getField(renderManager));
    }
    public Timer getTimer(){
        return new Timer(getField(timer));
    }
    public PlayerControllerMP getPlayerController(){
        return new PlayerControllerMP(getField(playerController));
    }
    public void setLeftClickCounter(int i){
        setField(leftClickCounter,i);
    }
    public EntityRenderer getEntityRenderer(){
        return new EntityRenderer(getField(entityRenderer));
    }
    public void setRightClickDelayTimer(int i){
        setField(rightClickDelayTimer,i);
    }
}
