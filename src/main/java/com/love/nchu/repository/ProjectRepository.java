package com.love.nchu.repository;

import com.love.nchu.demain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<Project,Integer> {


    @Query(value = "select p from Project p  order by p.position1,p.funding desc,p.date desc")
    List<Project> getAllPojects();


    //仅仅适用于 科研项目和横向项目
    @Query(value = "select p from Project p where p.type='科研项目' order by p.position2,p.funding desc,p.date desc")
    List<Project> getAllResearchProjects();

    @Query(value = "select p from Project p where p.type='横向项目' order by p.position4,p.funding desc,p.date desc")
    List<Project> getAllHengProjects();

    //仅仅适用于三小项目
    @Query(value = "select  p from Project p where p.type='三小项目' order by p.position3,p.date desc")
    List<Project> getAllThreeSmallProjects();

    List<Project> getAllByTypeAndUsername(String type,String username);

    List<Project> getAllByUsername(String username);

    @Modifying
    @Query("update Project p set p.position1 = 4  ")
    void position1SetInit();
    @Modifying
    @Query("update Project p set p.position2 = 8  ")
    void position2SetInit();
    @Modifying
    @Query("update Project p set p.position3 = 12  ")
    void position3SetInit();
    @Modifying
    @Query("update Project p set p.position4 = 16  ")
    void position4SetInit();


    @Modifying
    @Query("update Project  p set p.position1=?1 where p.id =?2")
    void updatePosition1Set(int position ,int id);

    @Modifying
    @Query("update Project  p set p.position2=?1 where p.id =?2")
    void updatePosition2Set(int position ,int id);

    @Modifying
    @Query("update Project  p set p.position3=?1 where p.id =?2")
    void updatePosition3Set(int position ,int id);

    @Modifying
    @Query("update Project  p set p.position4=?1 where p.id =?2")
    void updatePosition4Set(int position ,int id);

}
