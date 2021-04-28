package com.chanjet.TPOSPages;

import Utils.AppiumExecutorImpl;
import Utils.LogUtils;
import Utils.XmlUtils;
import com.chanjet.Locator;
import com.chanjet.AppPages.AppBasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.dom4j.DocumentException;

import java.util.HashMap;
import java.util.List;

public class TPOSBasePage extends AppiumExecutorImpl{
    private AppiumDriver<?> driver;
    private String pageName;  //页面名称
    public static String xmlPath=System.getProperty("user.dir")+"/src/main/resources/PageLocators/POSPage.xml";   //POS页面元素路径
    private HashMap<String, Locator> locatorMap;

    private LogUtils log = new LogUtils(AppBasePage.class);
    public TPOSBasePage(AppiumDriver<?> driver, String pageName) throws DocumentException {
        super(driver);
        this.driver = driver;
        this.pageName = pageName;
        locatorMap = XmlUtils.readXmlDocument(xmlPath, pageName);
    }

    public void click(int x,int y){
        TouchAction ta = new TouchAction(driver);
        ta.press(x, y).release().perform();
    }

    public void send(String locatorName,String values){
        log.info("type value is" + values);
        super.send(getLocator(locatorName),values);

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
}
