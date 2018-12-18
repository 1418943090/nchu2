package com.love.nchu.vo;

import java.io.Serializable;
import java.util.List;

public class deletePaperVo implements Serializable {

    private String userName;
    private List<Integer> listPaperId;
    private List<String> listPaperPath;
    protected deletePaperVo() {
    }

    public deletePaperVo(String userName, List<Integer> listPaperId, List<String> listPaperPath) {
        this.userName = userName;
        this.listPaperId = listPaperId;
        this.listPaperPath = listPaperPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Integer> getListPaperId() {
        return listPaperId;
    }

    public void setListPaperId(List<Integer> listPaperId) {
        this.listPaperId = listPaperId;
    }

    public List<String> getListPaperPath() {
        return listPaperPath;
    }

    public void setListPaperPath(List<String> listPaperPath) {
        this.listPaperPath = listPaperPath;
    }

    @Override
    public String toString() {
        return "deletePaperVo{" +
                "userName='" + userName + '\'' +
                ", listPaperId=" + listPaperId +
                ", listPaperPath=" + listPaperPath +
                '}';
    }
}
