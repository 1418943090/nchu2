package com.love.nchu.vo;

import java.util.List;

public class deleteProductVo {

    private List<Integer> listProductsId;


    protected deleteProductVo(){

    }

    public deleteProductVo(List<Integer> listProductId) {
        this.listProductsId = listProductId;
    }

    public List<Integer> getListProductsId() {
        return listProductsId;
    }

    public void setListProductsId(List<Integer> listProductId) {
        this.listProductsId = listProductId;
    }

    @Override
    public String toString() {
        return "deleteProductVo{" +
                "listProductId=" + listProductsId +
                '}';
    }
}
