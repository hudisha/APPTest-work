package Utils;

import com.chanjet.Locator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.util.List;

/**
 * @author yuxin
 * appium 常用api封装
 */
public interface AppiumExecutor {

    //点击元素
    public void click(Locator locator);

    //输入文本
    public void send(Locator locator, String values);

    //发送快捷键
    public void sendKeyCode(Locator locator, Keys keys) throws InterruptedException, IOException;

    //获取元素文本
    public String getText(Locator locator);

    //获取元素
    public MobileElement findElement(Locator locator);

    public List<MobileElement> findElements(Locator locator);

    //判断元素是否出现
    public boolean isElementDisplayed(Locator locator);

    //向左滑动
    public void swipeToLeft();

    //向右滑动
    public void swipeToRight();

    //向上滑动
    public void swipeToUp();

    //向下滑动
    public void swipeToDown();

    //通过坐标点击
    public void tapByXY(int x, int y);


}
