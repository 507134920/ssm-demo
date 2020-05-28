package com.ssm.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.ssm.dao.RepairDao;
import com.ssm.entity.Repair;
import com.ssm.exception.ServiceException;
import com.ssm.service.RepairService;
import com.ssm.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private RepairDao repairDao;


    @Override
    public void insertRepair(Repair repair) {
        if (repair == null) {
            throw new ServiceException("添加对象不能为空");
        } else {
            int rows = repairDao.insertRepair(repair);
            if (rows <= 0) {
                throw new ServiceException("添加失败");
            }
        }
    }

    @Override
    public Map<String, Object> findRepairPageObjects(int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Repair> list = repairDao.findAllRepair(startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = repairDao.repairNum();
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
        if(StringUtils.isEmpty(ids)){
            throw new ServiceException("ids 的值不能为空");
        }
        String[] idArray = ids.split(",");
        int rows = repairDao.delById(idArray);
        if(rows == 0) {
            throw new ServiceException("删除失败");
        }
    }
}
