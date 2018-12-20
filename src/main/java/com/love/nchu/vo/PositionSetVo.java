package com.love.nchu.vo;

import org.springframework.stereotype.Component;

@Component
public class PositionSetVo {

    private Integer no1;
    private Integer no2;
    private  Integer no3;

    protected PositionSetVo(){

    }
    public PositionSetVo(Integer no1, Integer no2, Integer no3) {
        this.no1 = no1;
        this.no2 = no2;
        this.no3 = no3;
    }

    public Integer getNo1() {
        return no1;
    }

    public void setNo1(Integer no1) {
        this.no1 = no1;
    }

    public Integer getNo2() {
        return no2;
    }

    public void setNo2(Integer no2) {
        this.no2 = no2;
    }

    public Integer getNo3() {
        return no3;
    }

    public void setNo3(Integer no3) {
        this.no3 = no3;
    }

    @Override
    public String toString() {
        return "newsSetVo{" +
                "no1=" + no1 +
                ", no2=" + no2 +
                ", no3=" + no3 +
                '}';
    }
}
