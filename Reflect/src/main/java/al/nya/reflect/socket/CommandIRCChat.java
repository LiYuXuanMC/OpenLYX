package al.nya.reflect.socket;

import al.nya.reflect.utils.data.UserData;

public class CommandIRCChat {
    public User dst;
    public String text;
    public CommandIRCChat(User dst, String text){
        this.dst = dst;
        this.text = text;
    }
}
