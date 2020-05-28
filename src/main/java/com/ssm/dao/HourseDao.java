package com.ssm.dao;

import com.ssm.entity.Home;
import com.ssm.entity.HoursePic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HourseDao {

    //分页查询 未租 房间信息
    List<Home> findNoRent(
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    int getNoRentRowCount();

    //分页查询 已租 房间信息
    List<Home> findRentAlready(
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    int getRentAlreadyRowCount();

    //分页查询 所有 房间信息
    List<Home> findAllObject(
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    int getAllRowCount();

    List<HoursePic> findPicById(@Param("id") Integer id);
    List<HoursePic> findAllPic();
}
