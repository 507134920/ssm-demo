package com.ssm.shiro;

import com.ssm.entity.ActiveUser;
import com.ssm.entity.Permission;
import com.ssm.entity.Role;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ShiroUserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("=========授权==========");
        //从 principals获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        ActiveUser activeUser =  (ActiveUser) principals.getPrimaryPrincipal();
        //单独定一个集合对象
        //根据登录用户查询角色和权限
        List<Role> roleList= userService.getRole(activeUser.getRealname(),activeUser.getPhone());
        List<Permission> permissionList=null;
        List<String> permissions=new ArrayList<>();
        List<String> roles=new ArrayList<>();

        for (Role role:roleList) {
            permissionList = userService.getPermission(role.getId());
            for (Permission p:permissionList) {
                permissions.add(p.getPermission());
            }
            roles.add(role.getRoleName());
        }

        //System.out.println("角色"+roles+"权限"+permissions);
        //查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("==登录认证==");
        //第一步：从token中取出用户名
        String realname = (String)token.getPrincipal();

        // 第二步：根据用户名 从数据库 获取用户
        User user = null;
        try {
            user = userService.findObjectByName(realname);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if(user == null) {
            return null;
        }

        // 从数据库查询到密码
        String password = user.getPassword();

        //盐值.
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());

        // 如果查询到返回认证信息AuthenticationInfo

        //activeUser就是用户身份信息
        ActiveUser activeUser = new ActiveUser();
        activeUser.setRealname(user.getRealname());
        activeUser.setPhone(user.getPhone());
        activeUser.setValid(user.getValid());


        //System.out.println("认证内"+activeUser);
        //自动完成密码比对   - 密码的比对:
        //通过 AuthenticatingRealm 的 credentialsMatcher 属性来进行的密码的比对!
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(activeUser,password,credentialsSalt,getName());
        SecurityUtils.getSubject().getSession().setAttribute("currentUser",user);
        return info;
    }
}
