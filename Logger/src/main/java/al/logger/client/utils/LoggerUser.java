package al.logger.client.utils;

import by.radioegor146.nativeobfuscator.Native;
import lombok.Getter;
import lombok.Setter;
@Native
public class LoggerUser {
    @Getter
    @Setter
    private int power;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String userid;
    @Getter
    @Setter
    private String jToken;
    @Getter
    @Setter
    private String idunToken;
    @Getter
    @Setter
    private String instanceTokenNot;
    @Getter
    @Setter
    private String instanceTokenCom;
    @Getter
    @Setter
    private String instanceTokenMod;


    public LoggerUser(String username,String password){
        this.username = username;
        this.password = password;
    }
}
