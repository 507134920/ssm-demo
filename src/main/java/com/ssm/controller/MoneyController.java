package com.ssm.controller;

import com.ssm.entity.User;
import com.ssm.service.MoneyService;
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

import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/money")
public class MoneyController {
    private Logger log =
            Logger.getLogger(MoneyController.class.getSimpleName());
    @Autowired
    private MoneyService moneyService;

    /**
     * 前往物业缴费管理页面 money
     */
    @RequestMapping("/money_list.do")
    @RequiresRoles(value={"SysManager"},logical= Logical.OR)
    public String moneyList(){
        return "money/money-list";
    }

    /**
     * 分页查询
     * @param phone
     * @param realname
     * @param pageCurrent
     * @return
     */
    @RequestMapping("/doGetPageObjects.do")
    @RequiresPermissions(value={"money:query"},logical=Logical.OR)
    @ResponseBody
    public JsonResult doGetPageObjects (String realname,String phone,
                                        Integer hourse,Integer floor,
                                        String room_number,Integer pageCurrent){
        Map<String,Object> map =
                moneyService.findPageObjects(realname,phone,hourse,floor,room_number,pageCurrent);
        return new JsonResult(map);
    }


    /**
     * 前往编辑页面
     * @return
     */
    @RequestMapping("/money_editUI.do")
    @RequiresRoles(value={"SysManager","Tenant"},logical= Logical.OR)
    public String editUI(){
        return "money/money-update";
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @RequestMapping("/updateObjectById.do")
    public String doFindObjectById(Integer id, Model model) {
        Map<String, Object> money = moneyService.doFindObjectById(id);
        log.info("money"+money);
        model.addAttribute("money",money);
        model.addAttribute("code",1);
        return "money/money-update";
    }

    /**
     * 修改缴费信息
     * @return
     */
    @RequestMapping("/doUpdateObject.do")
    @RequiresPermissions(value={"money:update"},logical= Logical.OR)
    @ResponseBody
    public AjaxResult doUpdateObject(Integer hourse,Integer floor, String room_number,
            float waterprice,float electricprice) {
        //log.info("Controller 更新的读者信息"+entity);
        AjaxResult ajaxResult = new AjaxResult();
        try{
            moneyService.updateMoney(hourse,floor,room_number,waterprice,electricprice);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("缴费成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("缴费失败");
        }
        return ajaxResult;
    }

}
