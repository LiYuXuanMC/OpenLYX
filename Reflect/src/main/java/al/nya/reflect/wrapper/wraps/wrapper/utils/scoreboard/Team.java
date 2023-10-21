package al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
@WrapperClass(mcpName = "net.minecraft.scoreboard.Team", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.scoreboard.Team", targetMap = Maps.Srg1_12_2)
public class Team extends IWrapper {
    public Team(Object obj) {
        super(obj);
    }
}
