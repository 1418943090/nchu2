package com.love.nchu.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class intervalVo implements Serializable {


    @Id
    String username;
    long count;

    private intervalVo(){

    }

    public intervalVo(String username, long count) {
        this.username = username;
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "intervalVo{" +
                "username='" + username + '\'' +
                ", count=" + count +
                '}';
    }
}
