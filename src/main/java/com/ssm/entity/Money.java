package com.ssm.entity;

public class Money {
    private int id;
    private int homeid;
    private float waterprice;
    private float electricprice;

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

    public float getWaterprice() {
        return waterprice;
    }

    public void setWaterprice(float waterprice) {
        this.waterprice = waterprice;
    }

    public float getElectricprice() {
        return electricprice;
    }

    public void setElectricprice(float electricprice) {
        this.electricprice = electricprice;
    }

    @Override
    public String toString() {
        return "Money{" +
                "id=" + id +
                ", homeid=" + homeid +
                ", waterprice=" + waterprice +
                ", electricprice=" + electricprice +
                '}';
    }
}
