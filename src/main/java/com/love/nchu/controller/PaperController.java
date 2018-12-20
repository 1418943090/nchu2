package com.love.nchu.controller;

import com.love.nchu.demain.Paper;
import com.love.nchu.service.PaperServer;
import com.love.nchu.service.TitleEditServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.tool.TitleTool;
import com.love.nchu.vo.MyDate;
import com.love.nchu.vo.PositionSetVo;
import com.love.nchu.vo.deletePaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Value("${spring.paper.path}")
    String paper_path;

    boolean paperSet = false;

    @GetMapping("/paper/admin")
    public ModelAndView admin(Model model){


        List<Paper> list = paperServer.getAllOeder();
        boolean hasPaper = true;
        if(list.size()==0) {
            hasPaper = false;
        }
        model.addAttribute("paperSet",paperSet);
        paperSet = false;
        model.addAttribute("hasPaper",hasPaper);
        model.addAttribute("list",list);
        return new ModelAndView("userPapers","model",model);
    }

    @PostMapping("/paper/set")
    public ModelAndView set(@RequestBody PositionSetVo paperSetVo){


        paperSet = true;
        paperServer.paperSetInit();
        paperServer.updatePosition(1,paperSetVo.getNo1());
        paperServer.updatePosition(2,paperSetVo.getNo2());
        paperServer.updatePosition(3,paperSetVo.getNo3());



      return  new ModelAndView("redirect:/userPapers/admin");
    }


   @PostMapping("/delete/paper")
    public ModelAndView deletePaper(@RequestBody deletePaperVo deletePaperVo){

       for(String s : deletePaperVo.getListPaperPath()){
           File file = new File(paper_path.substring(0,9)+s);
           if(file.exists()){
               file.delete();
           }
       }
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
    public ModelAndView papers(Model model)throws Exception{
       boolean isEmptyA = false;

       int year = MyDate.getYear();

       System.out.println(year);
       List<Paper> list= new ArrayList<>();
       HashMap<Integer,List> hashMap = new HashMap<>();
       while((list = paperServer.findPaperByYear(year)).size()!=0){
           hashMap.put(year,list);
           year--;
       }
       model.addAttribute("hashMap",hashMap);
       model.addAttribute("list",hashMap.get(MyDate.getYear()));
       //if(list.size()==0)
          //  isEmptyA = true;
       //model.addAttribute("isEmptyA",isEmptyA);
        //model.addAttribute("isEmptyB",false);
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
       return new ModelAndView("papers1","model",model);
    }

}
