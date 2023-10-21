package al.nya.proxy;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
public class Logger {
    public static boolean debug = false;
    public static void info(String message){
        System.out.println(ansi().fg(GREEN).a("[INFO] ").fg(DEFAULT).a(message).reset());
    }
    public static void warn(String message){
        System.out.println(ansi().fg(YELLOW).a("[WARN] ").fg(DEFAULT).a(message).reset());
    }
    public static void error(String message){
        System.out.println(ansi().fg(RED).a("[ERROR] ").fg(DEFAULT).a(message).reset());
    }
    public static void debug(String message){
        if (debug)
            System.out.println(ansi().fg(BLUE).a("[DEBUG] ").fg(DEFAULT).a(message).reset());
    }
}
