package com.ssm.dao;

import com.ssm.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeDao {

    //增加公告
    int insertNotice(Notice notice);
    //显示本月公告
    List<Notice> findNotice();
    //显示所有公告信息（按时间排行）
    List<Notice> findAllNotice(@Param("startIndex") int startIndex,
                               @Param("pageSize") int pageSize);
    Integer noticeNum();

    int delById(@Param("id")int id);
    int updateNotice(Notice notice);
    Notice findNoticeById(@Param("id")Integer id);
}
