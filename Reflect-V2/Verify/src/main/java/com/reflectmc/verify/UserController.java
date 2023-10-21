package com.reflectmc.verify;

import com.reflectmc.verify.data.LoginData;
import com.reflectmc.verify.data.UserData;

public class UserController {
    public static LoginData verifyUser(String name, String passwd){
        return new LoginData(200,new UserData(name));
    }
}
