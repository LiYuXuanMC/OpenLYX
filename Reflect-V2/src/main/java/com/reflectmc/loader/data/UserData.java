package com.reflectmc.loader.data;

import lombok.Getter;

public class UserData {
    @Getter
    private String username;
    @Getter
    private String decodeKey;

    public UserData(String username){
        this.username = username;
    }
}
