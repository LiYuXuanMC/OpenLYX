package al.logger.client.wrapper.LoggerMC;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapObject;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Field;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.client.settings.GameSettings",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.settings.GameSettings",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GameSettings extends IWrapper {
    @ClassInstance
    public static Class GameSettingsClass;
    @WrapField(mcpName = "keyBindForward",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindForward",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindForward;
    @WrapField(mcpName = "keyBindLeft",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindLeft",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindLeft;
    @WrapField(mcpName = "keyBindBack",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindBack",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindBack;
    @WrapField(mcpName = "keyBindRight",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindRight",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindRight;
    @WrapField(mcpName = "keyBindJump",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindJump",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindJump;
    @WrapField(mcpName = "keyBindSneak",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindSneak",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindSneak;
    @WrapField(mcpName = "keyBindSprint",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindSprint",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindSprint;
    @WrapField(mcpName = "keyBindInventory",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindInventory",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindInventory;
    @WrapField(mcpName = "keyBindUseItem",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindUseItem",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindUseItem;
    @WrapField(mcpName = "keyBindDrop",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindDrop",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindDrop;
    @WrapField(mcpName = "keyBindAttack",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindAttack",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindAttack;
    @WrapField(mcpName = "keyBindPickBlock",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindPickBlock",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindPickBlock;
    @WrapField(mcpName = "keyBindChat",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindChat",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindChat;
    @WrapField(mcpName = "keyBindPlayerList",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindPlayerList",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindPlayerList;
    @WrapField(mcpName = "keyBindCommand",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindCommand",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindCommand;
    @WrapField(mcpName = "keyBindScreenshot",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindScreenshot",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindScreenshot;
    @WrapField(mcpName = "keyBindTogglePerspective",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindTogglePerspective",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindTogglePerspective;
    @WrapField(mcpName = "keyBindSmoothCamera",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindSmoothCamera",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindSmoothCamera;
    @WrapField(mcpName = "keyBindFullscreen",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindFullscreen",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindFullscreen;
    @WrapField(mcpName = "keyBindSpectatorOutlines",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindSpectatorOutlines",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindSpectatorOutlines;
    @WrapField(mcpName = "keyBindsHotbar",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindsHotbar",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindsHotbar;
    @WrapField(mcpName = "keyBindings",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "keyBindings",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field keyBindings;
    @WrapField(mcpName = "mouseSensitivity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "mouseSensitivity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field mouseSensitivity;
    @WrapField(mcpName = "gammaSetting",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "gammaSetting",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field gammaSetting;
    @WrapField(mcpName = "chatLinks",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "chatLinks",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field chatLinks;
    @WrapField(mcpName = "forceUnicodeFont",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "forceUnicodeFont",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field forceUnicodeFont;
    @WrapField(mcpName = "viewBobbing", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "viewBobbing", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field viewBobbing;
    @WrapField(mcpName = "thirdPersonView", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "thirdPersonView", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field thirdPersonView;
    @WrapField(mcpName = "ofFastRender", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, customSrgName = "ofFastRender")
    @WrapField(mcpName = "ofFastRender", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, customSrgName = "ofFastRender")
    public static Field ofFastRender;
    @WrapField(mcpName = "debugCamEnable", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "debugCamEnable", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field debugCamEnable;
    @WrapField(mcpName = "renderDistanceChunks", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderDistanceChunks", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderDistanceChunks;
    @WrapField(mcpName = "fovSetting", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "fovSetting", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field fovSetting;
    public GameSettings(Object obj) {
        super(obj);
    }
    public KeyBinding getKeyBindAttack() {
        return new KeyBinding(getField(keyBindAttack));
    }
    public KeyBinding getKeyBindUseItem(){
        return new KeyBinding(getField(keyBindUseItem));
    }
    public float getMouseSensitivity(){
        return (float) getField(mouseSensitivity);
    }
    public KeyBinding getKeyBindSneak(){
        return new KeyBinding(getField(keyBindSneak));
    }
    public KeyBinding getKeyBindJump(){
        return new KeyBinding(getField(keyBindJump));
    }
    public KeyBinding getKeyBindForward(){
        return new KeyBinding(getField(keyBindForward));
    }
    public KeyBinding getKeyBindRight(){
        return new KeyBinding(getField(keyBindRight));
    }

    public KeyBinding getKeyBindLeft() {
        return new KeyBinding(getField(keyBindLeft));
    }

    public KeyBinding getKeyBindBack() {
        return new KeyBinding(getField(keyBindBack));
    }

    public KeyBinding getKeyBindSprint() {
        return new KeyBinding(getField(keyBindSprint));
    }

    public KeyBinding getkeyBindPlayerList() {
        return new KeyBinding(getField(keyBindPlayerList));
    }

    public void setGammaSetting(float v) {
        setField(gammaSetting, v);
    }

    public boolean getChatLinks() {
        return (boolean) getField(chatLinks);
    }

    public boolean getForceUnicodeFont() {
        return (boolean) getField(forceUnicodeFont);
    }

    public boolean getViewBobbing() {
        return (boolean) getField(viewBobbing);
    }

    public void setViewBobbing(boolean b) {
        setField(viewBobbing, b);
    }

    public int getThirdPersonView() {
        return (int) getField(thirdPersonView);
    }

    public void setThirdPersonView(int view) {
        setField(thirdPersonView, view);
    }

    public boolean isFastRendering() {
        if (ofFastRender == null) return false;
        return (boolean) getField(ofFastRender);
    }
    public boolean isDebugCamEnable() {
        return (boolean) getField(debugCamEnable);
    }
    public int getRenderDistanceChunks() {
        return (int) getField(renderDistanceChunks);
    }
    public float getFovSetting() {
        return (float) getField(fovSetting);
    }
}
