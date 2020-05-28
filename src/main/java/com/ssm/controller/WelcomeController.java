package com.ssm.controller;

import com.ssm.entity.Activity;
import com.ssm.entity.Message;
import com.ssm.entity.Notice;
import com.ssm.entity.Repair;
import com.ssm.service.ActivityService;
import com.ssm.service.NoticeService;
import com.ssm.service.RepairService;
import com.ssm.util.AjaxResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private RepairService repairService;
    @Autowired
    private ActivityService activityService;
    /**
     * 前往welcome
     * @return
     */
    @RequestMapping("/welcome.do")
    //@RequiresRoles(value={"SysManager","Tenant","user"},logical= Logical.OR)
    public String toWelcome(Model model) {
        List<Activity> activityList = activityService.findActivity();
        model.addAttribute("activityList",activityList);
        List<Notice> noticeList = noticeService.findNotice();
        model.addAttribute("noticeList",noticeList);
        return "welcome";
    }


    /**
     * 去往维修报备板块
     */
    @RequestMapping("toRepair.do")
    @RequiresRoles(value={"SysManager","Tenant"},logical= Logical.OR)
    public String toRepair(){
        return "repair/repair_add";
    }
    /**
     * 添加维修报备信息
     */
    @RequestMapping("addRepair.do")
    @RequiresPermissions(value={"repair:add"},logical=Logical.OR)
    @ResponseBody
    public AjaxResult addRepair(Repair repair){
        AjaxResult ajaxResult = new AjaxResult();
        try{
            repairService.insertRepair(repair);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }
}
