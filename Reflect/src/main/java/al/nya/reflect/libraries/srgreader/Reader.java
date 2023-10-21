package al.nya.reflect.libraries.srgreader;

import al.nya.reflect.libraries.srgreader.map.*;
import al.nya.reflect.libraries.srgreader.utils.StringStream;
import al.nya.reflect.transform.ReflectNative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
    private String map;
    private MapFindList mapNodes;
    private List<Class> loadedClasses = new ArrayList<Class>();
    public Reader(String MCP2SrgMap){
        this.map = MCP2SrgMap;
    }
    public List<MapNode> preRead(){
        //loadedClasses.addAll(Arrays.asList(ReflectNative.getAllLoadedClasses()));
        mapNodes = new MapFindList();
        for (String s : map.split("\\n")) {
            try{
                String[] strings = s.split(" ");
                if (strings.length != 0){
                    if (getNodeType(strings[0]) == NodeType.Method){
                        if (strings.length == 5){
                            Signature sig = genSignature(strings[4]);
                            mapNodes.add(new MethodNode(getNodeType(strings[0]),strings[1],strings[3], sig,strings[4],strings[2]));
                        }
                    }else {
                        if (strings.length == 3){
                            mapNodes.add(new MapNode(getNodeType(strings[0]),strings[1],strings[2]));
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return mapNodes;
    }
    public Class getClassNative(String name){
        try {
            return Reader.class.getClassLoader().loadClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Signature genSignature(String sig) throws ClassNotFoundException {
        List<Class<?>> arg = new ArrayList<Class<?>>();
        Class<?> ret = void.class;
        boolean readClassName = false;
        boolean readArg = true;
        String className = "";
        StringStream ss = new StringStream(sig);
        while (ss.available()){
            String t = ss.read();
            if (readArg){
                if (readClassName){
                    if (!t.equals(";")){
                        className += t;
                    }else {
                        readClassName = false;
                        Class<?> target = Class.forName(className.replace("/","."));
                        arg.add(target);
                    }
                }else
                if (t.equals("(")){

                }else if (t.equals("Z")){
                    arg.add(boolean.class);
                }else if (t.equals("C")){
                    arg.add(char.class);
                }else if (t.equals("B")){
                    arg.add(byte.class);
                }else if (t.equals("S")){
                    arg.add(short.class);
                }else if (t.equals("I")){
                    arg.add(int.class);
                }else if (t.equals("F")){
                    arg.add(float.class);
                }else if (t.equals("J")){
                    arg.add(long.class);
                }else if (t.equals("D")){
                    arg.add(double.class);
                }else if (t.equals("L")){
                    readClassName = true;
                    className = "";
                }else if (t.equals(")")){
                    readArg = false;
                }
            }else {
                if (readClassName){
                    if (!t.equals(";")){
                        className += t;
                    }else {
                        readClassName = false;
                        ret = (Class.forName(className.replace("/",".")));
                    }
                }else if (t.equals("Z")){
                    ret = (boolean.class);
                }else if (t.equals("C")){
                    ret = (char.class);
                }else if (t.equals("B")){
                    ret = (byte.class);
                }else if (t.equals("S")){
                    ret = (short.class);
                }else if (t.equals("I")){
                    ret = (int.class);
                }else if (t.equals("F")){
                    ret = (float.class);
                }else if (t.equals("J")){
                    ret = (long.class);
                }else if (t.equals("D")){
                    ret = (double.class);
                }else if (t.equals("L")){
                    readClassName = true;
                    className = "";
                }else if (t.equals("V")){
                    ret = void.class;
                }
            }

        }
        Class<?>[] classes = new Class[arg.size()];
        classes = arg.toArray(classes);
        for (Class<?> aClass : classes) {
            if (aClass == null){
                System.out.println("class = null");
            }
        }
        return new Signature(classes,ret);
    }
    public Class<?>[] toArray(List<Class<?>> classes){
        return (Class<?>[]) classes.toArray();
    }
    public MapFindList getMapNodes() {
        return mapNodes;
    }

    private NodeType getNodeType(String s){
        switch (s) {
            case "CL:":
                return NodeType.Class;
            case "FD:":
                return NodeType.Field;
            case "MD:":
                return NodeType.Method;
        }
        return null;
    }
}
