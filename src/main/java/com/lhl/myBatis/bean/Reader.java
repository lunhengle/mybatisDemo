package com.lhl.myBatis.bean;

import java.util.List;

/**
 * Created by lunhengle on 2015/8/12.
 */
public class Reader {

    private Integer id;
    private User user;
    private int money;
    private List<User> userList;

    public Reader() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
