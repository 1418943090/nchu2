package com.love.nchu.vo;

import java.io.Serializable;
import java.util.List;

public class deleteProjectsVo implements Serializable {

    private List<Integer> listProjectsId;
    private List<String> listProjectsPicPath;
    private List<String> listProjectsDocumentPath;


    protected  deleteProjectsVo(){

    }

    public deleteProjectsVo(List<Integer> listProjectsId, List<String> listProjectsPicPath, List<String> listProjectsDocumentPath) {
        this.listProjectsId = listProjectsId;
        this.listProjectsPicPath = listProjectsPicPath;
        this.listProjectsDocumentPath = listProjectsDocumentPath;
    }

    public List<Integer> getListProjectsId() {
        return listProjectsId;
    }

    public void setListProjectsId(List<Integer> listProjectsId) {
        this.listProjectsId = listProjectsId;
    }

    public List<String> getListProjectsPicPath() {
        return listProjectsPicPath;
    }

    public void setListProjectsPicPath(List<String> listProjectsPicPath) {
        this.listProjectsPicPath = listProjectsPicPath;
    }

    public List<String> getListProjectsDocumentPath() {
        return listProjectsDocumentPath;
    }

    public void setListProjectsDocumentPath(List<String> listProjectsDocumentPath) {
        this.listProjectsDocumentPath = listProjectsDocumentPath;
    }

    @Override
    public String toString() {
        return "deleteProjectsVo{" +
                "listProjectsId=" + listProjectsId +
                ", listProjectsPicPath=" + listProjectsPicPath +
                ", listProjectsDocumentPath=" + listProjectsDocumentPath +
                '}';
    }
}
