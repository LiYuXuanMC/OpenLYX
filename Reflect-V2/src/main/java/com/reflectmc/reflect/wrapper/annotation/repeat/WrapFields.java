package com.reflectmc.reflect.wrapper.annotation.repeat;

import com.reflectmc.reflect.wrapper.annotation.WrapField;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapFields {
    WrapField[] value();
}
