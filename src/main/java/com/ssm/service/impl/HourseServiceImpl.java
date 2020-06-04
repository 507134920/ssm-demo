package com.ssm.service.impl;

import com.ssm.dao.HourseDao;
import com.ssm.entity.Home;
import com.ssm.entity.HoursePic;
import com.ssm.entity.Notice;
import com.ssm.exception.ServiceException;
import com.ssm.service.HourseService;
import com.ssm.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class HourseServiceImpl implements HourseService {
    @Autowired
    private HourseDao hourseDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> findNoRent(int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Home> list = hourseDao.findNoRent(startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = hourseDao.getNoRentRowCount();
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
    public Map<String, Object> findRentAlready(int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Home> list = hourseDao.findRentAlready(startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = hourseDao.getRentAlreadyRowCount();
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
    public Map<String, Object> findAllObject(int pageCurrent) {
        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        List<Home> list = hourseDao.findAllObject(startIndex, pageSize);
        //获取总记录数并封装分页信息
        int rowCount = hourseDao.getAllRowCount();
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
    public List<HoursePic>  findPicById(Integer id) {
        return hourseDao.findPicById(id);
    }

    @Override
    public List<HoursePic> findAllPic() {
        return hourseDao.findAllPic();
    }

    @Override
    public Map<String, Object> findPic2(float startNum, float endNum,int pageCurrent) {
        //字符串的序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        int pageSize=4;
        int startIndex = (pageCurrent-1)*pageSize;
        //获取当页数据
        //List<Map<String, Object>> list = hourseDao.findPic2(startNum, endNum, startIndex, pageSize);

        Object userList;
        //当redis节点全挂了 抛出异常
        try{
            //高并发条件下，此处有点问题：缓存穿透
            //查询缓存
            userList = redisTemplate.opsForValue().get("userList");
            //双重检测锁
            if (null == userList) {
                synchronized (this) {
                    //从redis获取一下
                    userList = redisTemplate.opsForValue().get("userList");

                    if (null == userList) {
                        //缓存为空，查询一遍数据库
                        userList = hourseDao.findPic2(startNum, endNum, startIndex, pageSize);
                        //把数据库查询出来的数据，放入redis中
                        redisTemplate.opsForValue().set("userList", userList,300,TimeUnit.SECONDS);
                    }
                }
            }
        }catch (Exception e){
            userList = hourseDao.findPic2(startNum, endNum, startIndex, pageSize);
        }

        //获取总记录数并封装分页信息
        int rowCount = hourseDao.findPic2RowCount(startNum, endNum);
        PageObject pageObject = new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setStartIndex(startIndex);
        pageObject.setPageCurrent(pageCurrent);

        //将当前页数据以及分页信息分装到map
        Map<String,Object> map = new HashMap<>();
        map.put("list", userList);
        map.put("pageObject",pageObject);
        return map;
    }
}
