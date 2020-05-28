package com.ssm.controller;

import com.ssm.entity.Common;
import com.ssm.entity.Notice;
import com.ssm.entity.User;
import com.ssm.entity.UserRoom;
import com.ssm.service.UserService;
import com.ssm.util.AjaxResult;
import com.ssm.util.JsonResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger log =
            Logger.getLogger(UserController.class.getSimpleName());
    @Autowired
    private UserService userService;

    /**
     * 前往租客信息页面 tenant
     */
    @RequestMapping("/tenant_list.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String tenantList(){
        return "tenant/tenant-list";
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
    public JsonResult doGetPageObjects (String phone,
                                        String realname,
                                        Integer pageCurrent){
        Map<String,Object> map =
                userService.findPageObjects(phone,realname,pageCurrent);
        return new JsonResult(map);
    }

    /**
     * 前往租客编辑和添加页面
     */
    @RequestMapping("/tenant_editUI.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String tenant_editUI(){
        return "tenant/tenant-update";
    }

    /**
     * 前往租客修改页面
     */
    @RequestMapping("/findObjectById.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String findObjectById(Integer id, Model model) {
        Common common = userService.findObjectById(id);
        model.addAttribute("tenant",common);
        model.addAttribute("code",1);
        return "tenant/tenant-update";
    }

    /**
     * 添加租客 实际上为修改user_room表
     */
    @RequestMapping("/doSaveObject.do")
    @RequiresPermissions(value={"tenant:add"},logical=Logical.OR)
    @ResponseBody
    public AjaxResult doSaveObject(@RequestBody Common entity) {
        log.info("Controller 添加的Common"+entity);
        AjaxResult ajaxResult = new AjaxResult();
        try{
            // 1 根据姓名 判断是否存在该用户
            User user = userService.findObjectByName(entity.getRealname());
            //1.1 不存在 先添加该用户 在添加租客
            if (user == null){
                User user1 = new User();
                user1.setRealname(entity.getRealname());
                user1.setPhone(entity.getPhone());
                user1.setPassword("174d6f4c544799e0bd1323759687aa9e");
                user1.setSalt("9052e391-2efa-4643-9332-585a4e657473");
                user1.setValid(1);
                userService.insertUsers(user1);
            }
            //1.2 存在 直接添加租客
            userService.saveObject(entity);

            //2 查询是否存在该租客
            Map<String, Object> roles = userService.findRoles(entity.getRealname());
            // 2.1、不存在
            if (roles==null || roles.size()<=0){
                //自动分配租客权限
                int i = userService.insertUR(entity.getRealname());
                if (i<=0){
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("自动分配租客权限失败，请手动分配权限！！");
                }
            }
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
     * 修改租客 的租赁信息  实际上为修改user_room表
     */
    @RequestMapping("/doUpdateObject.do")
    @RequiresPermissions(value={"tenant:update"},logical=Logical.OR)
    @ResponseBody
    public AjaxResult doUpdateObject(Common entity) {
        //log.info("Controller 修改的UserRoom"+entity);
        UserRoom userRoom= new UserRoom();
        userRoom.setUserid(entity.getUserid());
        userRoom.setHomeid(entity.getHomeid());
        userRoom.setStartleasetime(entity.getStartleasetime());
        userRoom.setEndleasetime(entity.getEndleasetime());
        AjaxResult ajaxResult = new AjaxResult();
        try{
            userService.updateObject(userRoom);
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
     * 根据id删除租客
     * @param ids
     * @return
     */
    @RequestMapping("/doDeleteObjectById.do")
    @RequiresPermissions(value={"tenant:del"},logical= Logical.OR)
    @ResponseBody
    public JsonResult doDeleteObjectById(String ids) {
        userService.moveById(ids);
        return new JsonResult();
    }

    /**
     * 根据id拉黑租客
     * @param ids
     * @return
     */
    @RequestMapping("/doBlockObjectById.do")
    @RequiresPermissions(value={"tenant:del"},logical= Logical.OR)
    @ResponseBody
    public JsonResult doBlockObjectById(String ids) {
        userService.blockById(ids);
        return new JsonResult();
    }

    /**
     * 黑名单
     */
    @RequestMapping("/block_list.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String blockList(){
        return "tenant/block_list";
    }

    /**
     * 分页查询 拉黑名单
     * @param phone
     * @param realname
     * @param pageCurrent
     * @return
     */
    @RequestMapping("/doGetBlockPageObjects.do")
    @RequiresPermissions(value={"tenant:load"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doGetBlockPageObjects (String phone,
                                           String realname,
                                           Integer pageCurrent){
        Map<String,Object> map = userService.findBlockPageObjects(phone,realname,pageCurrent);
        return new JsonResult(map);
    }

    /**
     * 根据id取消拉黑
     * @param ids
     * @return
     */
    @RequestMapping("/doReloadById.do")
    @RequiresPermissions(value={"tenant:reload"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doReloadById(String ids){
        userService.reloadById(ids);
        return new JsonResult();
    }


    /**
     * 登录用户进入修改个人信息页面
     */
    @RequestMapping("/updateMsg.do")
    @RequiresRoles(value={"SysManager","Tenant","user"},logical= Logical.OR)
    public String updateMsgUI(Integer id, Model model) {
        log.info("id"+id);
        User user = userService.findUserObjectById(id);
        model.addAttribute("tenant",user);
        model.addAttribute("code",1);
        return "tenant/tenant";
    }
    /**
     * 修改租客信息
     * @param entity
     * @return
     */
    @RequestMapping("/updateUserObject.do")
    @RequiresPermissions(value={"tenant:update"},logical=Logical.OR)
    @ResponseBody
    public AjaxResult updateUserObject(User entity) {
        AjaxResult ajaxResult = new AjaxResult();
        User user = new User();
        user.setId(entity.getId());
        user.setPhone(entity.getPhone());
        user.setRealname(entity.getRealname());
        String saltStr = UUID.randomUUID().toString();   //生成盐值
        ByteSource salt = ByteSource.Util.bytes(saltStr);
        String pwd = new SimpleHash("MD5",entity.getPassword(),salt).toString();
        user.setPassword(pwd);
        user.setSalt(saltStr);
        user.setValid(1);
        //log.info("Controller 更新的读者信息"+user);
        try{
            userService.updateUserObject(user);
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
