package com.love.nchu.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
@Component
public class IntroductionTool {
    static   String  path;
    @Value("${spring.introduction.path}")
    public void setPath(String str){
    path = str;
    }
    public static StringBuilder read()throws Exception{
//        System.out.println(path);
        File file = new File(path);
        if(!file.exists())
            file.createNewFile();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"gbk");
        BufferedReader out = new BufferedReader (isr);
        StringBuilder s = new StringBuilder();
        String str ;
        while((str = out.readLine() )!=null) {
            s.append(str);
            s.append(System.getProperty("line.separator"));
        }
        out.close();
        isr.close();
        return s;
    }
    public static void write(String s)throws Exception{
        File file = new File(path);
        if(!file.exists())
            file.createNewFile();
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file),"gbk");
        BufferedWriter out = new BufferedWriter(osw);
        out.write(s);
        out.flush();
        out.close();
    }
}
