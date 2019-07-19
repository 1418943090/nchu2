package com.love.nchu.vo;

import org.springframework.stereotype.Component;

@Component
public class Account {
    private int id;
    private String accname;
    private String username;
    private String time;
    private String thing;
    private double money;
    private String type;
    private String remark;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", accname=" + accname + ", username="
                + username + ", time=" + time + ", thing=" + thing + ", money="
                + money + ", type=" + type + ", remark=" + remark +"]";
    }

}