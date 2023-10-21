package al.nya.reflect.features.modules.Visual.hud.implement;

import al.nya.reflect.events.events.client.EventShader;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Visual.hud.HUDObject;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RoundedUtil;
import al.nya.reflect.utils.render.font.FontManager;

import java.awt.*;
import java.util.ArrayList;

public class KeybindsHUD extends HUDObject {
    float startX = 0;
    float startY = 0;
    float endX = 108;
    float endY = 0;
    boolean editing = false;

    public KeybindsHUD() {
        super(150, 0, 115, "KeyBinds");
        setSpecial(true);
    }

    @Override
    public void drawHUD(int x, int y, float p, boolean isEditing) {
        startX = x;
        startY = y;
        int modules = 0;
        editing = isEditing;
        ArrayList<Module> bindM = new ArrayList<>();
        for (Module m : ModuleManager.getModules()) {
            if (m.getBinding().getKeyCode() != EnumKey.None.getKeyCode()) {
                bindM.add(m);
                modules++;
            }
        }
        endY = (FontManager.Verdana16.getHeight() + 2) * modules + FontManager.Verdana16.getHeight() + 8;
        int y2 = (int) (startY + 4 + 2);
        for (Module m : bindM) {
            FontManager.Verdana16.drawString(m.getName() + " (" + m.getBinding().getDisplayName() + ")", startX + 2, y2, -1);
            FontManager.Verdana16.drawString(m.isEnable() ? "[ON]" : "§7[OFF]", startX + 115 - FontManager.Verdana16.getStringWidth(m.isEnable() ? "[ON]" : "§7[OFF]") - 10, y2, -1);
            y2 += FontManager.Verdana16.getHeight() + 2;
        }
    }

    @Override
    public void onBlur(EventShader event) {
        float y = (isSpecial() || editing) ? startY - 18 : startY;
        float add = (isSpecial() || editing) ? 18 : 0;
        this.translate.interpolate(startX, y, 0.2f);
        RoundedUtil.drawRoundOutline(translate.getX(), translate.getY(), endX, endY + add, 2, .5f, ColorUtils.applyOpacity(Color.WHITE, .85f), ColorUtils.applyOpacity(Color.WHITE, .85f));
        RoundedUtil.drawRound(translate.getX(), translate.getY(), endX, endY + add, 2, false, Color.WHITE);
    }
}