package com.chanjet.H5Tests;

import Utils.*;
import com.chanjet.BaseActions.WebAction;
import com.chanjet.TestReport.TestNGListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class H5TestBase {

    public LogUtils log = new LogUtils(this.getClass());
    private WebDriver driver = null;
    private WebAction webAction = null;
    public String wangPu_xmlPath="WangPuH5Page.xml";
    public static String getWangPuProperties =PropertiesUtils.GetTheENVProperties();
    public static String WangPuInfoProp="TestData\\wangpu.properties";
    private static final String URL= PropertiesUtils.getTheProperty(getWangPuProperties,"wangpu_URL");
    Set<String> set = null;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = WebDriverFactory.createWebDriver();
        webAction = new WebAction(driver);
        TestNGListener.setDrivers(driver);
        Thread.sleep(5000);
        //driver.manage().window().maximize(); //旺铺不需要
        //设置隐性等待时间
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        //get()打开一个站点
        driver.get(URL);
        Thread.sleep(2000);
    }

    /**
     * 测试内容：游客取价
     * 旺铺配置-游客取价，验证商品的价格。
     */
    @Test(priority = 0)
    public void WangPu_VisitorPrice(){
        String username = PropertiesUtils.getTheProperty(getWangPuProperties,"userName");
        String password = PropertiesUtils.getTheProperty(getWangPuProperties,"password");
        webAction.click("我的Tab","HomePage",wangPu_xmlPath);
        webAction.click("登录Btn","UserPage",wangPu_xmlPath);
        webAction.click("帐号密码方式登录","LoginPage", wangPu_xmlPath);
        webAction.send("帐号","LoginPage",wangPu_xmlPath,username);
        webAction.send("密码","LoginPage",wangPu_xmlPath,password);
        webAction.click("登录按钮","LoginPage",wangPu_xmlPath);

        boolean logedIn=webAction.isElementDisplayed("我的Tab","HomePage",wangPu_xmlPath);
        Assert.isTrue(logedIn,"登录失败！");

    }

    @Test(priority = 0)
    public void WangPu_LoginIn(){
        String username = PropertiesUtils.getTheProperty(PropertiesUtils.GetTheENVProperties(),"userName");
        String password = PropertiesUtils.getTheProperty(PropertiesUtils.GetTheENVProperties(),"password");
        webAction.click("我的Tab","HomePage",wangPu_xmlPath);
        webAction.click("登录Btn","UserPage",wangPu_xmlPath);
        webAction.click("帐号密码方式登录","LoginPage", wangPu_xmlPath);
        webAction.send("帐号","LoginPage",wangPu_xmlPath,username);
        webAction.send("密码","LoginPage",wangPu_xmlPath,password);
        webAction.click("登录按钮","LoginPage",wangPu_xmlPath);

        boolean logedIn=webAction.isElementDisplayed("我的Tab","HomePage",wangPu_xmlPath);
        Assert.isTrue(logedIn,"登录失败！");

    }

    @Test(dependsOnMethods = {"WangPu_LoginIn"})
    public void WangPu_Logout(){
        webAction.click("我的Tab","HomePage",wangPu_xmlPath);
        webAction.scrollToBottom("退出登录Btn","UserPage",wangPu_xmlPath);
        webAction.click("退出登录Btn","UserPage",wangPu_xmlPath);
        boolean isLogedOut=webAction.isElementDisplayed("登录查看价格","HomePage",wangPu_xmlPath);
        Assert.isTrue(isLogedOut,"退出登录失败！");
    }


    /**
     * 旺铺商品搜索
     * 搜索商品编码、商品名称、商品规格、条形码
     */
    @Test(priority = 0)
    public void WangPu_SearchProduct(){
        webAction.click("首页搜索TextBox","HomePage",wangPu_xmlPath);
        //验证商品编码搜索结果
        String product_IdCode=PropertiesUtils.getTheProperty(WangPuInfoProp,"product_IdCode");
        webAction.send("搜索TextBox","SearchPage",wangPu_xmlPath,product_IdCode.split(">")[0]);
        webAction.KeysEnter("搜索TextBox","SearchPage",wangPu_xmlPath);
        String productInfos=webAction.getText("第一条商品名称","SearchPage",wangPu_xmlPath);
        Assert.isTrue(productInfos.contains(product_IdCode.split(">")[1]),"搜索结果错误！");

        //验证商品名称搜索结果
        String product_Name=PropertiesUtils.getTheProperty(WangPuInfoProp,"product_Name");
        webAction.click("搜完以后TextBox","SearchPage",wangPu_xmlPath);
        //webAction.click("清空x","SearchPage",wangPu_xmlPath);
        webAction.send("搜索TextBox","SearchPage",wangPu_xmlPath,product_Name.split(">")[0]);
        webAction.KeysEnter("搜索TextBox","SearchPage",wangPu_xmlPath);
        productInfos=webAction.getText("第一条商品名称","SearchPage",wangPu_xmlPath);
        Assert.isTrue(productInfos.contains(product_Name.split(">")[1]),"搜索结果错误！");

        //验证商品规格搜索结果
        String product_Standard=PropertiesUtils.getTheProperty(WangPuInfoProp,"product_Standard");
        webAction.click("搜完以后TextBox","SearchPage",wangPu_xmlPath);
        //webAction.click("清空x","SearchPage",wangPu_xmlPath);
        webAction.send("搜索TextBox","SearchPage",wangPu_xmlPath,product_Standard.split(">")[0]);
        webAction.KeysEnter("搜索TextBox","SearchPage",wangPu_xmlPath);
        productInfos=webAction.getText("第一条商品名称","SearchPage",wangPu_xmlPath);
        Assert.isTrue(productInfos.contains(product_Standard.split(">")[1]),"搜索结果错误！");

        //验证商品条码搜索结果
        String product_SPUCode=PropertiesUtils.getTheProperty(WangPuInfoProp,"product_SPUCode");
        webAction.click("搜完以后TextBox","SearchPage",wangPu_xmlPath);
        //webAction.click("清空x","SearchPage",wangPu_xmlPath);
        webAction.send("搜索TextBox","SearchPage",wangPu_xmlPath,product_SPUCode.split(">")[0]);
        webAction.KeysEnter("搜索TextBox","SearchPage",wangPu_xmlPath);
        productInfos=webAction.getText("第一条商品名称","SearchPage",wangPu_xmlPath);
        Assert.isTrue(productInfos.contains(product_SPUCode.split(">")[1]),"搜索结果错误！");

    }

    /**
     * 旺铺商品分类验证
     * 验证指定的分类下有指定的商品
     */
    @Test(priority = 0)
    public void WangPu_Category(){
        String product_category=PropertiesUtils.getTheProperty(WangPuInfoProp,"product_Category");
        String[] categories=product_category.split(">");
        webAction.click("分类Tab","HomePage",wangPu_xmlPath);

        List<WebElement> elementList=webAction.findWebElements("商品分类","ProductCategoryPage",wangPu_xmlPath);

        //用for循环,去验证商品分类都显示出来了。
        List<String> categoriesH5=null;
        for(WebElement element:elementList){

            String category=webAction.getText(element,"某一未选定商品分类名称","ProductCategoryPage",wangPu_xmlPath);
            categoriesH5.add(category);
        }
        for (String cateExpected : categories) {
            Assert.isTrue(categoriesH5.contains(cateExpected), "商品分类" + cateExpected + "未找到");
        }

        String productAndCategory=PropertiesUtils.getTheProperty(WangPuInfoProp,"product&Category");
        String cate=productAndCategory.split(">")[0];
        String[] products=productAndCategory.split(">")[1].split("&");
        //点击某一分类：普通商品，验证其下的商品
        //webAction.click();
    }

    public void WangPu_ProductDetails(){

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public WebElement GetTheParent(String locator,String pageName,String fileName){
        return webAction.findWebElement(locator,pageName,fileName);
    }

    public List<WebElement> GetTheData(String locator,String pageName,String fileName){

        return webAction.findWebElements(locator,pageName,fileName);
    }


    /**
    //旺铺的配置信息
    String filePath="wangpu.properties";
    public static final String URL= PropertiesUtils.getTheProperty("wangpu.properties","wangpu_URL");
    private static final String username = "18518759999";
    private static final String password = "605750";
    public LogUtils log = new LogUtils(this.getClass());
    public AndroidDriver<?> androidDriver = null;
    public AppAction appAction = null;
    public static String wangPu_xmlPath="WangPuH5Page.xml";

    //商米的配置信息
    private String packageName = "com.android.browser";
    private String appActivity = ".BrowserActivity";

    private int updateCode = 0;

    @BeforeClass
    public void setup() throws InterruptedException {
        androidDriver = DriverFactory.createAndroidDriver("","4723", packageName, appActivity);
        appAction = new AppAction(androidDriver);
        TestNGListener.setDriver(androidDriver);
        Thread.sleep(15000);
    }

    @Test(priority = 0)
    public void H5_Login(){
        //问题1. 浏览器 想要匹配某一个手机模式失败(selenium 的版本问题，未解决）
        //问题2. 手机版本得 浏览器，想要实现 sendkey以后回车失败（目前可输入，可回车，就是回车操作前会清空，还是没解决；浏览器输入地址的问题已经曲线解决）
        //问题3. 虽然有resource-id，但是用id不行，只能xpath（已经使用xpath，目前好使）
        //问题4. 输入用户名，密码，发现“登录”按钮的可点状态未回复，点不上，但是人手输入的时候立马可点击了。
        //String wangPuURL=" ";
        //appAction.sendStringAndEnter("BrowserControls",wangPu_xmlPath,"浏览器地址URL",wangPuURL, Keys.ENTER);

        appAction.click("HomePage",wangPu_xmlPath,"登录查看价格");
        //webAction.click("登录按钮","homepage","WangPuH5Page.xml");
        appAction.click("LoginPage", wangPu_xmlPath,"帐号密码方式登录");
        appAction.send("LoginPage",wangPu_xmlPath,"帐号",username);
        appAction.send("LoginPage",wangPu_xmlPath,"密码",password);
        appAction.send("LoginPage",wangPu_xmlPath,"帐号",username);
        appAction.click("LoginPage",wangPu_xmlPath,"登录按钮");

        boolean logedIn=appAction.isElementDisplayed("HomePage","我的Tab",wangPu_xmlPath);
        Assert.isTrue(logedIn,"登录失败！");


    }
    /**
    public void findAccountByKeyboard() throws InterruptedException, IOException {
        Thread.sleep(2000);
        driver.findElementById("com.tencent.mm:id/f_").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Add Contacts']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Official Accounts']").click();
        driver.findElementByXPath("//android.widget.EditText[@text='Search Official Accounts']").sendKeys("*****服务号");
        Thread.sleep(3000); //
        driver.pressKeyCode(66);
        excuteAdbShell("adb shell ime set com.sohu.inputmethod.sogou/.SogouIME");
        //再次点击输入框，调取键盘
        driver.findElementByXPath("//android.widget.EditText[@text='******服务号']").click();
        //点击右下角的搜索，即ENTER键
        driver.pressKeyCode(AndroidKeyCode.ENTER);
    }
     */
        /** * 执行adb命令 * @param s 要执行的命令 */
        /**
        private void excuteAdbShell(String s) {
            Runtime runtime=Runtime.getRuntime();
            try{ runtime.exec(s);
            }catch(Exception e){
                System.out.println("执行命令:"+s+"出错");
            }
        }


    @Test(priority = 0)
    public void H5_Logout(){

        appAction.click("HomePage",wangPu_xmlPath,"我的Tab");

        appAction.click("UserPage",wangPu_xmlPath,"退出登录Btn");

        boolean isLogedOut=appAction.isElementDisplayed("HomePage",wangPu_xmlPath,"登录查看价格");
        Assert.isTrue(isLogedOut,"退出登录失败！");
        //webAction.click("登录按钮","homepage","WangPuH5Page.xml");
    }

    @Test(priority=0)
    public void WangPu_SearchProduct(){
            appAction.sendStringAndEnter("HomePage",wangPu_xmlPath,"搜索TextBox","鸡蛋",Keys.ENTER);

    }

    @AfterClass
    public void tearDown(){
        try {
            Thread.sleep(2000);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
*/

}
