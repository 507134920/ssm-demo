package com.ssm.dao;

import com.ssm.entity.Repair;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairDao {

    //增加维修信息
    int insertRepair(Repair repair);

    //显示所有维修信息（按时间排行）
    List<Repair> findAllRepair(@Param("startIndex") int startIndex,
                               @Param("pageSize") int pageSize);
    Integer repairNum();

    int delById(@Param("ids")String[] ids);

}
