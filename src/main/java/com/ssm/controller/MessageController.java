package com.ssm.controller;

import com.ssm.entity.Message;
import com.ssm.service.MessageService;
import com.ssm.util.AjaxResult;
import com.ssm.util.JsonResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 去往留言板块
     */
    @RequestMapping("toMessage")
    //@RequiresRoles(value={"SysManager","Tenant","user"},logical= Logical.OR)
    public String toMessage(){
        return "message/message_add";
    }
    /**
     * 添加留言
     */
    @RequestMapping("addMessage")
    //@RequiresPermissions(value={"message:add"},logical=Logical.OR)
    @ResponseBody
    public AjaxResult addMessage(Message message){
        AjaxResult ajaxResult = new AjaxResult();
        try{
            messageService.insertMessage(message);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    /**
     * 显示留言信息
     */
    @RequestMapping("showMessage")
    @ResponseBody
    public JsonResult showMessage(){
        List<Message> messageList = messageService.findMessage();
        return new JsonResult(messageList);
    }

    /**
     * 修改赞数量
     */
    @RequestMapping("updateMessageAgreeNum")
    @ResponseBody
    public JsonResult updateMessageAgreeNum(Integer id){
        messageService.updateAgree(id);
        return new JsonResult();
    }
    /**
     * 修改反对数量
     */
    @RequestMapping("updateMessageDisAgreeNum")
    @ResponseBody
    public JsonResult updateMessageDisAgreeNum(Integer id){
        messageService.updateDisAgree(id);
        return new JsonResult();
    }

    /**
     * 删除留言信息
     */
    @RequestMapping("delMessage")
    @RequiresPermissions(value={"message:del"},logical=Logical.OR)
    @ResponseBody
    public JsonResult delMessage(Integer id){
        messageService.delMessage(id);
        return new JsonResult();
    }
}
