package com.love.nchu.demain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String date;
    @Column(length = 2000)
    private String content;
    private String url;
    private String pic;
    private int position = 4;
    protected News(){

    }

    public News(String title, String date, String content, String url, String pic) {
        this.title = title;
        this.date = date;
        this.content = content;
        this.url = url;
        this.pic = pic;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", pic='" + pic + '\'' +
                ", position=" + position +
                '}';
    }
}
