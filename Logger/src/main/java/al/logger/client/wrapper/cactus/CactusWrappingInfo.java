package al.logger.client.wrapper.cactus;

import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CactusWrappingInfo {
    @Getter
    private Field wrapper;
    @Getter
    private Class<?> targetClass;
    @Getter
    private Annotation annotation;
    public CactusWrappingInfo(Field wrapper, Class<?> targetClass, Annotation annotation){
        this.wrapper = wrapper;
        this.targetClass = targetClass;
        this.annotation = annotation;
    }
}
