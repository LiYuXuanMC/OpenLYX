package al.nya.reflect.wrapper.wraps.wrapper;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapObject;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.client.settings.GameSettings",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.settings.GameSettings",targetMap = Maps.Srg1_12_2)
public class GameSettings extends IWrapper{
    @WrapField(mcpName = "keyBindForward",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindForward",targetMap = Maps.Srg1_12_2)
    public static Field keyBindForward;
    @WrapField(mcpName = "keyBindLeft",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindLeft",targetMap = Maps.Srg1_12_2)
    public static Field keyBindLeft;
    @WrapField(mcpName = "keyBindBack",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindBack",targetMap = Maps.Srg1_12_2)
    public static Field keyBindBack;
    @WrapField(mcpName = "keyBindRight",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindRight",targetMap = Maps.Srg1_12_2)
    public static Field keyBindRight;
    @WrapField(mcpName = "keyBindJump",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindJump",targetMap = Maps.Srg1_12_2)
    public static Field keyBindJump;
    @WrapField(mcpName = "keyBindSneak",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindSneak",targetMap = Maps.Srg1_12_2)
    public static Field keyBindSneak;
    @WrapField(mcpName = "keyBindSprint",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindSprint",targetMap = Maps.Srg1_12_2)
    public static Field keyBindSprint;
    @WrapField(mcpName = "keyBindInventory",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindInventory",targetMap = Maps.Srg1_12_2)
    public static Field keyBindInventory;
    @WrapField(mcpName = "keyBindUseItem",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindUseItem",targetMap = Maps.Srg1_12_2)
    public static Field keyBindUseItem;
    @WrapField(mcpName = "keyBindDrop",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindDrop",targetMap = Maps.Srg1_12_2)
    public static Field keyBindDrop;
    @WrapField(mcpName = "keyBindAttack",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindAttack",targetMap = Maps.Srg1_12_2)
    public static Field keyBindAttack;
    @WrapField(mcpName = "keyBindPickBlock",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindPickBlock",targetMap = Maps.Srg1_12_2)
    public static Field keyBindPickBlock;
    @WrapField(mcpName = "keyBindChat",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindChat",targetMap = Maps.Srg1_12_2)
    public static Field keyBindChat;
    @WrapField(mcpName = "keyBindPlayerList",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindPlayerList",targetMap = Maps.Srg1_12_2)
    public static Field keyBindPlayerList;
    @WrapField(mcpName = "keyBindCommand",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindCommand",targetMap = Maps.Srg1_12_2)
    public static Field keyBindCommand;
    @WrapField(mcpName = "keyBindScreenshot",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindScreenshot",targetMap = Maps.Srg1_12_2)
    public static Field keyBindScreenshot;
    @WrapField(mcpName = "keyBindTogglePerspective",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindTogglePerspective",targetMap = Maps.Srg1_12_2)
    public static Field keyBindTogglePerspective;
    @WrapField(mcpName = "keyBindSmoothCamera",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindSmoothCamera",targetMap = Maps.Srg1_12_2)
    public static Field keyBindSmoothCamera;
    @WrapField(mcpName = "keyBindFullscreen",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindFullscreen",targetMap = Maps.Srg1_12_2)
    public static Field keyBindFullscreen;
    @WrapField(mcpName = "keyBindSpectatorOutlines",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindSpectatorOutlines",targetMap = Maps.Srg1_12_2)
    public static Field keyBindSpectatorOutlines;
    @WrapField(mcpName = "keyBindsHotbar",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindsHotbar",targetMap = Maps.Srg1_12_2)
    public static Field keyBindsHotbar;
    @WrapField(mcpName = "keyBindings",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "keyBindings",targetMap = Maps.Srg1_12_2)
    public static Field keyBindings;
    @WrapField(mcpName = "mouseSensitivity",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "mouseSensitivity",targetMap = Maps.Srg1_12_2)
    public static Field mouseSensitivity;
    @WrapField(mcpName = "gammaSetting",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "gammaSetting",targetMap = Maps.Srg1_12_2)
    public static Field gammaSetting;
    @WrapField(mcpName = "chatLinks",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "chatLinks",targetMap = Maps.Srg1_12_2)
    public static Field chatLinks;
    @WrapField(mcpName = "forceUnicodeFont",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "forceUnicodeFont",targetMap = Maps.Srg1_12_2)
    public static Field forceUnicodeFont;
    @WrapField(mcpName = "viewBobbing", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "viewBobbing", targetMap = Maps.Srg1_12_2)
    public static Field viewBobbing;
    @WrapField(mcpName = "thirdPersonView", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "thirdPersonView", targetMap = Maps.Srg1_12_2)
    public static Field thirdPersonView;
    @WrapField(mcpName = "ofFastRender", targetMap = Maps.Srg1_8_9, customSrgName = "ofFastRender")
    @WrapField(mcpName = "ofFastRender", targetMap = Maps.Srg1_12_2, customSrgName = "ofFastRender")
    public static Field ofFastRender;
    public GameSettings(Object obj) {
        super(obj);
    }
    public KeyBinding getKeyBindAttack() {
        return new KeyBinding(ReflectUtil.getField(keyBindAttack,getWrapObject()));
    }
    public KeyBinding getKeyBindUseItem(){
        return new KeyBinding(ReflectUtil.getField(keyBindUseItem,getWrapObject()));
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
