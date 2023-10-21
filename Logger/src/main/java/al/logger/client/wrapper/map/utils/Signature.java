package al.logger.client.wrapper.map.utils;

import al.logger.client.wrapper.map.utils.StringStream;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Signature {
    @Getter
    private String signature;
    @Getter
    private Class<?>[] args;
    @Getter
    private Class<?> returnClass;
    public Signature(String sig){
        this.signature = sig;
    }
    @SneakyThrows
    public void parse(){
        StringStream stream = new StringStream(signature);
        List<Class<?>> classes = new ArrayList<>();
        boolean readClass = false;
        StringBuilder sb  = new StringBuilder();
        while (stream.available()){
            String next = String.valueOf(stream.next());
            if (readClass){
                if (next.equals(";")){
                    readClass = false;
                    classes.add(Class.forName(sb.toString().replace("/",".")));
                }else {
                    sb.append(next);
                }
                continue;
            }
            if (next.equals(")")){
                break;
            }
            switch (next){
                case "Z":
                    classes.add(boolean.class);
                    break;
                case "C":
                    classes.add(char.class);
                    break;
                case "B":
                    classes.add(byte.class);
                    break;
                case "S":
                    classes.add(short.class);
                    break;
                case "I":
                    classes.add(int.class);
                    break;
                case "F":
                    classes.add(float.class);
                    break;
                case "J":
                    classes.add(long.class);
                    break;
                case "D":
                    classes.add(double.class);
                    break;
                case "L":
                    readClass = true;
                    sb = new StringBuilder();
                    break;
                default:
                    break;
            }
        }
        args = classes.toArray(new Class[0]);
        classes.clear();
        while (stream.available()) {
            String next = String.valueOf(stream.next());
            if (readClass) {
                if (next.equals(";")) {
                    readClass = false;
                    classes.add(Class.forName(sb.toString().replace("/", ".")));
                } else {
                    sb.append(next);
                }
                continue;
            }
            switch (next) {
                case "Z":
                    classes.add(boolean.class);
                    break;
                case "C":
                    classes.add(char.class);
                    break;
                case "B":
                    classes.add(byte.class);
                    break;
                case "S":
                    classes.add(short.class);
                    break;
                case "I":
                    classes.add(int.class);
                    break;
                case "F":
                    classes.add(float.class);
                    break;
                case "J":
                    classes.add(long.class);
                    break;
                case "D":
                    classes.add(double.class);
                    break;
                case "L":
                    readClass = true;
                    sb = new StringBuilder();
                    break;
                default:
                    break;
            }
        }
        returnClass = classes.size() == 0 ? null : classes.get(0);
    }
}
