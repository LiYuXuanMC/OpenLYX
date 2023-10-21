package al.nya.reflect.features.modules;

import al.nya.reflect.Reflect;
import al.nya.reflect.config.UserConfigManager;
import al.nya.reflect.features.modules.Player.IRC;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.socket.BuiltInLoginClient;
import al.nya.reflect.socket.CommandIRCChat;
import al.nya.reflect.socket.ControlCommand;
import al.nya.reflect.utils.client.ClientUtil;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Command {
    public static boolean message(String s){
        if (s.startsWith(".")){
            String[] s1 = s.split(" ");
            if (s1.length == 1 && s1[0].equalsIgnoreCase(".help")){
                ClientUtil.printChat("Reflect -Help");
                ClientUtil.printChat(".bind [Module] [Key] -Bind module to key");
                ClientUtil.printChat(".reconnect [Username] [Password] -Try reconnect");
                ClientUtil.printChat(".config list -List all available configs");
                ClientUtil.printChat(".config save [name] -Save config");
                ClientUtil.printChat(".config load [name] -Load config");
            }
            if (s1.length == 3 && s1[0].equalsIgnoreCase(".bind")){
                Module module = ModuleManager.getModuleByName(s1[1]);
                if (module == null){
                    ClientUtil.printChat("Cannot find module:"+s1[1]);
                    return false;
                }
                EnumKey key = EnumKey.getKey(s1[2]);
                if (key == null){
                    ClientUtil.printChat("Cannot find key:"+s1[2]);
                    return false;
                }
                module.setBinding(key);
                ClientUtil.printChat("Bind module "+module.getName()+" to "+key.getDisplayName());
            }
            if (s1.length == 3 && s1[0].equalsIgnoreCase(".reconnect")){
                if (!s1[1].equals(Reflect.USER.name)){
                    ClientUtil.printChat("You can't login as another user");
                    return false;
                }
                Reflect.password = s1[2];
                try {
                    new BuiltInLoginClient(new URI("ws://verify.sd.dustella.net")).connect();
                } catch (URISyntaxException e) {
                    ClientUtil.printChat(ClientUtil.Level.ERROR,e.getMessage());
                }
                ClientUtil.printChat("Trying reconnect as "+s1[1]);
            }
            if (s1.length >= 2 && s1[0].equalsIgnoreCase(".config")){
                if (s1[1].equalsIgnoreCase("list")){
                    List<String> configs = UserConfigManager.getAvailableConfigs();
                    ClientUtil.printChat(configs.size()+" Config(s):");
                    StringBuilder sb = new StringBuilder();
                    for (String config : configs) {
                        sb.append(UserConfigManager.using.equals(config) ? "\u00a75" : "\u00a7a").append(config).append("\u00a77,");
                    }
                    ClientUtil.printChat(sb.toString());
                }
                if (s1[1].equalsIgnoreCase("save")){
                    if (s1.length == 3){
                        UserConfigManager.save(s1[2]);
                    }else {
                        if (UserConfigManager.using == null) {
                            ClientUtil.printChat(".config save [name] -save config");
                        } else {
                            UserConfigManager.save(UserConfigManager.using);
                        }
                    }
                }
                if (s1[1].equalsIgnoreCase("load")){
                    if (s1.length == 3){
                        UserConfigManager.load(s1[2]);
                    }else {
                        ClientUtil.printChat(".config save [name] -load config");
                    }
                }
            }
            return false;
        }
        if (s.startsWith("'")){
            if (!ModuleManager.getModule(IRC.class).isEnable()) {
                ClientUtil.printChat(ClientUtil.Level.ERROR, "You're not enable IRC");
                return false;
            }
            if (Reflect.ircClient.isClosed()) {
                ClientUtil.printChat(ClientUtil.Level.ERROR, "You seem offline Use .reconnect [UserName] [PassWord] to reconnect to IRC Server");
                return false;
            }
            Reflect.ircClient.send(new Gson().toJson(new ControlCommand("IRCChat",new Gson().toJson(
                    new CommandIRCChat(Reflect.USER,s.replaceFirst("'",""))))));
            return false;
        }
        return true;
    }
}
