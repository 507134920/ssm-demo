package com.ssm.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BuyDao {

    List<Map<String,Object>> findTime(@Param("phone") String phone);

    Map<String,Object> findObjectByPhone(@Param("phone") String phone);

    int addTime(@Param("num")Integer num,@Param("phone") String phone);
    int addTime1(@Param("num")Integer num,@Param("phone") String phone);
}
