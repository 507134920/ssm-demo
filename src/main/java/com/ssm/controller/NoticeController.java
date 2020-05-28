package com.ssm.controller;

import com.ssm.entity.Notice;
import com.ssm.entity.User;
import com.ssm.service.NoticeService;
import com.ssm.util.AjaxResult;
import com.ssm.util.JsonResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 前往公告管理页面
     * @return
     */
    @RequestMapping("/toNotice.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String toNotice(){
        return "notice/notice";
    }

    @RequestMapping("/doSaveNotice.do")
    @RequiresPermissions(value={"notice:add"},logical= Logical.OR)
    @ResponseBody
    public AjaxResult doSaveNotice(Notice notice){
        //log.info("添加的通知信息"+notice);
        AjaxResult ajaxResult = new AjaxResult();
        try{
            noticeService.insertNotice(notice);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    @RequestMapping("/notice.do")
    @RequiresPermissions(value={"notice:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult notice (Integer pageCurrent){
        Map<String,Object> map = noticeService.findNoticePageObjects(pageCurrent);
        return new JsonResult(map);
    }
    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/doDeleteObjectById.do")
    @RequiresPermissions(value={"notice:del"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doDeleteObjectById(int id) {
        noticeService.delById(id);
        return new JsonResult();
    }
    /**
     * 前往公告编辑页面 根据id修改
     */
    @RequestMapping("/toEditNoticeById.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String toEditNoticeById(Integer id, Model model) {
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        model.addAttribute("code",1);
        return "notice/notice-edit";
    }
    /**
     * 修改公告
     * @param entity
     * @return
     */
    @RequestMapping("/doUpdateObject")
    @RequiresPermissions(value={"notice:update"},logical=Logical.OR)
    @ResponseBody
    public AjaxResult updateNotice(Notice entity) {
        AjaxResult ajaxResult = new AjaxResult();
        try{
            noticeService.updateNotice(entity);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }



}
