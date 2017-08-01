package com.Carousell.android.page;

import com.Carousell.android.ElementOP;
import com.Carousell.android.GeneralOP;
import com.Carousell.util.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/** Page object for the landing page **/
public abstract class OfferPage {

	public static void makeOffer(AppiumDriver driver, String ammount) throws Exception {

		try {
			loaded(driver);

			driver.findElementById("com.thecarousell.Carousell:id/text_offer").clear();
			driver.findElementById("com.thecarousell.Carousell:id/text_offer").sendKeys(ammount);
			driver.findElementById("com.thecarousell.Carousell:id/action_submit").click();				

			//Check confirm dialog
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Confirm offer?']"),2)) {
				Log.info("--->Confirm dialog is not opened. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to open confirm dialog");
			}
			else {
				driver.findElementByXPath("//android.widget.Button[@text='Yes']").click();				
			}

			ChatPage.loaded(driver);

		} catch(Exception e) {
			Log.infoTitle("--->Make an offer Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Make an offer fails.");
		}
	}

	public static void checkGuide(AppiumDriver driver) throws Exception {
		if (ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Make an offer to buy this item!']"),2)) {
			driver.findElementByXPath("//android.widget.TextView[@text='OK, Got it!']").click();
		}
		if (ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Got questions? Ask the seller!']"),2)) {
			driver.findElementByXPath("//android.widget.TextView[@text='OK, Got it!']").click();
		}
	}

	/** Verify the offer page has loaded **/
	public static void loaded(AppiumDriver driver) {
		if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Make an offer to buy this item!']"),2)) {
			Log.info("--->Not into offer page. Please check");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Fail to go into offer page");
		}
	}
}