package al.logger.client.features.commands.commands;

import al.logger.client.features.commands.Command;
import al.logger.client.utils.ChatUtils;
import al.logger.client.wrapper.LoggerMC.Minecraft;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class IGN extends Command {
    public IGN() {
        super("IGN", "IGN");
    }
    @Override
    public boolean trigger(String[] args) {
        if (Minecraft.getMinecraft().getThePlayer() != null) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String text = Minecraft.getMinecraft().getThePlayer().getName();
            Transferable trans = new StringSelection(text);
            clipboard.setContents(trans, null);
            ChatUtils.message("Copied!");
        } else {
            ChatUtils.message("Failed to get in game name!");
        }

        return false;
    }

    @Override
    public void help() {

    }
}
