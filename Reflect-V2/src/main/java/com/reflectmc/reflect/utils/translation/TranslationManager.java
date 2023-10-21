package com.reflectmc.reflect.utils.translation;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.Value;

import java.util.ArrayList;
import java.util.List;

public class TranslationManager {
    public List<TranslationEntry> entries = new ArrayList<>();
    public TranslationManager(){
        entries.add(new TranslationEntry(TranslationEntry.Type.Module,Language.Chinese,"AutoClicker","自动点击"));
        entries.add(new TranslationEntry(TranslationEntry.Type.Module,Language.Chinese,"NoJumpDelay","无跳跃间隔"));
        //Value
        //AutoClicker
        entries.add(new TranslationEntry(TranslationEntry.Type.Value,Language.Chinese,"LeftClick","左键"));
        entries.add(new TranslationEntry(TranslationEntry.Type.Value,Language.Chinese,"RightClick","右键"));
        entries.add(new TranslationEntry(TranslationEntry.Type.Value,Language.Chinese,"LeftCPS","左键每秒点击数"));
        entries.add(new TranslationEntry(TranslationEntry.Type.Value,Language.Chinese,"RightCPS","右键每秒点击数"));
    }
    public void translateAll(Language targetLanguage){
        for (Module module : Reflect.getINSTANCE().getModuleManager().getModules()) {
            translate(module,targetLanguage);
            for (Value value : module.getValues()) {
                translate(value,targetLanguage);
            }
        }
    }
    public void translate(Module module,Language targetLanguage){
        for (TranslationEntry entry : entries) {
            if (entry.getLanguage() == targetLanguage){
                if (entry.getType() == TranslationEntry.Type.Module){
                    if (entry.getFrom().equals(module.getName())){
                        module.setDisplayName(entry.getTo());
                        return;
                    }
                }
            }
        }
        module.setDisplayName(module.getName());
    }
    public void translate(Value value, Language targetLanguage){
        for (TranslationEntry entry : entries) {
            if (entry.getLanguage() == targetLanguage){
                if (entry.getType() == TranslationEntry.Type.Value){
                    if (entry.getFrom().equals(value.getName())){
                        value.setDisplayName(entry.getTo());
                        return;
                    }
                }
            }
        }
        value.setDisplayName(value.getName());
    }
    public enum Language {
        English,
        Chinese
    }
}
