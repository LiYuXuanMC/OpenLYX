
package al.logger.client.features.modules.impls.Visual.MotionBlur;

import al.logger.client.Logger;
import al.logger.client.bridge.bridges.IResourceManagerBridge;
import al.logger.client.wrapper.LoggerMC.resource.IResource;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.client.wrapper.ReflectUtil;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class MotionBlurResourceManager extends IResourceManagerBridge {
    public MotionBlurResourceManager() {
    }

    public Set<String> getResourceDomains() {
        return null;
    }

    @SneakyThrows
    public IResource getResource(ResourceLocation location) throws IOException {
        return new IResource(ReflectUtil.construction(Logger.getInstance().getBridgeManager().getIResourceBridgeConstructor(),new MotionBlurResource()));
    }

    public List<IResource> getAllResources(ResourceLocation location) throws IOException {
        return null;
    }
}
