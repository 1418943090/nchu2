package com.love.nchu.controller;

import com.love.nchu.demain.Paper;
import com.love.nchu.service.PaperServer;
import com.love.nchu.service.TitleEditServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.tool.FileServer;
import com.love.nchu.tool.TitleTool;
import com.love.nchu.vo.MyDate;
import com.love.nchu.vo.PositionSetVo;
import com.love.nchu.vo.deletePaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    @Value("${spring.paper.vm.path}")
    String paper_vm_path;


    @Autowired
    FileServer fileServer;
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
        model.addAttribute("role","admin");
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
      return  new ModelAndView("redirect:/userPapers");
    }
   @PostMapping("/delete/paper")
    public ModelAndView deletePaper(@RequestBody deletePaperVo deletePaperVo){

       for(String s : deletePaperVo.getListPaperPath()){
           File file = new File(paper_path.substring(0,13)+s);
           if(file.exists()){
               file.delete();
           }
       }
        for(Integer i : deletePaperVo.getListPaperId()){
            paperServer.deletePaper(i);
        }
       return new ModelAndView("redirect:/userPapers");
   }

    @PostMapping("/update/paper")
    public String updatePaper(int updateId, String updateTitle, String updatePublishDate, MultipartFile updateFile)throws Exception{



        Paper paper = paperServer.findPaperById(updateId);
        paper.setTitle(updateTitle);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s="";
        paper.setPublish_date(sdf.parse(updatePublishDate));

        System.out.println(updateFile.getOriginalFilename().length());
        System.out.println(updateFile.getOriginalFilename().equals(" "));
        System.out.println(updateFile.getOriginalFilename());
        if(updateFile!=null && updateFile.getOriginalFilename().length()>0 && paper.getPath()!=null){
            System.out.println("aaaa");
            fileServer.updatePaperFile(paper_path,paper.getPath(),updateFile);
        }else if(updateFile!=null  && updateFile.getOriginalFilename().length()>0 && paper.getPath()==null){
            System.out.println("BBB");
            s = fileServer.saveFile(updateFile,paper_path+paper.getUsername()+"/",paper_vm_path);
            System.out.println(s);
            paper.setPath(s.substring(0,8)+paper.getUsername()+"/"+s.substring(8));
        }

        paperServer.save(paper);

      // paperServer.updatePaper(updateId,updateTitle);
       return null;
    }
    @GetMapping("/papers")
    public ModelAndView papers(Model model)throws Exception{
       int year = MyDate.getYear();
       int k=5;
       System.out.println(year);
       List<Paper> list= new ArrayList<>();
       HashMap<Integer,List> hashMap = new LinkedHashMap<>();
       while(k-->0){
           if((list = paperServer.findPaperByYear(year)).size()!=0 ){
               hashMap.put(year,list);
           }
           year--;
       }
       model.addAttribute("hashMap",hashMap);
       model.addAttribute("list",hashMap.get(MyDate.getYear()));
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
