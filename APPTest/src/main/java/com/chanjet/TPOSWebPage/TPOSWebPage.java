package com.chanjet.TPOSWebPage;

import Utils.LogUtils;
import Utils.WebExecutorImpl;
import Utils.XmlUtils;
import com.chanjet.H5Pages.H5BasePage;
import com.chanjet.Locator;
import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class TPOSWebPage extends WebExecutorImpl {
    private LogUtils log = new LogUtils(H5BasePage.class);

    private WebDriver driver;
    private String pageName;  //页面名称
    private String xmlPath;
    private HashMap<String, Locator> locatorMap;

    public TPOSWebPage(WebDriver driver,String pageName,String xmlPath) throws DocumentException {
        super(driver);
        this.driver = driver;
        this.pageName = pageName;
        this.xmlPath = System.getProperty("user.dir")+"\\src\\main\\resources\\PageLocators\\"+xmlPath;
        locatorMap = XmlUtils.readXmlDocument(this.xmlPath, pageName);
    }

    public void click(String locator){
        super.click(getLocator(locator));
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

    public WebElement findElement(String locator) {
        return super.findElement(getLocator(locator));
    }

    public List<WebElement> findElements(Locator locator){
        return super.findElements(locator);
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


    public  Locator getLocator(String locatorName) {

        Locator locator =  null;
        if(locatorMap!=null)
        {
            locator = locatorMap.get(locatorName);
        }
        return locator;
    }
}
