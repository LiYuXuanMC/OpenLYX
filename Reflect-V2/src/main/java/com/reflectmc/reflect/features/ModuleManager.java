package com.reflectmc.reflect.features;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.modules.SubModule;
import com.reflectmc.reflect.features.modules.combat.AntiBot;
import com.reflectmc.reflect.features.modules.combat.KillAura;
import com.reflectmc.reflect.features.modules.combat.SuperKnockBack;
import com.reflectmc.reflect.features.modules.combat.Teams;
import com.reflectmc.reflect.features.modules.ghost.*;
import com.reflectmc.reflect.features.modules.movement.KeepSprint;
import com.reflectmc.reflect.features.modules.movement.NoSlow;
import com.reflectmc.reflect.features.modules.movement.SafeWalk;
import com.reflectmc.reflect.features.modules.movement.Sprint;
import com.reflectmc.reflect.features.modules.player.FastPlace;
import com.reflectmc.reflect.features.modules.player.FastUse;
import com.reflectmc.reflect.features.modules.player.Regen;
import com.reflectmc.reflect.features.modules.player.SpeedMine;
import com.reflectmc.reflect.features.modules.visual.*;
import com.reflectmc.reflect.features.modules.world.Scaffold;
import com.reflectmc.reflect.features.modules.world.Timer;
import com.reflectmc.reflect.obfuscate.ExportObfuscate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleManager {
    private final Map<Category, List<Module>> modules = new HashMap<>();
    private final List<Module> moduleReferences = new ArrayList<>();
    public ModuleManager(){

    }
    public void init(){
        //Visual
        moduleReferences.add(new HUD());
        moduleReferences.add(new RotationAnimation());
        moduleReferences.add(new ClickGui());
        moduleReferences.add(new ESP());
        moduleReferences.add(new Spin());
        //Ghost
        moduleReferences.add(new AimAssist());
        moduleReferences.add(new AutoClicker());
        moduleReferences.add(new Velocity());
        moduleReferences.add(new Reach());
        moduleReferences.add(new NoJumpDelay());
        //Combat
        moduleReferences.add(new AntiBot());
        moduleReferences.add(new Teams());
        moduleReferences.add(new KillAura());
        moduleReferences.add(new SuperKnockBack());
        //World
        moduleReferences.add(new Timer());
        moduleReferences.add(new Scaffold());
        //Movement
        moduleReferences.add(new Sprint());
        moduleReferences.add(new NoSlow());
        moduleReferences.add(new KeepSprint());
        moduleReferences.add(new SafeWalk());
        //Player
        moduleReferences.add(new SpeedMine());
        moduleReferences.add(new FastPlace());
        moduleReferences.add(new FastUse());
        moduleReferences.add(new Regen());

        for (Category value : Category.values()) {
            modules.put(value,new ArrayList<>());
        }

        for (Module moduleReference : moduleReferences) {
            modules.get(moduleReference.getCategory()).add(moduleReference);
        }

        registerEvents();
        Reflect.getINSTANCE().getEventBus().sortEvent();
    }
    private void registerEvents(){
        for (Module moduleReference : moduleReferences) {
            moduleReference.registerEvent();
            for (SubModule subModule : moduleReference.getSubModules()) {
                subModule.registerEvent();
            }
        }
    }
    public List<Module> getModules(Category category){
        return modules.get(category);
    }
    @ExportObfuscate(name = "getModule")
    public Module getModule(Class<? extends Module> moduleClass){
        for (Module moduleReference : moduleReferences) {
            if (moduleReference.getClass() == moduleClass) return moduleReference;
        }
        return null;
    }
    public Module getModule(String name){
        for (Module moduleReference : moduleReferences) {
            if (moduleReference.getName().equalsIgnoreCase(name)) return moduleReference;
        }
        return null;
    }
    public Module getByDisplayName(String displayName){
        for (Module moduleReference : moduleReferences) {
            if (moduleReference.getDisplayName().equalsIgnoreCase(displayName)) return moduleReference;
        }
        return null;
    }
    public List<Module> getModules(){
        return new ArrayList<>(moduleReferences);
    }
}
