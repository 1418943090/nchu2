package com.love.nchu.service.impl;

import com.love.nchu.demain.News;
import com.love.nchu.repository.NewsRepository;
import com.love.nchu.service.NewsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsServerImpl implements NewsServer {

    @Autowired
    NewsRepository newsRepository;
    @Override
    public List<News> getAllNews() {
        return newsRepository.getAll();
    }

    @Override
    public void save(News news) {
        newsRepository.save(news);
    }


    @Override
    public void updateNews(Integer id, String content) {
        newsRepository.updateNews(id,content);
    }

    @Override
    public void deleteNewsById(Integer id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> getRencentNews() {
        return newsRepository.getRencentNews();
    }

    @Override
    public void updatePosition(int position,int id) {
        newsRepository.updateSet(position,id);
    }

    @Override
    public void newsSetInt() {
        newsRepository.newssetInit();
    }


}

