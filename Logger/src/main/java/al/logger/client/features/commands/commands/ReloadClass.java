package al.logger.client.features.commands.commands;

import al.logger.client.Logger;
import al.logger.client.features.commands.Command;
import al.logger.client.transform.transformers.ClassReloadTransformer;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.FileUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ReloadClass extends Command {
    public ReloadClass() {
        super("ReloadClass", "rload");
    }

    @Override
    public boolean trigger(String[] args) {
        try {
            ClassReloadTransformer classReloadTransformer = new ClassReloadTransformer(FileUtils.readStream(new FileInputStream(args[0])));
            ChatUtils.warning("Reloading class " + classReloadTransformer.getTargetClass().getCanonicalName());
            Logger.getInstance().getTransformManager().addTransformer(classReloadTransformer);
            int result = Logger.getInstance().getTransformManager().transform();
            ChatUtils.warning("Reloaded " + result);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void help() {

    }
}
