package com.zjts.broadband.common.log;

import java.lang.annotation.*;

/**
 * 拦截Service
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {

    String description() default "";
}
