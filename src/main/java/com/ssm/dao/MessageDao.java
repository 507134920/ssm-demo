package com.ssm.dao;

import com.ssm.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao {

    int insertMessage(Message message);

    int updateAgree(@Param("id") Integer id);
    int updateDisAgree(@Param("id") Integer id);

    int delMessage(@Param("id") Integer id);

    List<Message> findMessage();
}
