package com.ssm.dao;

import com.ssm.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    //根据用户名查询用户信息（登录操作 shiro）
    User findObjectByName(String realname);

    //分页查询
    List<Map<String, Object>> findPageObjects(
            @Param("realname") String realname,
            @Param("phone") String phone,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    //总记录数
    int getRowCount(
            @Param("realname") String realname,
            @Param("phone") String phone);
    //拉黑
    int blockById(@Param("ids")String[] ids);

    //删除
    int moveById(@Param("ids")String[] ids);

    //分页查询
    List<User> findBlockPageObjects(
            @Param("realname") String realname,
            @Param("phone") String phone,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    //总记录数
    int getBlockRowCount(
            @Param("phone") String phone,
            @Param("realname") String realname);
    //恢复拉黑名单内的租客
    int reloadById(@Param("ids")String[] ids);


    int insertObject(Common userRoom);
    int insertUsers(User user);

    Common findObjectById(@Param("id") Integer id);
    int updateObject(UserRoom userRoom);


    //修改按钮
    int updateUserObject(User entity);
    //根据id查询读者信息
    User findUserObjectById(@Param("id") Integer id);

    //分页查询
    List<User> findTenantPageObjects(
            @Param("realname") String realname,
            @Param("phone") String phone,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    //总记录数
    int getTenantRowCount(
            @Param("realname") String realname,
            @Param("phone") String phone);

    List<Role> getRole(@Param("realname") String realname,
                       @Param("phone") String phone);
    List<Permission>getPermission(@Param("id") Integer id);

    int saveUser(User entity);



    Map<String, Object> findRoles(@Param("realname") String realname);
    int insertUR(@Param("realname") String realname);

}
