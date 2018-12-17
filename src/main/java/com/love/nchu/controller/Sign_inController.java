package com.love.nchu.controller;

import com.love.nchu.demain.*;
import com.love.nchu.service.FestivalServer;
import com.love.nchu.service.Sign_in_StatusServer;
import com.love.nchu.service.Sign_in_TimeServer;
import com.love.nchu.service.UserServer;
import com.love.nchu.tool.FestivalTool;
import com.love.nchu.tool.SignInTool;
import com.love.nchu.vo.MyDate;
import com.love.nchu.vo.Net;
import com.love.nchu.vo.intervalVo;
import com.love.nchu.vo.weekShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
@RestController
public class Sign_inController {
    @Autowired
    Sign_in_TimeServer sign_in_timeServer;
    @Autowired
    Sign_in_StatusServer sign_in_statusServer;

    @Autowired
    UserServer userServer;

    @Autowired
    FestivalServer festivalServer;

    @Value("${spring.net.public.ip}")
    String public_ip;

    String error="";
    Boolean isChange = false;
    public void cound_change(String date){

            List<Sign_in_Status> list = sign_in_statusServer.getSign_in_StatusByDate(date);
                for(Sign_in_Status s : list){
                    s.setCount(s.getCount());
                    sign_in_statusServer.save(s);
                }
    }

    @PostMapping("/sign_in/globalmodify")
    public ModelAndView globalmodify(String date,String mon_in,String mon_out,String aft_in,String aft_out,String eve_in,String eve_out,String signinstatus)throws  Exception{

        isChange = true;
//        if(MyDate.getDateTime(date)>=(MyDate.getDateTime(MyDate.getDate()))){
//            error = "只能修改过去日期签到信息";
//            return new ModelAndView("redirect:/sign_in/show/all/"+date);
//        }
        error="";
        boolean flag = false;
        List<Sign_in_Status> list = sign_in_statusServer.getSign_in_StatusByDate(date);
        if(list.size()==0){
            error = "没有查询到"+date+"的签到记录";
            return new ModelAndView("redirect:/sign_in/show/all/"+date);
        }

        if(FestivalTool.isFestival(date,festivalServer)){

            error = date+"是节假日哦,没有签到记录可以修改";
            return new ModelAndView("redirect:/sign_in/show/all/"+date);
        }

        if(mon_in!=null){
            flag = true;
            if(signinstatus.equals("未签到")){
                sign_in_statusServer.updateSign_in_Status_Mon_in(null,date);
            }
          else if(signinstatus.equals("已签到")){
                sign_in_statusServer.updateSign_in_Status_Mon_in(SignInTool.getTime(sign_in_timeServer).getMon_in().substring(1,5),date);
            }
        }
        if(mon_out!=null){
            flag = true;
            if(signinstatus.equals("未签到")){
                sign_in_statusServer.updateSign_in_Status_Mon_out(null,date);
            }
            else if(signinstatus.equals("已签到")){
                sign_in_statusServer.updateSign_in_Status_Mon_out(SignInTool.getTime(sign_in_timeServer).getMon_out().substring(1,6),date);
            }
        }
        if(aft_in!=null){
            flag = true;
            if(signinstatus.equals("未签到")){
                sign_in_statusServer.updateSign_in_Status_Aft_in(null,date);
            }
            else if(signinstatus.equals("已签到")){
                sign_in_statusServer.updateSign_in_Status_Aft_in(SignInTool.getTime(sign_in_timeServer).getAft_in().substring(1,6),date);
            }
        }
        if(aft_out!=null){
            flag = true;
            if(signinstatus.equals("未签到")){
                sign_in_statusServer.updateSign_in_Status_Aft_out(null,date);
            }
           else if(signinstatus.equals("已签到")){
                sign_in_statusServer.updateSign_in_Status_Aft_out(SignInTool.getTime(sign_in_timeServer).getAft_out().substring(1,6),date);
            }
        }
        if(eve_in!=null){
            flag = true;
            if(signinstatus.equals("未签到")){

                sign_in_statusServer.updateSign_in_Status_Eve_in(null,date);
            }
           else if(signinstatus.equals("已签到")){
                sign_in_statusServer.updateSign_in_Status_Eve_in(SignInTool.getTime(sign_in_timeServer).getEve_in().substring(1,6),date);
            }
        }
        if(eve_out!=null){
            flag = true;
            if(signinstatus.equals("未签到")){

                sign_in_statusServer.updateSign_in_Status_Eve_out(null,date);
            }
            else if(signinstatus.equals("已签到")){
                sign_in_statusServer.updateSign_in_Status_Eve_out(SignInTool.getTime(sign_in_timeServer).getEve_out().substring(1,6),date);
            }
        }
        if(flag==false){
            error = "你没有选择要更改的时间点";
        }
        cound_change(date);
        return new ModelAndView("redirect:/sign_in/show/all/"+date);
    }

    @PostMapping("/sign_in/modify")
    public ModelAndView modify(String username,String date,String mon_in,String mon_out,String aft_in,String aft_out,String eve_in,String eve_out){
        Sign_in_Time sign_in_time = SignInTool.getTime(sign_in_timeServer);
        Sign_in_Status sign_in_status = sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(username,date);
        sign_in_statusServer.save(SignInTool.changeSignInit(sign_in_time,sign_in_status,mon_in,mon_out,aft_in,aft_out,eve_in,eve_out));
        return new ModelAndView("redirect:/sign_in/show/all/"+date);
    }

    @GetMapping("/sign_in/show/interval/{startDate}/{endDate}")
    public ModelAndView interval(@PathVariable  String startDate,@PathVariable String endDate,Model model){

        boolean isEmpty = false;
        List<intervalVo> list = sign_in_statusServer.getIntervalCount(startDate,endDate);
        if(list.size()==0)
            isEmpty = true;
        model.addAttribute("list",list);
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("isEmpty",isEmpty);
        return new ModelAndView("intervalBody","model",model);
    }
    @GetMapping("/sign_in/show/week/{date}")
    public ModelAndView weekShow(Model model,@PathVariable  String date) throws Exception{

        Sign_in_Status sign_in_status ;
        List<String> list = MyDate.getWeekDays(date,"yyyy-MM-dd",true);
        List<weekShow> list2 = new ArrayList<>();
        List<String> user = userServer.getAllOrdinaryUsernmae();
        for(int i=0;i<user.size();i++){
            weekShow w = new weekShow(user.get(i),0,0,0,0,0,0,0);
            if(FestivalTool.isFestival(list.get(0),festivalServer)){
                w.setMonday(0);
                w.setMondayShow("节假日");

            }else{
                if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(0)))==null){
                    w.setMonday(0);
                    w.setMondayShow("无记录");
                }else {
                    w.setMonday(sign_in_status.getCount());
                    w.setMondayShow(String.valueOf(sign_in_status.getCount()));
                }
            }
            if(FestivalTool.isFestival(list.get(1),festivalServer)){
                w.setTuesday(0);
               w.setTuesdayShow("节假日");
            }else{
                if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(1)))==null){
                    w.setTuesday(0);
                    w.setTuesdayShow("无记录");
                }else{
                    w.setTuesday(sign_in_status.getCount());
                    w.setTuesdayShow(String.valueOf(sign_in_status.getCount()));
                }
            }
            if(FestivalTool.isFestival(list.get(2),festivalServer)){
                w.setWednesday(0);
                w.setWednesdayShow("节假日");
            }else{
                if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(2)))==null){
                    w.setWednesday(0);
                    w.setWednesdayShow("无记录");
                }else{
                    w.setWednesday(sign_in_status.getCount());
                    w.setWednesdayShow(String.valueOf(sign_in_status.getCount()));
                }
            }
            if(FestivalTool.isFestival(list.get(3),festivalServer)){
                w.setThursday(0);
               w.setTuesdayShow("节假日");
            }else{
                if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(3)))==null){
                    w.setThursday(0);
                    w.setThursdayShow("无记录");
                }else{
                    w.setThursday(sign_in_status.getCount());
                    w.setThursdayShow(String.valueOf(sign_in_status.getCount()));
                }
            }
            if(FestivalTool.isFestival(list.get(4),festivalServer)){
                w.setFriday(0);
                w.setFridayShow("节假日");

            }else{
                if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(4)))==null){
                    w.setFriday(0);
                    w.setFridayShow("无记录");
            }else{
                w.setFriday(sign_in_status.getCount());
                w.setFridayShow(String.valueOf(sign_in_status.getCount()));
            }
            }
            if(FestivalTool.isFestival(list.get(5),festivalServer)){
                w.setSaturday(0);
                w.setSaturdayShow("节假日");
            }else{
                if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(5)))==null){
                    w.setSaturday(0);
                    w.setSaturdayShow("无记录");
                }else{
                    w.setSaturday(sign_in_status.getCount());
                    w.setSaturdayShow(String.valueOf(sign_in_status.getCount()));
                }
            }
            if(FestivalTool.isFestival(list.get(6),festivalServer)){
                w.setSunday(0);
                w.setSundayShow("节假日");
            }else{
                if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(6)))==null){
                    w.setSunday(0);
                    w.setSundayShow("无记录");
                }else{
                    w.setSunday(sign_in_status.getCount());
                    w.setSundayShow(String.valueOf(sign_in_status.getCount()));
                }
            }
            list2.add(w);
            }
        model.addAttribute("date",date);
        model.addAttribute("datelist",list);
        model.addAttribute("list",list2);
        return new ModelAndView("weekBody","model",model);
    }
    @GetMapping("/sign_in/show/all/{date}")
    public ModelAndView show(@PathVariable String date, Model model)throws Exception{

        error=(isChange==true)?error:"";
        isChange = false;
        String d = date;
        if(date.equals("today")) {
            d = MyDate.getDate();
        }
        if(FestivalTool.isFestival(d,festivalServer)){
            model.addAttribute("sign_in_time",SignInTool.getTime(sign_in_timeServer));
            model.addAttribute("date",d);
            model.addAttribute("list",null);
            model.addAttribute("isFestival",true);
            model.addAttribute("isEmpty",false);
            model.addAttribute("error",error);
            return new ModelAndView("signinshow","model",model);
        }

        List<Sign_in_Status> list = new ArrayList<>();
        list = sign_in_statusServer.getSign_in_StatusByDate(d);
        if(list.size()==0){
            model.addAttribute("sign_in_time",SignInTool.getTime(sign_in_timeServer));
            model.addAttribute("date",d);
            model.addAttribute("list",null);
            model.addAttribute("isFestival",false);
            model.addAttribute("isEmpty",true);
            model.addAttribute("error",error);
            return new ModelAndView("signinshow","model",model);
        }
        model.addAttribute("sign_in_time",SignInTool.getTime(sign_in_timeServer));
        model.addAttribute("list",list);
        model.addAttribute("date",d);
        model.addAttribute("error",error);
        model.addAttribute("isFestival",false);
        System.out.println(d);
        return new ModelAndView("signinshow","model",model);
    }
    //用户点击签到后，判断是否满足签到条件不满足返回原因，满足后将签到时间保存到签到状态表中
    @GetMapping("/sign_in/in/{username}")
    public ErrorVo in(@PathVariable String username, HttpServletRequest request){

      ErrorVo errorVo = new ErrorVo("");

//      Festival f = festivalServer.getFestivalByDate(MyDate.getDate());
//      if(f!=null){
//          errorVo.setData("今天放假不用签到哦,世界那么大，出去看看吧");
//          return errorVo;
//      }
        //ip校验
      String ip = Net.getIP(request);
      System.out.println(ip);
//      if(!(ip.substring(0,8)).equals(public_ip)){
//        errorVo.setData("签到失败,不是实验室环境网络");
//        return errorVo;
//      }
       boolean isSignIn = false;
       boolean signIn = false;
       String date = MyDate.getDate();
       int signTime  = MyDate.getTimeInt();
       String signTimeStr = MyDate.getTimeString();
       Sign_in_Status sign_in_status = sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(username,date);
       HashMap<String,int[]> hash = MyDate.getTimeTable(SignInTool.getTime(sign_in_timeServer));
       Set<String> s = hash.keySet();
       for(String a : s){
           System.out.println(a);
           System.out.println(hash.get(a)[0]+"　"+hash.get(a)[1]);
           if (signTime >= hash.get(a)[0] && signTime<=hash.get(a)[1]) {
               switch (a){
                   case "mon_in":   if(sign_in_status.getMon_in().equals("未签到")) { sign_in_status.setMon_in(signTimeStr); } else{ isSignIn = true; } break;
                   case "mon_out":  if(sign_in_status.getMon_out().equals("未签到")) { sign_in_status.setMon_out(signTimeStr); } else{ isSignIn = true; } break;
                   case "aft_in":   if(sign_in_status.getAft_in().equals("未签到")) { sign_in_status.setAft_in(signTimeStr); } else{ isSignIn = true; } break;
                   case "aft_out":  if(sign_in_status.getAft_out().equals("未签到")) { sign_in_status.setAft_out(signTimeStr); } else{ isSignIn = true; } break;
                   case "eve_in":   if(sign_in_status.getEve_in().equals("未签到")) { sign_in_status.setEve_in(signTimeStr); } else{ isSignIn = true; } break;
                   case "eve_out":  if(sign_in_status.getEve_out().equals("未签到")) { sign_in_status.setEve_out(signTimeStr); } else{ isSignIn = true; } break;
                   default:break;
               }
               if(isSignIn == false) {
                   signIn = true;
                   sign_in_status.setCount(sign_in_status.getCount()-1);
                   sign_in_statusServer.save(sign_in_status);//将签到信息存储到数据库中
               }
             break;
           }
       }
       if(isSignIn==true){
           errorVo.setData("你已签到,不要重复签到哦!");
           return errorVo;
       }
       if(signIn == false){
           errorVo.setData("还没到签到时间哦");
           return errorVo;
       }
       return errorVo;
    }
    //用户点击签到信息后，返回签到状态信息
    //如果是新的一天则将空的签到信息存到数据中，确保有数据返回给前台
    @GetMapping("/sign_in/{username}")
    public ModelAndView Sign_in(@PathVariable String username, Model model)throws Exception{
        String s = MyDate.getDate();
        if(FestivalTool.isFestival(MyDate.getDate(),festivalServer)){
            return new ModelAndView("festivalday");
        }
        SignInTool.sign_in_status_check(sign_in_statusServer,festivalServer,username,s);
        model.addAttribute("sign_in_time", SignInTool.getTime(sign_in_timeServer));
        model.addAttribute("sign_in_status",sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(username,s));
        return new ModelAndView("signin","model",model);
    }
}
