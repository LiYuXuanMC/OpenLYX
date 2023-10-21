package com.reflectmc.reflect.wrapper.wrappers.minecraft;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.CustomWrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.client.settings.GameSettings",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.settings.GameSettings",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class GameSettings extends WrapperBase {
    @ClassInstance
    public static Class<?> GameSettingsClass;
    @WrapField(deobfName = "keyBindForward",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindForward",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindForward;
    @WrapField(deobfName = "keyBindLeft",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindLeft",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindLeft;
    @WrapField(deobfName = "keyBindBack",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindBack",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindBack;
    @WrapField(deobfName = "keyBindRight",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindRight",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindRight;
    @WrapField(deobfName = "keyBindJump",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindJump",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindJump;
    @WrapField(deobfName = "keyBindSneak",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindSneak",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindSneak;
    @WrapField(deobfName = "keyBindSprint",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindSprint",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindSprint;
    @WrapField(deobfName = "keyBindInventory",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindInventory",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindInventory;
    @WrapField(deobfName = "keyBindUseItem",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindUseItem",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindUseItem;
    @WrapField(deobfName = "keyBindDrop",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindDrop",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindDrop;
    @WrapField(deobfName = "keyBindAttack",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindAttack",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindAttack;
    @WrapField(deobfName = "keyBindPickBlock",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindPickBlock",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindPickBlock;
    @WrapField(deobfName = "keyBindChat",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindChat",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindChat;
    @WrapField(deobfName = "keyBindPlayerList",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindPlayerList",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindPlayerList;
    @WrapField(deobfName = "keyBindCommand",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindCommand",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindCommand;
    @WrapField(deobfName = "keyBindScreenshot",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindScreenshot",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindScreenshot;
    @WrapField(deobfName = "keyBindTogglePerspective",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindTogglePerspective",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindTogglePerspective;
    @WrapField(deobfName = "keyBindSmoothCamera",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindSmoothCamera",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindSmoothCamera;
    @WrapField(deobfName = "keyBindFullscreen",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindFullscreen",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindFullscreen;
    @WrapField(deobfName = "keyBindSpectatorOutlines",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindSpectatorOutlines",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindSpectatorOutlines;
    @WrapField(deobfName = "keyBindsHotbar",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindsHotbar",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindsHotbar;
    @WrapField(deobfName = "keyBindings",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyBindings",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyBindings;
    @WrapField(deobfName = "mouseSensitivity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "mouseSensitivity",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field mouseSensitivity;
    @WrapField(deobfName = "gammaSetting",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "gammaSetting",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field gammaSetting;
    @WrapField(deobfName = "chatLinks",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "chatLinks",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field chatLinks;
    @WrapField(deobfName = "forceUnicodeFont",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "forceUnicodeFont",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field forceUnicodeFont;
    @WrapField(deobfName = "viewBobbing", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "viewBobbing", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field viewBobbing;
    @WrapField(deobfName = "thirdPersonView", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "thirdPersonView", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field thirdPersonView;
    @CustomWrapField(customName = "ofFastRender", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @CustomWrapField(customName = "ofFastRender", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field ofFastRender;
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
}
