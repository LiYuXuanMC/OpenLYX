package com.reflectmc.loader;

import com.reflectmc.builder.annotation.ExportName;
import com.reflectmc.loader.data.UserData;
import com.reflectmc.loader.socket.InjectorSocket;
import com.reflectmc.loader.socket.ServerSocket;
import lombok.Getter;
import lombok.Setter;

import java.net.URISyntaxException;

@ExportName(export = "OBF/Loader")
public class Loader {
    @Getter
    private static Loader INSTANCE;
    @Getter
    private InjectorSocket injectorSocket;
    @Getter
    @Setter
    private ServerSocket serverSocket;
    @Getter
    @Setter
    private UserData userData;
    public Loader(){
        INSTANCE = this;
        try {
            injectorSocket = new InjectorSocket();
            injectorSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
