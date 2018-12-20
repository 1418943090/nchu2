package com.love.nchu.controller;


import com.love.nchu.demain.News;
import com.love.nchu.service.NewsServer;
import com.love.nchu.tool.NewsTool;
import com.love.nchu.vo.MyDate;
import com.love.nchu.vo.deleteNewsVo;
import com.love.nchu.vo.PositionSetVo;
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
    boolean newsSet = false;
    @GetMapping("/news")
    public ModelAndView index(Model model){
        List<News> list = newsServer.getAllNews();
        model.addAttribute("list",list);
        return new ModelAndView("news","model",model);
    }
    @PostMapping("/news/set")
    public ModelAndView set(@RequestBody PositionSetVo newsSetVo){
        newsSet = true;
        newsServer.newsSetInt();
        newsServer.updatePosition(1,newsSetVo.getNo1());
        newsServer.updatePosition(2,newsSetVo.getNo2());
        newsServer.updatePosition(3,newsSetVo.getNo3());
        return new ModelAndView("redirect:/newsCenter");
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
        model.addAttribute("newsSet",newsSet);
        newsSet = false;
        return new ModelAndView("newsCenter","model",model);
    }
    @PostMapping("/news/delete")
    public ModelAndView delete(@RequestBody deleteNewsVo listNewsId){
        for(Integer id : listNewsId.getListNewsId()){
            newsServer.deleteNewsById(id);
        }
        return new ModelAndView("redirect:/newsCenter");
    }
    @PostMapping("/news/edit")
    public ModelAndView edit(String editId,String editContent){
        newsServer.updateNews(Integer.parseInt(editId),editContent);
        return new ModelAndView("redirect:/newsCenter");
    }
    @PostMapping("/news/add")
    public ModelAndView add(String title, String content, String url, MultipartFile pic){
        News news = new News(title, MyDate.getDate("yyyy-MM-dd hh:mm:ss"),content,url,pic.getOriginalFilename());
        NewsTool.Save_Image(pic,news,news_img_path,news_img_vm_path);
        newsServer.save(news);
        return new ModelAndView("redirect:/newsCenter");
    }
}
