package com.chanjet.TestReport;

import Utils.LogUtils;
import Utils.ScreenShot;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by yuxin
 */
public class TestNGListener extends TestListenerAdapter{
	
	private static AppiumDriver<?> driver;
	private static WebDriver webDriver;
	LogUtils log = new LogUtils(this.getClass());
	
	public static void setDriver(AppiumDriver<?> driver) {
		TestNGListener.driver = driver;
	}
	public static void setDrivers(WebDriver webDriver) {
		TestNGListener.driver = driver;
	}


	@Override
	public void onTestSuccess(ITestResult tr) {
		log.info("Test Success");
		super.onTestSuccess(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		log.error("Test Failure");
		super.onTestFailure(tr);
		
		ScreenShot screenShot = new ScreenShot(driver);
		screenShot.getScreenShot();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log.error("Test Skipped");
		super.onTestSkipped(tr);
	}

	@Override
	public void onStart(ITestContext testContext) {
		log.info("Test Start");
		super.onStart(testContext);
	}

	@Override
	public void onFinish(ITestContext testContext) {
		log.info("Test Finish");
		super.onFinish(testContext);
	}

}
