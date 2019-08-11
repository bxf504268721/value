package com.value.utils;

import org.jasypt.util.text.BasicTextEncryptor;

/**
* @Description 加密工具
* @Author bxf
* @Date   2019/8/11
*/
public class JasyptUtil {
    private static BasicTextEncryptor encryptor = new BasicTextEncryptor();

    /**
     * 加密
     * @param msg
     * @return
     */
    public static String encrypt(String msg) {
        encryptor.setPassword("5201314");
        return encryptor.encrypt(msg);
    }

    /**
     * 解密
     * @param msg
     * @return
     */
    public static String decrypt(String msg) {
        encryptor.setPassword("5201314");
        return encryptor.decrypt(msg);
    }

}
