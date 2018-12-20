package com.love.nchu.repository;

import com.love.nchu.demain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PaperRepository extends JpaRepository<Paper,Integer> {

    List<Paper> findPaperByUsername(String usernaem);
    List<Paper> getAllByIdIsNotNull();
    void deletePaperById(Integer id);
    @Modifying
    @Query("update Paper p set p.title=?2 where p.id = ?1")
    void updatePaper(int id,String title);

    @Query("select p from Paper p where p.name like %?1% or p.title like %?1%")
    List<Paper> findPaperByNameLikeOrTitleLike(String value);
    @Query("select p from Paper p where p.name like %?1%")
    List<Paper> findPaperByNameLike(String name);
    @Query("select p from Paper p where p.title like %?1%")
    List<Paper> findPaperByTitleLike(String title);


    @Query(value = "select * from paper p where year(p.date)=?1 order by p.position ,p.date desc" ,nativeQuery = true)
    List<Paper> findPaperByYear(int year);


    @Modifying
    @Query( value = "update Paper p set p.position = 4")
    void papersetInit();

    @Modifying
    @Query("update Paper p set p.position=?1 where id=?2")
    void updatePosition(int position,int id);


    @Query("select p from Paper p order by p.position ,p.date desc")
    List<Paper> getAllOeder();



}
