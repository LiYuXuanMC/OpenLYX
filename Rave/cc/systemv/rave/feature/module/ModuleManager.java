package cc.systemv.rave.feature.module;

import cc.systemv.rave.event.annotation.Listener;
import cc.systemv.rave.event.events.EventKey;
import cc.systemv.rave.feature.module.modules.movement.KeepSprint;
import cc.systemv.rave.feature.module.modules.movement.NoJumpDelay;
import cc.systemv.rave.feature.module.modules.movement.NoSlow;
import cc.systemv.rave.feature.module.modules.visual.ClickGui;
import cc.systemv.rave.utils.IManager;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager extends IManager {
    private List<Module> modules = new ArrayList<>();
    public ModuleManager() {

    }

    public void init() {
        //Movement
        addModule(new KeepSprint());
        addModule(new NoSlow());
        addModule(new NoJumpDelay());
        //Visual
        addModule(new ClickGui());

    }
    @Listener
    public void onKey(EventKey key){
        if (Minecraft.getMinecraft().currentScreen == null){
            for (Module module : modules) {
                if (module.getBind().getKeyCode() == key.getKey()){
                    module.toggle();
                }
            }
        }
    }
    public void addModule(Module s) {
        modules.add(s);
    }
    public Module getModule(String arg) {
        for (Module mod : this.getModules()) {
            if (mod.getName().equalsIgnoreCase(arg)) {
                return mod;
            }
        }
        return null;
    }
    public List<Module> getModules() {
        return modules;
    }

    public <T extends Module> T getModule(Class<T> arg) {
        for (Module mod : this.getModules()) {
            if (mod.getClass().equals(arg)) {
                return (T) mod;
            }
        }
        return null;
    }
    public ArrayList<Module> getByCategory(Category value) {
        ArrayList<Module> result = new ArrayList<>();
        for (Module mod : this.getModules()) {
            if (mod.getCategory().equals(value)) {
                result.add(mod);
            }
        }
        return result;
    }
}
