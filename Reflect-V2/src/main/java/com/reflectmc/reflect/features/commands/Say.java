package com.reflectmc.reflect.features.commands;

public class Say extends Command{
    public Say() {
        super("Say","say");
    }

    @Override
    public boolean trigger(String[] args) {
        return false;
    }

    @Override
    public void help() {
        printHelp(new String[]{"[Message]"},"Say a message");
    }
}
