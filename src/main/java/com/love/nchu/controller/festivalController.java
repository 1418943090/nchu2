package com.love.nchu.controller;

import com.love.nchu.demain.Festival;
import com.love.nchu.service.FestivalServer;
import com.love.nchu.vo.deleteFestivalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RestController
public class festivalController {

    String result = "success";
    boolean flag = false;
    @Autowired
    FestivalServer festivalServer;
    @GetMapping("/festival")
    public ModelAndView festival(Model model) {

        if(flag==true && result.equals("error")){
             flag = false;
             model.addAttribute("result",result);
        }
        boolean isEmptyA = false;
        boolean isEmptyB = false;
        List<Festival> list1 = festivalServer.getAllByType("1");
        List<Festival> list2 = festivalServer.getAllByType("2");

        if(list1.size()==0)
            isEmptyA= true;
        if(list2.size()==0)
            isEmptyB = true;

        model.addAttribute("isEmptyA",isEmptyA);
        model.addAttribute("isEmptyB",isEmptyB);
        model.addAttribute("list1",list1);
        model.addAttribute("list2",list2);
        return new ModelAndView("festival","model",model);
    }
    @GetMapping("/festival/add/{note}/{date}/{type}")
    public ModelAndView add(@PathVariable String note,@PathVariable String date,@PathVariable String type){


        flag = true;
        Festival f = new Festival(note,date,type);
        Festival a = festivalServer.getFestivalByDate(date);
        if(a==null){
            result = "success";
            festivalServer.save(f);
            return new ModelAndView("redirect:/festival");
        }
        result = "error";
        return new ModelAndView("redirect:/festival");
    }

    @PostMapping("/delete/festival")
    public ModelAndView delete(@RequestBody deleteFestivalVo deleteFestivalVo){

        for(Integer id : deleteFestivalVo.getListFestivalId())
        {
            festivalServer.delete(id);
        }
        return new ModelAndView("redirect:/festival");
    }
}
