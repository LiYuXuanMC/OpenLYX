package cc.systemv.rave.config;

import cc.systemv.rave.utils.IManager;

public class ConfigManager extends IManager {
    @Override
    public void init() {

    }
    public void save(){
        rave.getExecutorService().execute(this::saveAsync);
    }
    private void saveAsync(){

    }
}
