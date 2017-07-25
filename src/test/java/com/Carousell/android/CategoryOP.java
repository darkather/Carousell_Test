package com.Carousell.android;

import com.Carousell.util.Log;
import com.Carousell.android.ElementOP;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class CategoryOP {

	public static void goToCar(AppiumDriver driver) throws Exception {

		try {
			if (!chooseCatagery(driver, "Cars")) {
				Log.infoTitle("--->choose cars fail. Please check!");
				GeneralOP.takeScreenShot(driver);
				//Log.info(e.getMessage());
				throw new AssertionError("--->Cannot choose cars.");
			}

			Log.infoTitle("goToCar  --Pass");
		} catch(Exception e) {
			Log.infoTitle("--->goToCar Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Login fails.");
		}
	}
		
	
	public static boolean chooseCatagery(AppiumDriver driver, String category) throws Exception {
		try {
			Log.info("Find " + category);
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
			if (!driver.findElementById("com.thecarousell.Carousell:id/bar_collection_title").getText().equalsIgnoreCase(category)) {
				Log.infoTitle("--->not landing " + category + " page. Please check!");
				GeneralOP.takeScreenShot(driver);
				//Log.info(e.getMessage());
				throw new AssertionError("--->not lading " + category + " page.");
			}

			Log.info("Go to " + category + " success");
			return true;
		} catch(Exception e) {
			Log.info("Go to " + category + " Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			return false;
		}
	}

}
