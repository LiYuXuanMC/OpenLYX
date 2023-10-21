package dev.vector.utils.wrappers;

public class ResourceWrapper {
    private String name;
    private byte[] bytes;
    public ResourceWrapper(byte[] bytes,String name) {
        this.bytes = bytes;
        this.name = name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getName() {
        return name;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public void setName(String name) {
        this.name = name;
    }
}
