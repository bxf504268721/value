package com.value;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValueApplicationTests {
    @Autowired
    StringEncryptor stringEncryptor;

    /** 加密测试*/
    @Test
    public void contextLoads() {
        String msg = stringEncryptor.encrypt("asdasdasdasd");
        System.out.println(msg + "-------------");
    }
}
