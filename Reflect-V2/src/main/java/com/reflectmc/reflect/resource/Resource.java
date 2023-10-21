package com.reflectmc.reflect.resource;

import com.reflectmc.loader.utils.EncryptUtil;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;

public class Resource {
    @Getter
    private final String name;
    @Getter
    private ByteBuffer buffer;
    @Setter
    private String md5;
    @Getter
    private String alias;
    public Resource(String name){
        this.name = name;
    }
    public Resource(String name,String alias){
        this.name = name;
        this.alias = alias;
    }
    public void allocateBuffer(int length){
        buffer = ByteBuffer.allocate(length);
    }
    public void writeBuffer(byte[] bytes){
        buffer.put(bytes);
    }
    public boolean md5Check(){
        return md5.equals(EncryptUtil.getMD5(buffer.array()));
    }
    public void uncompress(){
        try {
            byte[] bytes = EncryptUtil.uncompress(buffer.array());
            allocateBuffer(bytes.length);
            writeBuffer(bytes);
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
    }
}
