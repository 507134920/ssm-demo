package com.ssm.controller;

import com.ssm.entity.Home;
import com.ssm.service.HomeService;
import com.ssm.util.AjaxResult;
import com.ssm.util.JsonResult;
import com.ssm.util.Number;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/myhome")
public class MyHomeController {
    private Logger log =
            Logger.getLogger(MyHomeController.class.getSimpleName());
    @Autowired
    private HomeService homeService;

    /**
     * 前往门锁密码管理页面
     */
    @RequestMapping("/toHomeUI.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String homeUI(){
        return "tb_home/home-list";
    }

    /**
     * 分页查询
     * @param hourse
     * @param floor
     * @param room_number
     * @param pageCurrent
     * @return
     */
    @RequestMapping("/doGetHomeObjects.do")
    @RequiresPermissions(value={"home:query"},logical= Logical.OR)
    @ResponseBody
    public JsonResult doGetHomeObjects (Integer hourse, Integer floor, String room_number,
                                        Integer pageCurrent){
        Map<String,Object> map =
                homeService.findHomeObjects(hourse,floor,room_number,pageCurrent);
        return new JsonResult(map);
    }

    /**
     * 根据 id 查找 跳转到修改页面
     * @param id
     * @return
     */
    @RequestMapping("/doFindObjectById.do")
    @RequiresPermissions(value={"home:update"},logical=Logical.OR)
    public String doFindObjectById(Integer id, Model model) {
        Home home = homeService.doFindObjectById(id);
        model.addAttribute("home",home);
        return "tb_home/home_add";
    }

    /**
     * 随机生成6位数字的密码
     */
    @RequestMapping("/dogetNumber.do")
    @ResponseBody
    public JsonResult dogetNumber() {
        String number = Number.getNumber();
        return new JsonResult(number);
    }
    /**
     * 保存修改
     */
    @RequestMapping("/doUpdateObject.do")
    @RequiresPermissions(value={"home:save"},logical= Logical.OR)
    @ResponseBody
    public AjaxResult doUpdateObject(Home home) {
        AjaxResult ajaxResult = new AjaxResult();
        try{
            homeService.updateHome(home);
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
     * 前往获取密码界面
     */
    @RequestMapping("/updatePassword.do")
    @RequiresRoles(value={"SysManager","Tenant"},logical= Logical.OR)
    public String updatePassword(String homes,Model model) {
        String[] s = homes.split(" ");
        String realname =null;
        String phone=null;
        if (s.length>1){
            realname = s[0];
            phone = s[1];
        }
        //log.info("realname"+realname);
        //log.info("phone"+phone);
        Home home = homeService.findPageObjects(realname, phone);
        model.addAttribute("home",home);
        return "tb_home/home_update";
    }

}
