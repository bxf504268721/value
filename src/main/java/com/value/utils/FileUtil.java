package com.value.utils;

import java.io.*;

/**
* @Description file
* @Author bxf
* @Date   2019/8/8
*/
public class FileUtil {
    public static void byteToJpg(String baseDir, String picName, byte[] pic) {
        String basePath = baseDir + File.separator + picName + ".jpg";
        //将byte[] 转换为图片文件
        OutputStream os = null;
        try {
            os = new FileOutputStream(basePath);
            os.write(pic,0,pic.length );
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
