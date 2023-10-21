package al.nya.reflect.features.command.commands;

import al.nya.reflect.Reflect;
import al.nya.reflect.features.command.Command;
import al.nya.reflect.utils.client.ClientUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenDir extends Command {
    public OpenDir() {
        super("Opens Reflect directory in the file explorer", "opendir");
    }

    @Override
    public void onMessage(String msg, String[] args) {
        try {
            Desktop.getDesktop().open(new File(Reflect.ReflectDir));
        } catch (IOException e) {
            ClientUtil.printChat(ClientUtil.Level.ERROR, "Could not open directory: " + e.getMessage());
        }
        super.onMessage(msg, args);
    }
}
