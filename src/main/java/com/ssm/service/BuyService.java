package com.ssm.service;

import java.util.List;
import java.util.Map;

public interface BuyService {

    List<Map<String, Object>> findTime (String phone);
    Map<String, Object> findObjectByPhone(String phone);
    boolean addTime(Integer num,String phone);
    boolean addTime1(Integer num,String phone);
}
