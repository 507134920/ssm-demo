package com.ssm.service;

import com.ssm.entity.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    void insertActivity(Activity activity);
    List<Activity> findActivity();

    Map<String, Object> findActivityPageObjects(int pageCurrent);

    void delById(String ids);

    void updateActivity(Activity activity);

    Activity findActivityById(Integer id);

}
