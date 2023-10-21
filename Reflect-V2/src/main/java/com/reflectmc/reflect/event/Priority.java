package com.reflectmc.reflect.event;

import lombok.Getter;

public enum Priority {
    Highest(1000),
    High(800),
    Normal(500),
    Low(300),
    Lowest(0)
    ;
    @Getter
    private int order;
    private Priority(int order){
        this.order = order;
    }
}
