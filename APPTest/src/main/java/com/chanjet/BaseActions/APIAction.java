package com.chanjet.BaseActions;

import Utils.LogUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created By LaoYu
 * 2021/2/3
 */
public class APIAction {

    private final LogUtils Log =  new LogUtils(this.getClass());

    public Properties properties;
    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_201 = 201;
    public int RESPONSE_STATUS_CODE_404 = 404;
    public int RESPONSE_STATUS_CODE_500 = 500;

    public APIAction(){
        properties = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/java/com/chanjet/qa/objects/yx/config.properties");
            properties.load(fileInputStream);
            Log.info("开始读取配置文件...");
        } catch (IOException e) {
            // TODO: handle exception
            Log.info("读取配置文件失败");
            e.printStackTrace();
        }
    }
}
