package com.love.nchu.controller;


import com.love.nchu.demain.Product;
import com.love.nchu.service.ProductServer;
import com.love.nchu.tool.FileServer;
import com.love.nchu.vo.MyDate;
import com.love.nchu.vo.PositionSetVo;
import com.love.nchu.vo.deleteProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    ProductServer productServer;

    @Autowired
    FileServer fileServer;

    @Value("${spring.products.img.vm.path}")
    String img_vm_path;

    @Value("${spring.products.img.path}")
    String img_path;

    @Value("${spring.products.documents.vm.path}")
    String document_vm_path;

    @Value("${spring.products.documents.path}")
    String document_path;

    boolean productSet = false;



    @GetMapping("/products")
    public ModelAndView products(Model model){

        List<Product> list = productServer.getAll();
        model.addAttribute("list",list);
        return new ModelAndView("products","model",model);
    }

    @PostMapping("/product/set")
    public ModelAndView set(@RequestBody PositionSetVo positionSetVo){
        productSet = true;
        productServer.productSetInit();
        productServer.updatePosition(1,positionSetVo.getNo1());
        productServer.updatePosition(2,positionSetVo.getNo2());
        productServer.updatePosition(3,positionSetVo.getNo3());
        return new ModelAndView("redirect:/productsCenter");
    }



    @PostMapping("/product/delete")
    public ModelAndView dele(@RequestBody deleteProductVo deleteProductVo){

        for(Integer id : deleteProductVo.getListProductsId()){

            Product product = productServer.findById(id);
            fileServer.deleteFile(img_path,product.getPic());
            fileServer.deleteFile(document_path,product.getDocument());
            productServer.deleteById(id);
        }
        return new ModelAndView("redirect:/productsCenter");
    }

    @PostMapping("/product/edit")
    public ModelAndView edit(Product product,MultipartFile file_pic, MultipartFile file_document){

        System.out.println(product);
        Product product1 = productServer.findById(product.getId());

        product.setPic(product1.getPic());
        product.setDocument(product1.getDocument());
        product.setDate(product1.getDate());

        if(file_pic!=null){
            fileServer.updateFile(product,file_pic,img_path,1);

        }
        if(file_document!=null){
            fileServer.updateFile(product,file_document,document_path,2);
        }
        productServer.save(product);
        return new ModelAndView("redirect:/productsCenter");
    }


    @PostMapping("/product/add")
    public  ModelAndView add(Product product, MultipartFile file_pic, MultipartFile file_document){

        System.out.println(product.toString());
        System.out.println(file_pic.getName());
        System.out.println(file_document.getOriginalFilename());

        product.setPic( fileServer.saveFile(file_pic,img_path,img_vm_path));

        product.setDocument(fileServer.saveFile(file_document,document_path,document_vm_path));
        product.setDate(MyDate.getDate("yyyy-MM-dd  HH:mm:ss"));
        productServer.save(product);

        return new ModelAndView("redirect:/productsCenter");
    }

    @GetMapping("/productsCenter")
    public ModelAndView productsCenter(Model model){


        List<Product> list = productServer.getAll();
        model.addAttribute("list",list);
        model.addAttribute("productSet",productSet);
        model.addAttribute("hasProducts",list.size()==0?false:true);

        return new ModelAndView("productsCenter","model",model);
    }
}
