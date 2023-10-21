package com.reflectmc.reflect.wrapper.mapper.node;

import lombok.Getter;

public class FieldNode extends Node{
    @Getter
    private String obfFieldName;
    @Getter
    private String deobfFieldName;
    public FieldNode(String obfClassName, String deobfClassName,String obfFieldName,String deobfFieldName) {
        super(obfClassName, deobfClassName);
        this.obfFieldName = obfFieldName;
        this.deobfFieldName = deobfFieldName;
    }
}
