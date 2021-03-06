package com.chanjet.H5Pages;

import Utils.LogUtils;
import Utils.WebExecutorImpl;
import Utils.XmlUtils;
import com.chanjet.Locator;
import org.dom4j.DocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class H5BasePage extends WebExecutorImpl {
    private LogUtils log = new LogUtils(H5BasePage.class);

    private WebDriver driver;
    private String pageName;  //页面名称
    private String xmlPath;
    private HashMap<String, Locator> locatorMap;

    public H5BasePage(WebDriver driver,String pageName,String xmlPath) throws DocumentException {
        super(driver);
        this.driver = driver;
        this.pageName = pageName;
        this.xmlPath = System.getProperty("user.dir")+"\\src\\main\\resources\\PageLocators\\"+xmlPath;
        locatorMap = XmlUtils.readXmlDocument(this.xmlPath, pageName);
    }

    public void click(String locator){
        super.click(getLocator(locator));
    }

    public void click(WebElement element){
        super.click(element);
    }

    public void send(String locator, String values) {
        super.send(getLocator(locator),values);
    }

    public void KeysEnter(String locator){
        super.KeysEnter(getLocator(locator));
    }

    public String getText(String locator) {
        return super.getText(getLocator(locator));
    }

    public String getText(WebElement parentElement, String locator){
        WebElement element=super.findElementfromParent(parentElement,getLocator(locator));
        return super.getText(element);
    }

    public WebElement findElement(String locator) {
        return super.findElement(getLocator(locator));
    }

    public List<WebElement> findElements(String locator){
        return super.findElements(getLocator(locator));
    }

    public boolean isElementDisplayed(String locator){
        return super.isElementDisplayed(getLocator(locator));
    }

    public void closeAlert(){
        super.closeAlert();
    }

    public void acceptAlert(){
        super.acceptAlert();
    }

    public String getAlertText(){
        return super.getAlertText();
    }

    public void doubleClick(String locator) {
        super.doubleClick(getLocator(locator));
    }

    public void contextClick(Locator locator){
        super.contextClick(locator);
    }

    public void dragAndDrop(Locator source, Locator target){
        super.dragAndDrop(source,target);
    }

    public void closeBrowser() {

        super.closeBrowser();
    }

    public void quiltBrowser(){
        super.quiltBrowser();
    }

    public String getTitle() {

        return super.getTitle();
    }

    public String getUrl() {

        return super.getUrl();
    }

    public void maxBrowser() {

        super.maxBrowser();
    }

    public void moveToElement(String locator){
        super.moveToElement(getLocator(locator));
    }

    public void scrollToBottom(){
        super.scrollToView();
    }


    public  Locator getLocator(String locatorName) {

        Locator locator =  null;
        if(locatorMap!=null)
        {
            locator = locatorMap.get(locatorName);
        }
        return locator;
    }
}
