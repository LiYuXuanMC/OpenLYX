package al.logger.client.script.api;


import al.logger.client.Logger;
import al.logger.libs.org.luaj.vm2.LuaValue;
import al.logger.libs.org.luaj.vm2.lib.TwoArgFunction;

public class PacketSender extends TwoArgFunction {
    public PacketSender() {
    }
    @Override
    public LuaValue call(LuaValue modeName, LuaValue env) {
        LuaValue library = tableOf();
        library.set("sendPacket", new sendPacket());
        env.set("PacketSender", library);
        env.get("package").get("loaded").set("PacketSender", library);
        return library;
    }
    static class sendPacket extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue arg1,LuaValue arg2) {
            Logger.getInstance().getScriptManager().loadModule(arg1.toString(),arg2.toString());
            return null;
        }
    }
}
