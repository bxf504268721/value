package com.value.entities;
/**
* @Description 角色信息表
* @Author bxf
* @Date   2019/8/11
*/
public class Role {
    /** 角色id*/
    private Long roleId;

    /** 角色名称*/
    private String rolename;

    /** 角色权限*/
    private String auth;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", rolename='" + rolename + '\'' +
                ", auth='" + auth + '\'' +
                '}';
    }
}
