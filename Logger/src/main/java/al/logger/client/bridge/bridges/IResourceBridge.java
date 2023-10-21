package al.logger.client.bridge.bridges;

import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.LoggerMC.resource.IMetadataSection;
import al.logger.client.wrapper.LoggerMC.resource.IResource;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;

import java.io.InputStream;

public class IResourceBridge {
    @ExportObfuscate(name = "getResourceLocation")
    public ResourceLocation getResourceLocation(){
        return null;
    }
    @ExportObfuscate(name = "getInputStream")
    public InputStream getInputStream(){
        return null;
    }
    @ExportObfuscate(name = "hasMetadata")
    public boolean hasMetadata(){
        return false;
    }
    @ExportObfuscate(name = "getMetadata")
    public IMetadataSection getMetadata(String var1){
        return null;
    }
    @ExportObfuscate(name = "getResourcePackName")
    public String getResourcePackName(){
        return null;
    }
}
