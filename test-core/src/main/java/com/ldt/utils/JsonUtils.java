package com.ldt.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.util.Date;

public class JsonUtils {

    private static Logger logger = Logger.getLogger(JsonUtils.class);

    private static SerializeConfig mapping = new SerializeConfig();

    static {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
    }
    /**
     * @param obj 对象模型
     * @return String 转换完毕的字符串
     * @title: obj2Json
     * @description : 将对象转换为JSON字符串
     */
    public static String obj2Json(Object obj) {
        String str = null;
        try {
            str = JSON.toJSONString(obj, mapping, SerializerFeature.WriteMapNullValue);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return str;
    }

}
