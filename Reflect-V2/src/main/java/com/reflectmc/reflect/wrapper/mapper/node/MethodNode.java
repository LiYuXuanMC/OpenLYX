package com.reflectmc.reflect.wrapper.mapper.node;

import lombok.Getter;

public class MethodNode extends Node{
    @Getter
    private String obfMethodName;
    @Getter
    private String deobfMethodName;
    @Getter
    private Signature obfSignature;
    @Getter
    private Signature deobfSignature;
    public MethodNode(String obfClassName, String deobfClassName,String obfMethodName,String deobfMethodName,
                      Signature obfSignature,Signature deobfSignature) {
        super(obfClassName, deobfClassName);
        this.obfMethodName = obfMethodName;
        this.deobfMethodName = deobfMethodName;
        this.obfSignature = obfSignature;
        this.deobfSignature = deobfSignature;
        this.obfSignature.parse();
    }
}
