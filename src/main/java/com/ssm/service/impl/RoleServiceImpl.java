package com.ssm.service.impl;

import com.ssm.dao.RoleDao;
import com.ssm.entity.UserRole;
import com.ssm.exception.ServiceException;
import com.ssm.service.RoleService;
import com.ssm.util.PageObject;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;


    @Override
    public Map<String, Object> findRoles(String realname, int pageCurrent) {
        int pageSize = 8;
        int startIndex = (pageCurrent - 1) * pageSize;
        //获取当页数据
        List<Map<String,Object>> list = roleDao.findRoles(realname, startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = roleDao.getRowCount(realname);
        PageObject pageObject = new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setStartIndex(startIndex);
        pageObject.setPageCurrent(pageCurrent);

        //将当前页数据以及分页信息分装到map
        Map<String,Object> map= new HashMap<>();
        map.put("list", list);
        map.put("pageObject",pageObject);
        return map;
    }

    @Override
    public List<Map<String, Object>> FindRoleName() {
        return roleDao.FindRoleName();
    }

    @Override
    public void insertUR(String realname,String roleName) {
        //1.对参数进行业务验证
        if(realname==null || realname==" "){
            throw new ServiceException("读者名不能为空");
        }
        if(roleName==null || roleName==" "){
            throw new ServiceException("分配的角色不能为空");
        }
        //2.执行保存操作
        int rows=roleDao.insertUR(realname, roleName);
        //System.out.println("rows=="+rows);
        //3.对结果进行判定
        if(rows<1){
            throw new ServiceException("写入数据失败");
        }

    }

    @Override
    public UserRole findUserRole(String realname, String roleName) {
        return roleDao.findUserRole(realname, roleName);
    }
}
