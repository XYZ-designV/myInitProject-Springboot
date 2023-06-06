package com.xyz.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 自定义文件处理工具类
 * @author xyz
 * 2023/5/30
 */
public class FileUtil {

    /**
     * 转换文件大小的单位 Byte -> B KB MB GB
     * @param size
     * @return
     */
    public static String getFileSize(Long size){
        // 如果字节数少于1024，则直接为B单位，否则先除以1024
        if (size < 1024) {
            return size + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节除以1024后，少于1024，则直接以KB作为单位
        if (size < 1024) {
            return  Math.round(size * 100) / 100 + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            return  Math.round(size * 100) / 100 + "KB";
        } else {
            return  Math.round(size /1024 * 100) / 100 + "GB";
        }
    }

    /**
     * 按照时间创建文件夹: 年/月/日 ---  1999/1/01
     * @param dir 传过来的绝对路径,例如 D:\\test\\test1
     * @return
     */
    public static File mkdir(String dir) {
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String datePath = format.format(time);
        String Y = "\\" + calendar.get(Calendar.YEAR);
        String M = "\\" + (calendar.get(Calendar.MONTH) + 1);
        String D = "\\" + calendar.get(Calendar.DATE);
        String newPath = dir + Y + M + D;
        File folder = new File(newPath);
        // 判断目录是否存在，不存在则创建目录
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }
}
