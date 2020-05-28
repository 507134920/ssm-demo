package com.ssm.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssm.util.JsonDateTypeConvert;

import java.util.Date;

public class UserRoom {
    private int id;
    private int userid;
    private int homeid;
    private Date startleasetime;
    private Date endleasetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @JsonSerialize(using= JsonDateTypeConvert.class)
    public Date getStartleasetime() {
        return startleasetime;
    }

    public void setStartleasetime(Date startleasetime) {
        this.startleasetime = startleasetime;
    }

    @JsonSerialize(using= JsonDateTypeConvert.class)
    public Date getEndleasetime() {
        return endleasetime;
    }

    public void setEndleasetime(Date endleasetime) {
        this.endleasetime = endleasetime;
    }

    @Override
    public String toString() {
        return "UserRoom{" +
                "id=" + id +
                ", userid=" + userid +
                ", homeid=" + homeid +
                ", startleasetime=" + startleasetime +
                ", endleasetime=" + endleasetime +
                '}';
    }
}
