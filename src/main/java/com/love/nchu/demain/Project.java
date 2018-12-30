package com.love.nchu.demain;
import javax.persistence.*;
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String name;
    private String description;
    @Column(length = 1500)
    private String content;
    private String principal;
    private String member;
    private String date="";
    private String dateA="";
    private String dateB="";
    private Double funding;
    private String pic;
    private String document;
    private String type;
    private int position1=4;
    private int position2=8;
    private int position3=12;
    private int position4=16;
    private String unit="(元)";
    protected Project(){
    }
    public Project(String name, String description, String content, String principal, String member, Double funding,  String type) {
        this.name = name;
        this.description = description;
        this.content = content;
        this.principal = principal;
        this.member = member;
        this.funding = funding;
        this.type = type;
    }
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getFunding() {
        return funding;
    }

    public void setFunding(Double funding) {
        this.funding = funding;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition1() {
        return position1;
    }

    public void setPosition1(int position1) {
        this.position1 = position1;
    }

    public int getPosition2() {
        return position2;
    }

    public void setPosition2(int position2) {
        this.position2 = position2;
    }

    public int getPosition3() {
        return position3;
    }

    public void setPosition3(int position3) {
        this.position3 = position3;
    }

    public int getPosition4() {
        return position4;
    }

    public void setPosition4(int position4) {
        this.position4 = position4;
    }


    public String getDateA() {
        return dateA;
    }

    public void setDateA(String dateA) {
        this.dateA = dateA;
    }

    public String getDateB() {
        return dateB;
    }

    public void setDateB(String dateB) {
        this.dateB = dateB;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLengthName(){
        if(name.length()>50){
            return name.substring(0,50)+"...";
        }
        return name;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", principal='" + principal + '\'' +
                ", member='" + member + '\'' +
                ", date='" + date + '\'' +
                ", dateA='" + dateA + '\'' +
                ", dateB='" + dateB + '\'' +
                ", funding=" + funding +
                ", pic='" + pic + '\'' +
                ", document='" + document + '\'' +
                ", type='" + type + '\'' +
                ", position1=" + position1 +
                ", position2=" + position2 +
                ", position3=" + position3 +
                ", position4=" + position4 +
                ", unit='" + unit + '\'' +
                '}';
    }
}
