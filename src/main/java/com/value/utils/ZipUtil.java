package com.value.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* @Description zip
* @Author bxf
* @Date   2019/8/8
*/
public class ZipUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);
    
    private static final int BUFFER = 1024;
    
    /**
     * 取得指定目录下的所有文件列表，包括子目录. ，但不包含zip文件，也不包含txt文件
     *
     * @param baseDir 指定的目录
     * @return 指定目录下的所以文件列表
     */
    public static List<File> getSubFiles(File baseDir) {

        List<File> subFiles = new ArrayList<>();

        File[] files = baseDir.listFiles(file -> file.isFile() && !file.getName().endsWith(".zip") && !file.getName().endsWith(".txt"));
        if (files != null) {
            subFiles.addAll(Arrays.asList(files));
        }

        File[] dirs = baseDir.listFiles(File::isDirectory);
        if (dirs != null) {
            for (File dir : dirs) {
                subFiles.addAll(getSubFiles(dir));
            }
        }

        return subFiles;
    }
    
    /**
     * 给定根目录，返回另一个文件名的相对路径（用于zip文件夹中的路径）
     *
     * @param baseDir 根目录
     * @param file    实际的文件名
     * @return 相对文件名
     */
    public static String getAbsFileName(String baseDir, File file) {
        String absFileName = file.getName();

        while (true) {
            file = file.getParentFile();
            if (file == null || Objects.equals(baseDir, file.getPath())) {
                break;
            }

            absFileName = String.join(File.separator, file.getName(), absFileName);
        }

        return absFileName;
    }


    /**
     * zip压缩功能，压缩baseDir下的所以文件，包括子目录
     *
     * @param baseDir  文件夹目录
     * @param fileName 文件夹
     * @throws Exception 如果压缩异常
     */
    public static void zipFile(String baseDir, String fileName) throws Exception {
        List<File> fileList = getSubFiles(new File(baseDir));

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(fileName))) {
            ZipEntry entry;
            byte[] buf = new byte[BUFFER];
            int readLen;

            for (File file : fileList) {
                entry = new ZipEntry(getAbsFileName(baseDir, file));
                entry.setSize(file.length());
                entry.setTime(file.lastModified());
                zipOutputStream.putNextEntry(entry);

                try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                    while ((readLen = inputStream.read(buf, 0, BUFFER)) != -1) {
                        zipOutputStream.write(buf, 0, readLen);
                    }
                }
            }
        }
    }

    /**
     * 将文件解压到指定到目录中去
     *
     * @param filePath  zip文件路径
     * @param targetDir 目标目录
     */
    @SuppressWarnings({ "resource", "rawtypes" })
    public static void unzipFile(String filePath, String targetDir) throws IOException {
        ZipFile zipFile = new ZipFile(filePath);
        Enumeration enumeration = zipFile.entries();
        ZipEntry entry;
        File file;
        byte[] bytes = new byte[1024];

        while (enumeration.hasMoreElements()) {
            entry = (ZipEntry) enumeration.nextElement();
            file = new File(targetDir, entry.getName());

            // 如果是文件夹，则创建文件夹
            if (entry.isDirectory()) {
                if (!file.mkdirs()) {
                    logger.error("目录创建失败: {}", file.getPath());
                }
                continue;
            }

            // 如果文件所在目录不存在，则创建目录
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                logger.error("目录创建失败: {}", file.getParentFile().getPath());
            }

            // 此处在循环中try-catch
            try (InputStream inputStream = zipFile.getInputStream(entry); OutputStream outputStream = new FileOutputStream(file)) {
                int len;
                while ((len = inputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, len);
                }
            }

            if (logger.isDebugEnabled()) {
                logger.debug("解压得到文件：{}", file.getPath());
            }
        }
    }
    
}
