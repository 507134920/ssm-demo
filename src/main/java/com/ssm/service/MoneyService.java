package com.ssm.service;

import java.util.Map;

public interface MoneyService {

    //按条件进行分页查询
    Map<String, Object> findPageObjects(String realname, String phone,Integer hourse,
                                        Integer floor, String room_number,int pageCurrent);

    void updateMoney(Integer hourse,Integer floor, String room_number,
                     float waterprice,float electricprice);

    Map<String, Object> doFindObjectById(int id);
}
