package com.Carousell.android.page;

import java.util.Properties;

import com.Carousell.android.ElementOP;
import com.Carousell.android.GeneralOP;
import com.Carousell.util.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/** Page object for the landing page **/
public abstract class EmailPage {

	public static void logInClick(AppiumDriver driver) {
		try {
			Log.info("Click log in");
			loaded(driver);

			//Check email page
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='LOGIN']"),2)) {
				Log.info("--->Cannot find log in tab. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find log");
			}
			else {
				driver.findElementByXPath("//android.widget.TextView[@text='LOGIN']").click();
			}

			login_loaded(driver);

		} catch(Exception e) {
			Log.infoTitle("--->Click LOGIN tab fail. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Open LOGIN tab fails.");
		}
	}

	public static boolean isSignedIn(AppiumDriver driver) {
		Log.info("Verify logged in or not");
		loaded(driver);
		return ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='ME']"),2);
	}

	public static void logIn(AppiumDriver driver, Properties andauto) throws Exception {
		String username = andauto.getProperty("username");
		String password = andauto.getProperty("password");

		try {
			//Check login page
			login_loaded(driver);
			Log.info("Enter username: " + username);

			driver.findElementById("com.thecarousell.Carousell:id/text_username").sendKeys(username);
			driver.findElementById("com.thecarousell.Carousell:id/text_password").sendKeys(password);
			driver.findElementById("com.thecarousell.Carousell:id/action_signin").click();

			HomePage.loaded(driver);

		} catch(Exception e) {
			Log.infoTitle("--->Login Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Login fails.");
		}
	}

	/** Verify the loging tab has loaded **/
	public static void login_loaded(AppiumDriver driver)  {
		if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Forgot your password?']"), 2)) {
			Log.infoTitle("--->Not in loging tab. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Go to loging tab fails.");
		}
	}

	/** Verify the email page has loaded **/
	public static void loaded(AppiumDriver driver)  {
		if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='SIGN UP']"), 2) || 
			!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='LOGIN']"), 2) ) {
			Log.infoTitle("--->Not in email page. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Go to email page fails.");
		}
	}
}