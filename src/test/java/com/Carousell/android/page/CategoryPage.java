package com.Carousell.android.page;

import com.Carousell.android.ElementOP;
import com.Carousell.android.GeneralOP;
import com.Carousell.util.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/** Page object for the landing page **/
public abstract class CategoryPage {

	public static void choose1st(AppiumDriver driver, String category) throws Exception {

		try {
			Log.info("Choose the 1st listing in" + category);
			loaded(driver, category);

			//Tap the first product
			if (!ElementOP.isElementPresent(driver, MobileBy.id("com.thecarousell.Carousell:id/pic_product"),2)) {
				Log.info("--->Cannot find any product. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find product");
			}
			else {
				driver.findElementById("com.thecarousell.Carousell:id/pic_product").click();
			}

			ProductPage.checkGuide(driver);;
			ProductPage.loaded(driver);

		} catch(Exception e) {
			Log.infoTitle("--->Choose the first listing Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Choose the first listing fails.");
		}
	}

	/** Verify the category page has loaded **/
	public static void loaded(AppiumDriver driver, String category) {
		if (!driver.findElementById("com.thecarousell.Carousell:id/bar_collection_title").getText().equalsIgnoreCase(category)) {
			Log.infoTitle("--->not landing " + category + " page. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->not lading " + category + " page.");
		}
	}
}