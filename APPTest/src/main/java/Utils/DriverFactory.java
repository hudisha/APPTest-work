package Utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.log4testng.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuxin
 */

public class DriverFactory {
	
	private static AndroidDriver<?> androidDriver = null;
	private static IOSDriver<?> iosDriver = null;
	private static Logger log = Logger.getLogger(DriverFactory.class);

	 @SuppressWarnings("rawtypes")
	 public static AndroidDriver<?> createAndroidDriver(String udid,String port, String appPackage,String appActivity){
		
		DesiredCapabilities caps = new DesiredCapabilities();
		// apk地址，不需要安装的话这行不需要
        // File app=new File("C:\\Users\\\\chanjet.apk");
        // 不需要安装的话就去掉这个
        // caps.setCapability("app", app.getAbsolutePath());
		
		caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "7.1.2");  // pda
       // caps.setCapability("platformVersion", "9"); // huawei mate 20
		//caps.setCapability("platformVersion", "6.0");
        
       //caps.setCapability("deviceName", "Android");//SAMSUNG-SM-G950U //HUAWEI Mate 20
        // caps.setCapability("deviceName", "HUAWEI Mate 20"); //huawei mate 20
        //caps.setCapability("deviceName", "MEIZU MX6");  //
		caps.setCapability("deviceName", "HuaWeiMate30pro");
        caps.setCapability("automationName", "Appium"); // 1.7版本 uiautomator2
       // caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("udid", udid);
        caps.setCapability("appPackage", appPackage);
        caps.setCapability("appActivity", appActivity);
        caps.setCapability("newCommandTimeout", 600000); 
        // caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        caps.setCapability("unicodeKeyboard", "True"); // 支持中文输入
        caps.setCapability("resetKeyboard", "True"); // 重置输入法为系统默认
     
        // 安装时不对apk进行重签名，设置很有必要，否则有的apk在重签名后无法正常使用
        caps.setCapability("noSign", "True");
        caps.setCapability("noReset", "True"); //必须带
        try {
            androidDriver = new AndroidDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), caps);
            
        } catch (Exception e) {
            log.error("Android.appium连接失败");
        }
        return androidDriver;
	 }
	 
	 @SuppressWarnings("rawtypes")
	 public static IOSDriver<?> createIOSDriver (String udid, String port){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "11.4.1");
        caps.setCapability("deviceName", "iPhone");
        caps.setCapability("unicodeKeyboard", "True");
        caps.setCapability("resetKeyboard", "True");

        try {
            iosDriver = new IOSDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), caps);
        } catch (MalformedURLException e) {
            log.error("iOS.appium连接失败");
        }

        return iosDriver;
	 }

    public static void main(String[] args) {
	     /**
        System.setProperty("webdriver.chrome.driver", "E:/APPTest/src/main/resources/chromedriver.exe");
        //声明ChromeOptions，主要是给chrome设置参数

        DesiredCapabilities cap = DesiredCapabilities.chrome();

        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", "Google Nexus 5");
        //Map<String, Object> chromeOptions = new HashMap<String, Object>();
        //chromeOptions.put("mobileEmulation", mobileEmulation);

        //cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        ChromeOptions options = new ChromeOptions();
        options.setCapability("mobileEmulation",mobileEmulation);
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("E:/APPTest/src/main/resources/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        ChromeDriver driver = new ChromeDriver(service,options);

        //WebDriver driver = new ChromeDriver(cap);
        WebDriver.Navigation navigation=driver.navigate();
        navigation.to("https://m.baidu.com/");
        String title=driver.getTitle();
        System.out.println("title:"+title);
          */

    }
}
