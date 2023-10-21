package com.reflectmc.reflect.wrapper.annotation.repeat;

import com.reflectmc.reflect.wrapper.annotation.WrapEnum;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapEnums {
    WrapEnum[] value();
}
