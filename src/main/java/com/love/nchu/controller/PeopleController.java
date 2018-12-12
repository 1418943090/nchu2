package com.love.nchu.controller;

import com.love.nchu.demain.UserInfo;
import com.love.nchu.service.TitleEditServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.tool.TitleTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class PeopleController {

    @Autowired
    UserInfoServer userInfoServer;

    @Autowired
    TitleEditServer titleEditServer;
    @RequestMapping("/people")
    public ModelAndView people(Model model){
        List<UserInfo> teacher = userInfoServer.getUserInfoByIdentity("teacher");
        List<UserInfo> student = userInfoServer.getUserInfoByIdentity("student");
        model.addAttribute("teacher",teacher);
        model.addAttribute("student",student);
        model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
        return new ModelAndView("people","model",model);
    }
}
