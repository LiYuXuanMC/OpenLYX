package al.nya.reflect.socket;

import java.util.Date;

public class User {
    public User(String name, String rank) {
        this.name = name;
        this.rank = rank;
    }

    public String name;
    public long qq;
    public String rank;
    public String banReason = "";
    public Date registerTo;
    public boolean isBeta(){
        return rank.equals("Beta") || rank.equals("Dev");
    }
}
