package Utils;

import com.chanjet.Locator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @Created by LaoYu
 */
public class WebExecutorImpl implements Utils.WebExecutor {

    private WebDriver driver;
    private static final long timeOutInSeconds = 10;

    public WebExecutorImpl(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    @Override
    public void click(Locator locator) {
        try {
            findElement(locator).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Locator locator, String values) {
        try {
            Thread.sleep(1500);
            //findElement(locator).clear();
            WebElement element=findElement(locator);
            element.click();
            //相当于ctrl+a 快捷键全选
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            //快捷键删除
            element.sendKeys(Keys.DELETE);
            Thread.sleep(1500);
            findElement(locator).sendKeys(values);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void KeysEnter(Locator locator){
        try {
            findElement(locator).sendKeys(Keys.ENTER);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public String getText(Locator locator) {
        String text = "";
        try {
            synchronized (driver)
            {
                driver.wait(1500);
            }
            text = findElement(locator).getText();
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }

    public String getText(WebElement element){
        String text="";
        try {
            synchronized (driver)
            {
                driver.wait(1500);
            }
            text = element.getText();
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;

    }

    @Override
    public WebElement findElement(Locator locator) {
        WebElement e = null;
        switch(locator.getByType()){
            case xpath:
                e = driver.findElement(By.xpath(locator.getAddress()));
                break;
            case name:
                e = driver.findElement(By.name(locator.getAddress()));
                break;
            case className:
                e = driver.findElement(By.className(locator.getAddress()));
                break;
            case id:
                e = driver.findElement(By.id(locator.getAddress()));
                break;
            case linkText:
                e = driver.findElement(By.linkText(locator.getAddress()));
                break;
            case partLinkText:
                e = driver.findElement(By.partialLinkText(locator.getAddress()));
                break;
            case selector:
                e = driver.findElement(By.cssSelector(locator.getAddress()));
                break;
            case tagName:
                e = driver.findElement(By.tagName(locator.getAddress()));
                break;
            default:
                System.out.println("元素查找类型不匹配");
                break;
        }
        return e;
    }

    public WebElement findElementfromParent(WebElement parentElement,Locator locator) {
        WebElement e = null;
        switch (locator.getByType()) {
            case xpath:
                e = parentElement.findElement(By.xpath(locator.getAddress()));
                break;
            case name:
                e = parentElement.findElement(By.name(locator.getAddress()));
                break;
            case className:
                e = parentElement.findElement(By.className(locator.getAddress()));
                break;
            case id:
                e = parentElement.findElement(By.id(locator.getAddress()));
                break;
            case linkText:
                e = parentElement.findElement(By.linkText(locator.getAddress()));
                break;
            case partLinkText:
                e = parentElement.findElement(By.partialLinkText(locator.getAddress()));
                break;
            case selector:
                e = parentElement.findElement(By.cssSelector(locator.getAddress()));
                break;
            case tagName:
                e = parentElement.findElement(By.tagName(locator.getAddress()));
                break;
            default:
                System.out.println("元素查找类型不匹配");
                break;
        }
        return e;
    }

    @Override
    public List<WebElement> findElements(Locator locator) {
        List<WebElement> list = null;
        switch(locator.getByType()){
            case xpath:
                list = driver.findElements(By.xpath(locator.getAddress()));
                break;
            case id:
                list = driver.findElements(By.id(locator.getAddress()));
                break;
            case name:
                list = driver.findElements(By.name(locator.getAddress()));
                break;
            case className:
                list = driver.findElements(By.className(locator.getAddress()));
                break;
            case linkText:
                list = driver.findElements(By.linkText(locator.getAddress()));
                break;
            case partLinkText:
                list = driver.findElements(By.partialLinkText(locator.getAddress()));
                break;
            case selector:
                list = driver.findElements(By.cssSelector(locator.getAddress()));
                break;
            case tagName:
                list = driver.findElements(By.tagName(locator.getAddress()));
                break;
            default:
                list = driver.findElements(By.id(locator.getAddress()));
        }
        return list;
    }

    @Override
    public boolean isElementDisplayed(Locator locator) {
        boolean flag = false;
        try {
            findElement(locator).isDisplayed();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public void switchToFrame(String locator) {
        driver.switchTo().frame(locator);
    }

    @Override
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    @Override
    public void closeAlert() {
        try {
            Thread.sleep(1500);
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        }catch (Exception e){

            e.printStackTrace();
        }

    }

    @Override
    public void acceptAlert() {
        try {
            Thread.sleep(1500);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public String getAlertText() {
        String alertText = "";
        try {
            Thread.sleep(1500);
            Alert alert = driver.switchTo().alert();
            alert.getText();
        }catch (Exception e){
            e.printStackTrace();
        }

        return alertText;
    }

    @Override
    public void moveToElement(Locator locator) {
        Actions action = new Actions(driver);
        action.moveToElement(findElement(locator)).perform();
    }
    public void scrollToView(){

        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        //driver..execute_script("arguments[0].scrollIntoView();", findElement(locator))
    }

    @Override
    public void doubleClick(Locator locator) {
        try {
            Actions action = new Actions(driver);
            action.doubleClick(findElement(locator)).perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void contextClick(Locator locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(findElement(locator)).perform();
    }

    @Override
    public void dragAndDrop(Locator source, Locator target) {
        Actions action = new Actions(driver);
        action.dragAndDrop(findElement(source),findElement(target));
    }

    @Override
    public void closeBrowser() {
        driver.close();
    }


    public void quiltBrowser(){
        driver.quit();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public void maxBrowser() {
        driver.manage().window().maximize();
    }

}
