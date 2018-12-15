package com.love.nchu.tool;

import com.love.nchu.demain.User;
import com.love.nchu.security.SHAencrypt;
import com.love.nchu.service.UserServer;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class LoginTool {
    public static boolean  loginValidator(UserServer userServer,String username,String password) throws Exception{
        User user = userServer.findUserByUsername(username);
        if(user==null)
            return false;
        if(user.getPassword().equals(SHAencrypt.encryptSHA(password))){
            return true;
        }
        return false;
    }


    public static boolean isLoginUser(HttpServletRequest request,String username) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie2 : cookies) {
                if (cookie2.getName().equals("user")) {
                    if (cookie2.getValue().equals(username))
                        return true;
                    else return false;
                }
            }
        }
        return false;
    }
}
