package com.love.nchu.repository;

import com.love.nchu.demain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News,Integer> {

    @Modifying
    @Query("update News n set n.content=?2 where n.id=?1")
    void updateNews(Integer id,String content);

    @Query(value = "select * from news n order by n.date desc limit 4",nativeQuery = true)
    List<News> getRencentNews();

    @Modifying
    @Query( value = "update News n set n.position = 4")
    void newssetInit();

    @Modifying
    @Query("update News n set n.position=?1 where id=?2")
    void updateSet(int position,int id);


    @Query("select n from News n order by n.position ,n.date desc")
    List<News> getAll();
}
