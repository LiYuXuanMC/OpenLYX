package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.VertexFormat",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.VertexFormat",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class VertexFormat extends IWrapper {
    public VertexFormat(Object obj) {
        super(obj);
    }
}
