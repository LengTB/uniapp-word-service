package top.tobycold.wordservice.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SpringMVCConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
    }
}
