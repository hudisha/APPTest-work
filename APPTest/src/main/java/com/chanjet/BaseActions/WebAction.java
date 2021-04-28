package com.chanjet.BaseActions;

import com.chanjet.H5Pages.H5BasePage;
import Utils.LogUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.*;

/**
 * @Created by LaoYu
 */
public class WebAction {

    LogUtils log = new LogUtils(this.getClass());

    private WebDriver driver;

    private H5BasePage webPage = null;

    public WebAction(WebDriver driver) {

        this.driver = driver;

    }

    public void click(String locator,String pageName,String fileName){
        try {
            webPage = new H5BasePage(driver,pageName,fileName);
            webPage.click(locator);
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void send(String locator,String pageName,String fileName,String values) {
        try {
            webPage = new H5BasePage(driver,pageName,fileName);
            webPage.send(locator,values);
            Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * enter 操作
     * @param locator
     * @param pageName
     * @param filename
     */
    public void KeysEnter(String locator, String pageName, String filename){
        try {
            webPage = new H5BasePage(driver,pageName,filename);
            webPage.KeysEnter(locator);
            Thread.sleep(2500);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getText(String locator,String pageName,String filename) {
        String text = "";
        try {
            webPage = new H5BasePage(driver,pageName,filename);
            text = webPage.getText(locator);
            Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }

    public String getText(WebElement element, String locator,String pageName,String filename) {
        String text = "";
        try {
            webPage = new H5BasePage(driver,pageName,filename);
            text = webPage.getText(element,locator);
            Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }

    public boolean isElementDisplayed(String locator,String pageName,String fileName){
        boolean flag = false;
        try {
            webPage = new H5BasePage(driver,pageName,fileName);
            flag = webPage.isElementDisplayed(locator);
            //Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public void closeAlert(){
        try {
            webPage = new H5BasePage(driver,"homepage","web.xml");
            webPage.closeAlert();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeBrowser() {
        try {
            webPage = new H5BasePage(driver,"homepage","web.xml");
            webPage.closeBrowser();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void quiltBrowser(){
        try{
            webPage = new H5BasePage(driver,"homepage","web.xml");
            webPage.quiltBrowser();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //鼠标悬停
    public void moveToElement(String locator,String pageName,String fileName){
        try {
            webPage = new H5BasePage(driver,pageName,fileName);
            webPage.moveToElement(locator);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //页面滚动到底部
    public void scrollToBottom(String locator,String pageName,String fileName){
        try{
            webPage = new H5BasePage(driver,pageName,fileName);
            webPage.scrollToBottom();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String changeURL(String domain){
        String currentUrl = "";
        String finalUrl = "";
        String currentHandle = "";
        Set<String> setHandle = new HashSet<String>();
        try{
            setHandle = driver.getWindowHandles();
            currentHandle = driver.getWindowHandle();

            for(String handle:setHandle) {
                if (!handle.equals(currentHandle)) {
                    driver.switchTo().window(handle);
                    Thread.sleep(3000);
                    currentUrl = driver.getCurrentUrl();
                    URL url = new URL(currentUrl);
                    String host = url.getHost();
                    String path = url.getPath();
                    finalUrl = "https://" + domain + path;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return finalUrl;
    }

    public static void pressEnterKey(){

        Robot robot = null;
        try{
            robot = new Robot();
        }catch (AWTException e){
            e.printStackTrace();
        }
        //调用keypress方法来实现按下Enter键
        assert robot != null;
        robot.keyPress(KeyEvent.VK_ENTER);
        //调用keyrelease方法来实现释放Enter键
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void Enter(){
        Actions action = new Actions(driver);
        action.keyDown(Keys.ALT).sendKeys("q").keyUp(Keys.ALT).sendKeys("1").sendKeys(Keys.ENTER).perform();
    //action.KeyDown(Keys.Alt).SendKeys("q").KeyUp(Keys.Alt).SendKeys("1").SendKeys(Keys.Enter).Perform();
    }

    public void DoubleClick(String locator,String pageName,String fileName){
        try {
            webPage = new H5BasePage(driver,pageName,fileName);
            webPage.doubleClick(locator);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public WebElement findWebElement(String locator,String pageName,String fileName){
        WebElement we = null;
        try{
            webPage = new H5BasePage(driver,pageName,fileName);
            we = webPage.findElement(locator);
        }catch (Exception e){
            e.printStackTrace();
        }

        return  we;
    }

    public List<WebElement> findWebElements(String locator, String pageName, String fileName){
        List<WebElement> webElementList=null;
        try{
            webPage = new H5BasePage(driver,pageName,fileName);
            webElementList=webPage.findElements(locator);
        }catch (Exception e){
            e.printStackTrace();
        }

        return webElementList;
    }

}
