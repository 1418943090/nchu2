package com.love.nchu.vo;

import java.util.List;

public class deleteFestivalVo {

    private String flag;
    private List<Integer> listFestivalId;

    protected  deleteFestivalVo(){

    }

    public deleteFestivalVo(String flag, List<Integer> listFestivalId) {
        this.flag = flag;
        this.listFestivalId = listFestivalId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Integer> getListFestivalId() {
        return listFestivalId;
    }

    public void setListFestivalId(List<Integer> listFestivalId) {
        this.listFestivalId = listFestivalId;
    }

    @Override
    public String toString() {
        return "deleteFestivalVo{" +
                "flag='" + flag + '\'' +
                ", listFestivalId=" + listFestivalId +
                '}';
    }
}
