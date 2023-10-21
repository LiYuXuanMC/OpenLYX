package al.nya.reflect.socket;

public class LoginReturnPack {
    public int Code;
    public User Data;
    public String token;
    public LoginReturnPack(int code, User extraData){
        Code = code;
        Data = extraData;
    }
}
