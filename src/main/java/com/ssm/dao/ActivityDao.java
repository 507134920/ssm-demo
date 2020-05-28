package com.ssm.dao;

import com.ssm.entity.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityDao {

    //增加公告
    int insertActivity(Activity activity);
    //显示本月公告
    List<Activity> findActivity();
    //显示所有公告信息（按时间排行）
    List<Activity> findAllActivity(@Param("startIndex") int startIndex,
                               @Param("pageSize") int pageSize);
    Integer activityNum();

    int delById(@Param("ids")String[] ids);
    int updateActivity(Activity activity);
    Activity findActivityById(@Param("id")Integer id);
}
