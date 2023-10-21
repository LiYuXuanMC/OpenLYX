package al.nya.reflect.utils.data;

public class UserData {
    private String user;
    private String rank;
    public UserData(String user,String rank){
        this.user = user;
        this.rank = rank;
    }

    public String getUser() {
        return user;
    }

    public String getRank() {
        return rank;
    }
}
