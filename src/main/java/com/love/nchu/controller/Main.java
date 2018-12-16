package com.love.nchu.controller;

import com.love.nchu.demain.User;
import com.love.nchu.service.Sign_in_TimeServer;
import com.love.nchu.service.TitleEditServer;
import com.love.nchu.tool.TitleTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@RestController
public class Main {
    @Autowired
    TitleEditServer titleEditServer;
    @Autowired
    Sign_in_TimeServer sign_in_timeServer;
    @GetMapping("/")
    public ModelAndView main(){
        return new ModelAndView("redirect:/index");
    }
    @GetMapping(value = "/index")
    public ModelAndView index(Model model, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if(user!=null)
        {
            model.addAttribute("username",user.getUsername());
        }
        model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
        return new ModelAndView("index","login-success",model);
    }
    @GetMapping("/more")
    public ModelAndView getMore(){
        return new ModelAndView("more");
    }
    @GetMapping("/forgetPassword")
    public ModelAndView forgetPassword(){
        return new ModelAndView("ForgetOrChangePassword");
    }
}
