package com.reflectmc.verify.resource;

import com.reflectmc.verify.utils.EncryptUtil;
import lombok.Getter;

public class Resource {
    @Getter
    private String name;
    @Getter
    private String md5;
    @Getter
    private byte[] bytes;
    public Resource(String name){
        this.name = name;
    }
    public Resource(String name,String md5,byte[] bytes){
        this.name = name;
        this.md5 = md5;
        this.bytes = bytes;
    }
    public void setBytes(byte[] bytes){
        this.bytes = EncryptUtil.compress(bytes);
        this.md5 = EncryptUtil.getMD5(bytes);
    }
}
