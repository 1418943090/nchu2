package com.love.nchu.demain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="paper")
public class Paper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;
    @Column(name="title",length = 500)
    private String title;
    private String path;
    private String username;
    private String name;
    private Date  date;

    private int position=4;

    protected Paper(){

    }

    public Paper(String title, String path, String username, String name, Date date) {
        this.title = title;
        this.path = path;
        this.username = username;
        this.name = name;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", position=" + position +
                '}';
    }
}
