package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;
import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.util.EnumChatFormatting",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.text.TextFormatting",targetMap = Maps.Srg1_12_2)
public class EnumChatFormatting extends EnumWrapper {
    @WrapMethod(mcpName = "getTextWithoutFormattingCodes",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getTextWithoutFormattingCodes",targetMap = Maps.Srg1_12_2)
    public static Method getTextWithoutFormattingCodes;
    @WrapMethod(mcpName = "getColorIndex", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getColorIndex", targetMap = Maps.Srg1_12_2)
    public static Method getColorIndex;
    public EnumChatFormatting(Object obj) {
        super(obj);
    }
    public static String getTextWithoutFormattingCodes(String text){
        return (String) ReflectUtil.invoke(getTextWithoutFormattingCodes,text);
    }
    public static int getColorIndex(Enum enumChatFormatting){
        return (int) ReflectUtil.invoke(getColorIndex,enumChatFormatting);
    }
}
