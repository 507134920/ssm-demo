package com.ssm.dao;

import com.ssm.entity.Home;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HomeDao {

    //根据租客的姓名 + 手机号 得到房间密码
    Home findPageObjects(@Param("realname") String realname,
                         @Param("phone") String phone);

    //分页查询
    List<Home> findHomeObjects(
            @Param("hourse") Integer hourse,
            @Param("floor") Integer floor,
            @Param("room_number") String room_number,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    //总记录数
    int getRowCount(
            @Param("hourse") Integer hourse,
            @Param("floor") Integer floor,
            @Param("room_number") String room_number);

    int updateHome(Home home);

    Home doFindObjectById(@Param("id") Integer id);
}