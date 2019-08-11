package com.value.service.impl;

import com.value.dao.RoleDao;
import com.value.entities.Role;
import com.value.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
* @Description 角色信息实现类
* @Author bxf
* @Date   2019/8/11
*/
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /** 根据用户名获取角色信息*/
    @Override
    public List<Role> listByUsername(String username) {
        return roleDao.listByUsername(username);
    }
}
