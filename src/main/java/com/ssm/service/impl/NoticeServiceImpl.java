package com.ssm.service.impl;

import com.ssm.dao.NoticeDao;
import com.ssm.entity.Notice;
import com.ssm.entity.User;
import com.ssm.exception.ServiceException;
import com.ssm.service.NoticeService;
import com.ssm.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public void insertNotice(Notice notice) {
        if (notice == null) {
            throw new ServiceException("添加对象不能为空");
        } else {
            int rows = noticeDao.insertNotice(notice);
            if (rows <= 0) {
                throw new ServiceException("添加失败");
            }
        }
    }

    @Override
    public List<Notice> findNotice() {
        return noticeDao.findNotice();
    }

    @Override
    public Map<String, Object> findNoticePageObjects(int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Notice> list = noticeDao.findAllNotice(startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = noticeDao.noticeNum();
        PageObject pageObject = new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setStartIndex(startIndex);
        pageObject.setPageCurrent(pageCurrent);

        //将当前页数据以及分页信息分装到map
        Map<String,Object> map = new HashMap<>();
        map.put("list", list);
        map.put("pageObject",pageObject);
        return map;
    }

    @Override
    public void delById(int id) {
        int rows = noticeDao.delById(id);
        if(rows == 0) {
            throw new ServiceException("删除失败");
        }
    }

    @Override
    public void updateNotice(Notice notice) {
        int i = noticeDao.updateNotice(notice);
        if(i == 0) {
            throw new ServiceException("修改失败");
        }
    }

    @Override
    public Notice findNoticeById(Integer id) {
        if(id == null){
            throw new ServiceException("id 不能为空");
        }
        Notice notice = noticeDao.findNoticeById(id);
        if(notice == null) {
            throw new ServiceException("对象不存在");
        }
        return notice;
    }
}
