package com.Carousell.android.page;

import com.Carousell.android.ElementOP;
import com.Carousell.android.GeneralOP;
import com.Carousell.util.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/** Page object for the landing page **/
public abstract class HomePage {

	public static boolean chooseCatagery(AppiumDriver driver, String category) throws Exception {
		try {
			Log.info("Find " + category);
			loaded(driver);

			//Check if this category is in the page
			int count = 0;
			while (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='" + category + "']"),2)) {
				Log.info("Car is not in " + ++count + " page" );
				if (count > 6) {
					Log.info("Cannot find " + category + " category" );
					GeneralOP.takeScreenShot(driver);
					return false;
				}

				GeneralOP.swipeUp(driver, 500);
			}

			driver.findElementByXPath("//android.widget.TextView[@text='" + category + "']").click();

			//Check guide
			if (ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='OK, Got it!']"),2)) {
				driver.findElementByXPath("//android.widget.TextView[@text='OK, Got it!']").click();
			}

			//Check category
			CategoryPage.loaded(driver, category);

			return true;
		} catch(Exception e) {
			Log.info("Go to " + category + " Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			return false;
		}
	}

	public static boolean isSignedIn(AppiumDriver driver) {
		Log.info("Verify logged in or not");
		return ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='ME']"),2);
	}

	public static boolean loggingRightAccount(AppiumDriver driver, String username) {
		try {
			loaded(driver);
			driver.findElementByXPath("//android.widget.TextView[@text='ME']").click();
			
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='" + username + "']"),2)) {
				Log.info("--->Cannot find" + username + ". Not logged in right account. Please check");
				GeneralOP.takeScreenShot(driver);
				return false;
			}
			else {
				driver.findElementByXPath("//android.widget.TextView[@text='BROWSE']").click();
				return true;
			}
		} catch(Exception e) {
			Log.infoTitle("--->Check account fail. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			return false;
		}
	}

	public static void signOut(AppiumDriver driver) {
		try {
			loaded(driver);
			driver.findElementByXPath("//android.widget.TextView[@text='ME']").click();

			//Find setting
			if (!ElementOP.isElementPresent(driver, MobileBy.id("com.thecarousell.Carousell:id/button_settings"), 2)) {
				Log.info("--->Cannot find setting button. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find setting button");
			}
			else {
				driver.findElementById("com.thecarousell.Carousell:id/button_settings").click();;
			}

			//Tap logout
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Logout']"), 2)) {
				Log.info("--->Cannot find Logout. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find Logout");
			}
			else {
				driver.findElementByXPath("//android.widget.TextView[@text='Logout']").click();
			}

			LandingPage.loaded(driver);

		} catch(Exception e) {
			Log.infoTitle("--->Log out fail. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Log Out fails.");
		}
	}

	/** Verify the home page has loaded **/
	public static void loaded(AppiumDriver driver) {
		if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='BROWSE']"), 2) ||
			!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='GROUPS']"), 2) ||
			!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='ACTIVITY']"), 2) ||
			!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='ME']"), 2) ) {
			Log.infoTitle("--->Not in home page. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Go to home page fails.");
		}
	}
}