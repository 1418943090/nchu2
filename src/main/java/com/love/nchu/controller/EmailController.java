package com.love.nchu.controller;


import com.love.nchu.demain.ErrorVo;
import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.service.MailServer;
import com.love.nchu.service.TitleEditServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.service.UserServer;
import com.love.nchu.tool.EmailTool;
import com.love.nchu.tool.PasswordTool;
import com.love.nchu.tool.TitleTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class EmailController {

    @Autowired
    TitleEditServer titleEditServer;

    Date date ;

    String code;
    String  useremail;
    @Autowired
    MailServer mailServer;

    @Autowired
    UserServer userServer;

    @Autowired
    UserInfoServer userInfoServer;

    boolean step1 = false;

    @GetMapping("/emailValidator/index")
    public ModelAndView index(){
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/emailValidator")
    public ModelAndView emailValidator(Model model){
        step1 = true;
        model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
      return new ModelAndView("emailValidator","model",model);
    }



    @PostMapping("/emailValidator/getVcode")
    public void emailValidator(@RequestBody String email){


        date = new Date();
        System.out.println(email);
        useremail = email;
        code = EmailTool.getCode();
        mailServer.sendSimpleMail(email,"邮箱验证","本次的验证码为(十分钟内有效):"+code);
    }
    @PostMapping("/emailValidator/step1")
    public ErrorVo vcodeCheck(@RequestBody String vcode, HttpServletRequest request){
        ErrorVo errorVo = new ErrorVo("");
        if(!PasswordTool.vcoedCheck(code,vcode)||date==null){
            errorVo.setData("验证码错误");
            return errorVo;
        }
        if(!EmailTool.isNotExpiredCheck(date,new Date())){
            errorVo.setData("验证码已过期，请重新获取");//验证码过期
            return errorVo;
        }

        User user = (User)request.getSession().getAttribute("user");
        if(!user.getEmail().equals(useremail))
        {
            errorVo.setData(useremail+"并不是当前账号的绑定Email");
            return errorVo;
        }
        return errorVo;
    }

    @GetMapping("/emailValidator/changeBindEmail")
    public ModelAndView vcodeCheck2(Model model){

        model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
        return new ModelAndView("changeBindEmail");
    }





    @PostMapping("/emailValidator/step3")
    public ErrorVo vcodeCheck3(@RequestBody String vcode, HttpServletRequest request){
        ErrorVo errorVo = new ErrorVo("");
        if(!PasswordTool.vcoedCheck(code,vcode)||date==null){
            errorVo.setData("验证码错误");
            return errorVo;
        }
        if(!EmailTool.isNotExpiredCheck(date,new Date())){
            errorVo.setData("验证码已过期，请重新获取");//验证码过期
            return errorVo;
        }

        if(userServer.findUserByEmail(useremail)!=null){
            errorVo.setData("邮箱已被注册");
            return errorVo;
        }

        User user = (User)request.getSession().getAttribute("user");
        user.setEmail(useremail);
        userServer.save(user);
        UserInfo userInfo = userInfoServer.getUserByUsername(user.getUsername());
        userInfo.setEmail(useremail);
        userInfoServer.save(userInfo);

        return errorVo;
    }

    @GetMapping("/emailValidator/step4")
    public ModelAndView vcodeCheck4() {
        return new ModelAndView("changeBindEmailSuccess");
    }
}
