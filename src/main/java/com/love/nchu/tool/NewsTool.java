package com.love.nchu.tool;

import com.love.nchu.demain.News;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class NewsTool {

    public static boolean  Save_Image(MultipartFile file, News news, String news_img_path, String news_img_vm_path){
        File newfile = new File(news_img_path);
        if(!newfile.exists()){
            newfile.mkdirs();
        }
        String filenamestr = file.getOriginalFilename().substring(0,3)+String.valueOf((int)(Math.random()*100)) +".jpg";
        System.out.println(news_img_path);
        File bfile = new File(newfile,filenamestr);
        if(!bfile.exists()){
            try{
                bfile.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(bfile));//保存图片到目录下
            out.write(file.getBytes());
            out.flush();
            out.close();
            String filename = news_img_vm_path + filenamestr;
           news.setPic(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
