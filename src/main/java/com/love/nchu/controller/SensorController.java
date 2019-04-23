package com.love.nchu.controller;

import com.love.nchu.demain.SensorData;
import com.love.nchu.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sensorData")
public class SensorController {
    @Autowired
    SensorDataService sensorDataService;
   @RequestMapping("/")
    public ModelAndView sensorDate(Model model){
       List<SensorData> list = this.sensorDataService.getAll();
       if(list!=null){
           List<List<SensorData>> result = new ArrayList<>();
           List<SensorData> data = new ArrayList<>();
           for(int i=0;i<list.size();i++)
           {
               data.add(list.get(i));
               if(i!=0 && i%3==0){
                   result.add(data);
                   data = new ArrayList<>();
               }
           }
           if(data.size()>0)
               result.add(data);
           model.addAttribute("list",result);
       }
       return new ModelAndView("sensorDateIndex","model",model);
   }

   @RequestMapping("/getData")
    public List<SensorData> getData(){

       List<SensorData> list = this.sensorDataService.getAll();


       return list;
   }
  // @RequestMapping("/getSensorData")
   // public

}

