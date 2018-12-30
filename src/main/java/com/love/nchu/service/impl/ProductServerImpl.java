package com.love.nchu.service.impl;

import com.love.nchu.demain.Product;
import com.love.nchu.repository.ProductRepository;
import com.love.nchu.service.ProductServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServerImpl implements ProductServer {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAllByPositionAndDate();
    }

    @Override
    public void deleteById(Integer id) {
         productRepository.deleteById(id);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.getOne(id);
    }


    @Override
    public void productSetInit() {
        productRepository.productSetInit();
    }

    @Override
    public void updatePosition(Integer position, Integer id) {
        productRepository.updatePosition(position,id);
    }
}
