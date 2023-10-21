package al.nya.reflect.libraries.srgreader.utils;

public class StringStream {
    private String buf;
    private int count = 0;
    public StringStream(String s){
        buf = s;
    }
    public String read(){
        String r = String.valueOf(buf.charAt(count));
        count ++;
        return r;
    }
    public boolean available(){
        return count + 1 != buf.length();
    }
}
