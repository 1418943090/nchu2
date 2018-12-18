package com.love.nchu.service;

import com.love.nchu.demain.News;

import java.util.List;

public interface NewsServer {

    List<News> getAllNews();
    void save(News news);
    void updateNews(Integer id,String content);
    void deleteNewsById(Integer id);
    List<News> getRencentNews();
}
