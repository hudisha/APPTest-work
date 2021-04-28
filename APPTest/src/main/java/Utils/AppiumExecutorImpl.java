package Utils;

import com.chanjet.Locator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author yuxin
 */
public class AppiumExecutorImpl implements AppiumExecutor{


    private AppiumDriver<?> driver;
    private TouchAction action;

    public AppiumExecutorImpl(AppiumDriver<?> driver) {

        this.driver = driver;
    }

    public AppiumDriver<?> getDriver() {

        return driver;
    }

    public void setDriver(AppiumDriver<?> driver) {
        this.driver = driver;
    }

    @Override
    public void click(Locator locator) {
        // TODO Auto-generated method stub
        MobileElement e = findElement(locator);
        e.click();
    }

    public void click(int x,int y){
        TouchAction ta = new TouchAction(driver);
        System.out.println("坐标为："+x+""+y);
        ta.tap(x, y);
    }

    public void doubleClick() {
        TouchAction ta = new TouchAction(driver);
        int x = driver.manage().window().getSize().width;
        int y = driver.manage().window().getSize().height;
        try{
            ta.tap(x/2,y/2).perform();
            Thread.sleep(23);
            ta.tap(x/2,y/2).perform();
        }catch (Exception e){
            e.printStackTrace();
        }

        //ta.press((x/2,y/2)).release().perform().press(PointOption.point(x轴坐标,y轴坐标)).release().perform()
    }

    public void doubleClick2(){
        int x = driver.manage().window().getSize().width;
        int y = driver.manage().window().getSize().height;
        //driver.tap(2,x/2,y/2,80);

    }

    @Override
    public void send(Locator locator, String values) {
        // TODO Auto-generated method stub
        MobileElement e = findElement(locator);
        e.clear();
        e.sendKeys(values);
    }

    @Override
    public void sendKeyCode(Locator locator, Keys code ) throws InterruptedException, IOException {
        MobileElement e = findElement(locator);
        //e.getWrappedDriver().sendKeyEvent(66);
        String cmdstr="adb shell input keyevent 66";
        Runtime.getRuntime().exec(cmdstr).waitFor();
        Thread.sleep(10000);
    }

    public void sendStringAndEnter(Locator locator, String values,Keys code){
        MobileElement e=findElement(locator);
        e.sendKeys(values);
        e.sendKeys(code);
    }

    //获取元素文本信息
    @Override
    public String getText(Locator locator) {
        // TODO Auto-generated method stub
        MobileElement e = findElement(locator);
        return e.getText();
    }

    @Override
    public MobileElement findElement(Locator locator) {
        // TODO Auto-generated method stub
        MobileElement e = null;
        switch(locator.getByType()){
            case xpath:
                e = (MobileElement) driver.findElement(By.xpath(locator.getAddress()));
                break;
            case id:
                e = (MobileElement) driver.findElement(By.id(locator.getAddress()));
                break;
            case name:
                e = (MobileElement) driver.findElement(By.name(locator.getAddress()));
                break;
            case className:
                e = (MobileElement) driver.findElement(By.className(locator.getAddress()));
                break;
            default:
                e = (MobileElement) driver.findElement(By.id(locator.getAddress()));
        }

        return e;
    }


    @Override
    public boolean isElementDisplayed(Locator locator) {
        // TODO Auto-generated method stub
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
    public void swipeToLeft() {
        // TODO Auto-generated method stub
        int x = driver.manage().window().getSize().width;
        int y = driver.manage().window().getSize().height;
        try {
            driver.swipe((x / 8 * 7), (y / 2 * 1), (x / 8 * 1), (y / 2 * 1), 1000);
        } catch (Exception e) {
            driver.swipe((x / 8 * 7), (y / 2 * 1), (x / 8 * 1), (y / 2 * 1), 1000);
        }
    }

    @Override
    public void swipeToRight() {
        // TODO Auto-generated method stub
        int x = driver.manage().window().getSize().width;
        int y = driver.manage().window().getSize().height;
        try {
            driver.swipe((x / 8 * 1), (y / 2 * 1), (x / 8 * 7), (y / 2 * 1), 1000);
        } catch (Exception e) {
            driver.swipe((x / 8 * 1), (y / 2 * 1), (x / 8 * 7), (y / 2 * 1), 1000);
        }

    }

    @Override
    public void swipeToUp() {
        // TODO Auto-generated method stub
        int x = driver.manage().window().getSize().width;
        int y = driver.manage().window().getSize().height;
        try {
            driver.swipe((x / 2 * 1), (y / 4 * 3), (x / 2 * 1), (y / 8 * 1), 800);
            Thread.sleep(2000);
        } catch (Exception e) {
            driver.swipe((x / 2 * 1), (y / 4 * 3), (x / 2 * 1), (y / 3 * 1), 800);

        }
    }

    @Override
    public void swipeToDown() {
        // TODO Auto-generated method stub
        int x = driver.manage().window().getSize().width;
        int y = driver.manage().window().getSize().height;
        try {
            driver.swipe((x / 2 * 1), (y / 8 * 1), (x / 2 * 1), (y / 8 * 7), 1000);
        } catch (Exception e) {
            driver.swipe((x / 2 * 1), (y / 8 * 1), (x / 2 * 1), (y / 8 * 7), 1000);
        }
    }

    @Override
    public void tapByXY(int x,int y) {
        // TODO Auto-generated method stub
        try {
            action.tap(x, y).release().perform();
        } catch (Exception e) {
            System.out.println("点击失败");
        }
    }

    //拖动元素
    public void dragElement(Locator locator,Locator locator2){
        action.press(findElement(locator)).moveTo(findElement(locator2)).release().perform();
    }

    /**
     * 切换Webview页面查找元素
     *
     * @author
     */
    public  void switchtoWebview(){
        try {

            Set<String> contextNames  =driver.getContextHandles();
            for (String contextName : contextNames ) {
                System.out.println(contextName);
                //if(contextName.contains("WEBVIEW")||contextName.contains("webview"))
                if (contextName.toLowerCase().contains("webview")) {
                    driver.context(contextName);
                    System.out.println("跳转到web页 开始操作web页面");
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MobileElement> findElements(Locator locator) {
        // TODO Auto-generated method stub
        List<MobileElement> list = null;
        switch(locator.getByType()){
            case xpath:
                list = (List<MobileElement>) driver.findElements(By.xpath(locator.getAddress()));
                break;
            case id:
                list = (List<MobileElement>) driver.findElements(By.id(locator.getAddress()));
                break;
            case name:
                list = (List<MobileElement>) driver.findElements(By.name(locator.getAddress()));
                break;
            case className:
                list = (List<MobileElement>) driver.findElements(By.className(locator.getAddress()));
                break;
            default:
                list = (List<MobileElement>) driver.findElements(By.id(locator.getAddress()));
        }

        return list;
    }

    /**
     * 长按10s
     */
    public void longPress(MobileElement element){
        TouchAction ta = new TouchAction(driver);
        ta.longPress(element,10000).release().perform();//长按10s
    }

}
