package al.logger.client.script;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello MC");
        ScriptManager scriptManager = new ScriptManager(new File("./lgscripts"));
        scriptManager.search();
        scriptManager.loadScript("test");
        /*
        Globals globals = JsePlatform.standardGlobals();
        LuaJC.install(globals);
        LuaValue chunk = globals.load("print 'hello, world'");
        chunk.call();
         */
    }
}