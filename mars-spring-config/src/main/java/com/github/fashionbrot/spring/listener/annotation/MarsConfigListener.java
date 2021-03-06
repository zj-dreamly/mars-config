package com.github.fashionbrot.spring.listener.annotation;


import com.github.fashionbrot.spring.enums.ConfigTypeEnum;

import java.lang.annotation.*;

/**
 * @author fashionbrot
 * @version 0.1.1
 * @date 2019/12/8 22:45
 *
 * Annotation that marks a method as a listener for mars Config change.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MarsConfigListener {

    /**
     * listener filename
     * @return
     */
    String fileName();

    /**
     * support properties text
     * @see ConfigTypeEnum
     * @return
     */
    ConfigTypeEnum type() default ConfigTypeEnum.PROPERTIES;

    /**
     * It indicates that the currently bound property is auto-refreshed when mars configuration is changed.
     * @return
     */
    boolean autoRefreshed() default false;
}
