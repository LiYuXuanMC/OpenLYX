package com.reflectmc.loader.packet;

import lombok.Getter;
import lombok.Setter;

public enum EnumServerChannel {
    Debug(0,"ws://127.0.0.1:9894")
    ;
    @Getter
    private int order;
    @Getter
    private String address;
    EnumServerChannel(int order,String address){
        this.order = order;
        this.address = address;
    }
    public static EnumServerChannel getByOrder(int order){
        for (EnumServerChannel value : values()) {
            if(value.order == order){
                return value;
            }
        }
        return null;
    }
}
