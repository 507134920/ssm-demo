package com.ssm.entity;

import java.io.Serializable;
import java.util.List;


public class ActiveUser implements Serializable {

	private static final long serialVersionUID = -4763148918239860331L;
	private String realname;// 用户真实姓名
	private String phone;
	private Integer valid; //状态 0 已拉黑 1 已启用
	private List<Permission> permissions;// 权限
	private List<Role> roles;// 角色



	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "ActiveUser{" +
				"phone='" + phone + '\'' +
				", realname='" + realname + '\'' +
				", valid=" + valid +
				", permissions=" + permissions +
				", roles=" + roles +
				'}';
	}
}
