package com.love.nchu.controller;


import com.love.nchu.demain.User;
import com.love.nchu.service.TitleEditServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.tool.TitleTool;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserInfoServer userInfoServer;
    @Autowired
    TitleEditServer titleEditServer;
    @GetMapping("/user")
    public ModelAndView user( Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if(user==null)
            return new ModelAndView("redirect:/login");
        model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
        if(user.getRole().equals("admin")){
            return new ModelAndView("person_center_admin", "model", model);
        }
        else
        if (user.getRole().equals("postgraduate")) {
            return new ModelAndView("person_center_postgraduate", "model", model);
        }
        return new ModelAndView("person_center_undergraduate", "model", model);
    }
}
