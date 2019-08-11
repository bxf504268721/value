package com.value.dao;

import com.value.entities.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 角色信息dao
 * @Author bxf
 * @Date   2019/8/11
 */
@Repository("roleDao")
public interface RoleDao {
    /** 根据用户名获取角色信息*/
    List<Role> listByUsername(String username);
}
