package com.example.demo.config.intercepors;

import com.example.demo.beans.user;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //普通路径放行
        if ("/login/signin".equals(arg0.getRequestURI()) || "/login/loginhtml".equals(arg0.getRequestURI())||"/login/signup".equals(arg0.getRequestURI())) {
            return true;}
        //权限路径拦截
        Object object = arg0.getSession().getAttribute("user");
        System.out.println(object);
        if (null == object) {
            arg1.sendRedirect("/login/signin");
            return false;}
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}