package al.logger.client.features.commands.commands;

import al.logger.client.Logger;
import al.logger.client.features.commands.Command;
import al.logger.client.transform.TransformManager;
import al.logger.client.transform.transformers.ClassDumpTransformer;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.FileUtils;

import java.io.File;

public class DumpClass extends Command {
    public DumpClass() {
        super("DumpClass", "dclass");
    }

    @Override
    public boolean trigger(String[] args) {
        try {
            ClassDumpTransformer classDumpTransformer = new ClassDumpTransformer(Class.forName(args[0]));
            TransformManager tm = Logger.getInstance().getTransformManager();
            tm.addTransformer(classDumpTransformer);
            tm.transform();
            FileUtils.writeFile(new File(classDumpTransformer.getTarget().getCanonicalName() + ".class"), classDumpTransformer.getClassBytes());
            ChatUtils.warning("Dumped class " + classDumpTransformer.getTarget().getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void help() {

    }
}
