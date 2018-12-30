package com.love.nchu.tool.impl;

import com.love.nchu.demain.Product;
import com.love.nchu.tool.FileServer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
@Service
public class FileServerImpl implements FileServer {
    @Override
    public String saveFile(MultipartFile file, String path,String img_vm_path) {
        File newfile = new File(path);
        if(!newfile.exists()){
            newfile.mkdirs();
        }
        String filenamestr = String.valueOf((int)(Math.random()*1000)) + file.getOriginalFilename();
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  img_vm_path+filenamestr;
    }
    @Override
    public void updateFile(Product product, MultipartFile file,String path,int flag) {
        File newfile = new File(path);
        if(!newfile.exists()){
            newfile.mkdirs();
        }
        File bfile;
        if(flag==1)
           bfile = new File(path.substring(0,13)+product.getPic());
        else {
            bfile = new File(path.substring(0,13)+product.getDocument());
        }
        if(!bfile.exists()){
            try{
                bfile.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(bfile));
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteFile(String path, String filename) {
        File file = new File(path.substring(0,13)+filename);
        if(file.exists()){
            file.delete();
        }
    }
}
