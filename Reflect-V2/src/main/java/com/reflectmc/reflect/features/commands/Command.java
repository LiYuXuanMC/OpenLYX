package com.reflectmc.reflect.features.commands;

import com.reflectmc.reflect.utils.ClientUtil;
import lombok.Getter;

import java.util.List;

public abstract class Command {
    @Getter
    private String name;
    @Getter
    private String command;
    @Getter
    private String[] alias = new String[]{};
    public Command(String name,String command){
        this.name = name;
        this.command = command;
    }
    public Command(String name,String command,String[] alias){
        this.name = name;
        this.command = command;
        this.alias = alias;
    }
    public abstract boolean trigger(String[] args);
    public abstract void help();
    public void printHelp(String[] args, String describe){
        StringBuilder sb = new StringBuilder();
        sb.append("⧈ ");
        sb.append(".").append(command);
        for (String arg : args) {
            sb.append(" ").append(arg);
        }
        sb.append(" -").append(describe);
        ClientUtil.printChat(ClientUtil.Level.INFO,sb.toString());
    }
}
