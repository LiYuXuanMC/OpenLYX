package al.nya.reflect.features.command.commands;

import al.nya.reflect.Reflect;
import al.nya.reflect.features.command.Command;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.World.NoteBotModule;
import al.nya.reflect.utils.client.ClientUtil;

import java.io.File;

public class Play extends Command {
    private final File directory;

    public Play() {
        super("Plays a sound", "play");
        this.directory = new File(Reflect.ReflectDir, "Songs");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Override
    public void onMessage(String input, String[] args) {
        if (!this.clamp(input, 2, 2)) {
            this.printUsage();
            return;
        }

        final String[] split = input.split(" ");

        final NoteBotModule notebotModule = ModuleManager.getModule(NoteBotModule.class);
        try {
            // check for .mid or .midi
            if (split[1].contains(".mid"))
                split[1] = split[1].replaceAll(".mid", "");

            if (split[1].contains(".midi"))
                split[1] = split[1].replaceAll(".midi", "");

            File midiFile = new File(directory, split[1] + ".mid");
            if (!midiFile.exists()) {
                midiFile = new File(directory, split[1] + ".midi");
            }

            // now check if the midi file exists
            if (midiFile.exists()) {
                notebotModule.state.setValue(NoteBotModule.BotState.PLAYING);
                notebotModule.getNotePlayer().begin(midiFile, notebotModule);
                ClientUtil.printChat("Playing '" + "§e" + midiFile.getName() + "§r" + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMessage(input, args);
    }
}
