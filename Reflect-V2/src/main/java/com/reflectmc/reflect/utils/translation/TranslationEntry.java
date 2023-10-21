package com.reflectmc.reflect.utils.translation;

import lombok.Getter;

public class TranslationEntry {
    @Getter
    private final Type type;
    @Getter
    private final String from;
    @Getter
    private final String to;
    @Getter
    private final TranslationManager.Language language;
    public TranslationEntry(Type type, TranslationManager.Language language, String from, String to){
        this.type = type;
        this.language = language;
        this.from = from;
        this.to = to;
    }
    public enum Type {
        Module,
        Value
    }
}
