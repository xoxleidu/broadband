package com.zjts.broadband.common.log;

import java.lang.annotation.*;

/**
 * 拦截Controller
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {

    String description() default "";
}
