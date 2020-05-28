package com.ssm.service;

import com.ssm.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    void insertNotice(Notice notice);
    List<Notice> findNotice();

    Map<String, Object> findNoticePageObjects(int pageCurrent);

    void delById(int id);

    void updateNotice(Notice notice);

    Notice findNoticeById(Integer id);

}
