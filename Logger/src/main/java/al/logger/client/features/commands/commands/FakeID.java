package al.logger.client.features.commands.commands;

import al.logger.client.features.commands.Command;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.FakeIDManager;

public class FakeID extends Command {
    public FakeID() {
        super("FakeID", "fakeid");
    }

    @Override
    public boolean trigger(String[] args) {
        if (args.length == 3){
            if (args[0].equalsIgnoreCase("add")){
                FakeIDManager.addFakeID(args[1],args[2]);
                ChatUtils.message("Added fake id " + args[2] + " for real id " + args[1]);
            }
            return true;
        }
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("remove")){
                FakeIDManager.removeFakeID(args[1]);
                ChatUtils.message("Removed fake id for real id " + args[1]);
            }
            return true;
        }
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("list")) {
                ChatUtils.message("Fake IDs:");
                for (String key : FakeIDManager.getFakeIDs().keySet()) {
                    ChatUtils.message(key + " -> " + FakeIDManager.getFakeID(key));
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("clear")){
                FakeIDManager.clearFakeIDs();
                ChatUtils.message("Cleared fake ids");
                return true;
            }
        }
        return false;
    }

    @Override
    public void help() {
        printHelp(new String[]{"add","[RealID]","[FakeID]"},"Add a fake id for a real id");
        printHelp(new String[]{"remove","[RealID]"},"Remove a fake id for a real id");
        printHelp(new String[]{"list"},"List all fake ids");
    }
}
