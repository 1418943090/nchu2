package com.love.nchu.repository;

import com.love.nchu.demain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FestivalRepository extends JpaRepository<Festival,Integer> {


    @Query("select f from Festival  f where f.type=?1")
    List<Festival> getAllByType(String type);

    @Query("select f from Festival f where f.date=?1")
    Festival getFestivalByDate(String date);
}
