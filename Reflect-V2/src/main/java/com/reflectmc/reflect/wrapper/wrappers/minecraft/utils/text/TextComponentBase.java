package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text;

import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.util.text.TextComponentBase",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class TextComponentBase extends IChatComponent{
    public TextComponentBase(Object obj) {
        super(obj);
    }
}
