package dev.qingwan.crater.builder;

import lombok.Getter;

public enum WorkType {
    //VM
    Unsafe(0),
    Reflect(1),
    //Proxy
    Direct(0)
    ;
    @Getter
    int ord;
    WorkType(int ord){
        this.ord = ord;
    }
}
