package com.reflectmc.reflect.features.commands;

import com.reflectmc.reflect.utils.ClientUtil;

public class Help extends Command{
    public Help() {
        super("Help","help",new String[]{"h"});
    }

    @Override
    public boolean trigger(String[] args) {
        ClientUtil.printChat(ClientUtil.Level.INFO,"-Help");
        return true;
    }

    @Override
    public void help() {
        printHelp(new String[]{},"Get help");
    }
}
