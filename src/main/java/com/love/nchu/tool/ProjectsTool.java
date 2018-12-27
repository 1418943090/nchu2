package com.love.nchu.tool;

import com.love.nchu.demain.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class ProjectsTool {

    static   String  img_path;
    @Value("${spring.projects.img.path}")
    public void setPath(String str){
        img_path = str;
    }

    static   String  img_vm_path;
    @Value("${spring.projects.img.vm.path}")
    public void setVMPath(String str){
        img_vm_path = str;
    }

    static   String  document_path;
    @Value("${spring.projects.documents.path}")
    public void setsocumentPath(String str){
        document_path = str;
    }

    static   String  document_vm_path;
    @Value("${spring.projects.documents.vm.path}")
    public void setdocumentVMPath(String str){
        document_vm_path = str;
    }



    public static void deletePic(String path){
        File file = new File(img_path.substring(0,13)+path);
        if(file.exists()){
            file.delete();
        }
    }
    public static void deleteDocument(String path){
        File file = new File(document_path.substring(0,13)+path);
        if(file.exists()){
            file.delete();
        }
    }

    public static void saveDocument(Project project,MultipartFile document){

        File f = new File(document_path);
        if(!f.exists()){
            f.mkdirs();
        }
        String filenamestr = project.getPrincipal()+String.valueOf((int)(Math.random()*1000)) +".pdf";
        File realFile = new File(f,filenamestr);
        try{
            if(!realFile.exists()){
                realFile.createNewFile();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(realFile))) {
            bos.write(document.getBytes());
            bos.flush();
            String filename = document_vm_path + filenamestr;
            project.setDocument(filename);

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());

        }catch(IOException e){
            System.out.println(e.getMessage());

        }
    }

    public static void  UpdatePic(Project project,MultipartFile pic){

        File newfile = new File(img_path);
        if(!newfile.exists()){
            newfile.mkdirs();
        }
        File bfile = new File(img_path.substring(0,13)+project.getPic());

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
            out.write(pic.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void  UpdateDocument(Project project,MultipartFile pic){

        File newfile = new File(document_path);
        if(!newfile.exists()){
            newfile.mkdirs();
        }
        File bfile = new File(document_path.substring(0,13)+project.getDocument());
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
            out.write(pic.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void savePic(Project project, MultipartFile pic){


        File newfile = new File(img_path);
        if(!newfile.exists()){
            newfile.mkdirs();
        }
        String filenamestr = project.getPrincipal()+String.valueOf((int)(Math.random()*1000)) +".jpg";
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
            out.write(pic.getBytes());
            out.flush();
            out.close();
            String filename = img_vm_path + filenamestr;
            project.setPic(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
