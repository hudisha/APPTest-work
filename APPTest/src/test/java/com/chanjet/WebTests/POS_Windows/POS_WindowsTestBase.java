package com.chanjet.WebTests.POS_Windows;

import Utils.LogUtils;
import Utils.Utools;
import Utils.WebDriverFactory;
import com.chanjet.BaseActions.WebAction;
import com.chanjet.TestReport.TestNGListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class POS_WindowsTestBase {
    public LogUtils log = new LogUtils(this.getClass());
    private WebDriver driver = null;
    private WebAction webAction = null;
    private static final String URL= "https://inte-cloud.chanjet.com/cc/uoii33woxttu/o3o0a1s270/shop/index.html#/";
    private static final String username = "15899991005";
    private static final String password = "123456q";
    Set<String> set = null;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = WebDriverFactory.createWebDriver();
        webAction = new WebAction(driver);
        TestNGListener.setDrivers(driver);
        Thread.sleep(5000);
        //driver.manage().window().maximize();
        //设置隐性等待时间
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        //get()打开一个站点
        driver.get(URL);
        Thread.sleep(2000);
    }

    @Test(priority = 0)
    public void Test_HomePage_Login(){
        webAction.click("登录按钮","homepage","web.xml");
        webAction.send("账号","homepage","web.xml",username);
        webAction.send("密码","homepage","web.xml",password);
        webAction.click("立即登录","homepage","web.xml");
    }

    @Test(priority = 1)
    public void Inte_WorkPlate_Hsy(){
        try{
            Thread.sleep(10000);
            webAction.moveToElement("用户图标","homepage","web.xml");
            webAction.click("企业工作台","homepage","web.xml");
            webAction.click("进入应用","homepage","web.xml");
            Thread.sleep(10000);
            // 获取句柄
            set = new HashSet<String>();
            String currentHandler = driver.getWindowHandle();
            set = driver.getWindowHandles();
            for(String handler : set){

                if (!currentHandler.equals(handler)){
                    driver.switchTo().window(handler);

                }
            }
            Thread.sleep(2000);
            log.info("切换帐套");

        }catch (Exception e){
            e.printStackTrace();
        }

    }



    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
