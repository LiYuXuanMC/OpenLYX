package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;

@WrapperClass(mcpName = "net.minecraft.util.EnumWorldBlockLayer", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.BlockRenderLayer", targetMap = Maps.Srg1_12_2)
public class EnumWorldBlockLayer extends EnumWrapper {
    @WrapClass(mcpName = "net.minecraft.util.EnumWorldBlockLayer", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.util.BlockRenderLayer", targetMap = Maps.Srg1_12_2)
    public static Class EnumWorldBlockLayerClass;
    @WrapEnum(mcpName = "SOLID", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "SOLID", targetMap = Maps.Srg1_12_2)
    public static Enum SOLID;
    @WrapEnum(mcpName = "CUTOUT_MIPPED", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "CUTOUT_MIPPED", targetMap = Maps.Srg1_12_2)
    public static Enum CUTOUT_MIPPED;
    @WrapEnum(mcpName = "CUTOUT", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "CUTOUT", targetMap = Maps.Srg1_12_2)
    public static Enum CUTOUT;
    @WrapEnum(mcpName = "TRANSLUCENT", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "TRANSLUCENT", targetMap = Maps.Srg1_12_2)
    public static Enum TRANSLUCENT;
    public EnumWorldBlockLayer(Object obj) {
        super(obj);
    }
}
