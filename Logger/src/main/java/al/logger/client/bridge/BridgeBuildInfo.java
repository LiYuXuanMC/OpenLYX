package al.logger.client.bridge;

import al.logger.client.bridge.bridges.GuiScreenBridge;
import lombok.Getter;
import lombok.Setter;

public class BridgeBuildInfo {
    @Getter
    private final Class<? extends GuiScreenBridge> bridge;
    @Getter
    private final Type buildType;
    @Getter
    private final Class<?> parasiticTarget;
    @Getter
    @Setter
    private byte[] bytes = null;
    @Setter
    @Getter
    private Class<?> generatedClass = null;
    public BridgeBuildInfo(Class<? extends GuiScreenBridge> bridge, Class<?> parasiticTarget){
        this.bridge = bridge;
        this.buildType = Type.PARASITIC;
        this.parasiticTarget = parasiticTarget;
    }
    public BridgeBuildInfo(Class<? extends GuiScreenBridge> bridge){
        this.bridge = bridge;
        this.buildType = Type.DIRECT;
        this.parasiticTarget = null;
    }

    public enum Type{
        DIRECT,
        BRIDGE,
        PARASITIC
    }
}
