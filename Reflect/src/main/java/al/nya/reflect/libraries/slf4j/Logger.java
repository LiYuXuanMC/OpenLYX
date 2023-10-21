package al.nya.reflect.libraries.slf4j;

public class Logger {
    private Class logClass;
    public Logger(Class log){
        logClass = log;
    }
    public void trace(String s,Object... objects){
        //String.format(s,objects);
    }
    public boolean isTraceEnabled(){
        return false;
    }
    public void error(String s,Object... objects){
        System.out.println("[Error] "+ String.format(s,objects));
    }
}
