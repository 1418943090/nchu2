package com.love.nchu.controller;

import com.love.nchu.demain.Festival;
import com.love.nchu.demain.Sign_in_Status;
import com.love.nchu.service.FestivalServer;
import com.love.nchu.service.Sign_in_StatusServer;
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
    @Autowired
    Sign_in_StatusServer sign_in_statusServer;
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

            List<Sign_in_Status> list = sign_in_statusServer.getSign_in_StatusByDate(date);
            for(Sign_in_Status s : list){

                s.setMon_in(null);s.setMon_out(null);
                s.setAft_in(null);s.setAft_out(null);
                s.setMon_in(null);s.setMon_out(null);
                if(type.equals("1")){
                    s.setFestival(true);
                    s.setCount(0);
                }
                else if(type.equals("2")){
                    s.setFestival(false);
                    s.setCount(6);
                }

                sign_in_statusServer.save(s);
            }
            return new ModelAndView("redirect:/festival");
        }
        result = "error";
        return new ModelAndView("redirect:/festival");
    }

    @PostMapping("/delete/festival")
    public ModelAndView delete(@RequestBody deleteFestivalVo deleteFestivalVo){


        for(Integer id : deleteFestivalVo.getListFestivalId())
        {

            Festival festival = festivalServer.getFestivalById(id);
            List<Sign_in_Status> list = sign_in_statusServer.getSign_in_StatusByDate(festival.getDate());

            for(Sign_in_Status s : list) {
                s.setMon_in(null);
                s.setMon_out(null);
                s.setAft_in(null);
                s.setAft_out(null);
                s.setMon_in(null);
                s.setMon_out(null);
                System.out.println(deleteFestivalVo.getFlag());
                if(deleteFestivalVo.getFlag().equals("2"))
                {
                    s.setFestival(true);
                    s.setCount(0);
                }
               else if(deleteFestivalVo.getFlag().equals("1")) {
                    s.setFestival(false);
                    s.setCount(6);
                }
                sign_in_statusServer.save(s);
            }
             festivalServer.delete(id);
            }
        return new ModelAndView("redirect:/festival");
    }
}
