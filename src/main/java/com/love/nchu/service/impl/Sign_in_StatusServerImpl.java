package com.love.nchu.service.impl;

import com.love.nchu.demain.Sign_in_Status;
import com.love.nchu.repository.Sign_in_StatusRepository;
import com.love.nchu.service.Sign_in_StatusServer;
import com.love.nchu.vo.intervalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Sign_in_StatusServerImpl implements Sign_in_StatusServer {

    @Autowired
    Sign_in_StatusRepository sign_in_statusRepository;
    @Override
    public Sign_in_Status getSign_in_StatusByUsernaemAndDate(String username, String date) {
        return sign_in_statusRepository.findSign_in_StatusByUsernameAndDateEquals(username,date);
    }

    @Override
    public void save(Sign_in_Status sign_in_status) {
        sign_in_statusRepository.save(sign_in_status);
    }

    @Override
    public int signin(String time, String username, String date) {
        return sign_in_statusRepository.singin(time,username,date);
    }

    @Override
    public List<Sign_in_Status> getSign_in_StatusByDate(String date) {
        return sign_in_statusRepository.getSign_in_StatusByDate(date);
    }

    @Override
    public List<intervalVo> getIntervalCount(String startDate, String endDate) {
        return sign_in_statusRepository.getIntervalCount(startDate,endDate);
    }

    @Override
    public void updateSign_in_Status_Mon_in(String time, String date) {
        sign_in_statusRepository.updateSign_in_Status_Mon_in(time,date);
    }

    @Override
    public void updateSign_in_Status_Mon_out(String time, String date) {
        sign_in_statusRepository.updateSign_in_Status_Mon_out(time,date);
    }

    @Override
    public void updateSign_in_Status_Aft_in(String time, String date) {
        sign_in_statusRepository.updateSign_in_Status_Aft_in(time,date);
    }

    @Override
    public void updateSign_in_Status_Aft_out(String time, String date) {
        sign_in_statusRepository.updateSign_in_Status_Aft_out(time,date);
    }

    @Override
    public void updateSign_in_Status_Eve_in(String time, String date) {
        sign_in_statusRepository.updateSign_in_Status_Eve_in(time,date);
    }

    @Override
    public void updateSign_in_Status_Eve_out(String time, String date) {
        sign_in_statusRepository.updateSign_in_Status_Eve_out(time,date);
    }

    //    @Override
//    public List<weekShow> getWeekShow(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
//        return sign_in_statusRepository.getWeekShow(monday,tuesday,wednesday,thursday,friday,saturday,sunday);
//    }
}
