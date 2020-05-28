package com.ssm.controller;

import com.ssm.entity.Activity;
import com.ssm.entity.Notice;
import com.ssm.service.ActivityService;
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

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    private Logger log =
            Logger.getLogger(ActivityController.class.getSimpleName());

    @Autowired
    private ActivityService activityService;

    /**
     * 前往活动管理页面
     * @return
     */
    @RequestMapping("/toActivity.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String toActivity(){
        return "activity/activity";
    }

    /**
     * 分页查询
     * @param pageCurrent
     * @return
     */
    @RequestMapping("/doGetPageObjects.do")
    @RequiresPermissions(value={"activity:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult activity (Integer pageCurrent){
        Map<String,Object> map = activityService.findActivityPageObjects(pageCurrent);
        return new JsonResult(map);
    }

    /**
     * 根据id删除
     * @param ids
     * @return
     */
    @RequestMapping("/doDeleteObjectById.do")
    @RequiresPermissions(value={"activity:del"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doDeleteObjectById(String ids) {
        activityService.delById(ids);
        return new JsonResult();
    }

    /**
     * 前往活动修改页面 根据id修改
     */
    @RequestMapping("/toEditActivityById.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String toEditActivityById(Integer id, Model model) {
        Activity activity = activityService.findActivityById(id);
        model.addAttribute("activity",activity);
        model.addAttribute("code",1);
        return "activity/activity_update";
    }
    /**
     * 前往活动添加页面
     */
    @RequestMapping("/activity_editUI.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String editUI(){
        return "activity/activity_update";
    }

    /**
     * 修改活动信息
     * @param activity
     * @return
     */
    @RequestMapping("/doUpdateObject")
    @RequiresPermissions(value={"activity:update"},logical=Logical.OR)
    @ResponseBody
    public AjaxResult updateActivity(Activity activity) {
        AjaxResult ajaxResult = new AjaxResult();
        try{
            activityService.updateActivity(activity);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }


    /**
     * 添加 活动信息
     * @param activity
     * @return
     */
    @RequestMapping("/doSaveActivity.do")
    @RequiresPermissions(value={"activity:add"},logical= Logical.OR)
    @ResponseBody
    public AjaxResult doSaveActivity(Activity activity){
        AjaxResult ajaxResult = new AjaxResult();
        log.info("Activity为"+activity);
        try{
            activityService.insertActivity(activity);
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
