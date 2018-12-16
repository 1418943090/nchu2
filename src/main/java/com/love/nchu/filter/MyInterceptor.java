package com.love.nchu.filter;

import com.love.nchu.demain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user =(User)request.getSession().getAttribute("user");

        if(user==null)
        {
            response.sendRedirect("/login");
            return false;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie2 : cookies){
                if(cookie2.getName().equals("sessionId")){
                    if(cookie2.getValue().equals(user.getPassword())){
                        return true;
                    }
                    else{
                        response.sendRedirect("/login");
                        return false;
                    }
                }
            }
        }

       return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
