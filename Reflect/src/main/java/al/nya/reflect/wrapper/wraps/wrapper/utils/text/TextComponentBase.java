package al.nya.reflect.wrapper.wraps.wrapper.utils.text;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.util.text.TextComponentBase",targetMap = Maps.Srg1_12_2)
public class TextComponentBase extends IChatComponent{
    public TextComponentBase(Object obj) {
        super(obj);
    }
}
