package com.love.nchu.controller;

import com.love.nchu.demain.Paper;
import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.service.PaperServer;
import com.love.nchu.service.UserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RestController
public class PersonCenterController {
    @Autowired
    PaperServer paperServer;
    @Value("${spring.paper.vm.path}")
    String paper_vm_path;
    @Value("${spring.paper.ab.path}")
    String paper_ab_path;
    @Value("${spring.paper.path}")
    String paper_path;
    String nowuser;

    String filenamestr;
    @Autowired
    private UserInfoServer userInfoServer;
    @GetMapping("/basic_information")
    public ModelAndView basic_Information(Model model, HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");

        UserInfo userInfo = userInfoServer.getUserByUsername(user.getUsername());
        model.addAttribute("user_info",userInfo);
        return new ModelAndView("basic_information","basic_information",model);
    }
    @GetMapping("/userPapers")
    public ModelAndView paper(HttpServletRequest request,Model model)
    {

        User user = (User)request.getSession().getAttribute("user");

        nowuser = user.getUsername();
        if(user.getRole().equals("admin")){
            return new ModelAndView("redirect:/paper/admin");
        }
        List<Paper> list = paperServer.findPaperByUsername(user.getUsername());
        boolean hasPaper = true;
        if(list.size()==0) {
            hasPaper = false;
        }
        model.addAttribute("role",user.getRole());
        System.out.println(user.getRole());
        model.addAttribute("hasPaper",hasPaper);
        model.addAttribute("list",list);
        return new ModelAndView("userPapers","model",model);
    }




    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @PostMapping("/upload/paper")
    public String upload_paper(Paper paper, MultipartFile file){
        paper.setDate(new Date());
        paper.setUsername(nowuser);
        if(file!=null) {
            filenamestr = String.valueOf((int) (Math.random() * 100)) + file.getOriginalFilename();
            paper.setPath(paper_vm_path + nowuser + "/" + filenamestr);
            if( savePaper(file).equals("success")){
                paperServer.save(paper);
                return "success";
            }
            return "error";
        }
        paper.setName(userInfoServer.getNameByUsername(paper.getUsername()));
        paperServer.save(paper);
        return "success";
    }
    private String savePaper(MultipartFile file){
        File f = new File(paper_path+nowuser+"/");
        if(!f.exists()){
            f.mkdirs();
        }
        File realFile = new File(f,filenamestr);
        try{
          if(!realFile.exists()){
            realFile.createNewFile();
          }
          }catch(IOException e){
            e.printStackTrace();
            return "error";
         }
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(realFile))) {
            bos.write(file.getBytes());
            bos.flush();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            return "error";
        }catch(IOException e){
            System.out.println(e.getMessage());
            return "error";
        }
        return "success";
    }

}
