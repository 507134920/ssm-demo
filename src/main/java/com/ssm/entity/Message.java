package com.ssm.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssm.util.JsonDateTypeConvert;

import java.util.Date;

public class Message {
    private int id;
    private String realname;
    private String content;
    private int agree;
    private int disagree;
    private Date committime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public int getDisagree() {
        return disagree;
    }

    public void setDisagree(int disagree) {
        this.disagree = disagree;
    }

    @JsonSerialize(using= JsonDateTypeConvert.class)
    public Date getCommittime() {
        return committime;
    }

    public void setCommittime(Date committime) {
        this.committime = committime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", realname='" + realname + '\'' +
                ", content='" + content + '\'' +
                ", agree=" + agree +
                ", disagree=" + disagree +
                ", committime=" + committime +
                '}';
    }
}
