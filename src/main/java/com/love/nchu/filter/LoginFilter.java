package com.love.nchu.filter;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ServletComponentScan
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
//        String currentUrl = req.getRequestURI();
//        System.out.println(currentUrl);
//        boolean isLogin =false;
//        Cookie[] cookies = req.getCookies();
//        if(cookies!=null){
//            for(Cookie cookie2 : cookies){
//                if(cookie2.getName().equals("user")){
//                    isLogin = true;
//                }
//            }
//        }
//        if(isLogin==true)
//            chain.doFilter(req,res);
//        else{
//
//
//            if(currentUrl.equals("/")||currentUrl.equals("/index")||currentUrl.equals("/more")||currentUrl.equals("/review")||currentUrl.substring(0,6).equals("/login")||currentUrl.substring(0,7).equals("/papers")||currentUrl.substring(0,7).equals("/people")||currentUrl.substring(0,9).equals("/registry"))
//            {
//                chain.doFilter(req,res);
//            }
//            else
//            if(currentUrl.substring(0,3).equals("/js")||currentUrl.substring(0,4).equals("/img") || currentUrl.substring(0,7).equals("/images"))
//            {
//                chain.doFilter(req,res);
//            }
//
//            else{
//                res.sendRedirect("/login");
//            }
//        }
        chain.doFilter(req,res);
    }
    @Override
    public void destroy() {

    }
}