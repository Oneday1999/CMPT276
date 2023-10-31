package com.example.myapplication.db;

public class AccountBean {
    int id;
    int sImageId;
    String typename;
    int imageId;
    String notes;
    float money;
    String time;
    int year;
    int month;
    int day;
    int kind; //income is 1 and outcome is 0

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public AccountBean() {
    }

    public String getComment() {
        return notes;
    }

    public AccountBean(int id, String typename, int imageId, String notes, float money, String time, int year, int month, int day, int kind) {
        this.id = id;
        this.typename = typename;
        this.imageId = imageId;
        this.notes = notes;
        this.money = money;
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
        this.kind = kind;
    }


}
