package com.ssm.service.impl;

import com.ssm.dao.HomeDao;
import com.ssm.entity.Home;
import com.ssm.exception.ServiceException;
import com.ssm.service.HomeService;
import com.ssm.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeDao homeDao;


    @Override
    public Home findPageObjects(String realname, String phone) {
        return homeDao.findPageObjects(realname, phone);
    }

    @Override
    public Map<String, Object> findHomeObjects(Integer hourse, Integer floor, String room_number, int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Home> list = homeDao.findHomeObjects(hourse,floor,room_number,startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = homeDao.getRowCount(hourse,floor,room_number);
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
    public void updateHome(Home home) {
        if(home== null){
            throw new ServiceException("更新对象不能为空");
        }
        int rows = homeDao.updateHome(home);
        if(rows <= 0) {
            throw new ServiceException("修改失败");
        }
    }

    @Override
    public Home doFindObjectById(Integer id) {
        if(id == null){
            throw new ServiceException("id 不能为空");
        }
        Home home = homeDao.doFindObjectById(id);
        if(home == null) {
            throw new ServiceException("对象不存在");
        }
        return home;
    }
}
