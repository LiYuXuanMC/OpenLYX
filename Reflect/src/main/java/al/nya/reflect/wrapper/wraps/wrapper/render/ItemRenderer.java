package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumHand;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.ItemRenderer", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.ItemRenderer", targetMap = Maps.Srg1_12_2)
public class ItemRenderer extends IWrapper {
    @WrapMethod(mcpName = "resetEquippedProgress", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "resetEquippedProgress", targetMap = Maps.Srg1_12_2)
    public static Method resetEquippedProgress;

    public ItemRenderer(Object obj) {
        super(obj);
    }

    public void resetEquippedProgress() {
        invoke(resetEquippedProgress);
    }

    public void resetEquippedProgress(EnumHand enumHand) {
        invoke(resetEquippedProgress, enumHand);
    }
}
