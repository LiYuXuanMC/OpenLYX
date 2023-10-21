package com.reflectmc.reflect.wrapper.annotation.repeat;


import com.reflectmc.reflect.wrapper.annotation.WrapObject;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapObjects {
    WrapObject[] value();
}
