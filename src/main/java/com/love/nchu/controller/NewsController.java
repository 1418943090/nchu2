package com.love.nchu.controller;


import com.love.nchu.demain.News;
import com.love.nchu.service.NewsServer;
import com.love.nchu.tool.NewsTool;
import com.love.nchu.vo.MyDate;
import com.love.nchu.vo.deleteNewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsServer newsServer;


    @Value("${spring.news.img.vm.path}")
    String news_img_vm_path;

    @Value("${spring.news.img.path}")
    String news_img_path;


    @GetMapping("/news")
    public ModelAndView index(Model model){
        List<News> list = newsServer.getAllNews();
        model.addAttribute("list",list);
        return new ModelAndView("news","model",model);
    }
    @GetMapping("/news/recentNews/{id}")
    public ModelAndView news(Model model,@PathVariable(required = false) Integer id ){

        if(id==null)
            id=0;
        List<News> list = newsServer.getAllNews();
        model.addAttribute("list",list);
        model.addAttribute("id",id);

        return new ModelAndView("news","model",model);
    }
    @GetMapping("/newsCenter")
    public ModelAndView newsCenter(Model model) {
        boolean hasNews = false;
        List<News> list = newsServer.getAllNews();
        if(list.size()>0){
            hasNews = true;
        }
        model.addAttribute("list",list);
        model.addAttribute("hasNews",hasNews);
        return new ModelAndView("newsCenter","model",model);
    }

    @PostMapping("/news/delete")
    public ModelAndView delete(@RequestBody deleteNewsVo listNewsId){

        System.out.println(listNewsId);
        for(Integer id : listNewsId.getListNewsId()){

            newsServer.deleteNewsById(id);
        }
        return new ModelAndView("redirect:/newsCenter");

    }
    @PostMapping("/news/edit")
    public ModelAndView edit(String editId,String editContent){
        System.out.println(editId+" "+editContent);
        newsServer.updateNews(Integer.parseInt(editId),editContent);

        return new ModelAndView("redirect:/newsCenter");
    }
    @PostMapping("/news/add")
    public ModelAndView add(String title, String content, String url, MultipartFile pic){

        News news = new News(title, MyDate.getDate("yyyy-MM-dd hh:mm:ss"),content,url,pic.getOriginalFilename());

        NewsTool.Save_Image(pic,news,news_img_path,news_img_vm_path);

        newsServer.save(news);

        System.out.println(title);
        System.out.println(content);
        System.out.println(url);
        System.out.println(pic.getOriginalFilename());
        return new ModelAndView("redirect:/newsCenter");
    }
}
