package com.love.nchu.controller;

import com.love.nchu.demain.ErrorVo;
import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.demain.editUserInfo;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.tool.RegistryTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class basicInformationController {
    @Autowired
    UserInfoServer userInfoServer;
    @Value("${spring.img.vm.path}")
    String img_vm_path;

    @Value("${spring.img.ab.path}")
    String img_ab_path;

    @Value("${spring.img.path}")
    String img_path;

    @RequestMapping("/update/pic")
    public ModelAndView updatePic(MultipartFile pic, HttpServletRequest request)
    {
        System.out.println(pic.getOriginalFilename());
        User user =(User) request.getSession().getAttribute("user");
        UserInfo userInfo = userInfoServer.getUserByUsername(user.getUsername());
        RegistryTool.Save_Image(pic,userInfo,img_path,img_vm_path);
        userInfoServer.save(userInfo);
        System.out.println(userInfo.toString());
        return new ModelAndView("redirect:/basic_information/"+user.getUsername());
    }


    @RequestMapping("/update/basic_information")
    public Object update(@RequestBody  editUserInfo userInfo, Model model){
        ErrorVo error = new ErrorVo("");
       // UserInfo u = userInfoServer.getUserByEmail(userInfo.getEditEmail());
       // if(u!=null && !u.getUsername().equals(userInfo.getEditUsername())){
            error.setData("邮箱已被注册");
        //}else{
        UserInfo  u = userInfoServer.getUserByTel((userInfo.getEditTel()));
            if(u!=null && !u.getUsername().equals(userInfo.getEditUsername()))
            {
                error.setData("电话号码已被注册");
            }

        System.out.println(userInfo);
        if(error.getData().equals("")){
            userInfoServer.updateUserInfo(userInfo);
        }
        return error;
    }
}
