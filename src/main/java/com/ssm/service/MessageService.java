package com.ssm.service;

import com.ssm.entity.Message;

import java.util.List;

public interface MessageService {

    void insertMessage(Message message);

    void updateAgree(Integer id);
    void updateDisAgree(Integer id);
    void delMessage(Integer id);

    List<Message> findMessage();
}
