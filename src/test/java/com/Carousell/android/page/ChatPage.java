package com.Carousell.android.page;

import com.Carousell.android.ElementOP;
import com.Carousell.android.GeneralOP;
import com.Carousell.util.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/** Page object for the landing page **/
public abstract class ChatPage {

	public static boolean checkOfferResult(AppiumDriver driver, String amount) {
		if (ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='MADE AN OFFER\nNT$" + amount + "']"), 2)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static String printProductTitle(AppiumDriver driver)
	{
		return driver.findElementById("com.thecarousell.Carousell:id/text_product_name").getText();
	}

	public static void closeGuide(AppiumDriver driver)
	{
		driver.findElementById("com.thecarousell.Carousell:id/button_guide_close").click();
	}

	/** Verify the chat page has loaded **/
	public static void loaded(AppiumDriver driver) {
		if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.EditText[@text='Type your message here']"),2)) {
			Log.info("--->Not into chat page. Please check");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Fail to go into chat page");
		}
	}
}