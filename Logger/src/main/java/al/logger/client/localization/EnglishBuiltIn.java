package al.logger.client.localization;

import lombok.Getter;

import java.util.Properties;

public class EnglishBuiltIn {
    @Getter
    private final static Properties lang = new Properties();
    static {
        lang.setProperty("Module.description", "No description");
    }
}
