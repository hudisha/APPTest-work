package com.chanjet.AppTests.POS_Sunmi;

import com.chanjet.AppPages.AppBasePage;
import com.chanjet.AppTests.TestBase;
import com.chanjet.TPOSPages.TPOSBasePage;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SignUpTest extends SMTestBase {

    @Test
    public void SignUpAPOS() throws MalformedURLException, DocumentException {

        String userName="18611203882";
        String passWord="123456a";
        String enterprise="123456a";
        String store="123456a";
        String POSID="123456a";
        //安装apk以后，首次登录需要注册
        //填写注册Form表单
        appAction.send("TSignUpPage", TPOSBasePage.xmlPath,"账户",userName);
        appAction.send("TSignUpPage", TPOSBasePage.xmlPath,"密码",passWord);
        appAction.send("TSignUpPage", TPOSBasePage.xmlPath,"企业",passWord);
        appAction.send("TSignUpPage", TPOSBasePage.xmlPath,"门店",passWord);
        appAction.send("TSignUpPage", TPOSBasePage.xmlPath,"POSID",passWord);
        appAction.click("TSignUpPage", TPOSBasePage.xmlPath,"登录");

        //注册后验证,进入POS首页
        boolean isPOSHomePage=appAction.isElementDisplayed("TPOSHomePage","当班",TPOSBasePage.xmlPath);
        Assert.assertTrue(isPOSHomePage,"POS 注册成功");

        //注销场景

    }



}
