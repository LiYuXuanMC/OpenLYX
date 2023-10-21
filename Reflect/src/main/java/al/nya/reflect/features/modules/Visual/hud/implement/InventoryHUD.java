package al.nya.reflect.features.modules.Visual.hud.implement;

import al.nya.reflect.events.events.client.EventShader;
import al.nya.reflect.features.modules.Visual.hud.HUDObject;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RoundedUtil;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.entity.InventoryPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderHelper;

import java.awt.*;

public class InventoryHUD extends HUDObject {
    private int X = 0;
    private int Y = 0;
    private int endX = 0;
    private int endY = 0;
    private boolean editing;

    public InventoryHUD() {
        super(0, 150, 182, "Inventory");
        setSpecial(true);
    }

    @Override
    public void drawHUD(int x, int y, float p, boolean isEditinh) {
        X = x;
        Y = y;
        endX = (20 * 9) + 2;
        endY = (20 * 3) + 2;
        editing = isEditinh;
        EntityPlayerSP player = mc.getThePlayer();
        InventoryPlayer inventory = player.getInventory();
        int startX = x + 2;
        int startY = y + 3;
        int curIndex = 0;
        for (int i = 9; i < 36; ++i) {
            ItemStack slot = inventory.getMainInventory()[i];
            if (slot.isNull()) {
                startX += 20;
                curIndex += 1;

                if (curIndex > 8) {
                    curIndex = 0;
                    startY += 20;
                    startX = x + 2;
                }

                continue;
            }
            this.drawItemStack(slot, startX, startY);
            startX += 20;
            curIndex += 1;
            if (curIndex > 8) {
                curIndex = 0;
                startY += 20;
                startX = x + 2;
            }
        }
    }

    @Override
    public void onBlur(EventShader event) {
        float y = (isSpecial() || editing) ? Y - 18 : Y;
        float add = (isSpecial() || editing) ? 18 : 0;
        this.translate.interpolate(X, y, 0.2f);
        RoundedUtil.drawRoundOutline(translate.getX(), translate.getY(), endX, endY + add, 2, .5f, ColorUtils.applyOpacity(Color.WHITE, .85f), ColorUtils.applyOpacity(Color.WHITE, .85f));
        RoundedUtil.drawRound(translate.getX(), translate.getY(), endX, endY + add, 2, false, Color.WHITE);
    }

    private void drawItemStack(ItemStack stack, int x, int y) {
        GlStateManager.pushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableAlpha();
        GlStateManager.clear(256);
        mc.getRenderItem().setZLevel(-150.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        mc.getRenderItem().renderItemIntoGUI(stack, x, y);
        mc.getRenderItem().renderItemOverlayIntoGUI(mc.getFontRenderer(), stack, x, y, null);
        mc.getRenderItem().setZLevel(0.0F);
        GlStateManager.enableAlpha();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }
}