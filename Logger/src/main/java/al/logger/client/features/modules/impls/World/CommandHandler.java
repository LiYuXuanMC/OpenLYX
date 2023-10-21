package al.logger.client.features.modules.impls.World;

import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;

public class CommandHandler extends Module {
    public CommandHandler() {
        super("CommandHandler", "Runs commands", Category.World);
        this.setEnabled(true);
        this.setHide(true);
    }

}
