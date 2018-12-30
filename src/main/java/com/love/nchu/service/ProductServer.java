package com.love.nchu.service;

import com.love.nchu.demain.Product;

import java.util.List;

public interface  ProductServer {



    void save(Product product);
    List<Product> getAll();
    void deleteById(Integer id);

    Product findById(Integer id);


    void productSetInit();
    void updatePosition(Integer position,Integer id);


}
