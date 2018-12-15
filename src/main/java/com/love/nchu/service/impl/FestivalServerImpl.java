package com.love.nchu.service.impl;

import com.love.nchu.demain.Festival;
import com.love.nchu.repository.FestivalRepository;
import com.love.nchu.service.FestivalServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FestivalServerImpl implements FestivalServer {

    @Autowired
    FestivalRepository festivalRepository;
    @Override
    public void save(Festival festival) {
        festivalRepository.save(festival);
    }

    @Override
    public List<Festival> getAllByType(String type) {
        return festivalRepository.getAllByType(type);
    }

    @Override
    public void delete(Integer id) {
        festivalRepository.deleteById(id);
    }

    @Override
    public Festival getFestivalByDate(String date) {
        return festivalRepository.getFestivalByDate(date);
    }

    @Override
    public Festival getFestivalById(Integer id) {
        return festivalRepository.getOne(id);
    }
}
