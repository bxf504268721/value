package com.value;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
* @Description
* @Author bxf
* @Date   2019/8/11
*/
public class NormalTest {
    /**
     * 加密测试
     */
    @Test
    public void encode() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("admin"));
    }
}
