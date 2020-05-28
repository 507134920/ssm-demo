package com.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class Common {
    private int userid;
    private int homeid;
    private String realname;
    private String phone;
    private int hourse;
    private int floor;
    private String room_number;
    private Date startleasetime;
    private Date endleasetime;

    public Common() {
    }

    public Common(int userid, int homeid, String realname, String phone, int hourse, int floor, String room_number,
                  Date startleasetime, Date endleasetime) {
        this.userid = userid;
        this.homeid = homeid;
        this.realname = realname;
        this.phone = phone;
        this.hourse = hourse;
        this.floor = floor;
        this.room_number = room_number;
        this.startleasetime = startleasetime;
        this.endleasetime = endleasetime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getHomeid() {
        return homeid;
    }

    public void setHomeid(int homeid) {
        this.homeid = homeid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getStartleasetime() {
        return startleasetime;
    }

    public void setStartleasetime(Date startleasetime) {
        this.startleasetime = startleasetime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getEndleasetime() {
        return endleasetime;
    }

    public void setEndleasetime(Date endleasetime) {
        this.endleasetime = endleasetime;
    }

    @Override
    public String toString() {
        return "Common{" +
                "userid=" + userid +
                ", homeid=" + homeid +
                ", realname='" + realname + '\'' +
                ", phone='" + phone + '\'' +
                ", hourse=" + hourse +
                ", floor=" + floor +
                ", room_number='" + room_number + '\'' +
                ", startleasetime=" + startleasetime +
                ", endleasetime=" + endleasetime +
                '}';
    }
}
