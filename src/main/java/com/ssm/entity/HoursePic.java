package com.ssm.entity;

public class HoursePic {
    private int id;
    private int homeid;
    private String pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomeid() {
        return homeid;
    }

    public void setHomeid(int homeid) {
        this.homeid = homeid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "HoursePic{" +
                "id=" + id +
                ", homeid=" + homeid +
                ", pic='" + pic + '\'' +
                '}';
    }
}
