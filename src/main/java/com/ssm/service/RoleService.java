package com.ssm.service;

import com.ssm.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleService {

    //按条件进行分页查询
    Map<String, Object> findRoles(String realname, int pageCurrent);

    List<Map<String, Object>> FindRoleName();

    void insertUR(String realname,String roleName);
    UserRole findUserRole(String realname, String roleName);

}
