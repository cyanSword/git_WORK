package org.gleeman.webConfig;

/**
 * Created by yjf on 2018/7/9.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
* 步骤三，配置WebMvcConfigurerAdapter，用这种方式运用拦截器可以对不同url进行做不同的判断，
* url中有bussiness进BussinessInterceptor拦截器，有terminal的url进TerminalInterceptor例如对终端是一套处理逻辑，
* 对服务端的http请求又是一套处理逻辑，创建的两个bean就是两个拦截器，视图控制器，可以进行网址的跳转，
* 此时如果地址输入 http:// ip:port/
 * 则自动跳转到 http:// ip:port/bussiness/hello,   CORS是为了防止跨域的问题而产生的的解决方案
* */

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(bussinessInterceptor()).addPathPatterns("/bussiness/**");
        registry.addInterceptor(terminalInterceptor()).addPathPatterns("/terminal/**");
    }

    @Bean
    public BussinessInterceptor bussinessInterceptor() {
        return new BussinessInterceptor();
    }
    @Bean
    public TerminalInterceptor terminalInterceptor() {
        return new TerminalInterceptor();
    }

    /**
     * 视图控制器配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/news/index").setViewName("/bussiness/hello");
    }

    /**
     * 跨域CORS配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST","GET","OPTIONS")
                .allowedOrigins("*");
    }
}
