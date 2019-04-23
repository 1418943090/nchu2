package com.love.nchu.repository;

import com.love.nchu.demain.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData,Integer> {

    @Query("select s from SensorData  s ")
    List<SensorData> getAll();

}
