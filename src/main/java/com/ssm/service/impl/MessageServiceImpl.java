package com.ssm.service.impl;
import com.ssm.dao.MessageDao;
import com.ssm.entity.Message;
import com.ssm.exception.ServiceException;
import com.ssm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void insertMessage(Message message) {
        if (message == null) {
            throw new ServiceException("添加留言信息不能为空");
        } else {
            int rows = messageDao.insertMessage(message);
            if (rows <= 0) {
                throw new ServiceException("添加失败");
            }
        }
    }

    @Override
    public void updateAgree(Integer id) {
        messageDao.updateAgree(id);
}

    @Override
    public void updateDisAgree(Integer id) {
        messageDao.updateDisAgree(id);
    }

    @Override
    public void delMessage(Integer id) {
        messageDao.delMessage(id);
    }

    @Override
    public List<Message> findMessage() {
        return messageDao.findMessage();
    }


}
