package com.love.nchu.tool;

import com.love.nchu.demain.Product;
import org.springframework.web.multipart.MultipartFile;

public interface FileServer {


    String saveFile(MultipartFile file,String path,String img_vm_path);

    void updateFile(Product product,MultipartFile file,String path,int flag);
    void deleteFile(String path,String filename);

}
