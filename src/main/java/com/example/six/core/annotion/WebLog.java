package com.example.six.core.annotion;


import java.lang.annotation.*;

/**
 * AOP日志功能
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {
    String value() default "";
}
