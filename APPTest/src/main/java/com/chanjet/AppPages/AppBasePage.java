package com.chanjet.AppPages;

import Utils.AppiumExecutorImpl;
import Utils.LogUtils;
import Utils.XmlUtils;
import com.chanjet.Locator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.dom4j.DocumentException;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
//import com.chanjet.Locator;

public class AppBasePage extends AppiumExecutorImpl {
    private AppiumDriver<?> driver;
    private String pageName;  //页面名称
    private String xmlPath;   //页面元素路径
    private HashMap<String, Locator> locatorMap;

    private LogUtils log = new LogUtils(AppBasePage.class);

    public AppBasePage(AppiumDriver<?> driver, String pageName, String xmlPath) throws DocumentException {
        super(driver);
        this.driver = driver;
        this.pageName = pageName;
        this.xmlPath = System.getProperty("user.dir")+"\\src\\main\\resources\\PageLocators\\"+xmlPath;
        locatorMap = XmlUtils.readXmlDocument(this.xmlPath, pageName);
    }

    public void click(int x,int y){
        TouchAction ta = new TouchAction(driver);
        ta.press(x, y).release().perform();
    }

    public void send(String locatorName,String values){
        log.info("type value is" + values);
        super.send(getLocator(locatorName),values);

    }

    public void sendEnter(String locatorName) throws IOException, InterruptedException {
        log.info("Press down {Enter}");
        super.sendKeyCode(getLocator(locatorName),Keys.ENTER);
    }

    public void sendStringAndEnter(String locatorName, String values, Keys key){
        excuteAdbShell("adb shell ime set io.appium.settings/.UnicodeIME");
        //再次点击输入框，调取键盘
        super.send(getLocator(locatorName),values);
        //点击右下角的搜索，即ENTER键
        driver.getKeyboard().pressKey(Keys.ENTER);
        //super.sendStringAndEnter(getLocator(locatorName),values,key);
    }

    public void click(String locatorName){
        log.info("click: "+locatorName);
        super.click(getLocator(locatorName));

    }

    public String getText(String locatorName){

        return super.getText(getLocator(locatorName));
    }

    public MobileElement findElement(String locatorName){

        return super.findElement(getLocator(locatorName));
    }

    public List<MobileElement> findElements(String locatorName){

        return super.findElements(getLocator(locatorName));
    }

    public boolean isElementDisplayed(String locatorName){

        return super.isElementDisplayed(getLocator(locatorName));
    }

    public void dragElement(String locatorName,String locatorName2){
        super.dragElement(getLocator(locatorName), getLocator(locatorName2));
    }

    public void swipeToLeft(){
        super.swipeToLeft();
    }

    public void swipeToUp(){
        super.swipeToUp();
    }

    public void doubleClick(){
        super.doubleClick();
    }

    public  Locator getLocator(String locatorName) {

        Locator locator =  null;

        if(locatorMap!=null)
        {
            locator = locatorMap.get(locatorName);
            System.out.println(locator);
        }
        return locator;
    }

    private void excuteAdbShell(String s) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(s);
        } catch (Exception e) {
            System.out.println("执行命令:" + s + "出错");
        }
    }
}
