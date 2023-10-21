package al.logger.client.features.commands.commands;

import al.logger.client.Logger;
import al.logger.client.features.commands.Command;
import al.logger.client.utils.FileUtils;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.network.server.S38.AddPlayerData;
import al.logger.client.wrapper.LoggerMC.network.server.S38.S38PacketPlayerListItem;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.map.ClassMap;
import al.logger.client.wrapper.map.FieldMap;
import al.logger.client.wrapper.map.Map;
import al.logger.client.wrapper.map.MethodMap;
import al.logger.libs.asm.Type;
import lombok.SneakyThrows;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class ExportDebug extends Command {
    public ExportDebug() {
        super("ExportDebug","edebug");
    }

    @SneakyThrows
    @Override
    public boolean trigger(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Logger version: "+ Logger.getInstance().currentVer+"\n");
        sb.append("Wrapper info: \n");
        for (Class<? extends IWrapper> wrapper : Wrapper.getWrappers()) {
            sb.append("     ").append(wrapper.getCanonicalName()).append("\n");
            for (Field declaredField : wrapper.getDeclaredFields()) {
                declaredField.setAccessible(true);
                sb.append("          ").append(declaredField.getName()).append(": \n");
                sb.append("               ").append("Type: ").append(declaredField.getType().getCanonicalName()).append("\n");
                try {
                    sb.append("               ").append("Value: ").append(toString(declaredField.get(null))).append("\n");
                }catch (Exception e){
                    sb.append("               ").append("Value: ").append(e.getMessage()).append("\n");
                    e.printStackTrace();
                }
            }
        }
        sb.append("Map parsing info: \n");
        for (Map map : Logger.getInstance().getMapParser().getMaps()) {
            if (map instanceof ClassMap){
                sb.append("     ").append("Class: ").append(((ClassMap) map).getMcpName()).append(" -> ").append(((ClassMap) map).getSrgName()).append("\n");
            }
            if (map instanceof FieldMap){
                sb.append("     ").append("Field: ").append(((FieldMap) map).getMcpClassName()).append(" ").append(((FieldMap) map).getMcpName()).append(" -> ").append(((FieldMap) map).getSrgClassName()).append(" ").append(((FieldMap) map).getSrgName()).append("\n");
            }
            if (map instanceof MethodMap){
                sb.append("     ").append("Method: ").append(((MethodMap) map).getMcpClassName()).append(" ").append(((MethodMap) map).getMcpMethodName()).append(((MethodMap) map).getMcpSignature().getSignature()).append(" -> ").append(((MethodMap) map).getSrgClassName()).append(" ").append(((MethodMap) map).getSrgMethodName()).append(((MethodMap) map).getSrgSignature().getSignature()).append("\n");
            }
        }
        FileUtils.writeFile(new File("debug.txt"),sb.toString().getBytes(StandardCharsets.UTF_8));
        return true;
    }
    private String toString(Object o){
        if (o == null)
            return "null";
        if (o instanceof Method){
            Method method = (Method) o;
            return method.getDeclaringClass().getCanonicalName()+"."+method.getName()+" "+ Type.getMethodDescriptor(method);
        }
        if (o instanceof Field){
            Field field = (Field) o;
            return field.getDeclaringClass().getCanonicalName()+"."+field.getName()+" "+ Type.getDescriptor(field.getType());
        }
        if (o instanceof Enum){
            Enum e = (Enum) o;
            return e.getDeclaringClass().getCanonicalName()+"."+e.name();
        }
        return o.toString();
    }

    @Override
    public void help() {

    }
}
