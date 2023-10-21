package al.nya.reflect.wrapper.wraps.wrapper.network.C16;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;

public class C16State extends EnumWrapper {
    @WrapEnum(mcpName = "PERFORM_RESPAWN",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "PERFORM_RESPAWN",targetMap = Maps.Srg1_12_2)
    public static Enum PERFORM_RESPAWN;
    @WrapEnum(mcpName = "REQUEST_STATS",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "REQUEST_STATS",targetMap = Maps.Srg1_12_2)
    public static Enum REQUEST_STATS;
    @WrapEnum(mcpName = "OPEN_INVENTORY_ACHIEVEMENT",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "OPEN_INVENTORY_ACHIEVEMENT",targetMap = Maps.Srg1_12_2)
    public static Enum OPEN_INVENTORY_ACHIEVEMENT;
    public C16State(Object obj) {
        super(obj);
    }
}
