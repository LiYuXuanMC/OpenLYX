package al.nya.reflect.wrapper.wraps.wrapper.utils.event.click;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.event.ClickEvent$Action",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.text.event.ClickEvent$Action",targetMap = Maps.Srg1_12_2)
public class ClickEventAction extends EnumWrapper {
    @WrapEnum(mcpName = "OPEN_URL",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "OPEN_URL",targetMap = Maps.Srg1_12_2)
    public static Enum OPEN_URL;
    @WrapEnum(mcpName = "OPEN_FILE",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "OPEN_FILE",targetMap = Maps.Srg1_12_2)
    public static Enum OPEN_FILE;
    @WrapEnum(mcpName = "RUN_COMMAND",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "RUN_COMMAND",targetMap = Maps.Srg1_12_2)
    public static Enum RUN_COMMAND;
    @WrapEnum(mcpName = "TWITCH_USER_INFO",targetMap = Maps.Srg1_8_9)
    public static Enum TWITCH_USER_INFO;
    @WrapEnum(mcpName = "SUGGEST_COMMAND",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "SUGGEST_COMMAND",targetMap = Maps.Srg1_12_2)
    public static Enum SUGGEST_COMMAND;
    @WrapEnum(mcpName = "CHANGE_PAGE",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "CHANGE_PAGE",targetMap = Maps.Srg1_12_2)
    public static Enum CHANGE_PAGE;
    public ClickEventAction(Object obj) {
        super(obj);
    }
}
