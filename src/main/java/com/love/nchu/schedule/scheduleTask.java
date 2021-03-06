package com.love.nchu.schedule;

import com.love.nchu.demain.UserInfo;
import com.love.nchu.service.FestivalServer;
import com.love.nchu.service.Sign_in_StatusServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.tool.SignInTool;
import com.love.nchu.vo.MyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class scheduleTask {
    @Autowired
    Sign_in_StatusServer sign_in_statusServer;
    @Autowired
    FestivalServer festivalServer;
    @Autowired
    UserInfoServer userInfoServer;
    //0 0 12 * * ?
    //@Scheduled(cron="0 5/1 * * * ?")
    @Scheduled(cron="0 0 1,12,6 * * ?")
    public void sign_in_Init() throws  Exception{
        List<UserInfo> userlist =userInfoServer.getUserInfoByIdentity("postgraduate");
        String today = MyDate.getDate("yyyy-MM-dd");
        for(UserInfo userInfo : userlist){
            SignInTool.sign_in_status_check(sign_in_statusServer,festivalServer,userInfo.getUsername(),today);
        }
       System.out.println(new Date());
    }
}
