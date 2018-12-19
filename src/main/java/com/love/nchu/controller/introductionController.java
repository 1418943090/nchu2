package com.love.nchu.controller;

import com.love.nchu.tool.IntroductionTool;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class introductionController {

    @RequestMapping("/introduction")
    public ModelAndView introduction(Model model) throws Exception{
        //IntroductionTool.write(IntroductionTool.read().toString());
         model.addAttribute("introduction", IntroductionTool.read());
        return new ModelAndView("introduction","model",model);
    }
    @PostMapping("/introduction/edit")
    public void edit(@RequestBody String str)throws Exception{
        IntroductionTool.write(str);
    }
}
