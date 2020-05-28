package com.ssm.service;


import com.ssm.entity.Repair;

import java.util.Map;

public interface RepairService {

    void insertRepair(Repair repair);

    Map<String, Object> findRepairPageObjects(int pageCurrent);

    void delById(String ids);
}
