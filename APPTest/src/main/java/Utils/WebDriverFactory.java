package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.log4testng.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by LaoYu
 */
public class WebDriverFactory {
    private static WebDriver driver = null;
    private static Logger log = Logger.getLogger(DriverFactory.class);

    public static WebDriver createWebDriver(){
        try {
            String path =  System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe";//+"/src/test/resources/";
            //System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            System.setProperty("webdriver.chrome.driver", path);

            //H5 模拟手机样式访问
            //声明ChromeOptions，主要是给chrome设置参数
            ChromeOptions chromeOptions = new ChromeOptions();

            //设置user agent为iphone6plus
            //options.addArguments("--user-agent=iphone 6 plus");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");

            // 如果只测试手机端浏览器 h5 页面需要加上下面三行
            Map<String, String> mobileEmulationMap = new HashMap<>();
            mobileEmulationMap.put("deviceName", "Galaxy S5");
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulationMap);

            driver=new ChromeDriver(chromeOptions);
        }catch (Exception e){
            log.error("webdriver create failure");
            e.printStackTrace();
        }
        return driver;

    }

}
