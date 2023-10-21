package al.logger.client.script.api;


import al.logger.client.Logger;
import al.logger.libs.org.luaj.vm2.LuaValue;
import al.logger.libs.org.luaj.vm2.lib.TwoArgFunction;

public class ModuleLoader extends TwoArgFunction {
    public ModuleLoader() {
    }
    @Override
    public LuaValue call(LuaValue modeName, LuaValue env) {
        LuaValue library = tableOf();
        library.set("loadModule", new test());
        env.set("ModuleLoader", library);
        env.get("package").get("loaded").set("ModuleLoader", library);
        return library;
    }
    static class test extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue arg1,LuaValue arg2) {
            System.out.println("Loading module: " + arg1.toString() + " From: " + arg2.toString());
            Logger.getInstance().getScriptManager().loadModule(arg1.toString(),arg2.toString());
            return null;
        }
    }
}
