package com.chanjet.BaseActions;

import com.chanjet.AppPages.AppBasePage;
import Utils.LogUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.Keys;

/**
 * Created by yuxin
 * 销货单Action
 */
public class AppAction {

    LogUtils log = new LogUtils(this.getClass());

    private AppiumDriver<?> driver;

    private AppBasePage basePage = null;

    public AppAction(AppiumDriver<?> driver) {

        this.driver = driver;

    }

    /**
     * 点击操作
     * @param pagename 页面名称
     * @param locatorFile xml文件
     * @param elementname 元素value
     *
     */
    public void click(String pagename,String locatorFile,String elementname){
        try {
                basePage = new AppBasePage(driver,pagename,locatorFile);
                basePage.click(elementname);
                Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 输入操作
     * @param pagename
     * @param locatorFile
     * @param element
     * @param value
     */
    public void send(String pagename,String locatorFile,String element,String value){
        try {
            basePage = new AppBasePage(driver,pagename,locatorFile);
            basePage.send(element, value);
            Thread.sleep(1500);
        } catch (Exception e) {
            // TODO: handle exception
            log.error(element + "输入内容:"+value+"失败");
            e.printStackTrace();
        }
    }

    public void sendStringAndEnter(String pagename,String locatorFile,String element,String values,Keys keyCode){
        try {
            basePage = new AppBasePage(driver,pagename,locatorFile);
            basePage.sendStringAndEnter(element,values,keyCode);
            Thread.sleep(1500);
        } catch (Exception e) {
            // TODO: handle exception
            log.error(element + "Press keyboard:{Enter}失败");
            e.printStackTrace();
        }
    }

    /**
     * 获取text value
     * @param locatorName
     * @param pageName
     * @param locatorFile
     * @return
     */
    public String getText(String locatorName,String pageName,String locatorFile){
        String text = "";
        try {
            basePage = new AppBasePage(driver,pageName,locatorFile);
            text = basePage.getText(locatorName);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("获取元素文本内容失败");
        }
        return text;
    }

    /**
     * 判断元素是否存在
     */
    public boolean isElementDisplayed(String pagename,String name,String locatorFile){
        boolean flag = false;
        try {
            basePage = new AppBasePage(driver,pagename,locatorFile);
            flag = basePage.isElementDisplayed(name);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 上滑
     */
    public void swipToUp(){
        try{
            basePage = new AppBasePage(driver,"homepage","promoteSales.xml");
            basePage.swipeToUp();
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void doubleClick(){
        try{
            basePage = new AppBasePage(driver,"homepage","promoteSales.xml");
            basePage.doubleClick();
            //basePage.doubleClick();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
