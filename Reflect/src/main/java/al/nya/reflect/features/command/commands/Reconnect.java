package al.nya.reflect.features.command.commands;

import al.nya.reflect.Reflect;
import al.nya.reflect.features.command.Command;
import al.nya.reflect.utils.client.ClientUtil;

import java.net.URI;
import java.net.URISyntaxException;

public class Reconnect extends Command {

    public Reconnect() {
        super("Try reconnect", "reconnect");
    }

    @Override
    public void onMessage(String msg, String[] args) {

        ClientUtil.printChat("Trying reconnect as "+args[0]);
    }

    @Override
    public String[] getHelpMessage() {
        return new String[]{".reconnect [Username] - Try reconnect as another user"};
    }
}
