package com.ssm.service;

import com.ssm.entity.HoursePic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HourseService {

    //分页查询 未租 房间信息
    Map<String, Object> findNoRent(int pageCurrent);
    //分页查询 已租 房间信息
    Map<String, Object> findRentAlready(int pageCurrent);
    //分页查询 所有 房间信息
    Map<String, Object> findAllObject(int pageCurrent);


    List<HoursePic> findPicById(Integer id);
    List<HoursePic> findAllPic();
}
