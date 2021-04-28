package Utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 截图并保存至本地
 * 
 * Created by yuxin
 *
 */
public class ScreenShot {

	private AppiumDriver<?> driver;
	private String path;

	public ScreenShot(AppiumDriver<?> driver){
		this.driver = driver;
		path = System.getProperty("user.dir")+"\\snapshot\\"+this.getClass().getSimpleName()+"_"+getCurrentTime()+".png";
	
	}

	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String currentTime = sdf.format(date);
		return currentTime;
	}
	
	public void getScreenShot() {
		System.out.println(driver);
        File screen = driver.getScreenshotAs(OutputType.FILE);
        System.out.println(screen);
        File screenFile = new File(path);
        try {
            FileUtils.copyFile(screen, screenFile);
            
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
		

}
