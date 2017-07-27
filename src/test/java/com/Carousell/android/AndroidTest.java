package com.Carousell.android;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.varia.NullAppender;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.Carousell.util.Log;
public class AndroidTest{

	public static AppiumDriver driver;
	public static WebDriver webdriver;
	public static Process appium;
	public static Properties andauto;

	@BeforeSuite
	public static void setUp() throws Exception 
	{
		try {
			org.apache.log4j.BasicConfigurator.configure(new NullAppender());

			andauto = new Properties();
			FileInputStream andautoFis = new FileInputStream("androidauto.properties");
			andauto.load(andautoFis);

			Log.info("Setup Starts");

			String androidApkPath = andauto.getProperty("APKPATH");
			Log.info("androidApkPath : "+androidApkPath);
			Log.info("appiumPort: " + andauto.getProperty("appiumPort"));
			Log.info("udid: " + andauto.getProperty("udid"));

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("app", androidApkPath);
			capabilities.setCapability("deviceName", "device");
			capabilities.setCapability("udid", andauto.getProperty("udid"));
			capabilities.setCapability("noReset", true);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.thecarousell.*");

			driver = new AndroidDriver(new URL("http://127.0.0.1:"+andauto.getProperty("appiumPort")+"/wd/hub"),capabilities);
			Log.info("driver started");
			Log.info("waiting for app to be launched");

			if (driver==null) {
				Thread.sleep(80000);
			}
			//wait for app to be lauched
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch(Exception e) {
			Log.info("--->Setup Fails. Pls check! Probably signed in already");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Set Up fails. Pls check!");
		}
	}

	//Arthus Shen
	//For testng, we separate the shell for each test items so we can sort the test cases in files.
	//We put all the shells here.

	@Test(description = "Login")
	public static void logIn() throws Exception {
		Log.infoTitle("Log in Starts");
		SignOP.signIn(driver, andauto);
		Thread.sleep(5000);
	}

	@Test(description = "Go to Cars")
	public static void goToCar() throws Exception {
		Log.infoTitle("Go to Cars Starts");
		CategoryOP.goToCar(driver);
		Thread.sleep(5000);
	}

	@Test(description = "Choose the first listing")
	public static void chooseTheFirstListing() throws Exception {
		Log.infoTitle("Choose the first listing Starts");
		OfferOP.choose1st(driver);
		Thread.sleep(5000);
	}

	@Test(description = "Make an offer")
	public static void makeAnOffer() throws Exception {
		Log.infoTitle("Make an offer Starts");
		OfferOP.makeOffer(driver, andauto);
		Thread.sleep(5000);
	}

	@AfterSuite
	public static void tearDown() throws Exception {
		Log.info("tearDown Starts");

		if(!driver.equals(null))
		       driver.quit();
		Log.info("tearDown ends");
	}

}