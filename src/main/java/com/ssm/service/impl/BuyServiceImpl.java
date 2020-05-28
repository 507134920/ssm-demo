package com.ssm.service.impl;

import com.ssm.dao.BuyDao;
import com.ssm.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyServiceImpl implements BuyService {
    @Autowired
    private BuyDao buyDao;


    @Override
    public List<Map<String, Object>> findTime(String phone) {
        return buyDao.findTime(phone);
    }

    @Override
    public Map<String, Object> findObjectByPhone(String phone) {
        return buyDao.findObjectByPhone(phone);
    }

    @Override
    public boolean addTime(Integer num, String phone) {
        int i = buyDao.addTime(num, phone);
        if (i>0){
            return true;
        }
        return false;
    }
    @Override
    public boolean addTime1(Integer num, String phone) {
        int i = buyDao.addTime1(num, phone);
        if (i>0){
            return true;
        }
        return false;
    }
}
