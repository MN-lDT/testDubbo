package com.ldt.utils;

import java.io.*;
import java.util.Properties;
import org.apache.log4j.Logger;

/***
 * 读取配置文件
 */
public class ReadProperties {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(ReadProperties.class);

    public static String ReadPropertiesFromFiles(String propertiesFileName,String property,String vm){
        Properties  properties = System.getProperties();
        String confHome = properties.getProperty(vm);
        logger.info("vm : "+confHome);
        Properties properties1 = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(confHome+propertiesFileName);
            properties1.load(new InputStreamReader(in, "UTF-8"));
            String value = properties1.getProperty(property);
            logger.info("value:"+value);
            in.close();
            return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
