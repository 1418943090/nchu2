package com.love.nchu.tool;

import com.love.nchu.service.FestivalServer;

public class FestivalTool {

        public static boolean isFestival(String s, FestivalServer festivalServer) throws  Exception{

            //如果是周末 判断是不是工作日
           // System.out.println(s);
           // System.out.println(MyDate.getWeek(s));
//            if(MyDate.getWeek(s)==1 || MyDate.getWeek(s)==7){
//                if(festivalServer.getFestivalByDateAndType2(s)!=null)
//                    return false;
//                return true;
//            }
           //不是周末 看看是不是法定节假日
            if(festivalServer.getFestivalByDateAndType1(s)!=null)
                return true;
             return false;
        }
}