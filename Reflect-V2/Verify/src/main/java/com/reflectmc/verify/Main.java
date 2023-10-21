package com.reflectmc.verify;

import com.reflectmc.verify.resource.ResourceManager;
import com.reflectmc.verify.socket.ClientSocket;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

public class Main {
    @Getter
    private static boolean proxyServer = false;
    @Getter
    private static ResourceManager resourceManager;
    private static Logger logger = new SimpleLoggerFactory().getLogger("Main");
    @Getter
    private static ClientSocket IRCServer;
    public static void main(String[] args){
        logger.info("Starting verify server");
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("--proxy")){
                proxyServer = true;
            }
        }
        resourceManager = new ResourceManager();
        if (proxyServer) {
            logger.info("Verify server will start as proxy server");
            return;
        }
        resourceManager.initAsMainServer();
        IRCServer = new ClientSocket();
        IRCServer.start();
    }
}
