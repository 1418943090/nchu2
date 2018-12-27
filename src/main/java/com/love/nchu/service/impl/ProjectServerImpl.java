package com.love.nchu.service.impl;

import com.love.nchu.demain.Project;
import com.love.nchu.repository.ProjectRepository;
import com.love.nchu.service.ProjectServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServerImpl implements ProjectServer {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.getAllPojects();
    }

    @Override
    public List<Project> getAllThreeSmallProject() {
        return projectRepository.getAllThreeSmallProjects();
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void deleteById(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project findById(Integer id) {
        return projectRepository.getOne(id);
    }

    @Override
    public void position1SetInit() {
        projectRepository.position1SetInit();
    }

    @Override
    public void position2SetInit() {
        projectRepository.position2SetInit();
    }

    @Override
    public void position3SetInit() {
        projectRepository.position3SetInit();
    }

    @Override
    public void position4SetInit() {
        projectRepository.position4SetInit();
    }

    @Override
    public void updatePosition1Set(int position, int id) {
         projectRepository.updatePosition1Set(position,id);
    }

    @Override
    public void updatePosition2Set(int position, int id) {
        projectRepository.updatePosition2Set(position,id);
    }

    @Override
    public void updatePosition3Set(int position, int id) {
        projectRepository.updatePosition3Set(position,id);
    }

    @Override
    public void updatePosition4Set(int position, int id) {
        projectRepository.updatePosition4Set(position,id);
    }

    @Override
    public List<Project> getAllResearchProjects() {
        return projectRepository.getAllResearchProjects();
    }

    @Override
    public List<Project> getAllHengProjects() {
        return projectRepository.getAllHengProjects();
    }

    @Override
    public List<Project> getAllByTypeAndUsername(String type,String username) {
        return projectRepository.getAllByTypeAndUsername(type,username);
    }

    @Override
    public List<Project> getAllByUsername(String username) {
        return projectRepository.getAllByUsername(username);
    }
}
