package top.tobycold.wordservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
    }
}
