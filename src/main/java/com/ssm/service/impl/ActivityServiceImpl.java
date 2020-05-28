package com.ssm.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.ssm.dao.ActivityDao;
import com.ssm.entity.Activity;
import com.ssm.exception.ServiceException;
import com.ssm.service.ActivityService;
import com.ssm.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;


    @Override
    public void insertActivity(Activity activity) {
        if (activity == null) {
            throw new ServiceException("添加对象不能为空");
        } else {
            int rows = activityDao.insertActivity(activity);
            if (rows <= 0) {
                throw new ServiceException("添加失败");
            }
        }
    }

    @Override
    public List<Activity> findActivity() {
        return activityDao.findActivity();
    }

    @Override
    public Map<String, Object> findActivityPageObjects(int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Activity> list = activityDao.findAllActivity(startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = activityDao.activityNum();
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
    public void delById(String ids) {
        if(StringUtils.isEmpty(ids)) {
            throw new ServiceException("ids 的值不能为空");
        }
        String[] idArray = ids.split(",");
        int rows = activityDao.delById(idArray);
        if(rows == 0) {
            throw new ServiceException("删除失败");
        }
    }

    @Override
    public void updateActivity(Activity activity) {
        int i = activityDao.updateActivity(activity);
        if(i == 0) {
            throw new ServiceException("修改失败");
        }
    }

    @Override
    public Activity findActivityById(Integer id) {
        if(id == null){
            throw new ServiceException("id 不能为空");
        }
        Activity activity = activityDao.findActivityById(id);
        if(activity == null) {
            throw new ServiceException("对象不存在");
        }
        return activity;
    }
}
