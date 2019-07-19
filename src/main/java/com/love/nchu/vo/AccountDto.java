package com.love.nchu.vo;

import java.io.Serializable;
import java.util.List;

public class AccountDto  {

    List<Account> list;

    public List<Account> getList() {
        return list;
    }

    public void setList(List<Account> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "list=" + list +
                '}';
    }
}
