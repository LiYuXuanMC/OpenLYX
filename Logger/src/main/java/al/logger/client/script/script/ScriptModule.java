package al.logger.client.script.script;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.event.client.render.EventRender3D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.libs.org.luaj.vm2.LuaValue;



public class ScriptModule extends Module {
    LuaValue script;

    public ScriptModule(String name,String category,LuaValue object) {
        super(name, Category.getCategoryByStr(category));
        script = object;
    }

    @Override
    public void onEnable() {
        LuaValue func = script.get("onEnable");
        if(func.isfunction()){
            func.call();
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        LuaValue func = script.get("onDisable");
        if(func.isfunction()){
            func.call();
        }
        super.onDisable();
    }


    @Listener
    public void onUpdate(EventLivingUpdate update) {
        LuaValue func = script.get("onUpdate");
        if(func.isfunction()){
            func.call();
        }
    }
    @Listener
    public void onRender2D(EventRender2D e) {
        LuaValue func = script.get("onRender2D");
        if(func.isfunction()){
            func.call();
        }
    }
    @Listener
    public void onRender3D(EventRender3D e) {
        LuaValue func = script.get("onRender3D");
        if(func.isfunction()){
            func.call();
        }
    }

}
