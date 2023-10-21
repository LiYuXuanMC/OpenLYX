package al.nya.reflect.libraries.slf4j;

public class LoggerFactory {

    public static Logger getLogger(Class clazz){
        return new Logger(clazz);
    }
}
