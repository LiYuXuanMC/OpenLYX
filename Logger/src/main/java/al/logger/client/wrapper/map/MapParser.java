package al.logger.client.wrapper.map;

import al.logger.client.utils.FileUtils;
import al.logger.client.wrapper.map.utils.Signature;
import lombok.Getter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapParser {
    private String map;
    @Getter
    private List<Map> maps = new ArrayList<>();
    public MapParser(InputStream stream){
        map = new String(FileUtils.readStream(stream));
    }
    public void parse(){
        String[] lines = map.replace("\r","").split("\n");
        System.out.println("Start parsing Srg[" + lines.length + " lines]");

        for (String line :lines) {
            String[] s = line.split(" ");
            if (s.length > 1){
                if (s[0].equals("CL:")){
                    //Class
                    ClassMap cm = new ClassMap(getClassName(s[1]),getClassName(s[2]));
                    maps.add(cm);
                }
                if (s[0].equals("FD:")){
                    int last = s[1].lastIndexOf("/");
                    String className = s[1].substring(0,last);
                    String fieldName = s[1].substring(last).replace("/","");
                    last = s[2].lastIndexOf("/");
                    String classNameSrg = s[2].substring(0,last);
                    String fieldNameSrg = s[2].substring(last).replace("/","");
                    FieldMap fm = new FieldMap(getClassName(className),fieldName,getClassName(classNameSrg),fieldNameSrg);
                    maps.add(fm);
                }
                if (s[0].equals("MD:")){
                    int last = s[1].lastIndexOf("/");
                    String className = s[1].substring(0,last);
                    String methodName = s[1].substring(last).replace("/","");
                    Signature signature = new Signature(s[2]);
                    last = s[3].lastIndexOf("/");
                    String classNameSrg = s[3].substring(0,last);
                    String methodNameSrg = s[3].substring(last).replace("/","");
                    Signature signatureSrg = new Signature(s[4]);
                    MethodMap mm = new MethodMap(getClassName(className),methodName,signature,getClassName(classNameSrg),methodNameSrg,signatureSrg);
                    maps.add(mm);
                }
            }
        }
        System.out.println("Srg Parsed!");

    }
    private static String getClassName(String name){
        return name.replace("/",".").replace("$",".");
    }
}
