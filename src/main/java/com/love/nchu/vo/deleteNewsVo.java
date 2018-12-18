package com.love.nchu.vo;

import java.io.Serializable;
import java.util.List;

public class deleteNewsVo implements Serializable {


    private List<Integer> listNewsId;

    protected deleteNewsVo(){

    }

    public deleteNewsVo(List<Integer> listNewsId) {
        this.listNewsId = listNewsId;
    }

    public List<Integer> getListNewsId() {
        return listNewsId;
    }

    public void setListNewsId(List<Integer> listNewsId) {
        this.listNewsId = listNewsId;
    }

    @Override
    public String toString() {
        return "deleteNewsVo{" +
                "listNewsId=" + listNewsId +
                '}';
    }
}
