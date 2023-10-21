package dev.qingwan.crater.builder;

public class FieldModify {
    private String targetClass;
    private String originFieldName;
    private String newMethodName;
    private String newMethodDesc;
    public FieldModify(String targetClass,String originFieldName,String newMethodName,String newMethodDesc){
        this.targetClass = targetClass;
        this.originFieldName = originFieldName;
        this.newMethodName = newMethodName;
        this.newMethodDesc = newMethodDesc;
    }
}
