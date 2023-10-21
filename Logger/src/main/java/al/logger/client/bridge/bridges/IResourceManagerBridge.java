package al.logger.client.bridge.bridges;

import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.LoggerMC.resource.IResource;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IResourceManagerBridge {
    @ExportObfuscate(name = "getResourceDomains")
    public Set<String> getResourceDomains() {
        return null;
    }

    @ExportObfuscate(name = "getResource")
    public IResource getResource(ResourceLocation location) throws IOException {
        return null;
    }
    @ExportObfuscate(name = "getAllResources")
    public List<IResource> getAllResources(ResourceLocation location) throws IOException {
        return null;
    }
}
