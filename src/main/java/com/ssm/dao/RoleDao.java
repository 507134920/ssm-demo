package com.ssm.dao;

import com.ssm.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    //分页查询
    List<Map<String, Object>> findRoles(
            @Param("realname") String realname,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    //总记录数
    int getRowCount(@Param("realname") String realname);

    List<Map<String,Object>> FindRoleName();

    int insertUR(@Param("realname") String realname,
                 @Param("roleName") String roleName);
    UserRole findUserRole(@Param("realname") String realname,
                          @Param("roleName") String roleName);
}
