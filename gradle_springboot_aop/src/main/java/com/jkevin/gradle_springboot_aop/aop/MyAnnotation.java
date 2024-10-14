package com.jkevin.gradle_springboot_aop.aop;

import java.lang.annotation.*;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/14 14:00
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
}
