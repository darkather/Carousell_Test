package com.Carousell.android.page;

import com.Carousell.android.ElementOP;
import com.Carousell.android.GeneralOP;
import com.Carousell.util.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/** Page object for the landing page **/
public abstract class LandingPage {

    public static void emailClick(AppiumDriver driver) {
		try {
			Log.info("Click email button");
			loaded(driver);

			//Check email button
			if (!ElementOP.isElementPresent(driver, MobileBy.id("com.thecarousell.Carousell:id/email_signin_button"),2)) {
				Log.info("--->Cannot find email button. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find email button");
			}
			else {
				driver.findElementById("com.thecarousell.Carousell:id/email_signin_button").click();
			}

			EmailPage.loaded(driver);

		} catch(Exception e) {
			Log.infoTitle("--->Click email button in landing page fail. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Click email button in landing page fails.");
		}
    }

	/** Verify the landing page has loaded **/
	public static void loaded(AppiumDriver driver)  {
		if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='SIGN UP OR LOGIN WITH']"), 2)) {
			Log.infoTitle("--->Not in landing page. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Go to landing page fails.");
		}
	}
}