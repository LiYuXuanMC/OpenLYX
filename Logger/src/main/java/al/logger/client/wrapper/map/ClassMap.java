package al.logger.client.wrapper.map;

import lombok.Getter;

public class ClassMap extends Map{
    @Getter
    private String mcpName;
    @Getter
    private String srgName;
    public ClassMap(String mcpName,String srgName){
        this.mcpName = mcpName;
        this.srgName = srgName;
    }
}
