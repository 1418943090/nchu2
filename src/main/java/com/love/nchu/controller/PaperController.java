package com.love.nchu.controller;

import com.love.nchu.demain.Paper;
import com.love.nchu.service.PaperServer;
import com.love.nchu.service.TitleEditServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.tool.TitleTool;
import com.love.nchu.vo.deletePaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class PaperController {
    @Autowired
    private PaperServer paperServer;
    @Autowired
    private TitleEditServer titleEditServer;
    @Autowired
    private UserInfoServer userInfoServer;
    //删除论文控制器
   @PostMapping("/delete/paper")
    public ModelAndView deletePaper(@RequestBody deletePaperVo deletePaperVo){
        for(Integer i : deletePaperVo.getListPaperId()){
            paperServer.deletePaper(i);
        }
       return new ModelAndView("redirect:/userPapers/"+deletePaperVo.getUserName());
    }
    @PostMapping("/update/paper")
    public String updatePaper(int updateId,String updateTitle){
       paperServer.updatePaper(updateId,updateTitle);
       return null;
    }
    @GetMapping("/papers")
    public ModelAndView papers(Model model){
       boolean isEmptyA = false;
       List<Paper> list = paperServer.getAllPapers();
       model.addAttribute("list",list);
       if(list.size()==0)
            isEmptyA = true;
       model.addAttribute("isEmptyA",isEmptyA);
        model.addAttribute("isEmptyB",false);
       model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
       return new ModelAndView("papers","model",model);
    }
    @GetMapping("/papers/search/{condition}/{value}")
    public ModelAndView search(@PathVariable String condition,@PathVariable  String value,Model model){
       List list = null;
       if(condition.equals("all")){
            list =  paperServer.getPaperByNameLikeOrTitleLike(value);
           model.addAttribute("list",list);
       }
       else if(condition.equals("author")){
           list = paperServer.getPaperByNameLike(value);
           System.out.println(list);
           model.addAttribute("list",list);
       }else if(condition.equals("title")){
           list = paperServer.getPaperByTitleLike(value);
           model.addAttribute("list",list);
       }
       boolean isEmptyB = false;
       if(list.size()==0)
           isEmptyB = true;

       model.addAttribute("isEmptyB",isEmptyB);
       model.addAttribute("isEmptyA",false);
       model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
       return new ModelAndView("papers","model",model);
    }

}
