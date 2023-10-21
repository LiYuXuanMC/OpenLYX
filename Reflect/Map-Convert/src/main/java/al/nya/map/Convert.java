package al.nya.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Convert {
    static List<ClassMap> classMaps = new ArrayList<ClassMap>();
    static List<FieldMap> fieldMaps = new ArrayList<FieldMap>();
    static List<MethodMap> methodMaps = new ArrayList<MethodMap>();
    public static void main(String[] args) throws IOException {
        if (args.length == 1){
            String src;
            File f = new File(args[0]);
            FileInputStream fis = new FileInputStream(f);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            src = new String(bytes);
            String[] lines = src.split("\n");
            StringBuilder sb = new StringBuilder();
            ClassMap classMap = null;
            for (String line : lines) {
                if (line.startsWith("\t")){
                    String[] strings = line.replace("\t","").split(" ");
                    if (strings.length == 2){
                        FieldMap fieldMap = new FieldMap(classMap.mcp+"/"+strings[0],classMap.srg+"/"+strings[1]);
                        fieldMaps.add(fieldMap);
                    }else if (strings.length == 3){
                        MethodMap methodMap = new MethodMap(classMap.mcp+"/"+strings[0],classMap.srg+"/"+strings[2],strings[1],strings[1]);
                        methodMaps.add(methodMap);
                    }
                }else {
                    String[] strings = line.split(" ");
                    classMap = new ClassMap(strings[0],strings[1]);
                    classMaps.add(classMap);
                }
            }
            for (ClassMap map : classMaps) {
                sb.append("CL:").append(" ").append(map.mcp).append(" ").append(map.srg).append("\n");
            }
            for (FieldMap fieldMap : fieldMaps) {
                sb.append("FD:").append(" ").append(fieldMap.mcp).append(" ").append(fieldMap.srg).append("\n");
            }
            for (MethodMap methodMap : methodMaps) {
                sb.append("MD:").append(" ").append(methodMap.mcp).append(" ").append(methodMap.mcpSig).append(" ").append(methodMap.srg).append(" ").append(methodMap.srgSig).append("\n");

            }
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        }
    }
}
