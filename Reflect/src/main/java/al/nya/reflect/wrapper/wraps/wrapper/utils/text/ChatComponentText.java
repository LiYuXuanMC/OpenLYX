package al.nya.reflect.wrapper.wraps.wrapper.utils.text;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.util.ChatComponentText",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.text.TextComponentString",targetMap = Maps.Srg1_12_2)
public class ChatComponentText extends TextComponentBase {
    @WrapClass(mcpName = "net.minecraft.util.ChatComponentText",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.util.text.TextComponentString",targetMap = Maps.Srg1_12_2)
    public static Class ChatComponentTextClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {String.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {String.class})
    public static Constructor ChatComponentText_S;
    private ChatComponentText(Object obj) {
        super(obj);
    }
    public ChatComponentText(String text){
        super(ReflectUtil.construction(ChatComponentText_S,text));
    }
    public static ChatComponentText wrap(Object obj){
        return new ChatComponentText(obj);
    }
}
