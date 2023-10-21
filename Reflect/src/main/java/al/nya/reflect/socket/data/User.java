package al.nya.reflect.socket.data;

import java.util.Date;

public class User {
    public String name;
    public String rank;
    public Date registerTo;

    public boolean isBeta() {
        return !rank.equals("Release");
    }
}
