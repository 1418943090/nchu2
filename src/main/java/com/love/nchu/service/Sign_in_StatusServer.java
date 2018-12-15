package com.love.nchu.service;

import com.love.nchu.demain.Sign_in_Status;
import com.love.nchu.vo.intervalVo;

import java.util.List;

public interface Sign_in_StatusServer {


    Sign_in_Status getSign_in_StatusByUsernaemAndDate(String username, String date);
    void save(Sign_in_Status sign_in_status);
    int signin(String time,String username,String date);

    List<Sign_in_Status> getSign_in_StatusByDate(String date);

    List<intervalVo> getIntervalCount(String startDate,String endDate);
//    List<weekShow> getWeekShow(String monday,String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday);

    void updateSign_in_Status_Mon_in(String time,String date);
    void updateSign_in_Status_Mon_out(String time,String date);
    void updateSign_in_Status_Aft_in(String time,String date);
    void updateSign_in_Status_Aft_out(String time,String date);
    void updateSign_in_Status_Eve_in(String time,String date);
    void updateSign_in_Status_Eve_out(String time,String date);
}

