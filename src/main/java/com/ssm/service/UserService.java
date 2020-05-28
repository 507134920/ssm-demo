package com.ssm.service;

import com.ssm.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    //根据用户名查询用户信息（登录操作 shiro）
    User findObjectByName(String realname);
    User doLogin(String realname, String password);

    //按条件进行分页查询
    Map<String, Object> findPageObjects(String realname, String phone, int pageCurrent);
    //拉黑操作
    void blockById(String ids);

    //移除操作
    void moveById(String ids);

    //按条件进行分页查询 回收站
    Map<String, Object> findBlockPageObjects(String realname, String phone, int pageCurrent);
    //恢复读者信息
    void reloadById(String ids);

    //添加按钮
    void saveObject(Common entity);
    void insertUsers(User entity);
    //修改按钮
    void updateObject(UserRoom entity);
    //根据id查询
    Common findObjectById(Integer id);


    //修改个人信息按钮
    void updateUserObject(User entity);
    //根据id查询个人信息
    User findUserObjectById(Integer id);

    //按条件进行分页查询
    Map<String, Object> findTenantPageObjects(String realname,
                                              String phone, int pageCurrent);

    List<Role> getRole(String realname,String phone);
    List<Permission>getPermission(Integer id);

    int saveUser(User entity);

    Map<String, Object> findRoles(String realname);
    int insertUR(String realname);
}