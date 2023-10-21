package al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.scoreboard.Score", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.scoreboard.Score", targetMap = Maps.Srg1_12_2)
public class Score extends IWrapper {
    @WrapField(mcpName = "scorePlayerName", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "scorePlayerName", targetMap = Maps.Srg1_12_2)
    public static Field scorePlayerName;
    public Score(Object obj) {
        super(obj);
    }
    public String getPlayerName(){
        return (String) getField(scorePlayerName);
    }
}
