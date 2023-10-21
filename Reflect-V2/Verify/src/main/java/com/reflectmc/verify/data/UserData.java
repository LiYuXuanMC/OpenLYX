package com.reflectmc.verify.data;

import lombok.Getter;

public class UserData {
    @Getter
    private String username;
    @Getter
    private String decodeKey = "8db6d8db159cddf8";

    public UserData(String username){
        this.username = username;
    }
}
