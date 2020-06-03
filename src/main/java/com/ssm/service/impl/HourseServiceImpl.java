package com.ssm.service.impl;

import com.ssm.dao.HourseDao;
import com.ssm.entity.Home;
import com.ssm.entity.HoursePic;
import com.ssm.entity.Notice;
import com.ssm.exception.ServiceException;
import com.ssm.service.HourseService;
import com.ssm.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HourseServiceImpl implements HourseService {
    @Autowired
    private HourseDao hourseDao;

    @Override
    public Map<String, Object> findNoRent(int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Home> list = hourseDao.findNoRent(startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = hourseDao.getNoRentRowCount();
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
    public Map<String, Object> findRentAlready(int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Home> list = hourseDao.findRentAlready(startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = hourseDao.getRentAlreadyRowCount();
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
    public Map<String, Object> findAllObject(int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Home> list = hourseDao.findAllObject(startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = hourseDao.getAllRowCount();
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
    public List<HoursePic>  findPicById(Integer id) {
        return hourseDao.findPicById(id);
    }

    @Override
    public List<HoursePic> findAllPic() {
        return hourseDao.findAllPic();
    }

    @Override
    public Map<String, Object> findPic2(float startNum, float endNum,int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Map<String, Object>> list = hourseDao.findPic2(startNum, endNum, startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = hourseDao.findPic2RowCount(startNum, endNum);
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
}
