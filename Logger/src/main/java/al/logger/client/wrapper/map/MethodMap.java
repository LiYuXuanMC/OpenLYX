package al.logger.client.wrapper.map;

import al.logger.client.wrapper.map.utils.Signature;
import lombok.Getter;

import java.util.List;

public class MethodMap extends Map{
    @Getter
    private String mcpClassName;
    @Getter
    private String mcpMethodName;
    @Getter
    private Signature mcpSignature;
    @Getter
    private String srgClassName;
    @Getter
    private String srgMethodName;
    @Getter
    private Signature srgSignature;
    public MethodMap(String mcpClassName, String mcpMethodName, Signature mcpSignature,
                     String srgClassName, String srgMethodName, Signature srgSignature){
        this.mcpClassName = mcpClassName;
        this.mcpMethodName = mcpMethodName;
        this.mcpSignature = mcpSignature;
        this.srgClassName = srgClassName;
        this.srgMethodName = srgMethodName;
        this.srgSignature = srgSignature;
    }
}
