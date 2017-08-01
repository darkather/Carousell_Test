package com.Carousell.android.page;

import com.Carousell.android.ElementOP;
import com.Carousell.android.GeneralOP;
import com.Carousell.util.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/** Page object for the landing page **/
public abstract class ProductPage {

	public static void buyNow(AppiumDriver driver) throws Exception {

		try {
			loaded(driver);

			//Tap buy button
			//If it's bought before, cancel it
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Buy Now']"),2)) {
				if (ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='View Offer']"),2)) {
					Log.info("Bought. Cancel it.");
					driver.findElementById("com.thecarousell.Carousell:id/button_buy").click();
					driver.findElementById("com.thecarousell.Carousell:id/button_chat_left").click();
					driver.findElementByXPath("//android.widget.Button[@text='Yes']").click();
					driver.findElementById("com.thecarousell.Carousell:id/button_chat_offer").click();					
				}
				else {
					Log.info("--->Cannot find buy button. Please check");
					GeneralOP.takeScreenShot(driver);
					throw new AssertionError("--->Fail to find buy button");
				}
			}
			else {
				driver.findElementByXPath("//android.widget.TextView[@text='Buy Now']").click();
			}

			OfferPage.loaded(driver);
		} catch(Exception e) {
			Log.infoTitle("--->Buy now Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Buy now fails.");
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

	public static String printProductTitle(AppiumDriver driver)
	{
		return driver.findElementById("com.thecarousell.Carousell:id/text_product_title").getText();
	}

	public static boolean inProductPage(AppiumDriver driver)
	{
		if (ElementOP.isElementPresent(driver, MobileBy.id("com.thecarousell.Carousell:id/text_product_title"), 2))
			return true;
		else
			return false;
	}

	/** Verify the product page has loaded **/
	public static void loaded(AppiumDriver driver) {
		if (!inProductPage(driver)) {
				Log.infoTitle("--->Not in product page. Please check!");
				GeneralOP.takeScreenShot(driver);
				//Log.info(e.getMessage());
				throw new AssertionError("--->Go to product page fails.");
		}
	}
}