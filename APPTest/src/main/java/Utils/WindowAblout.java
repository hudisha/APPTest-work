package Utils;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * Created By LaoYu
 * 2020/5/19
 */
public class WindowAblout {

    public static boolean isAlertPresent(WebDriver driver) {
        try {

            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {

            return false;
        }
    }
}
