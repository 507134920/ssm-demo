package com.ssm.config;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("==============异常开始=============");
        //如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
        if (ex instanceof UnauthorizedException) {
            System.out.println("异常处理！！！！！！！！！！！");
            WebUtils.getAndClearSavedRequest(request);
            try {
                WebUtils.redirectToSavedRequest(request, response, "/error.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ex.printStackTrace();
        System.out.println("==============异常结束=============");
        return null;
    }
}

