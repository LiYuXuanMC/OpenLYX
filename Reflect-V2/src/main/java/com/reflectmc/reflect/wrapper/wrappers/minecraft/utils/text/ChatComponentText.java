package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.reflect.Constructor;

@WrapperClass(deobfName = "net.minecraft.util.ChatComponentText",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.text.TextComponentString",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ChatComponentText extends TextComponentBase {
    @ClassInstance
    public static Class ChatComponentTextClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {String.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {String.class})
    public static Constructor ChatComponentText_S;
    private ChatComponentText(Object obj) {
        super(obj);
    }
    public ChatComponentText(String text){
        super(construct(ChatComponentText_S,text));
    }
    public static ChatComponentText wrap(Object obj){
        return new ChatComponentText(obj);
    }
}
