package com.ssm.service.impl;

import com.ssm.dao.MoneyDao;
import com.ssm.exception.ServiceException;
import com.ssm.service.MoneyService;
import com.ssm.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    private MoneyDao moneyDao;


    @Override
    public Map<String, Object> findPageObjects(String realname, String phone, Integer hourse, Integer floor,
                                               String room_number, int pageCurrent) {
        int pageSize=8;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Map<String,Object>> list = moneyDao.findPageObjects(realname,phone,hourse,floor,
                                        room_number,startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = moneyDao.getRowCount(realname,phone,hourse,floor,room_number);
        PageObject pageObject = new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setStartIndex(startIndex);
        pageObject.setPageCurrent(pageCurrent);

        //将当前页数据以及分页信息分装到map
        Map<String,Object> map = new HashMap<>();
        map.put("list", list);
        map.put("pageObject",pageObject);
        return map;
    }

    @Override
    public void updateMoney(Integer hourse, Integer floor, String room_number, float waterprice, float electricprice) {
        int i = moneyDao.updateMoney(hourse, floor, room_number, waterprice, electricprice);
        if (i == 0){
            throw new ServiceException("缴费失败");
        }
    }

    @Override
    public Map<String, Object> doFindObjectById(int id) {
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> list = moneyDao.doFindObjectById(id);
        map.put("list", list);
        return map;
    }
}
