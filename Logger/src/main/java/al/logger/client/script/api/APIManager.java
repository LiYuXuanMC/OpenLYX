package al.logger.client.script.api;


import al.logger.libs.org.luaj.vm2.LuaValue;

public class APIManager {
    public static void loadAPI(LuaValue env){
        env.load(new ModuleLoader());
    }
}
