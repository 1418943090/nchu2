package com.love.nchu.tool;

import com.love.nchu.demain.User;
import com.love.nchu.security.SHAencrypt;
import com.love.nchu.service.UserServer;
import org.springframework.stereotype.Component;

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

        if(request.getSession().getAttribute("user")==null)
            return false;

        if(((User)request.getSession().getAttribute("user")).getUsername().equals(username))
            return true;
        return false;
    }
}
