package com.love.nchu.tool;

import com.love.nchu.demain.Sign_in_Status;
import com.love.nchu.demain.Sign_in_Time;
import com.love.nchu.service.FestivalServer;
import com.love.nchu.service.Sign_in_StatusServer;
import com.love.nchu.service.Sign_in_TimeServer;
import com.love.nchu.vo.MyDate;

public class SignInTool {

    public static  Sign_in_Time sign_in_time;


    public static void sign_in_status_check(Sign_in_StatusServer sign_in_statusServer, FestivalServer festivalServer,String username, String date)throws Exception{
        Sign_in_Status s = sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(username,date);

        int count = 6;
        if(s==null){

            boolean isFestival = FestivalTool.isFestival(date,festivalServer);
            count = (isFestival==true)?0:6;
            s = new Sign_in_Status(username,date,isFestival,count);

            sign_in_statusServer.save(s);
        }
    }


    protected static void saveTime(Sign_in_TimeServer sign_in_timeServer){

        Sign_in_Time s = new Sign_in_Time("(7:30~8:10)","(11:35~11:50)","(13:30~14:10)","(17:35~17:50)",
                "(18:30~19:10)","(20:55~23:00)","5-1","9-30","summer");


        Sign_in_Time s2 = new Sign_in_Time("(7:30~8:10)","(11:35~11:50)","(13:00~13:40)","(17:05~17:20)",
                "(18:30~19:10)","(20:55~23:00)","10-1","4-30","spring");

        sign_in_timeServer.save(s);
        sign_in_timeServer.save(s2);

    }



    public static Sign_in_Time getTime(Sign_in_TimeServer sign_in_timeServer){

        if(sign_in_time==null){
            if(sign_in_timeServer.count()<2){
                 saveTime(sign_in_timeServer);
            }
        int month = MyDate.getMonth();
        if(month>=5 && month<10){
            sign_in_time= sign_in_timeServer.getTimeBySeason("summer");

        }else{
            sign_in_time = sign_in_timeServer.getTimeBySeason("spring");
        }
        }
        return sign_in_time;
    }

    public static Sign_in_Status changeSignInit(Sign_in_Time sign_in_time,Sign_in_Status sign_in_status,String mon_in,String mon_out,String aft_in,String aft_out,String eve_in,String eve_out){
        if(mon_in.equals("未签到")) {
            sign_in_status.setMon_in(null);
        }
        else{
            System.out.println(sign_in_status.getMon_in());

            if(sign_in_status.getMon_in().equals("未签到")){
                sign_in_status.setMon_in(sign_in_time.getMon_in().substring(1,5));
            }
        }

        if(mon_out.equals("未签到"))
        {
            sign_in_status.setMon_out(null);
        }
        else{
            if(sign_in_status.getMon_out().equals("未签到"))
                sign_in_status.setMon_out(sign_in_time.getMon_out().substring(1,6));
        }

        if(aft_in.equals("未签到"))
        {

            sign_in_status.setAft_in(null);
        }
        else{
            if(sign_in_status.getAft_in().equals("未签到"))
                sign_in_status.setAft_in(sign_in_time.getAft_in().substring(1,6));
        }
        if(aft_out.equals("未签到"))
        {

            sign_in_status.setAft_out(null);
        }
        else{
            if(sign_in_status.getAft_out().equals("未签到"))
                sign_in_status.setAft_out(sign_in_time.getAft_out().substring(1,6));
        }
        if(eve_in.equals("未签到"))
        {

            sign_in_status.setEve_in(null);
        }
        else{
            if(sign_in_status.getEve_in().equals("未签到"))
                sign_in_status.setEve_in(sign_in_time.getEve_in().substring(1,6));
        }
        if(eve_out.equals("未签到"))
        {

            sign_in_status.setEve_out(null);
        }
        else{
            if(sign_in_status.getEve_out().equals("未签到"))
                sign_in_status.setEve_out(sign_in_time.getEve_out().substring(1,6));
        }
     sign_in_status.setCount(sign_in_status.getCount());
        return sign_in_status;
    }
}
