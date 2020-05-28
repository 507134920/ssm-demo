package com.ssm.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.ssm.dao.UserDao;
import com.ssm.entity.*;
import com.ssm.exception.ServiceException;
import com.ssm.service.UserService;
import com.ssm.util.Constants;
import com.ssm.util.PageObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findObjectByName(String realname) {
        return userDao.findObjectByName(realname);
    }

    @Override
    public User doLogin(String realname, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(realname,password);
        try {
            subject.login(token);
            Session session = subject.getSession();
            User user = findObjectByName(realname);
            session.setAttribute(Constants.SESSION,user);
            return user;
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> findPageObjects(String realname, String phone, int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Map<String,Object>> list = userDao.findPageObjects(phone,realname,startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = userDao.getRowCount(phone,realname);
        PageObject pageObject = new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setStartIndex(startIndex);
        pageObject.setPageCurrent(pageCurrent);

        //将当前页数据以及分页信息分装到map
        Map<String,Object> map =
                new HashMap<>();
        map.put("list", list);
        map.put("pageObject",pageObject);
        return map;
    }

    @Override
    public void moveById(String ids) {
        if(StringUtils.isEmpty(ids)) {
            throw new ServiceException("ids 的值不能为空");
        }
        String[] idArray = ids.split(",");
        int rows = userDao.moveById(idArray);
        if(rows == 0) {
            throw new ServiceException("删除失败");
        }
    }

    @Override
    public Map<String, Object> findBlockPageObjects(String realname, String phone, int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<User> list = userDao.findBlockPageObjects(realname,phone,startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = userDao.getBlockRowCount(realname,phone);
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
    public void reloadById(String ids) {
        if(StringUtils.isEmpty(ids)){
            throw new ServiceException("ids 的值不能为空");
        }
        String[] idArray = ids.split(",");
        int rows = userDao.reloadById(idArray);
        if(rows == 0) {
            throw new ServiceException("恢复失败");
        }
    }

    @Override
    public void saveObject(Common entity) {
        //传来的数据不能为空
        if(entity == null){
            throw new ServiceException("保存对象不能为空");
        }
        int rows = userDao.insertObject(entity);
        if(rows <= 0) {
            throw new ServiceException("添加失败");
        }
    }

    @Override
    public void insertUsers(User entity) {
        //传来的数据不能为空
        if(entity == null){
            throw new ServiceException("保存对象不能为空");
        }
        int rows = userDao.insertUsers(entity);
        if(rows <= 0) {
            throw new ServiceException("添加失败");
        }
    }

    @Override
    public void updateObject(UserRoom entity) {
        if(entity== null){
            throw new ServiceException("更新对象不能为空");
        }
        int rows = userDao.updateObject(entity);
        if(rows <= 0) {
            throw new ServiceException("修改失败");
        }
    }

    @Override
    public Common findObjectById(Integer id) {
        Common common = userDao.findObjectById(id);
        return common;
    }

    @Override
    public void updateUserObject(User entity) {
        if(entity== null){
            throw new ServiceException("更新对象不能为空");
        }
        int rows = userDao.updateUserObject(entity);
        if(rows <= 0) {
            throw new ServiceException("修改失败");
        }
    }

    @Override
    public User findUserObjectById(Integer id) {
        if(id == null){
            throw new ServiceException("id 不能为空");
        }
        User user = userDao.findUserObjectById(id);
        if(user == null) {
            throw new ServiceException("对象不存在");
        }
        return user;
    }

    @Override
    public Map<String, Object> findTenantPageObjects(String realname, String phone, int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<User> list = userDao.findTenantPageObjects(realname,phone,startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = userDao.getTenantRowCount(realname,phone);
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
    public List<Role> getRole(String realname, String phone) {
        return userDao.getRole(realname,phone);
    }

    @Override
    public List<Permission> getPermission(Integer id) {
        return userDao.getPermission(id);
    }

    @Override
    public void blockById(String ids) {
        if(StringUtils.isEmpty(ids)) {
            throw new ServiceException("ids 的值不能为空");
        }
        String[] idArray = ids.split(",");
        int rows = userDao.blockById(idArray);
        if(rows == 0) {
            throw new ServiceException("拉黑失败");
        }
    }

    @Override
    public int saveUser(User entity){
        if (entity == null){
            throw new ServiceException("传入的参数不能为空");
        }
        return userDao.saveUser(entity);
    }

    @Override
    public Map<String, Object> findRoles(String realname) {
        return userDao.findRoles(realname);
    }

    @Override
    public int insertUR(String realname) {
        return userDao.insertUR(realname);
    }

}
