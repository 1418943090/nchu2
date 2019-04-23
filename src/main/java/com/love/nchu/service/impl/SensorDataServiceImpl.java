package com.love.nchu.service.impl;

import com.love.nchu.demain.SensorData;
import com.love.nchu.repository.SensorDataRepository;
import com.love.nchu.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataServiceImpl implements SensorDataService {

    @Autowired
    SensorDataRepository sensorDataRepository;

    @Override
    public List<SensorData> getAll() {
        return this.sensorDataRepository.getAll();
    }
}
