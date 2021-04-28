package com.chanjet.AppTests;

import Utils.DriverFactory;
import Utils.LogUtils;
import com.chanjet.BaseActions.AppAction;
import com.chanjet.TestReport.TestNGListener;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.internal.BaseAction;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class TestBase {
    public LogUtils log = new LogUtils(this.getClass());
    public AndroidDriver<?> androidDriver = null;
    public AppAction appAction = null;

    //商米的配置信息
    private String packageName = "com.chanjet.sunmid2";
    private String appActivity = ".ui.Activity.main.MainActivity";

    private String username = "18611203882";
    private String password = "123456a";

    private int updateCode = 0;

    @BeforeClass
    public void setup() throws InterruptedException {
        androidDriver = DriverFactory.createAndroidDriver("","4723", packageName, appActivity);
        appAction = new AppAction(androidDriver);
        TestNGListener.setDriver(androidDriver);
        Thread.sleep(15000);
    }

    @Test(priority = 0)
    public void Homepage_Initialization(){
        if(updateCode == 1 && appAction.isElementDisplayed("homepage","立即更新","promoteSales.xml")){
            appAction.click("homepage","purchase.xml","立即更新");
        }else if(updateCode == 0 ){
            appAction.click("homepage","purchase.xml","暂时忽略");
        }else {
            log.info("暂无内容更新");
        }
    }

    /**
     * Login
     */
    @Test(priority = 1)
    public void Login(){
        try {
            appAction.send("login","purchase.xml","用户名",username);
            appAction.send("login","purchase.xml","密码",password);
            appAction.click("login","purchase.xml","登录");

        }catch (Exception e){
            log.error("登录失败");
        }
    }
}
