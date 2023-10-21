package al.logger.client.wrapper.map;

import lombok.Getter;

public class FieldMap extends Map{
    @Getter
    private String mcpClassName;
    @Getter
    private String mcpName;
    @Getter
    private String srgClassName;
    @Getter
    private String srgName;
    public FieldMap(String mcpClassName,String mcpName,String srgClassName,String srgName){
        this.mcpClassName = mcpClassName;
        this.mcpName = mcpName;
        this.srgClassName = srgClassName;
        this.srgName = srgName;
    }
}
