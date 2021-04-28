package Utils;

import com.chanjet.Locator;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @Created by LaoYu
 */
public interface WebExecutor {
    //点击元素
    public void click(Locator locator);

    //输入文本
    public void send(Locator locator, String values);

    //获取元素文本
    public String getText(Locator locator);

    //获取元素
    public WebElement findElement(Locator locator);

    public List<WebElement> findElements(Locator locator);

    //判断元素是否出现
    public boolean isElementDisplayed(Locator locator);

    //切换Frame
    public void switchToFrame(String locator);

    //切换ParentFrame
    public void switchToParentFrame();

    //关闭Alert
    public void closeAlert();

    //接受Alert
    public void acceptAlert();

    //获取AlertText
    public String getAlertText();

    //移动到其他元素
    public void moveToElement(Locator locator);

    //双击
    public void doubleClick(Locator locator);

    //右击
    public void contextClick(Locator locator);

    //拖拽元素
    public void dragAndDrop(Locator source, Locator target);

    //关闭浏览器
    public void closeBrowser();

    public void quiltBrowser();

    //获取title
    public String getTitle();

    //获取URL
    public String getUrl();

    //maxBrower
    public void maxBrowser();

    //

}
