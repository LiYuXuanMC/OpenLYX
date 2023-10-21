package al.nya.reflect.features.modules.Player;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventText;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.ModeValue;

public class NameProtect extends Module {
    private static ModeValue prefix = new ModeValue("Prefix",PrefixMode.ReflectRank,PrefixMode.values());
    public NameProtect() {
        super("NameProtect",ModuleType.Player);
        addValues(prefix);
    }
    @EventTarget
    public void onText(EventText text){
        if (!mc.getThePlayer().isNull())
        text.setText(text.getText().replace(mc.getThePlayer().getGameProfile().getName(),getPrefix()+Reflect.USER.name));
    }
    public String getPrefix(){
        if (prefix.getValue() == PrefixMode.HypixelRank){
            return "\u00a76[MVP++]";
        }else if (prefix.getValue() == PrefixMode.ReflectRank){
            return (Reflect.USER.rank.equals("Release") ? "\u00a7a" :  "\u00a76") + "["+Reflect.USER.rank+"]";
        }
        return "";
    }
    public enum PrefixMode{
        HypixelRank,
        ReflectRank,
        None
    }
}
