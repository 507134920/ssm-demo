package com.ssm.controller;

import com.ssm.entity.HoursePic;
import com.ssm.service.HourseService;
import com.ssm.util.JsonResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hourse")
public class HourseController {
    @Autowired
    private HourseService hourseService;

    /**
     * 前往房间信息页面
     */
    @RequestMapping("/tohourse.do")
    //@RequiresRoles(value={"SysManager","Tenant","user"},logical= Logical.OR)
    public String tohourse(){
        return "hourse/hourse_list";
    }

    /**
     * 查询所有 房间信息
     */
    @RequestMapping("/doGetAllObject.do")
    //@RequiresPermissions(value={"hourse:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doGetAllObject (Integer pageCurrent){
        Map<String,Object> map = hourseService.findAllObject(pageCurrent);
        return new JsonResult(map);
    }

    /**
     * 查询已租 房间信息
     */
    @RequestMapping("/doGetRentAlready.do")
    //@RequiresPermissions(value={"read:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doGetRentAlready (Integer pageCurrent){
        Map<String,Object> map = hourseService.findRentAlready(pageCurrent);
        return new JsonResult(map);
    }
    /**
     * 查询未租 房间信息
     */
    @RequestMapping("/doGetNoRent.do")
    //@RequiresPermissions(value={"read:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doGetNoRent (Integer pageCurrent){
        Map<String,Object> map = hourseService.findNoRent(pageCurrent);
        return new JsonResult(map);
    }

    /**
     * 房间详细信息界面
     */
    @RequestMapping("toPicUI")
    public String toPicUI(){
        //return "hourse/hourse_pic";
        return "redirect:hourse_pic.html";
    }
    /**
     * 查询房间详细信息
     */
    @RequestMapping("/toPic.do")
    //@RequiresPermissions(value={"read:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult toPic(Integer id){
        List<HoursePic> picById = hourseService.findPicById(id);
        return new JsonResult(picById);
    }

    /**
     * 查询所有房间图片
     */
    @RequestMapping("/findAllPic.do")
    //@RequiresPermissions(value={"read:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult findAllPic(){
        List<HoursePic> Allpic = hourseService.findAllPic();
        return new JsonResult(Allpic);
    }


}
