package com.ssm.controller;

import com.ssm.entity.User;
import com.ssm.entity.UserRole;
import com.ssm.service.RoleService;
import com.ssm.service.UserService;
import com.ssm.util.JsonResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    private Logger log =
            Logger.getLogger(RoleController.class.getSimpleName());

    @RequestMapping("/roleUI.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String roleUI(){
        return "role/role-list";
    }

    //根据条件分页查询
    @RequestMapping("/findRoles.do")
    @RequiresPermissions(value={"role:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult roleService(String realname, int pageCurrent){
        Map<String,Object> map = roleService.findRoles(realname,pageCurrent);
        return new JsonResult(map);
    }

    @RequestMapping("/roleSendUI.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String roleSendUI(){
        return "role/role-send";
    }

    @RequestMapping("/doFindRoleName.do")
    @ResponseBody
    public JsonResult doFindRoleName(){
        List<Map<String,Object>> list = roleService.FindRoleName();
        return new JsonResult(list);
    }
    @RequestMapping("/FindUserByName.do")
    @ResponseBody
    public JsonResult FindUserByName(String realname){
        //log.info("username为"+username);
        User user = userService.findObjectByName(realname);
        //log.info("user为"+user);
        return new JsonResult(user);
    }

    @RequestMapping("insertUR.do")
    @RequiresPermissions(value={"role:add"},logical=Logical.OR)
    @ResponseBody
    public JsonResult insertUR(String realname, String roleName){
        //1、判断该角色是否拥有该权限
        UserRole userRole = roleService.findUserRole(realname, roleName);
        if (userRole!=null){
            return new JsonResult(0,"不必重复分配角色");
        }
        //没有 则添加
        roleService.insertUR(realname, roleName);
        return new JsonResult();
    }

    /**
     * 分页查询
     * @param phone
     * @param realname
     * @param pageCurrent
     * @return
     */
    @RequestMapping("/doGetPageObjects.do")
    @RequiresPermissions(value={"tenant:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doGetPageObjects (String realname,String phone,
                                        Integer pageCurrent){
        Map<String,Object> map =
                userService.findTenantPageObjects(realname,phone,pageCurrent);
        return new JsonResult(map);
    }

}
