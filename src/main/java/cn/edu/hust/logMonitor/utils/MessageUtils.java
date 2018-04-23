package cn.edu.hust.logMonitor.utils;

import cn.edu.hust.logMonitor.bean.AppMessage;
import org.apache.commons.lang.StringUtils;

public class MessageUtils {
    /**
     * 将消息封装成对象
     * 消息的格式是公式定义的，需要以特定的格式分割
     * @param message
     * @return
     */
    public static AppMessage str2Object(String message)
    {
        if(message==null|| StringUtils.isBlank(message)) return null;
        String[] splits=message.split("\\$\\$\\$\\$\\$");
        if(splits.length<2) return null;
        return new AppMessage(Integer.parseInt(splits[0]),splits[1]);
    }
}
