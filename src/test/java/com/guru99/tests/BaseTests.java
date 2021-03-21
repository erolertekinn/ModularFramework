package com.guru99.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import com.guru99.pages.commonLibs.implementation.CommonDriver;
import com.guru99.pages.commonLibs.utils.ConfigUtils;
import com.guru99.pages.commonLibs.utils.ReportUtils;
import com.guru99.pages.commonLibs.utils.ScreenshotUtils;
import com.guru99.pages.pagemodel.LoginPageModel;

public class BaseTests {
	CommonDriver cmnDriver;
	String url;
	WebDriver driver;
	
	LoginPageModel loginpage;
	
	String configFileName;
	String currentWorkingDirectory;
	
	Properties configProperty;
	String reportFilename;
	ReportUtils reportUtils;
	
	ScreenshotUtils screenshot;
	
	@BeforeSuite
	public void preSetup() throws Exception {
		currentWorkingDirectory = System.getProperty("user.dir");
		
		configFileName = currentWorkingDirectory + "/config/config.properties";
		configProperty = ConfigUtils.readProperties(configFileName);
		
		reportFilename = currentWorkingDirectory + "/reports/guru99TestReport.html";
		reportUtils = new ReportUtils(reportFilename);
	}
	
	
	@BeforeClass
	public void setup() throws Exception {
		url = configProperty.getProperty("baseUrl");
		String browserType = configProperty.getProperty("browserType");
		
		cmnDriver = new CommonDriver(browserType);
		driver = cmnDriver.getDriver();
		
		loginpage = new LoginPageModel(driver);
		
		screenshot = new ScreenshotUtils(driver);
		
		cmnDriver.navigateToUrl(url);
		
	}
	
	@AfterMethod
	public void postTestAction(ITestResult result) throws Exception {
		String testcasename = result.getName();
		long executionTime = System.currentTimeMillis();
		
		String screenshotFilename = currentWorkingDirectory + "/screenshots/" + testcasename + executionTime +".jpeg";
		
		if(result.getStatus() == ITestResult.FAILURE) {
			reportUtils.addTestLog(Status.FAIL, "One or more steps failed");
			screenshot.captureAndSaveScreenshot(screenshotFilename);
			reportUtils.attachScreenshotToReport(screenshotFilename);
		}
		
	}
	@AfterClass
	public void tearDown() {
		cmnDriver.closeAllBrowser();
	}
	
	@AfterSuite
	public void postTeardown() {
		reportUtils.flushReport();
	}

}
