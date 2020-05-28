package com.ssm.entity;

public class Home {
    private int id;
    private int hourse; //几栋
    private int floor;  //几楼
    private String room_number; //几号房
    private String hourse_pasword;//外门密码
    private String room_password;//房间密码
    private float price;  //价格

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHourse() {
        return hourse;
    }

    public void setHourse(int hourse) {
        this.hourse = hourse;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getHourse_pasword() {
        return hourse_pasword;
    }

    public void setHourse_pasword(String hourse_pasword) {
        this.hourse_pasword = hourse_pasword;
    }

    public String getRoom_password() {
        return room_password;
    }

    public void setRoom_password(String room_password) {
        this.room_password = room_password;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", hourse=" + hourse +
                ", floor=" + floor +
                ", room_number='" + room_number + '\'' +
                ", hourse_pasword='" + hourse_pasword + '\'' +
                ", room_password='" + room_password + '\'' +
                ", price=" + price +
                '}';
    }
}
