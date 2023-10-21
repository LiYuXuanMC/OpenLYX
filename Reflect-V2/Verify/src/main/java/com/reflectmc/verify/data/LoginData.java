package com.reflectmc.verify.data;

import lombok.Getter;

public class LoginData {
    @Getter
    private int status;
    @Getter
    private String reason;
    @Getter
    private UserData userData;
    public LoginData(int status,String reason){
        this.status = status;
        this.reason = reason;
    }
    public LoginData(int status,UserData userData){
        this.status = status;
        this.userData = userData;
    }
}
