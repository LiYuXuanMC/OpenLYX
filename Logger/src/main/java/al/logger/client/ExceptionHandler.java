package al.logger.client;

import al.logger.client.utils.ChatUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExceptionHandler {
    public static boolean outPut = false;
    public static void handle(Throwable throwable){
        throwable.printStackTrace();
        if (outPut){
            ChatUtils.error(getException(throwable));
            Logger.getInstance().getAgent().nativeLog(getException(throwable));
        }
    }
    private static String getException(Throwable throwable){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos);
        throwable.printStackTrace(printStream);
        return bos.toString();
    }
}
