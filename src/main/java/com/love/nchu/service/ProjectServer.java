package com.love.nchu.service;

import com.love.nchu.demain.Project;

import java.util.List;

public interface ProjectServer {

    List<Project> getAllProjects();

    void save(Project project);
    void deleteById(Integer id);
    Project findById(Integer id);

    void position1SetInit();
    void position2SetInit();
    void position3SetInit();
    void position4SetInit();

    void updatePosition1Set(int position,int id);
    void updatePosition2Set(int position,int id);
    void updatePosition3Set(int position,int id);
    void updatePosition4Set(int position,int id);


    List<Project> getAllResearchProjects();
    List<Project> getAllHengProjects();
    List<Project> getAllThreeSmallProject();

   List<Project> getAllByTypeAndUsername(String type,String username);

   List<Project> getAllByUsername(String username);

}
