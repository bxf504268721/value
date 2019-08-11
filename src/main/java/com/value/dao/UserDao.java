package com.value.dao;

import com.value.entities.User;
import org.springframework.stereotype.Repository;
/**
* @Description
* @Author bxf 用户dao
* @Date   2019/8/11
*/
@Repository("userDao")
public interface UserDao {
    User getUserByName(String username);
}
