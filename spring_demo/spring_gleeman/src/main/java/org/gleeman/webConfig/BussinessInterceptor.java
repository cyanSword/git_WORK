package org.gleeman.webConfig;

/**
 * Created by yjf on 2018/7/9.
 */
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BussinessInterceptor extends HandlerInterceptorAdapter {

    public BussinessInterceptor(){
        System.out.println("init BussinessInterceptor");
    }
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("BussinessInterceptor preHandle enter");
        return true;
    }
}