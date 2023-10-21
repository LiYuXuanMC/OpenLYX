package al.logger.client.localization;

import java.util.Properties;

public class Localization {
    private static Properties lang = new Properties();
    public static String get(String key) {
        if(lang.containsKey(key)) {
            return lang.getProperty(key);
        }else if(EnglishBuiltIn.getLang().containsKey(key)){
            return EnglishBuiltIn.getLang().getProperty(key);
        }
        return key;
    }
}
