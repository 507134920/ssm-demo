package com.ssm.controller;

import com.ssm.entity.Notice;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import com.ssm.util.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
public class LoginController {
    private Logger log =
            Logger.getLogger(LoginController.class.getSimpleName());

    @Autowired
    private UserService userService;
    /**
     * 前往登录页面
     * @return
     */
    @RequestMapping("/loginUI.do")
    public String login(){
        return "login";
    }

    /**
     * 前往注册页面
     * @return
     */
    @RequestMapping("/registUI.do")
    public String regist(){
        return "reg";
    }

    /**
     * 登录
     * @param realname  姓名
     * @param password  密码
     * @param rs
     * @return
     */
    @PostMapping("/login.do")
    public String toLogin(String realname, String password, HttpServletRequest rs) {
        //log.info("姓名为"+realname+"密码为"+password);
        User user = userService.doLogin(realname, password);
        if(user == null) {
            rs.setAttribute("error", "用户名或密码错误");
            return "login";
        }
        if (user.getValid()==0){
            rs.setAttribute("error", "该用户已被拉黑！请联系管理员");
            return "login";
        }
        return "index";
    }
    /**
     * 退出
     * @return
     */
    @GetMapping("/logout.do")
    public String logout(){
        // 销毁会话
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    /**
     * 切换账号
     * @return
     */
    @GetMapping("/unlogin.do")
    public String unlogin(){
        // 销毁会话
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 注册
     */
    @RequestMapping("/reg.do")
    public String doSaveNotice(User entity){
        AjaxResult ajaxResult = new AjaxResult();
        User user = new User();
        user.setId(entity.getId());
        user.setPhone(entity.getPhone());
        user.setRealname(entity.getRealname());
        String saltStr = UUID.randomUUID().toString();
        ByteSource salt = ByteSource.Util.bytes(saltStr);
        String pwd = new SimpleHash("MD5",entity.getPassword(),salt).toString();
        user.setPassword(pwd);
        user.setSalt(saltStr);
        user.setValid(1);

        int i = userService.saveUser(user);
        if (i>=0){
            return login();
        }else {
            return null;
        }
    }

}
