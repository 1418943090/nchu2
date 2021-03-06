package com.love.nchu.service;

import com.love.nchu.demain.Festival;

import java.util.List;

public interface FestivalServer {

    void save(Festival festival);
    List<Festival> getAllByType(String type);

    void delete(Integer id);
    Festival getFestivalByDate(String date);
    Festival getFestivalById(Integer id);

    Festival getFestivalByDateAndType1(String date);

    Festival getFestivalByDateAndType2(String date);
}
