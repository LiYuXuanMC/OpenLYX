package al.logger.client.wrapper.LoggerMC;

import al.logger.client.wrapper.IWrapper;

public abstract class EnumWrapper extends IWrapper {
    public EnumWrapper(Object obj) {
        super(obj);
    }

    public static Enum[] values(){
        return new Enum[0];
    }
}
