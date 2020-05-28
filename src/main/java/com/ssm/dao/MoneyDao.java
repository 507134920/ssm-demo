package com.ssm.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MoneyDao {

    //分页查询
    List<Map<String, Object>> findPageObjects(
            @Param("realname") String realname,
            @Param("phone") String phone,
            @Param("hourse") Integer hourse,
            @Param("floor") Integer floor,
            @Param("room_number") String room_number,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    //总记录数
    int getRowCount(
            @Param("realname") String realname,
            @Param("phone") String phone,
            @Param("hourse") Integer hourse,
            @Param("floor") Integer floor,
            @Param("room_number") String room_number);

    int updateMoney(@Param("hourse") Integer hourse,
                    @Param("floor") Integer floor,
                    @Param("room_number") String room_number,
                    @Param("waterprice") float waterprice,
                    @Param("electricprice") float electricprice);

    List<Map<String, Object>> doFindObjectById(@Param("id") int id);
}
