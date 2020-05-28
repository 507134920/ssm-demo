package com.ssm.service;

import com.ssm.entity.Home;

import java.util.Map;

public interface HomeService {
    Home findPageObjects(String realname, String phone);

    //按条件进行分页查询
    Map<String, Object> findHomeObjects(Integer hourse,Integer floor, String room_number,
                                        int pageCurrent);

    void updateHome(Home home);

    Home doFindObjectById(Integer id);

}
