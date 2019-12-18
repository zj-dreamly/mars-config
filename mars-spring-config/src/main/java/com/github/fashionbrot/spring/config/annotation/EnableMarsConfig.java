package com.github.fashionbrot.spring.config.annotation;

import com.github.fashionbrot.spring.config.MarsConfigBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author fashionbrot
 * @version 0.1.1
 * @date 2019/12/8 22:45
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MarsConfigBeanDefinitionRegistrar.class)
public @interface EnableMarsConfig {

    String APP_ID = "${mars.config.app-id}";

    String ENV_CODE = "${mars.config.env-code}";

    String SERVER_ADDRESS = "${mars.config.http.server-address}";
    /**
     * Whether to enable local caching
     */
    String ENABLE_LOCAL_CACHE = "${mars.config.enable-local-cache}";
    /**
     * Local cache file path
     */
    String LOCAL_CACHE_PATH = "${mars.config.local-cache-path}";

    /**
     * listen long poll log enabled
     */
    String ENABLE_ERROR_LOG = "${mars.config.enable-error-log}";

    /**
     * project name
     * @return
     */
    String appId() default APP_ID;

    /**
     * profiles env code
     * @return
     */
    String envCode() default ENV_CODE;

    /**
     * mars server address
     * @return
     */
    String serverAddress() default SERVER_ADDRESS;

    /**
     * listen long poll timeout  default 30000 ms
     */
    String listenLongPollMs() default "30000";

    /**
     * listen long poll log enabled
     */
    String enableErrorLog() default ENABLE_ERROR_LOG;

    /**
     * Whether to enable local caching
     * @return
     */
    String enableLocalCache() default ENABLE_LOCAL_CACHE;

    /**
     * Local cache file path
     * @return
     */
    String localCachePath() default LOCAL_CACHE_PATH;
}
