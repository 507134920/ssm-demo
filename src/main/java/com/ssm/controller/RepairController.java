package com.ssm.controller;

import com.ssm.service.RepairService;
import com.ssm.util.JsonResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;

    /**
     * 前往维修报备管理页面
     * @return
     */
    @RequestMapping("/toRepair.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String toRepair(){
        return "repair/repair-list";
    }

    /**
     * 分页显示
     */
    @RequestMapping("/doGetPageObjects.do")
    @RequiresPermissions(value={"repair:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doGetPageObjects (Integer pageCurrent){
        Map<String,Object> map = repairService.findRepairPageObjects(pageCurrent);
        return new JsonResult(map);
    }

    /**
     * 根据id删除
     * @param ids
     * @return
     */
    @RequestMapping("/doDeleteObjectById.do")
    @RequiresPermissions(value={"repair:del"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doDeleteObjectById(String ids) {
        repairService.delById(ids);
        return new JsonResult();
    }


}
