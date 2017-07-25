package com.Carousell.android;

import java.util.Properties;

import com.Carousell.util.Log;
import com.Carousell.android.ElementOP;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class SignOP {

	public static boolean isSignedIn(AppiumDriver driver) {
		Log.info("Verify logged in or not");

		return ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='ME']"),2);
	}

	public static void signIn(AppiumDriver driver, Properties andauto) throws Exception {
		String username = andauto.getProperty("username");
		String password = andauto.getProperty("password");

		//If already logged in, logout.
		if (isSignedIn(driver))
		{
			Log.info("Already logged in. Log out it");
			signOut(driver);
		}
		else
		{
			Log.info("Not logged in");
		}

		try {
			Log.info("Choose Email to sign in");
			//Check landing page
			if (!ElementOP.isElementPresent(driver, MobileBy.id("com.thecarousell.Carousell:id/email_signin_button"),2)) {
				Log.info("--->Cannot find email button. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find email button");
			}
			else {
				driver.findElementById("com.thecarousell.Carousell:id/email_signin_button").click();
			}

			//Check email page
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='LOGIN']"),2)) {
				Log.info("--->Cannot find log in tab. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find log");
			}
			else {
				driver.findElementByXPath("//android.widget.TextView[@text='LOGIN']").click();
			}

			//Check login page
			if (!ElementOP.isElementPresent(driver, MobileBy.id("com.thecarousell.Carousell:id/text_username"),2)) {
				Log.info("--->Cannot find username field. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find username field");
			}
			else {
				Log.info("Enter username: " + username);

				driver.findElementById("com.thecarousell.Carousell:id/text_username").sendKeys(username);
				driver.findElementById("com.thecarousell.Carousell:id/text_password").sendKeys(password);
				driver.findElementById("com.thecarousell.Carousell:id/action_signin").click();
			}

			//Check if sign in successful
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='ME']"),2))
			{
				Log.info("--->Cannot find ME. Not landing on logged in page. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find ME");
			}
			else
			{
				driver.findElementByXPath("//android.widget.TextView[@text='ME']").click();
				if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='" + username + "']"),2))
				{
					Log.info("--->Cannot find" + username + ". Not logged in right account. Please check");
					GeneralOP.takeScreenShot(driver);
					throw new AssertionError("--->Not logged in right account.");
				}
				else
				{
					driver.findElementByXPath("//android.widget.TextView[@text='BROWSE']").click();
				}
			}

			Log.infoTitle("Login  --Pass");
		} catch(Exception e) {
			Log.infoTitle("--->Login Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Login fails.");
		}
	}

	public static void signOut(AppiumDriver driver) {
		try {
			driver.findElementByXPath("//android.widget.TextView[@text='ME']").click();

			//Find setting
			if (!ElementOP.isElementPresent(driver, MobileBy.id("com.thecarousell.Carousell:id/button_settings"),2)) {
				Log.info("--->Cannot find setting button. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find setting button");
			}
			else {
				driver.findElementById("com.thecarousell.Carousell:id/button_settings").click();;
			}

			//Tap logout
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Logout']"),2)) {
				Log.info("--->Cannot find Logout. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find Logout");
			}
			else {
				driver.findElementByXPath("//android.widget.TextView[@text='Logout']").click();
			}

			//Check landing page
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='SIGN UP OR LOGIN WITH']"),2)) {
				Log.info("--->Cannot successful Logout. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to logout");
			}
			else {
				Log.info("Log out success.");
			}
			
		} catch(Exception e) {
			Log.infoTitle("--->Log out fail. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Log Out fails.");
		}
	}
}
