package com.love.nchu.vo;

import java.io.Serializable;



public class weekShow implements Serializable {

    String username;
    int monday;
    int tuesday;
    int wednesday;
    int thursday;
    int friday;
    int saturday;
    int sunday;
    int count;
    int a=0;
    String mondayShow;
    String tuesdayShow;
    String wednesdayShow;
    String thursdayShow;
    String fridayShow;
    String saturdayShow;
    String sundayShow;
    protected weekShow(){

    }

    public weekShow(String username, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday) {
        this.username = username;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int count(){
        return getA()+this.monday+this.tuesday+this.thursday+this.friday+this.saturday+this.sunday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMonday()
    {
         if(monday==-1)
             return "节假日";
        return String.valueOf(monday);
    }

    public void setMonday(int monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        if(tuesday==-1)
            return "节假日";
        return String.valueOf(tuesday);
    }

    public void setTuesday(int tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday()
    {
        if(wednesday==-1)
            return "节假日";
        return String.valueOf(wednesday);
    }

    public void setWednesday(int wednesday) {
        this.wednesday = wednesday;
    }


    public String getThursday()
    {
        if(thursday==-1)
            return "节假日";
        return String.valueOf(thursday);
    }
    public void setThursday(int thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        if(friday==-1)
            return "节假日";
        return String.valueOf(friday);
    }

    public void setFriday(int friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        if(saturday==-1)
            return "节假日";
        return String.valueOf(saturday);
    }

    public void setSaturday(int saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        if(sunday==-1)
            return "节假日";
        return String.valueOf(sunday);
    }

    public void setSunday(int sunday) {
        this.sunday = sunday;
    }


    public int getCount() {
        return a+monday+tuesday+wednesday+thursday+friday+saturday+sunday;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public String getMondayShow() {
        return mondayShow;
    }

    public void setMondayShow(String mondayShow) {
        this.mondayShow = mondayShow;
    }

    public String getTuesdayShow() {
        return tuesdayShow;
    }

    public void setTuesdayShow(String tuesdayShow) {
        this.tuesdayShow = tuesdayShow;
    }

    public String getWednesdayShow() {
        return wednesdayShow;
    }

    public void setWednesdayShow(String wednesdayShow) {
        this.wednesdayShow = wednesdayShow;
    }

    public String getThursdayShow() {
        return thursdayShow;
    }

    public void setThursdayShow(String thursdayShow) {
        this.thursdayShow = thursdayShow;
    }

    public String getFridayShow() {
        return fridayShow;
    }

    public void setFridayShow(String fridayShow) {
        this.fridayShow = fridayShow;
    }

    public String getSaturdayShow() {
        return saturdayShow;
    }

    public void setSaturdayShow(String saturdayShow) {
        this.saturdayShow = saturdayShow;
    }

    public String getSundayShow() {
        return sundayShow;
    }

    public void setSundayShow(String sundayShow) {
        this.sundayShow = sundayShow;
    }

    @Override
    public String toString() {
        return "weekShow{" +
                "username='" + username + '\'' +
                ", monday=" + monday +
                ", tuesday=" + tuesday +
                ", Wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                ", saturday=" + saturday +
                ", sunday=" + sunday +
                '}';
    }
}
