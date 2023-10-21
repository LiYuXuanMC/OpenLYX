package al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.scoreboard.ScoreObjective", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.scoreboard.ScoreObjective", targetMap = Maps.Srg1_12_2)
public class ScoreObjective extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.scoreboard.ScoreObjective", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.scoreboard.ScoreObjective", targetMap = Maps.Srg1_12_2)
    public static Class<?> ScoreObjectiveClass;
    @WrapField(mcpName = "displayName", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "displayName", targetMap = Maps.Srg1_12_2)
    public static Field displayName;

    public ScoreObjective(Object obj) {
        super(obj);
    }

    public String getDisplayName() {
        return (String) getField(displayName);
    }
}
