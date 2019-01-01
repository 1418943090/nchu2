package com.love.nchu.controller;

import com.love.nchu.demain.Project;
import com.love.nchu.demain.User;
import com.love.nchu.service.ProjectServer;
import com.love.nchu.tool.ProjectsTool;
import com.love.nchu.vo.PositionSetVo;
import com.love.nchu.vo.deleteProjectsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@RestController
public class ProjectsController {
    @Autowired
    ProjectServer projectServer;
    String url="all";
    User user ;
    Boolean projectSet = false;
    @GetMapping("/projects/{type}")
    public ModelAndView all(@PathVariable String type , Model model){
        List<Project> list = new ArrayList<>();
        switch (type){
            case"all":list=projectServer.getAllProjects();break;
            case"researchProject":list=projectServer.getAllResearchProjects();break;
            case"threesmallProject":list=projectServer.getAllThreeSmallProject();break;
            case"hengProject":list=projectServer.getAllHengProjects();break;
            default: break;
        }
        System.out.println(list);
        model.addAttribute("list",list);
        return new ModelAndView("Projects","model",model);
    }
    @GetMapping("/projectsCenter")
    public ModelAndView projectsCenter(){
        return new ModelAndView("redirect:/projectCenter/type/all");
    }
    @GetMapping("/projectCenter/type/{type}")
    public ModelAndView type(@PathVariable String type,Model model,HttpServletRequest request){
         url = type;
         System.out.println(type);
         List<Project> list = new ArrayList<>();
         User user = (User) request.getSession().getAttribute("user");
         if(user.getRole().equals("admin")){
         switch (type){
             case"all":list=projectServer.getAllProjects();break;
             case"researchProject":list=projectServer.getAllResearchProjects();break;
             case"threesmallProject":list=projectServer.getAllThreeSmallProject();break;
             case"hengProject":list=projectServer.getAllHengProjects();break;
             default: break;
         }
         }
         else {
             String typeStr ="";
             if(type.equals("all"))
                 typeStr = "所有项目";
             else if(type.equals("researchProject"))
                 typeStr = "科研项目";
             else if(type.equals("threesmallProject"))
                 typeStr = "三小项目";
             else if(type.equals("hengProject"))
                 typeStr = "横向项目";
             switch (type){
                 case "all":list = projectServer.getAllByUsername(user.getUsername());break;
                 default: list = projectServer.getAllByTypeAndUsername(typeStr,user.getUsername());
             }
         }
         model.addAttribute("projectSet",projectSet);
         projectSet = false;
         model.addAttribute("list",list);
         model.addAttribute("hasProjects",list.size()==0?false:true);
         model.addAttribute("type",type);
         model.addAttribute("role",user.getRole());
         return new ModelAndView("projectsCenter","model",model);
    }

    @PostMapping("/projectCenter/set")
    public  ModelAndView set(@RequestBody PositionSetVo positionSetVo)
    {
        System.out.println(positionSetVo.toString());
         switch (positionSetVo.getType()){
            case "所有项目":
                url = "all";
                projectServer.position1SetInit();
                projectServer.updatePosition1Set(1,positionSetVo.getNo1());
                projectServer.updatePosition1Set(2,positionSetVo.getNo2());
                projectServer.updatePosition1Set(3,positionSetVo.getNo3());
                break;
            case "科研项目":
                url = "researchProject";
                projectServer.position2SetInit();
                projectServer.updatePosition2Set(5,positionSetVo.getNo1());
                projectServer.updatePosition2Set(6,positionSetVo.getNo2());
                projectServer.updatePosition2Set(7,positionSetVo.getNo3());
                break;
            case "三小项目":
                url="threesmallProject";
                projectServer.position3SetInit();
                projectServer.updatePosition3Set(9,positionSetVo.getNo1());
                projectServer.updatePosition3Set(10,positionSetVo.getNo2());
                projectServer.updatePosition3Set(11,positionSetVo.getNo3());
                break;
            case "横向项目":
                url="hengProject";
                projectServer.position4SetInit();
                projectServer.updatePosition4Set(13,positionSetVo.getNo1());
                projectServer.updatePosition4Set(14,positionSetVo.getNo2());
                projectServer.updatePosition4Set(15,positionSetVo.getNo3());
                break;
                default:break;
        }
        projectSet = true;
        return new ModelAndView("redirect:/projectCenter/type/"+url);
    }

    @PostMapping("/projectCenter/delete")
    public ModelAndView delete(@RequestBody  deleteProjectsVo deleteProjectsVo){

        Project project ;
        for(Integer id : deleteProjectsVo.getListProjectsId()){
            project = projectServer.findById(id);
            ProjectsTool.deletePic(project.getPic());
            ProjectsTool.deleteDocument(project.getDocument());
            projectServer.deleteById(id);
        }
        return new ModelAndView("redirect:/projectCenter/type/"+url);
    }
    @PostMapping("/projectCenter/edit")
    public ModelAndView edit(Project project,MultipartFile file_pic,MultipartFile file_document){
        Project project1 = projectServer.findById(project.getId());
        project.setPic(project1.getPic());
        project.setDocument(project1.getDocument());
        project.setPosition1(project1.getPosition1());
        project.setPosition2(project1.getPosition2());
        project.setPosition3(project1.getPosition3());
        project.setPosition4(project1.getPosition4());
        project.setUsername(project1.getUsername()) ;

        if(file_pic!=null && project.getPic()!=null){
          ProjectsTool.UpdatePic(project,file_pic);
        }else if(file_pic!=null && project.getPic()==null){
            ProjectsTool.savePic(project,file_pic);
        }

        if(file_document!=null && project.getDocument()!=null){
           ProjectsTool.UpdateDocument(project,file_document);
        }else if(file_document!=null && project.getDocument()==null){
            ProjectsTool.saveDocument(project,file_document);
        }
        projectServer.save(project);
        return new ModelAndView("redirect:/projectCenter/type/"+url);
    }
    @PostMapping("/projectCenter/add")
    public ModelAndView add(Project project, MultipartFile file_pic, MultipartFile file_document, HttpServletRequest request){

        User user =(User) request.getSession().getAttribute("user");
        project.setUsername(user.getUsername());

        if(file_pic!=null)
           ProjectsTool.savePic(project,file_pic);
        if(file_document!=null)
            ProjectsTool.saveDocument(project,file_document);
        projectServer.save(project);
        return new ModelAndView("redirect:/projectsCenter");
    }
}
